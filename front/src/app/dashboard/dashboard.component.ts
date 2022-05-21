import { Component, OnInit } from '@angular/core';
import { dashBoardPolls, Poll } from '../model/model';
import { PollService } from '../poll-service.service';




@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [PollService]
})
export class DashboardComponent implements OnInit {
  myPollArray: dashBoardPolls = {}
  dataSourcead
  dataSourceus

  constructor(public pollService: PollService) { }
  displayedColumnsUs = ['id', "Title", "Description","UserLink"]
  displayedColumnsAd = ['id', "Title", "Description","AdminLink"]

  //TODO: how to get the current id of the user( he is supposed to have identified previously)
  //Test ID => TO DELETE
  UserCurrentID = 1;

  ngOnInit(): void {
    this.pollService.getAllPollsFromUser(this.UserCurrentID).subscribe(allPoll=>{
      this.myPollArray = allPoll
      this.dataSourcead = this.myPollArray.adPolls
      this.dataSourceus = this.myPollArray.userPolls
    }) 
  }

}
