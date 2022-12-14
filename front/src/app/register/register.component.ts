import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { PollService } from '../poll-service.service';
import { AppCookieService } from '../app-cookie-service.service';
import { JWTTokenService } from '../jwttoken-service.service';

@Component({
    templateUrl: 'register.component.html',
    providers: [PollService, JWTTokenService, AppCookieService]
})
export class RegisterComponent implements OnInit {
    form: FormGroup;
    loading = false;
    submitted = false;
    tmpToken

    constructor(
        private pollService: PollService,
        private formBuilder: FormBuilder,
        private router: Router,
        private appCookieService: AppCookieService,
        private jwtService: JWTTokenService
    ) { }

    ngOnInit() {
        this.form = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
    }

    // convenience getter for easy access to form fields
    get f() { return this.form.controls; }

    onSubmit() {




        this.submitted = true;


        // stop here if form is invalid
        if (this.form.invalid) {
            return;
        }

        this.loading = true;


        this.pollService.getAdToken().subscribe(result => {
            this.tmpToken = result.access_token
            console.log(result.access_token);
            this.pollService.registerUser(this.tmpToken, this.f.username.value, this.f.password.value)
                .pipe(first())
                .subscribe({
                    next: (token) => {
                        this.appCookieService.set("mail", this.f.username.value);

                        this.pollService.logUser(this.f.username.value, this.f.password.value).subscribe((token) => {
                            this.appCookieService.set("token", token.access_token)
                            this.pollService.setHeaderToken(token.access_token)
                            this.router.navigate([""])
                        })

                    },
                    error: error => {
                        this.loading = false;
                        alert(error)
                    }
                });

        })


    }
}
