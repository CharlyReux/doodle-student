import { Component, OnInit } from '@angular/core';
import { dashBoardPolls, Poll } from '../model/model';
import { PollService } from '../poll-service.service';

import { JWTTokenService } from '../jwttoken-service.service';
import { AppCookieService } from '../app-cookie-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [PollService,JWTTokenService,AppCookieService]
})
export class DashboardComponent implements OnInit {
  myPollArray: dashBoardPolls = {}
  dataSourcead
  dataSourceus

  constructor(public pollService: PollService,
    private appCookieService: AppCookieService,
    private jwtService: JWTTokenService,
    private router:Router) { }

  displayedColumnsUs = ['id', "Title", "Description","UserLink"]
  displayedColumnsAd = ['id', "Title", "Description","AdminLink"]


  ngOnInit(): void {

/*     /////////////TEMPORARY TO MAKE A USER////////////////(charlyreux@gmail.com)
    this.appCookieService.set("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiZGlzcGxheW5hbWUiOiJjaGFybHkiLCJpYXQiOjk5OTk5OTk5OTk5OSwiZW1haWwiOiJjaGFybHlyZXV4QGdtYWlsLmNvbSJ9.HOvarf0vOTUe-qX9ar1ojZh_rEx-7E8UYw1xT6TeUAI")
    */
    
    this.jwtService.setToken(this.appCookieService.get("token"))

    if(!this.jwtService.getUser() || this.jwtService.isTokenExpired()){
      this.router.navigate(["/log"])
    } 

    console.log(this.appCookieService.get("mail"))





    //retrieving all the polls 
    this.pollService.getAllPollsFromUser(this.appCookieService.get("mail")).subscribe(allPoll=>{
      this.myPollArray = allPoll
      this.dataSourcead = this.myPollArray.adPolls
      this.dataSourceus = this.myPollArray.userPolls
    }) 
  }

}
