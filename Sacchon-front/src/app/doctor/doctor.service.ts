import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../model/doctor.model';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private readonly addDoctorEndpoint= 'http://localhost:9000/project/doctors';


  constructor(private http: HttpClient) { }

  addDoctor(doctor: Doctor): Observable<Doctor> {
    return this.http.post<Doctor>(this.addDoctorEndpoint, {
      'firstName': doctor.firstName,
      'lastName': doctor.lastName,
      'userName': doctor.username,
      'password': doctor.password
    });
}

}
