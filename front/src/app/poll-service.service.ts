import { DebugElement, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Poll, PollChoice, User, ChoiceUser, PollCommentElement, EventDTOAndSelectedChoice, dashBoardPolls } from './model/model';
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





  //TODO: add the user register and login endpoints
/**
 * 
 * @param username the mail of the user
 * @param password The password of the user
 * @returns an identification token
 */
  public logUser(username:string, password:string):Observable<string>{
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    const userLogPost={username:username,password:password,client_id:"nginx"}
    return this.http.post<string>('/auth/realms/Myrealm/protocol/openid-connect/token',userLogPost,requestOptions)
  }

//
  public regUser(username:string, password:string):Observable<string>{
    const tmpHeader = this.headerDict

    tmpHeader.Authorization ='Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJFY2dlX3Y0c09fZUZ0TnhqYWJjT19QLTBhQ3p6S2VfMW02OU5mRjlBc1VzIn0.eyJleHAiOjE1OTI1Njc4OTEsImlhdCI6MTU5MjU2NzgzMSwianRpIjoiNjJiMWRlODEtNTBhMS00NzA2LWFmN2MtYzhhZTc1YTg1OTJhIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL21hc3RlciIsInN1YiI6IjNmYjc1YTM4LTA4NjctNGZlYi04ZTBlLWYzMTkxZTZlODZlMSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFkbWluLWNsaSIsInNlc3Npb25fc3RhdGUiOiJhMDMwNGNiMS0xMzViLTQzODItYjYwMi0xNjNmNzgzYWNlN2IiLCJhY3IiOiIxIiwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhZG1pbjIifQ.G9-OiyrGWk8cY4S3Ho255Y_euk_gTKDgYmGmU8RPBSeBNtFb_A68tuPFJxFKbzhZ1lipKJCXQsHbStoihvXAmmRsKzud5hDIvvnrD7CcVxAIpbd2wV5i6mB2wVLocV0_FCrE0-DNi_GPAKnazjFiVu3TxxM2L8Zsw7PHN9sb8Ux_lRvAFyNY5bT7NTbmEmt6LsO2An7iTZdBLScK9Lk9ZW8_0WG4eLMy9fatrpVV3MXhINW56gZD8WsWISY0m-cbIftDreZ1f2lzIjMGfkDrgCjy-VZjeIpbmffN-YGrUVywziymBRwA7FFLAxcf6jS5548HVxxKeMPIvNEfDG7eWw'
    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(this.headerDict), 
    };
    const userRegPost = {"firstName":"","lastName":"", "email":username, "enabled":"true", "username":"app-user","password":password}
    return this.http.post<string>("/auth/admin/realms/appsdeveloperblog/users",userRegPost,requestOptions)
  }


}
