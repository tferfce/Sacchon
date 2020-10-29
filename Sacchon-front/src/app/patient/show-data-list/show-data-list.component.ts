import { Route } from '@angular/compiler/src/core';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
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

  dataPatientList:PatientData[]=[];
  datesForm: FormGroup;
  patient:Patient;
  patientData:PatientData;
  user:User;
  loadComponent = false;
  onTableNav=false;
  loadUpdateComponent=false;

  constructor(private storageService:StorageService,private patientService:PatientService) {

    
   }

  ngOnInit(): void {
    //this.patient=this.storageService.getScope();
    this.user=this.storageService.getScopeUser();
    this.datesForm = new FormGroup({
      fromDate: new FormControl(),
      toDate: new FormControl()
    })

 
  }

 onSubmitForm(form: FormGroup){
this.patientService.showPatientData(form.value,this.user).subscribe(data=>{
  this.dataPatientList=data;

})
 }

  loadMyAddDataComponent(){
     this.loadComponent = !this.loadComponent;
  }

  loadMyUpdateDataComponent(patientData:PatientData){
    this.patientData=patientData;
    this.loadUpdateComponent = !this.loadUpdateComponent;
 }

  onClickDeleteBtn(patientData:PatientData){
    this.patientService.patientDataDelete(this.user,patientData).subscribe(data=>{
      console.log('works');
    })
  }
  

}
