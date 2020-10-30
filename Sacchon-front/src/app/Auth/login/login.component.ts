import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { AuthServiceService } from '../auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  user:User={
    id:null,
    username:'',
    password:'',
    role:''
  }
  constructor(private authService:AuthServiceService,private storage:StorageService) {
    this.loginForm=new FormGroup({
      username:new FormControl(),
      password:new FormControl(),
    });
   }

   getUser(){
    this.user.username=this.loginForm.get('username').value;
    this.user.password=this.loginForm.get('password').value;
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', {
        validators: [Validators.required, Validators.required]
      }),
      password: new FormControl('', { validators: [Validators.required] })
    });
  }

  onSubmit(){
    this.getUser();
    this.authService.login(this.user).subscribe(data=>{
   if(data.role=="ROLE_PATIENT")
   {
     let redirectTo = '/patientData';
     this.storage.setScopeUser(data);
     this.authService.authSuccessfully(redirectTo);
   }
    })
  }
  
}
