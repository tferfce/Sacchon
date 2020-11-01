import { Route } from '@angular/compiler/src/core';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Patient } from 'src/app/model/patient.model';
import { PatientData } from 'src/app/model/patientData.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-show-data-list',
  templateUrl: './show-data-list.component.html',
  styleUrls: ['./show-data-list.component.scss']
})
export class ShowDataListComponent implements OnInit {
   isVisible: boolean = false;
  dataPatientList:PatientData[]=[];
  datesForm: FormGroup;
  patient:Patient;
  patientData:PatientData;
  user:User;
  successMessage:string;
  loadComponent = false;
  onTableNav=false;
  loadUpdateComponent=false;
  errorMessage:string='';
  isSuccesfullVisible=false;
  constructor(private storageService:StorageService,private patientService:PatientService,private modalService:NgbModal,private router:Router) {

    
   }

  ngOnInit(): void {
    //this.patient=this.storageService.getScope();
    
    this.user=this.storageService.getScopeUser();
    this.datesForm = new FormGroup({
      fromDate: new FormControl(),
      toDate: new FormControl()
    })

    this.patientService.getPatient(this.user).subscribe(data=>{
      this.patient=data;
    })

 
  }

 onSubmitForm(form: FormGroup){
this.patientService.showPatientData(form.value,this.user).subscribe(data=>{
  this.dataPatientList=data;
  

},
(error)=>{
    this.errorMessage='You dont have data for these dates';
    if (this.isVisible) { // if the alert is visible return
      return;
    } 
    this.isVisible = true;
    setTimeout(()=> this.isVisible=false,1500); // hide the alert after 2.5s
})
 }




  onClickDeleteBtn(patientData:PatientData){
    this.patientService.patientDataDelete(this.user,patientData).subscribe(data=>{
      this.successMessage='the data with ID'+patientData.id+'complete deleted';
      console.log('works');
      if (this.isSuccesfullVisible) { // if the alert is visible return
        return;
      } 
      this.isSuccesfullVisible = true;
      setTimeout(()=> this.isSuccesfullVisible=false,1500); 
    },(error)=>{
      this.errorMessage='Server Problem, You cant delete the data';
      if (this.isVisible) { // if the alert is visible return
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); // hide the alert after 2.5s
    })
  }

  DeletePatient(){
    this.patientService.deletePatient(this.user).subscribe(data=>{
      console.log('works');
      this.storageService.deleteUser();
      this.router.navigate(['/login']);
    },
    (error)=>{
      this.errorMessage='Server Problem, You cant delete Your Account';
      if (this.isVisible) { // if the alert is visible return
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); // hide the alert after 2.5s
    })
   
  }
  
  
  openModal(targetModal) {
    this.modalService.open(targetModal, {
      size:'md',
     centered: true,
     backdrop: 'static'
    });
   
   }

  openModalForUpdateData(targetModal,patientData:PatientData) {
    this.patientData=patientData;
    this.modalService.open(targetModal, {
      size:'md',
     centered: true,
     backdrop: 'static'
    });
   
   }

}
