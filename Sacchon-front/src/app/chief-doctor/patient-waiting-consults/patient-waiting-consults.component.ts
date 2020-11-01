import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/model/patient.model';
import { PatientsWaitConsult } from 'src/app/model/patientsWaitConsult.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-patient-waiting-consults',
  templateUrl: './patient-waiting-consults.component.html',
  styleUrls: ['./patient-waiting-consults.component.scss']
})
export class PatientWaitingConsultsComponent implements OnInit {

  patients: PatientsWaitConsult[];
  user:User;
  constructor(private patientsWaitingConsults: ChiefDoctorServiceService,private storageService:StorageService) { }

  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
    this.patientsWaitingConsults.getPatientsWaitingForConsult(this.user).subscribe((data) => {
      this.patients = data
    });
  }

}
