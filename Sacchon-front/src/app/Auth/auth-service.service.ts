import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { Patient } from '../model/patient.model';
import { StorageService } from '../storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private patient:Patient=this.storage.getScope();
private  endpoint='http://localhost:9000/project/patient';
  errorMessage:string;
authChange=new Subject<boolean>();
constructor(
  private http:HttpClient,
  private router:Router,
  private storage:StorageService
  ) { }


  signup(patient:Patient){
  return  this.http.post<Patient>(this.endpoint,{
      'firstName':patient.firstName,
      'lastName':patient.lastName,
      'gender':patient.gender,
      'userName':patient.userName,
      'password':patient.password

  
  })
  
    
  }

  authSuccessfully(){
    this.authChange.next(true);
    this.router.navigate(['/patientData']);
}
isAUth(){
  return this.patient != null;
}

logout(){
  this.patient=null;
  this.authChange.next(false);
}

}
