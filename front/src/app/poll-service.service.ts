import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Poll, PollChoice, User, ChoiceUser, PollCommentElement, EventDTOAndSelectedChoice, dashBoardPolls } from './model/model';
import { Observable } from 'rxjs';
import { JWTTokenService } from './jwttoken-service.service';

@Injectable({
  providedIn: 'root'
})
export class PollService {


  headerDict = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Host': '',
    'Authorization':"Bearer "+this.jwtService.getToken
    //pas sur de a quoi ca ser'Access-Control-Allow-Headers': 'Content-Type',
  }





  constructor(private http: HttpClient,
    private jwtService: JWTTokenService) { }

  public createPoll(p: Poll): Observable<Poll> {
    this.headerDict.Host ="poll.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    console.log('create poll');
    return this.http.post<Poll>('/api/polls', p);
  }


  public updtatePoll(p: Poll): Observable<Poll> {
    this.headerDict.Host ="poll.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.put<Poll>('/api/poll/update1', p);
  }


  public getPollBySlugId(slugId: string): Observable<Poll>{
    this.headerDict.Host ="poll.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<Poll>('/api/poll/slug/' + slugId);
  }

  public getComentsBySlugId(slugId: string): Observable<PollCommentElement[]>{
    this.headerDict.Host ="poll.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<PollCommentElement[]>('/api/polls/' + slugId + '/comments');
  }

  public getPollBySlugAdminId(slugId: string): Observable<Poll>{
    this.headerDict.Host ="poll.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<Poll>('/api/poll/aslug/' + slugId);

  }

  public updateChoice4user( cu: ChoiceUser): Observable<User>{
    this.headerDict.Host ="poll.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<User>('/api/poll/choiceuser/', cu);
  }

  public addComment4Poll( slug: string, comment: PollCommentElement ): Observable<PollCommentElement>{
    this.headerDict.Host ="poll.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<PollCommentElement>('/api/poll/comment/' + slug, comment);
  }

  selectEvent(choiceid: number): Observable<void> {
    this.headerDict.Host ="poll.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<void>('/api/poll/selectedchoice/' + choiceid, null);

  }

  getICS(slug: string, ics: string): Observable<EventDTOAndSelectedChoice> {
    this.headerDict.Host ="calendar.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<EventDTOAndSelectedChoice>('/api/ics/polls/' + slug + '/' + btoa(ics));
  }


  public getAllPollsFromUser(mail:String): Observable<dashBoardPolls>{
    this.headerDict.Host ="dashboard.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<dashBoardPolls>('/api/dashBoard/getUserPolls/'+mail,requestOptions);
  }
  
  public addPollToAdmin(mail:String,poll:Poll):Observable<void>{
    this.headerDict.Host ="dashboard.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<void>('/api/dashBoard/addPollAdmin/'+mail,poll);//FIXME: need to test this
  }

  public addPollToUser(mail:String,poll:Poll):Observable<void>{
    this.headerDict.Host ="dashboard.doodle.fr"
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<void>('/api/dashBoard/addPollUser/'+mail,poll);//FIXME: need to test this
  }





  //TODO: add the user register and login endpoints

}
