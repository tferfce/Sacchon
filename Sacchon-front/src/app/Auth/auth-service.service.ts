import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { Patient } from '../model/patient.model';
import { User } from '../model/user.model';
import { StorageService } from '../storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  // private patient:Patient=this.storage.getScope();
  private user:User=this.storage.getScopeUser();
private  endpoint='http://localhost:9000/project/patient';
private loginEndPoint='http://localhost:9000/project/login';
  errorMessage:string;
authChange=new Subject<boolean>();
constructor(
  private http:HttpClient,
  private router:Router,
  private storage:StorageService
  ) { }



  login(user:User){
    let httpHeaders = new HttpHeaders()
              .set('authorization','Basic ' +
              btoa(user.username+':'+ user.password))
              .set('Content-Type', 'application/json');
    const httpOptions = {
        headers: httpHeaders
}; 
    return this.http.get<User>(this.loginEndPoint,httpOptions);
  }

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
  return this.user != null;
}

logout(){
  this.user=null;
  this.authChange.next(false);
  
}

}
