import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../model/doctor.model';

@Injectable({
  providedIn: 'root'
})
export class ChiefDoctorServiceService {

  private readonly noactivity = 'http://localhost:9000/project/noactivity';


  constructor(private http: HttpClient) { }

  getDoctorsWithNoActivity(dates): Observable<Doctor[]>{
    const headers = { 'Content-Type': 'application/json' }
    let params = new HttpParams().set("fromDate",dates.fromDate).set("toDate", dates.toDate);
    return this.http.get<Doctor[]>(this.noactivity, { headers, params })
  }

}
