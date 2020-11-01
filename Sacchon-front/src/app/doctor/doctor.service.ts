import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../model/doctor.model';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private readonly addDoctorEndpoint= 'http://localhost:9000/project/doctors';


  constructor(private http: HttpClient) { }

  addDoctor(doctor: Doctor,user:User): Observable<Doctor> {

    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');
    
    return this.http.post<Doctor>(this.addDoctorEndpoint, {
      'firstName': doctor.firstName,
      'lastName': doctor.lastName,
      'userName': doctor.username,
      'password': doctor.password
    },{headers:httpHeaders});
}

}
