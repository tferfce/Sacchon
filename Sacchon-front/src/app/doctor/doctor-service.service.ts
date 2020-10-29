import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Consultation } from '../model/consultations.model';
import { Doctor } from '../model/doctor.model';
import { Patient } from '../model/patient.model';
import { PatientData } from '../model/patientData.model';

@Injectable({
  providedIn: 'root'
})
export class DoctorServiceService {
  private readonly url_endpoint= 'http://localhost:9000/project'
  private readonly noconsults = '/doctorsPatientsWaitForConsult/'
  private readonly addconsult='/consultations'
  private readonly addDoctorEndpoint= '/doctors';
  private readonly patientDataEndpoint= '/patient/';



  constructor(private http: HttpClient) { }

  getPatientsWithNoConsult(doctorId): Observable<Patient[]>{
    const headers = { 'Content-Type': 'application/json' }
    
    return this.http.get<Patient[]>(this.url_endpoint+this.noconsults+doctorId, { headers})
  }

  addConsult(consult): Observable<Consultation>{
    const headers = { 'Content-Type': 'application/json' }
    //let params = new HttpParams().set("doctorId",doctorId).set("patientId", patientId);       
    return this.http.post<Consultation>(this.url_endpoint+this.addconsult, consult , { headers})


  }

  getPatientData(patientId): Observable<PatientData[]>{
    const headers = { 'Content-Type': 'application/json' }
    
    return this.http.get<PatientData[]>(this.url_endpoint+this.patientDataEndpoint+patientId+'/data', { headers})
  }

  addDoctor(doctor: Doctor): Observable<Doctor> {
    const headers = { 'Content-Type': 'application/json' }

    return this.http.post<Doctor>(this.url_endpoint+this.addDoctorEndpoint, {
      'firstName': doctor.firstName,
      'lastName': doctor.lastName,
      'userName': doctor.username,
      'password': doctor.password
    }, {headers});

  }
}
