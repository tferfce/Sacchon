import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/model/patient.model';
import { PatientsWaitConsult } from 'src/app/model/patientsWaitConsult.model';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-patient-waiting-consults',
  templateUrl: './patient-waiting-consults.component.html',
  styleUrls: ['./patient-waiting-consults.component.scss']
})
export class PatientWaitingConsultsComponent implements OnInit {

  patients: PatientsWaitConsult[];

  constructor(private patientsWaitingConsults: ChiefDoctorServiceService) { }

  ngOnInit(): void {
    this.patientsWaitingConsults.getPatientsWaitingForConsult().subscribe((data) => {
      this.patients = data
    });
  }

}
