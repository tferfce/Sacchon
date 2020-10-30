import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Consultation } from '../model/consultations.model';

@Injectable({
  providedIn: 'root'
})
export class ConsultationService {
  
  private readonly addConsultationEndpoint= 'http://localhost:9000/project/consultations';

  constructor(private http: HttpClient) { }

  addConsultation(consultation: Consultation): Observable<Consultation> {
    return this.http.post<Consultation>(this.addConsultationEndpoint, {
      'consult': consultation.consult,
      'doctor': {id: 1}, //current doctor user id from cookie
      'patient': {id: consultation.patient.id}
    });
}
}
