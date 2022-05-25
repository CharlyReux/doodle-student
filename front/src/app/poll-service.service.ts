import { DebugElement, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Poll, PollChoice, User, ChoiceUser, PollCommentElement, EventDTOAndSelectedChoice, dashBoardPolls, responseToken } from './model/model';
import { Observable } from 'rxjs';
import { ITS_JUST_ANGULAR } from '@angular/core/src/r3_symbols';

@Injectable({
  providedIn: 'root'
})
export class PollService {


  headerDict = {
    'content-type': 'application/json',
    'Accept': 'application/json',
    'Authorization':""
    //pas sur de a quoi ca ser'Access-Control-Allow-Headers': 'Content-Type',
  }





  constructor(private http: HttpClient) { }

  public createPoll(p: Poll): Observable<Poll> {
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<Poll>('/api/poll/polls', p,requestOptions);
  }


  public updtatePoll(p: Poll): Observable<Poll> {
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.put<Poll>('/api/poll/update1', p,requestOptions);
  }


  public getPollBySlugId(slugId: string): Observable<Poll>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<Poll>('/api/poll/slug/' + slugId,requestOptions);
  }

  public getComentsBySlugId(slugId: string): Observable<PollCommentElement[]>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<PollCommentElement[]>('/api/poll/polls/' + slugId + '/comments',requestOptions);
  }

  public getPollBySlugAdminId(slugId: string): Observable<Poll>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<Poll>('/api/poll/aslug/' + slugId,requestOptions);
  }

  public updateChoice4user( cu: ChoiceUser): Observable<User>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<User>('/api/poll/choiceuser/', cu,requestOptions);
  }

  public addComment4Poll( slug: string, comment: PollCommentElement ): Observable<PollCommentElement>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<PollCommentElement>('/api/poll/comment/' + slug, comment,requestOptions);
  }

  selectEvent(choiceid: number): Observable<void> {
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.post<void>('/api/poll/selectedchoice/' + choiceid,requestOptions);

  }

  getICS(slug: string, ics: string): Observable<EventDTOAndSelectedChoice> {
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<EventDTOAndSelectedChoice>('/api/ics/polls/' + slug + '/' + btoa(ics),requestOptions);
  }


  public getAllPollsFromUser(mail:String): Observable<dashBoardPolls>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    return this.http.get<dashBoardPolls>('/api/dashboard/getUserPolls/'+mail,requestOptions);
  }
  
  public addPollToAdmin(mail:String,poll:Poll):Observable<Poll>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    console.log(poll);
    console.log(mail);
    poll.id=null;
    return this.http.post<Poll>('/api/dashboard/addPollAdmin/'+mail,poll,requestOptions);//FIXME: need to test this
  }

  public addPollToUser(mail:String,poll:Poll):Observable<Poll>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    console.log(poll);
    console.log(mail);
    poll.id=null;
    return this.http.post<Poll>('/api/dashboard/addPollUser/'+mail,poll,requestOptions);//FIXME: need to test this
  }


  public setHeaderToken(token:string){
    this.headerDict.Authorization = "Bearer "+token
  }







  public getAdToken():Observable<responseToken>{
    
    let body = new URLSearchParams();
    body.set("username","admin")
    body.set("password","admin")
    body.set("grant_type","password")
    body.set("client_id","admin-cli")

    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  };
    return this.http.post<responseToken>("/auth/realms/master/protocol/openid-connect/token",body.toString(),options)
  }

  public registerUser(token:string,email:string,password:string):Observable<void>{
    this.headerDict.Authorization = "bearer "+ token;
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    const userData=
    {"username":email,
    "email":email,
    "enabled":"true",
    "credentials":[{"type":"password","value":password,"temporary":false}]}
    return this.http.post<void>("/auth/admin/realms/projet_gl/users",userData,requestOptions)
  }

  public logUser(username:string,password:string):Observable<responseToken>{

    let body = new URLSearchParams();
    body.set("username",username)
    body.set("grant_type","password")
    body.set("client_secret","eiwqZFuSfUkrwU3LzMZlprlLxRDozxHL")
    body.set("client_id","nginx")
    body.set("password",password)
    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  };

    return this.http.post<responseToken>("/auth/realms/projet_gl/protocol/openid-connect/token",body.toString(),options)

  }



}
