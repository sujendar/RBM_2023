import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatPaginator} from '@angular/material/paginator';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import {MatDialog,MatDialogModule,MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import {FormBuilder,FormGroup,Validator,Validators} from '@angular/forms';
import {MatTabsModule} from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import {MatTooltipModule} from '@angular/material/tooltip';
import { HttpClientModule } from '@angular/common/http';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {DateAdapter, MatNativeDateModule} from '@angular/material/core';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { AddblogComponent } from './addblog/addblog.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    AddblogComponent
  ],
  imports: [
    BrowserModule,MatTabsModule,MatFormFieldModule,MatSelectModule,FormsModule,FormsModule,ReactiveFormsModule,
    AppRoutingModule,MatButtonModule,HttpClientModule,MatToolbarModule,MatTableModule,
    MatCardModule, MatIconModule,BrowserAnimationsModule,MatInputModule,MatCheckboxModule,
    MatRadioModule,MatPaginatorModule,MatDatepickerModule,MatNativeDateModule,MatTooltipModule,MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
