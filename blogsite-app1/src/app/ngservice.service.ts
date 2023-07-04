import { Injectable } from '@angular/core';
import { Userdetails } from './userdetails';
import { HttpClient,HttpHeaders,HttpResponse } from '@angular/common/http';
import {Observable} from 'rxjs';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';
import { Blogdetails } from './blogdetails';

@Injectable({
  providedIn: 'root'
})
export class NgserviceService {
 
  constructor(private _http:HttpClient) { }
  fetchUserDetailsFromRemote(userName: String,userPassword:string): Observable<any>{
    return this._http.post<any>('http://localhost:5000/api/v1/blogsite/signin/'+userName+'/'+userPassword,null);
  }
  addUserDetailsToRemote(userDetails: Userdetails): Observable<any>{
    return this._http.post<any>('http://localhost:5000/api/v1/blogsite/user/register',userDetails);
  }
  homepage(): Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({ 
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
                'key': 'x-api-key',
                'Authorization':'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYW0iLCJleHAiOjE2ODg0Nzk1ODQsImlhdCI6MTY4ODQ0MzU4NH0.rCLJNE-0y_4kvTTx3KZQ8Zer2sq604W7E2n7gXL2qeg'})
    };
    //console.log("Output from jwt"+JSON.stringify(localStorage.getItem('jwt_token'))+"headers:"+httpOptions.headers.get('Content-Type'));
    //httpOptions.headers.set('Content-Type','application/json');
    //httpOptions.headers.set('Authorization','Bearer '+JSON.parse(localStorage.getItem('jwt_token')));
    console.log("check again:"+httpOptions.headers.get('Access-Control-Allow-Origin'));
    return this._http.get<any>('http://localhost:5000/api/v1/blogsite/user/getall',httpOptions);
  }

  addBlogToRemote(blogdetails:Blogdetails): Observable<any>{
  const httpOptions = {
    headers: new HttpHeaders({ 
              'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Credentials': 'true',
              'Access-Control-Allow-Headers': 'Content-Type',
              'Content-Type': 'application/json',
              'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
              'key': 'x-api-key',
              'Authorization':'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYW0iLCJleHAiOjE2ODg0Nzk1ODQsImlhdCI6MTY4ODQ0MzU4NH0.rCLJNE-0y_4kvTTx3KZQ8Zer2sq604W7E2n7gXL2qeg'})
  };
  //console.log("Output from jwt"+JSON.stringify(localStorage.getItem('jwt_token'))+"headers:"+httpOptions.headers.get('Content-Type'));
  //httpOptions.headers.set('Content-Type','application/json');
  //httpOptions.headers.set('Authorization','Bearer '+JSON.parse(localStorage.getItem('jwt_token')));
  console.log("check again:"+httpOptions.headers.get('Access-Control-Allow-Origin'));
  return this._http.post<any>('http://localhost:5000/api/v1/blogsite/users/blogs/add',JSON.stringify(blogdetails),httpOptions);
}
searchbycategory(searchtext:String): Observable<any>{
  const httpOptions = {
    headers: new HttpHeaders({ 
              'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Credentials': 'true',
              'Access-Control-Allow-Headers': 'Content-Type',
              'Content-Type': 'application/json',
              'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
              'key': 'x-api-key',
              'Authorization':'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYW0iLCJleHAiOjE2ODg0Nzk1ODQsImlhdCI6MTY4ODQ0MzU4NH0.rCLJNE-0y_4kvTTx3KZQ8Zer2sq604W7E2n7gXL2qeg'})
  };
  //console.log("Output from jwt"+JSON.stringify(localStorage.getItem('jwt_token'))+"headers:"+httpOptions.headers.get('Content-Type'));
  //httpOptions.headers.set('Content-Type','application/json');
  //httpOptions.headers.set('Authorization','Bearer '+JSON.parse(localStorage.getItem('jwt_token')));
  console.log("check again:"+httpOptions.headers.get('Access-Control-Allow-Origin'));
  return this._http.get<any>('http://localhost:5000/api/v1/blogsite/blogs/info/'+searchtext,httpOptions);
}

deletebyblogname(blogname:String): Observable<any>{
  const httpOptions = {
    headers: new HttpHeaders({ 
              'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Credentials': 'true',
              'Access-Control-Allow-Headers': 'Content-Type',
              'Content-Type': 'application/json',
              'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
              'key': 'x-api-key',
              'Authorization':'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYW0iLCJleHAiOjE2ODg0Nzk1ODQsImlhdCI6MTY4ODQ0MzU4NH0.rCLJNE-0y_4kvTTx3KZQ8Zer2sq604W7E2n7gXL2qeg'})
  };
  //console.log("Output from jwt"+JSON.stringify(localStorage.getItem('jwt_token'))+"headers:"+httpOptions.headers.get('Content-Type'));
  //httpOptions.headers.set('Content-Type','application/json');
  //httpOptions.headers.set('Authorization','Bearer '+JSON.parse(localStorage.getItem('jwt_token')));
  console.log("check again:"+httpOptions.headers.get('Access-Control-Allow-Origin'));
  return this._http.delete<any>('http://localhost:5000/api/v1/blogsite/user/delete/'+blogname,httpOptions);
}
  
}
