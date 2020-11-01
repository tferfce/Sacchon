import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChiefDoctor } from '../model/chiefDoctor.model';
import { Consultation } from '../model/consultations.model';
import { Doctor } from '../model/doctor.model';
import { Patient } from '../model/patient.model';
import { PatientData } from '../model/patientData.model';
import { PatientsWaitConsult } from '../model/patientsWaitConsult.model';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class ChiefDoctorServiceService {

  private readonly noactivity = 'http://localhost:9000/project/noactivity';
  private readonly patientsNoActivity = 'http://localhost:9000/project/patientsWithNoActivity';
  private readonly showDoctors = 'http://localhost:9000/project/doctors';
  private readonly showDoctorConsults = 'http://localhost:9000/project/admin/doctor';
  private readonly showPatients = 'http://localhost:9000/project/patient/AllPatients/get';
  private readonly showPatientData = 'http://localhost:9000/project/patient';
  private readonly showPatientWaitingForConsult = 'http://localhost:9000/project/allPatientsWaitForConsult';
  private readonly adminDetails = 'http://localhost:9000/project/admin/';


  constructor(private http: HttpClient) { }

  getDoctorsWithNoActivity(dates,user:User): Observable<Doctor[]>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    let params = new HttpParams().set("fromDate",dates.fromDate).set("toDate", dates.toDate);
    return this.http.get<Doctor[]>(this.noactivity, { headers:httpHeaders, params })
  }

  getPatientsWithNoActivity(dates,user:User): Observable<Patient[]>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    let params = new HttpParams().set("fromDate",dates.fromDate).set("toDate", dates.toDate);
    return this.http.get<Patient[]>(this.patientsNoActivity, { headers:httpHeaders, params })
  }

  getDoctors(user:User): Observable<Doctor[]>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<Doctor[]>(this.showDoctors,{headers:httpHeaders});
  }

  getDoctorConsultations(fromDate, toDate, doctorId,user:User): Observable<Consultation[]>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    let params = new HttpParams().set("fromDate", fromDate).set("toDate", toDate);
    return this.http.get<Consultation[]>(`${this.showDoctorConsults}/${doctorId}/consults`, { headers:httpHeaders, params })
  }

  getPatients(user:User): Observable<Patient[]>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<Patient[]>(this.showPatients,{headers:httpHeaders});
  }

  getPatientData(fromDate, toDate, patientId,user:User): Observable<PatientData[]>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    let params = new HttpParams().set("fromDate", fromDate).set("toDate", toDate);
    return this.http.get<PatientData[]>(`${this.showPatientData}/${patientId}/data`, { headers:httpHeaders, params })
  }

  getPatientsWaitingForConsult(user:User): Observable<PatientsWaitConsult[]>{
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<PatientsWaitConsult[]>(this.showPatientWaitingForConsult,{headers:httpHeaders});
  }

  getChiefDetails(user:User){
    let httpHeaders = new HttpHeaders()
    .set('authorization','Basic ' +
    btoa(user.username+':'+ user.password))
    .set('Content-Type', 'application/json');

    return this.http.get<ChiefDoctor>(this.adminDetails+user.id,{headers:httpHeaders});
  }

}
