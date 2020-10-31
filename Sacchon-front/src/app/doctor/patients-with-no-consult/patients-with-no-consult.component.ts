import { Component, OnInit } from '@angular/core';
import { DoctorServiceService } from '../doctor-service.service';
import { StorageService } from '../../storage.service';
import { User } from '../../model/user.model';
import { Patient } from 'src/app/model/patient.model';
import { Consultation } from 'src/app/model/consultations.model';
import { Doctor } from 'src/app/model/doctor.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-patients-with-no-consult',
  templateUrl: './patients-with-no-consult.component.html',
  styleUrls: ['./patients-with-no-consult.component.scss']
})
export class PatientsWithNoConsultComponent implements OnInit {

  user: User
  patientsWithNoConsults: Array<Patient>
  selectedPatient : Patient
  consult= ""



  constructor(private doctorService: DoctorServiceService, private storage: StorageService, private modalService: NgbModal) { }

  ngOnInit(): void {

    this.user = this.storage.getScopeUser();
    this.getPatientsWithNoConsult();

  }

  getPatientsWithNoConsult() {

    this.doctorService.getPatientsWithNoConsult(this.user.id).subscribe((data) => {
      this.patientsWithNoConsults = data
      console.log(data)
    });

  }

  addConsultation() {

    
    const consultation: Consultation ={
      id:null,
      consult: this.consult,
      date: null,
      doctorId: this.user.id,
      patientId: this.selectedPatient.id, 
      doctor: null,
      patient: null
     }
      this.doctorService.addConsult(consultation).subscribe((data)=>{
      this.modalService.dismissAll();
       console.log(data)
     })

  }

  openModal(targetModal, patient) {
    this.selectedPatient= patient
    this.modalService.open(targetModal, {
     centered: true,
     backdrop: 'static'
    });
   
   }


}
