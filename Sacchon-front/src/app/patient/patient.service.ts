import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from '../model/patient.model';
import { PatientData } from '../model/patientData.model';
import { User } from '../model/user.model';
import { StorageService } from '../storage.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  postDataUrl="http://localhost:9000/project/patient/";
  constructor(private storageService:StorageService,private http:HttpClient ) { }

  addData(patientData:PatientData,user:User){
    return this.http.post<PatientData>(this.postDataUrl+user.id,{
      'carbIntake':patientData.carbIntake,
      'bloodGlucose':patientData.bloodGlucose
    });
  }
}
