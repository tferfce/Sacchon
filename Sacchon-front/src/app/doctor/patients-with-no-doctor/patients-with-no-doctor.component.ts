import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Consultation } from 'src/app/model/consultations.model';
import { Patient } from 'src/app/model/patient.model';
import { PatientData } from 'src/app/model/patientData.model';
import { DoctorServiceService } from '../doctor-service.service';

@Component({
  selector: 'app-patients-with-no-doctor',
  templateUrl: './patients-with-no-doctor.component.html',
  styleUrls: ['./patients-with-no-doctor.component.scss']
})
export class PatientsWithNoDoctorComponent implements OnInit {

  patients:Patient[]=[];
  dataPatients:PatientData[]=[];
  consultations:Consultation[]=[];
  constructor(private doctorService:DoctorServiceService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.doctorService.getAllPatientsWithNoDoctor().subscribe(data=>{
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
