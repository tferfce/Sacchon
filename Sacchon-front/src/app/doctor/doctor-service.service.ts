import { HttpClient, HttpParams } from '@angular/common/http';
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

  getDoctorPatients(user:User){
    const headers = { 'Content-Type': 'application/json' }
    return this.http.get<Patient[]>(this.url_endpoint+this.doctorEndpoint+user.id+this.myPatientsEndPoint,{headers:headers});
  }

  getAllPatientsWithNoDoctor(){
    const headers = { 'Content-Type': 'application/json' }
    return this.http.get<Patient[]>(this.url_endpoint+this.getPatientsWithNoDoctorEndPoint,{headers:headers})
  }

  getAllDataFromPatient(patient:Patient){
    const headers = { 'Content-Type': 'application/json' }
    return this.http.get<PatientData[]>(this.url_endpoint+this.patientDataEndpoint+patient.id+this.getPatientDataEndPoint,{headers:headers})
  }

  getAllConsultationsFromPatient(patient:Patient){
    const headers = { 'Content-Type': 'application/json' }
    return this.http.get<Consultation[]>(this.url_endpoint+this.patientDataEndpoint+patient.id+this.addconsult,{headers:headers})
  }

  patientPicker(user:User,patient:Patient){
    return this.http.get<Patient>(this.url_endpoint+'/'+this.pickPatientUrl+user.id+'/'+patient.id);
  }

  updateConsult(consult:Consultation){
    const headers = { 'Content-Type': 'application/json' }
    return this.http.put<Consultation>(this.url_endpoint+this.updateConsultUrl+consult.id,{
      'consult':consult.consult },{headers});
  }

  deleteDoctor(user:User){
    const headers = { 'Content-Type': 'application/json' }
    return this.http.delete(this.url_endpoint+this.doctorEndpoint+user.id,{headers});
  }

  getDoctorDetails(user:User){
    const headers = { 'Content-Type': 'application/json' }
    return this.http.get<Doctor>(this.url_endpoint+this.doctorEndpoint+user.id);
  }
  
  }


