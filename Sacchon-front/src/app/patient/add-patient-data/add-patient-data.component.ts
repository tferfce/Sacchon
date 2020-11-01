import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Patient } from 'src/app/model/patient.model';
import { PatientData } from 'src/app/model/patientData.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-add-patient-data',
  templateUrl: './add-patient-data.component.html',
  styleUrls: ['./add-patient-data.component.scss']
})
export class AddPatientDataComponent implements OnInit {
 user:User;
 isDataPost=false;
 dataPatientForm: FormGroup;
 patientData:PatientData={
   id:null,
   carbIntake:null,
   bloodGlucose:null,
   date:null
   
 }

 
  constructor(private storageService:StorageService,private patientService:PatientService) {
    this.dataPatientForm=new FormGroup({
      carbIntake:new FormControl(),
      bloodGlucose:new FormControl(),
    });
    
   }



  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
    console.log(this.user);
  }

  getPatientData(){
    this.patientData.bloodGlucose=this.dataPatientForm.get('bloodGlucose').value;
    this.patientData.carbIntake=this.dataPatientForm.get('carbIntake').value;
  }

  dataPushSubmit(){
    this.getPatientData()

this.patientService.addData(this.patientData,this.user,).subscribe(data=>{
  console.log(data);
})

this.patientData.bloodGlucose=this.dataPatientForm.get('carbIntake').value;
this.patientData.carbIntake=this.dataPatientForm.get('bloodGlucose').value;
this.isDataPost=true;
  }
  
  
}
