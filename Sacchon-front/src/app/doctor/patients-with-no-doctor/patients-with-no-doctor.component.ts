import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Consultation } from 'src/app/model/consultations.model';
import { Patient } from 'src/app/model/patient.model';
import { PatientData } from 'src/app/model/patientData.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { DoctorServiceService } from '../doctor-service.service';

@Component({
  selector: 'app-patients-with-no-doctor',
  templateUrl: './patients-with-no-doctor.component.html',
  styleUrls: ['./patients-with-no-doctor.component.scss']
})
export class PatientsWithNoDoctorComponent implements OnInit {
  user:User;
  patients:Patient[]=[];
  dataPatients:PatientData[]=[];
  consultations:Consultation[]=[];
  isVisible: boolean = false;
  successMessage:string;
  isSuccesfullVisible=false;
  errorMessage:string='';
  constructor(private doctorService:DoctorServiceService, private modalService: NgbModal,private storageService:StorageService) { }

  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
    this.doctorService.getAllPatientsWithNoDoctor(this.user).subscribe(data=>{
      this.patients=data;

     
    },
    (error)=>{
      this.errorMessage='Error with Server You cant see the patients!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
    });
  }

  getDataFromPatient(patient:Patient){
    this.doctorService.getAllDataFromPatient(patient,this.user).subscribe(data=>{
        this.dataPatients=data;
    },
    (error)=>{
      this.errorMessage='Error with Server You cant see the data!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
    })
  }

  getConsultsFromPatient(patient:Patient){
    this.doctorService.getAllConsultationsFromPatient(patient,this.user).subscribe(data=>{
      this.consultations=data;
    },
    (error)=>{
      this.errorMessage='Error with Server You cant see the consultations!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
    })
  }

  pickPatient(patient:Patient){
    this.doctorService.patientPicker(this.user,patient).subscribe(data=>{
      console.log(data);
      this.successMessage='You succesfully pick '+patient.firstName+' '+patient.lastName;
      if (this.isSuccesfullVisible) {
        return;
      } 
      this.isSuccesfullVisible = true;
      setTimeout(()=> this.isSuccesfullVisible=false,1500); 
    },
    (error)=>{
      this.errorMessage='Error with Server You cant pick the Patient';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
     
    })
  }

  openModalForData(targetModal,patient:Patient) {
    this.getDataFromPatient(patient);
    this.modalService.open(targetModal, {
     centered: true,
     backdrop: 'static'
    });
   }

   openModalForConsult(targetModal,patient:Patient) {
   this.getConsultsFromPatient(patient);
   this.modalService.open(targetModal, {
    centered: true,
    backdrop: 'static'
   });
   }
  
}
