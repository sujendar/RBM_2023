import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Userdetails } from '../userdetails';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  userName!:string;
  userPassword!:string;
  userDetails=new Userdetails();
    constructor(private _route: Router,private service:NgserviceService) { }
  
    ngOnInit(): void {
    }
    signUpPage(){
      this._route.navigate(['/signup']);
    }
  logIn(){
    console.log(this.userName);
    this.service.fetchUserDetailsFromRemote(this.userName,this.userPassword).subscribe(
      (resp) => {
        if(resp==null){
          alert("INVALID CRDENTAILS");
        }else{
          alert("login successfully")
        this.userDetails = resp;
        localStorage.setItem('userDetails', JSON.stringify(this.userDetails.userId));
        localStorage.setItem('userName', JSON.stringify(this.userDetails.userName));
        this._route.navigate(['/home']);
        }
      }, error=>alert("Please enterd correct username and password"),
    )
    
  }
}
