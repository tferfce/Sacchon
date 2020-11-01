import { ChangeDetectionStrategy, Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Consultation } from 'src/app/model/consultations.model';
import { Doctor } from 'src/app/model/doctor.model';
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
  consult="";
  user:User;
  patients:Patient[]=[];
  doctor:Doctor={
    id:null,
    firstName:'',
    lastName:'',
    username:'',
    password:'',
    customeRole:'',
    uri:''
    
  };
  dataPatients:PatientData[]=[];
  consultations:Consultation[]=[];
   consultation: Consultation ={
      id:null,
      consult: null,
      date: null,
      doctorId: null,
      patientId: null,
      doctor: null,
      patient: null
     }
  constructor(private doctorService:DoctorServiceService,private storageService:StorageService, private modalService: NgbModal,private secondModalService:NgbModal,private router:Router) 
  {}

  refreshPage() {
    window.location.reload();
   }

  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
    this.doctorService.getDoctorPatients(this.user).subscribe(data=>{
          //console.log(data);
        this.patients=data;
        
    });
    this.getDoctor();
  }

  getDataFromPatient(patient:Patient){
    this.doctorService.getAllDataFromPatient(patient,this.user).subscribe(data=>{
        this.dataPatients=data;
    })
  }

  getConsultsFromPatient(patient:Patient){
    this.doctorService.getAllConsultationsFromPatient(patient,this.user).subscribe(data=>{
      this.consultations=data;
    })
  }

  updateConsultation() {
      this.consultation.consult=this.consult;
     this.doctorService.updateConsult(this.consultation,this.user).subscribe((data)=>{
      this.secondModalService.dismissAll();
      
     })

  }

  deleteDoctor(){
  this.doctorService.deleteDoctor(this.user).subscribe(data=>{
    console.log(data);
  });
  this.storageService.deleteUser();
  this.router.navigate(['/login']);

  }

  getDoctor(){
    this.doctorService.getDoctorDetails(this.user).subscribe(data=>{
      this.doctor=data;
    })
  }

  openModal(targetModal,patient:Patient) {
    this.getDataFromPatient(patient);
    this.getConsultsFromPatient(patient);
    this.modalService.open(targetModal, {
      size:'xl',
     centered: true,
     backdrop: 'static'
    });
   
   }

   
  openModalForConsult(targetModal,consult:Consultation) {
    this.consultation.id=consult.id;
    this.secondModalService.open(targetModal, {
      size:'xl',
     centered: true,
     backdrop: 'static'
    });
   
   }
   
   

}
