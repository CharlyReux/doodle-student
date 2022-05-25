import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { PollService } from '../poll-service.service';
import { JWTTokenService } from '../jwttoken-service.service';
import { AppCookieService } from '../app-cookie-service.service';


@Component({ templateUrl: 'login.component.html',
providers:[PollService,JWTTokenService,AppCookieService] })
export class LoginComponent implements OnInit {
    form: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private pollService: PollService,
        private formBuilder: FormBuilder,
        private router:Router,
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

 
         this.pollService.logUser(this.f.username.value, this.f.password.value)
            .pipe(first())
            .subscribe({
                next: (token) => {
                    this.appCookieService.set("token",token.access_token)
                    this.appCookieService.set("mail",this.f.username.value);
                    this.pollService.setHeaderToken(token.access_token)
                    this.router.navigate([""])
                },
                error: error => {
                    this.loading = false;
                    alert("Mauvais mot de passe ou l'utilisateur n'existe pas")
                    window.location.reload()
                }
            }); 
    }
}
