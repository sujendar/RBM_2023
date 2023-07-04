import { Component } from '@angular/core';
import {FormBuilder,FormGroup,Validator,Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import { Userdetails } from '../userdetails';
import { NgserviceService } from '../ngservice.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  userF!:FormGroup;
  user=new Userdetails();
  constructor(private formBuilder:FormBuilder,private _route: Router,
    private _service: NgserviceService) { }

  ngOnInit(): void {
    this.userF=this.formBuilder.group({
      userName:['',Validators.required],
      email:['',Validators.required],
      password:['',Validators.required]
    });
    console.log("Data ");
  }
  loginPage(){
    this._route.navigate(['/login']);
  }
  signupU(){
    console.log("Data saving");
    this._service.addUserDetailsToRemote(this.userF.value).subscribe
    (
      data =>{
       alert("Account created successfully!!!");
       this.userF.reset();
       
      },
      error =>console.log("Error")
    )
  }
}
