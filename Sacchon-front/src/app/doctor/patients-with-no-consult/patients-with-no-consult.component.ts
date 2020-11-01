import { Component, OnInit } from '@angular/core';
import { DoctorServiceService } from '../doctor-service.service';
import { StorageService } from '../../storage.service';
import { User } from '../../model/user.model';
import { Patient } from 'src/app/model/patient.model';
import { Consultation } from 'src/app/model/consultations.model';

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
  isVisible: boolean = false;
  successMessage:string;
  isSuccesfullVisible=false;
  errorMessage:string='';


  constructor(private doctorService: DoctorServiceService, private storage: StorageService, private modalService: NgbModal) { }

  ngOnInit(): void {

    this.user = this.storage.getScopeUser();
    this.getPatientsWithNoConsult();

  }

  getPatientsWithNoConsult() {

    this.doctorService.getPatientsWithNoConsult(this.user).subscribe((data) => {
      this.patientsWithNoConsults = data
      console.log(data)
    },
    (error)=>{
      this.errorMessage='Error with Server You cant get Patients!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
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
     
     this.doctorService.addConsult(consultation,this.user).subscribe((data)=>{
      this.modalService.dismissAll();
      this.successMessage='Consultation Succesfully added!';
      if (this.isSuccesfullVisible) { 
        return;
      } 
      this.isSuccesfullVisible = true;
      setTimeout(()=> this.isSuccesfullVisible=false,1500); 
     },
     (error)=>{
       this.errorMessage='Error with Server You cant add a Consult!';
       if (this.isVisible) { 
         return;
       } 
       this.isVisible = true;
       setTimeout(()=> this.isVisible=false,1500); 
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
