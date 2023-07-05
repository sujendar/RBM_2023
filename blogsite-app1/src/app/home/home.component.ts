import { Component, OnInit, Optional ,Inject } from '@angular/core';
import { Blogdetails } from '../blogdetails';
import {MatDialog,MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import {MatDialogModule} from '@angular/material/dialog';
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { MatChipsModule } from '@angular/material/chips';
import {Router} from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { AddblogComponent } from '../addblog/addblog.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  searchtext : String ="";
  book: Array<Blogdetails> = [];
  b1:Blogdetails=new Blogdetails();
  displayedColumns: string[] = ['id', 'blogName', 'category','streamType','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor( private _route: Router,private _service:NgserviceService,public addblog: MatDialog,
    //@Optional() private dialogRef:MatDialogRef<BookComponent>,
    @Optional() private dialog:MatDialog) { }
   
  ngOnInit(): void {
    this.getblogdetails();
  }

  home(){
    this._service.homepage().subscribe(data=>{console.log(data)},error=>alert("jwt error"));
  }
  goToAddBlog() {
    const dialogRef = this.addblog.open(AddblogComponent);
    this.addblog.afterAllClosed.subscribe(() => {
      this.getblogdetails();
    });
  }
 search(){
    this._service.searchbycategory(this.searchtext).subscribe(
      {
        next:(res)=>{
          this.dataSource=new MatTableDataSource(res)
          this.dataSource.paginator=this.paginator
          this.dataSource.sort=this.sort
        },error:(err)=> { 
          alert("Exception occurred 1")}
      })
  }
  deleteBlog(row:any){
    this._service.deletebyblogname(row).subscribe(
      {
        next:(res)=>{
         this.getblogdetails();
        },error:(err)=> { 
          alert("Exception occurred 1")}
      })
  }
  getblogdetails() {
    this._service.homepage().subscribe(
       {
         next:(res)=>{
           this.dataSource=new MatTableDataSource(res)
           this.dataSource.paginator=this.paginator
           this.dataSource.sort=this.sort
         },error:(err)=> { 
           alert("Exception occurred 1")}
       })
      }
      logout(){
        this._route.navigate(['/login']);
      }
      
  isEmpty()
  {
    if (this.book == null)
    {
      return true;
    }
    else { return false; }
  }
}
