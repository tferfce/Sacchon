import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
import { StorageService } from 'src/app/storage.service';
import {Patient} from '../../model/patient.model';
import { AuthServiceService } from '../auth-service.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
 registerForm:FormGroup;
  disableButton=false;
  patient:Patient={
     id:null,
     firstName:'',
     lastName:'',
     userName:'',
     password:'',
     gender:'',
     customRole:'',
     uri:''
   };
   
  constructor(private authService:AuthServiceService,private storageService:StorageService) {   

    this.registerForm=new FormGroup({
    firstName:new FormControl(),
    lastName:new FormControl(),
    username:new FormControl(),
    password:new FormControl(),
    gender:new FormControl()
  });
}

  ngOnInit(): void {

  }
getPatient(){
  this.patient.firstName=this.registerForm.get('firstName').value;
  this.patient.lastName=this.registerForm.get('lastName').value;
  this.patient.userName=this.registerForm.get('username').value;
  this.patient.password=this.registerForm.get('password').value;
  this.patient.gender=this.registerForm.get('gender').value;
}
  onSubmit() {
this.getPatient();
this.authService.signup(this.patient).subscribe((data)=>{
  this.authService.registerSuccessfylyPatient();
});



  }
}
