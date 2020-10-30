import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Consultation } from 'src/app/model/consultations.model';
import { Patient } from 'src/app/model/patient.model';
import { PatientData } from 'src/app/model/patientData.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { DoctorServiceService } from '../doctor-service.service';

@Component({
  selector: 'app-doctor-data-view',
  templateUrl: './doctor-data-view.component.html',
  styleUrls: ['./doctor-data-view.component.scss']
})
export class DoctorDataViewComponent implements OnInit {
  
  user:User;
  patients:Patient[]=[];
  dataPatients:PatientData[]=[];
  consultations:Consultation[]=[];
  constructor(private doctorService:DoctorServiceService,private storageService:StorageService, private modalService: NgbModal) 
  {

  }

  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
    this.doctorService.getDoctorPatients(this.user).subscribe(data=>{
          //console.log(data);
        this.patients=data;
    });
  }

  getDataFromPatient(patient:Patient){
    this.doctorService.getAllDataFromPatient(patient).subscribe(data=>{
        this.dataPatients=data;
    })
  }

  getConsultsFromPatient(patient:Patient){
    this.doctorService.getAllConsultationsFromPatient(patient).subscribe(data=>{
      this.consultations=data;
    })
  }


  openModal(targetModal,patient:Patient) {
    this.getDataFromPatient(patient);
    this.getConsultsFromPatient(patient);
    this.modalService.open(targetModal, {
     centered: true,
     backdrop: 'static'
    });
   
   }
   

}
