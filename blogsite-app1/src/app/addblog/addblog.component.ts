import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { HttpClient, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-addblog',
  templateUrl: './addblog.component.html',
  styleUrls: ['./addblog.component.scss']
})
export class AddblogComponent {
  blogForm!:FormGroup;
  selectedFile!: File;
  filebytes:any;
  event:any;
    constructor(private formBuilder:FormBuilder,private _route: Router,private _service: NgserviceService,private dialogRef:MatDialogRef<AddblogComponent>) { }
  
    ngOnInit(): void {
      this.blogForm=this.formBuilder.group({
        blogName:['',Validators.required],
        category:['',Validators.required],
        article:['',Validators.required]
        
      
      })
  
    }
 
    addBlog(){
      console.log(this.blogForm.value);
      this._service.addBlogToRemote(this.blogForm.value).subscribe
(
  data =>{
    alert("Blog added successfully");
    this.dialogRef.close();
  },
  error =>console.log("Error"+error)
)
    }
    exit(){
      console.log("its in page");
      this._route.navigateByUrl('/home');
    }
}
