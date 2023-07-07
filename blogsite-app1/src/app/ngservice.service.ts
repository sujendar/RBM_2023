import { Injectable } from '@angular/core';
import { Userdetails } from './userdetails';
import { HttpClient,HttpHeaders,HttpResponse } from '@angular/common/http';
import {Observable} from 'rxjs';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';
import { Blogdetails } from './blogdetails';
import { Jwttoken } from './jwttoken';

@Injectable({
  providedIn: 'root'
})
export class NgserviceService {
  jwtToken=new Jwttoken();
  constructor(private _http:HttpClient) { }
  fetchUserDetailsFromRemote(userName: String,userPassword:string): Observable<any>{
    //localStorage.clear();
    return this._http.post<any>('http://localhost:5000/api/v1/blogsite/signin/'+userName+'/'+userPassword,null);
  }
  jwtauthenticationCheck(userName: String,userPassword:string): Observable<any>{
    return this._http.post<any>('http://localhost:5000/api/v1/blogsite/authenticate/'+userName+'/'+userPassword,null);
  }
  addUserDetailsToRemote(userDetails: Userdetails): Observable<any>{
    return this._http.post<any>('http://localhost:5000/api/v1/blogsite/user/register',userDetails);
  }
  homepage(): Observable<any>{
    let Token=localStorage.getItem('jwt_token');
   // alert(Token);
    const httpOptions = {
      headers: new HttpHeaders({ 
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
                'key': 'x-api-key',
                'Authorization':'Bearer ' +Token,})
    };
    return this._http.get<any>('http://localhost:5000/api/v1/blogsite/user/getall',httpOptions);
  }

  addBlogToRemote(blogdetails:Blogdetails): Observable<any>{
    let Token=localStorage.getItem('jwt_token');
  const httpOptions = {
    headers: new HttpHeaders({ 
              'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Credentials': 'true',
              'Access-Control-Allow-Headers': 'Content-Type',
              'Content-Type': 'application/json',
              'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
              'key': 'x-api-key',
              'Authorization':'Bearer ' +Token,})
  };
  console.log("check again:"+httpOptions.headers.get('Access-Control-Allow-Origin'));
  return this._http.post<any>('http://localhost:5000/api/v1/blogsite/users/blogs/add',JSON.stringify(blogdetails),httpOptions);
}
searchbycategory(searchtext:String): Observable<any>{
  let Token=localStorage.getItem('jwt_token');
  const httpOptions = {
    headers: new HttpHeaders({ 
              'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Credentials': 'true',
              'Access-Control-Allow-Headers': 'Content-Type',
              'Content-Type': 'application/json',
              'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
              'key': 'x-api-key',
              'Authorization':'Bearer ' +Token,})
  };
  
  return this._http.get<any>('http://localhost:5000/api/v1/blogsite/blogs/info/'+searchtext,httpOptions);
}

deletebyblogname(blogname:String): Observable<any>{
  let Token=localStorage.getItem('jwt_token');
  const httpOptions = {
    headers: new HttpHeaders({ 
              'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Credentials': 'true',
              'Access-Control-Allow-Headers': 'Content-Type',
              'Content-Type': 'application/json',
              'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
              'key': 'x-api-key',
              'Authorization':'Bearer ' +Token,})
  };
  return this._http.delete<any>('http://localhost:5000/api/v1/blogsite/user/delete/'+blogname,httpOptions);
}
  
}
