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
  isVisible: boolean = false;
  successMessage:string;
  isSuccesfullVisible=false;
  errorMessage:string='';

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
        
    },(error)=>{
      this.errorMessage='Error with Server You cant get your Patients!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
    });
    this.getDoctor();
  }

  getDataFromPatient(patient:Patient){
    this.doctorService.getAllDataFromPatient(patient,this.user).subscribe(data=>{
        this.dataPatients=data;
      
    },(error)=>{
      this.errorMessage='Error with Server You cant get data for your Patient!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); })
  }

  getConsultsFromPatient(patient:Patient){
    this.doctorService.getAllConsultationsFromPatient(patient,this.user).subscribe(data=>{
      this.consultations=data;
    },(error)=>{
      this.errorMessage='Error with Server You cant get consults for your  Patient!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); })
  }

  updateConsultation() {
      this.consultation.consult=this.consult;
     this.doctorService.updateConsult(this.consultation,this.user).subscribe((data)=>{
      this.secondModalService.dismissAll();
      
     },(error)=>{
      this.errorMessage='Error with Server You cant update consult for your   Patient!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); })

  }

  deleteDoctor(){
  this.doctorService.deleteDoctor(this.user).subscribe(data=>{
    this.storageService.deleteUser();
    this.router.navigate(['/login']);
  },(error)=>{
    this.errorMessage='Error with Server You cant delete your account';
    if (this.isVisible) { 
      return;
    } 
    this.isVisible = true;
    setTimeout(()=> this.isVisible=false,1500); });


  }

  getDoctor(){
    this.doctorService.getDoctorDetails(this.user).subscribe(data=>{
      this.doctor=data;
    },(error)=>{
      this.errorMessage='Error with Server You cant take your Details';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); })
  }

  openModalForConsults(targetModal,patient:Patient) {

    
    this.getConsultsFromPatient(patient);
    this.modalService.open(targetModal, {
      size:'xl',
     centered: true,
     backdrop: 'static'
    });
   
   }

   openModalForData(targetModal,patient:Patient) {
    
    this.getDataFromPatient(patient);
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
