import { Injectable } from '@angular/core';
import { Patient } from '../model/patient.model';
import { StorageService } from '../storage.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
 private  patient:Patient;
  constructor(private storageService:StorageService) { }

  
}
