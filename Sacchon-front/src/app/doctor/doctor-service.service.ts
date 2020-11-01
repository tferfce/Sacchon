import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Consultation } from '../model/consultations.model';
import { Doctor } from '../model/doctor.model';
import { Patient } from '../model/patient.model';
import { PatientData } from '../model/patientData.model';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class DoctorServiceService {
  private readonly url_endpoint= 'http://localhost:9000/project';
  private readonly noconsults = '/doctorsPatientsWaitForConsult/';
  private readonly addconsult='/consultations';
  private readonly addDoctorEndpoint= '/doctors';
  private readonly patientDataEndpoint= '/patient/';
  private readonly doctorEndpoint='/doctor/';
  private readonly myPatientsEndPoint='/myPatients';
  private readonly getPatientsWithNoDoctorEndPoint='/newPatients';
  private readonly getPatientDataEndPoint='/AllData';
  private readonly pickPatientUrl='addPatientToDoctor/';
  private readonly updateConsultUrl='/consultation/';


  constructor(private http: HttpClient) { }

  getPatientsWithNoConsult(user:User): Observable<Patient[]>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');
    
    return this.http.get<Patient[]>(this.url_endpoint+this.noconsults+user.id, { headers:httpHeaders})
  }

  addConsult(consult,user:User): Observable<Consultation>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

  
    //let params = new HttpParams().set("doctorId",doctorId).set("patientId", patientId);       
    return this.http.post<Consultation>(this.url_endpoint+this.addconsult, consult , { headers:httpHeaders})

  }



  getDoctorPatients(user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<Patient[]>(this.url_endpoint+this.doctorEndpoint+user.id+this.myPatientsEndPoint,{headers:httpHeaders});
  }

  getAllPatientsWithNoDoctor(user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    const headers = { 'Content-Type': 'application/json' }
    return this.http.get<Patient[]>(this.url_endpoint+this.getPatientsWithNoDoctorEndPoint,{headers:httpHeaders})
  }

  getAllDataFromPatient(patient:Patient,user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<PatientData[]>(this.url_endpoint+this.patientDataEndpoint+patient.id+this.getPatientDataEndPoint,{headers:httpHeaders})
  }

  getAllConsultationsFromPatient(patient:Patient,user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<Consultation[]>(this.url_endpoint+this.patientDataEndpoint+patient.id+this.addconsult,{headers:httpHeaders})
  }

  patientPicker(user:User,patient:Patient){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<Patient>(this.url_endpoint+'/'+this.pickPatientUrl+user.id+'/'+patient.id,{headers:httpHeaders});
  }

  updateConsult(consult:Consultation,user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.put<Consultation>(this.url_endpoint+this.updateConsultUrl+consult.id,{
      'consult':consult.consult },{headers:httpHeaders});
  }

  deleteDoctor(user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');
    return this.http.delete(this.url_endpoint+this.doctorEndpoint+user.id,{headers:httpHeaders});
  }

  getDoctorDetails(user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');
    return this.http.get<Doctor>(this.url_endpoint+this.doctorEndpoint+user.id,{headers:httpHeaders});
  }
  
  }


