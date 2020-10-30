import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Consultation } from '../model/consultations.model';
import { Doctor } from '../model/doctor.model';
import { Patient } from '../model/patient.model';

@Injectable({
  providedIn: 'root'
})
export class ChiefDoctorServiceService {

  private readonly noactivity = 'http://localhost:9000/project/noactivity';
  private readonly patientsNoActivity = 'http://localhost:9000/project/patientsWithNoActivity';
  private readonly showDoctors = 'http://localhost:9000/project/doctors';
  private readonly showDoctorConsults = 'http://localhost:9000/project/admin/doctor';


  constructor(private http: HttpClient) { }

  getDoctorsWithNoActivity(dates): Observable<Doctor[]>{
    const headers = { 'Content-Type': 'application/json' }
    let params = new HttpParams().set("fromDate",dates.fromDate).set("toDate", dates.toDate);
    return this.http.get<Doctor[]>(this.noactivity, { headers, params })
  }

  getPatientsWithNoActivity(dates): Observable<Patient[]>{
    const headers = { 'Content-Type': 'application/json' }
    let params = new HttpParams().set("fromDate",dates.fromDate).set("toDate", dates.toDate);
    return this.http.get<Patient[]>(this.patientsNoActivity, { headers, params })
  }

  getDoctors(): Observable<Doctor[]>{
    return this.http.get<Doctor[]>(this.showDoctors);
  }

  getDoctorConsultations(fromDate, toDate, doctorId): Observable<Consultation[]>{
    const headers = { 'Content-Type': 'application/json' }
    let params = new HttpParams().set("fromDate", fromDate).set("toDate", toDate);
    return this.http.get<Consultation[]>(`${this.showDoctorConsults}/${doctorId}/consults`, { headers, params })
  }

}
