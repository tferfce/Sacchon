import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Average } from '../model/average.model';
import { Consultation } from '../model/consultations.model';
import { Patient } from '../model/patient.model';
import { PatientData } from '../model/patientData.model';
import { User } from '../model/user.model';
import { StorageService } from '../storage.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  postDataUrl="http://localhost:9000/project/patient/";
  showDataPatientUrl = 'http://localhost:9000/project/patient/';
  constructor(private storageService:StorageService,private http:HttpClient ) { }

  addData(patientData:PatientData,user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');
    return this.http.post<PatientData>(this.postDataUrl+user.id,{
      'carbIntake':patientData.carbIntake,
      'bloodGlucose':patientData.bloodGlucose
    },{headers:httpHeaders});
  }

  showPatientData(dates,user:User): Observable<PatientData[]>{
    let httpHeaders = new HttpHeaders()
              .set('authorization','Basic ' +
              btoa(user.username+':'+ user.password))
              .set('Content-Type', 'application/json');

    let params = new HttpParams().set("fromDate",dates.fromDate).set("toDate", dates.toDate);
    return this.http.get<PatientData[]>(this.showDataPatientUrl+user.id+'/data', {headers:httpHeaders, params })
  }

  avgPatientData(dates,user:User): Observable<Average>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    let params = new HttpParams().set("fromDate",dates.fromDate).set("toDate", dates.toDate);
    return this.http.get<Average>(this.showDataPatientUrl+user.id+'/avg', { headers:httpHeaders, params })
  }

  patientDataDelete(user:User,patientData:PatientData){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');


    let params = new HttpParams().set('id',patientData.id);
    return this.http.delete(this.showDataPatientUrl+user.id+'/data/edit',{headers:httpHeaders,params});
  }

  updateData(patientData:PatientData,user:User){

    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    let params = new HttpParams().set('id',patientData.id);
    return this.http.put<PatientData>(this.showDataPatientUrl+user.id+'/data/edit',{
      'carbIntake':patientData.carbIntake,
      'bloodGlucose':patientData.bloodGlucose
    },{headers:httpHeaders,params});
  }

  getConsultations(user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<Consultation[]>(this.showDataPatientUrl+user.id+'/consultations',{headers:httpHeaders});
  }


  deletePatient(user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    const headers = { 'Content-Type': 'application/json' }
    return this.http.delete(this.postDataUrl+user.id,{headers:httpHeaders});
  }

  getPatient(user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    const headers = { 'Content-Type': 'application/json' }
    return this.http.get<Patient>(this.showDataPatientUrl+user.id, { headers:httpHeaders})
  }


}
