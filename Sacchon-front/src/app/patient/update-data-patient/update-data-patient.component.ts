import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { PatientData } from 'src/app/model/patientData.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-update-data-patient',
  templateUrl: './update-data-patient.component.html',
  styleUrls: ['./update-data-patient.component.scss']
})
export class UpdateDataPatientComponent implements OnInit {
  UpdateDataPatientForm: FormGroup;
  user:User;
  @Input() patientData:PatientData;
  constructor(private patientService:PatientService,private storageService:StorageService) { 
    this.UpdateDataPatientForm=new FormGroup({
      carbIntake:new FormControl(),
      bloodGlucose:new FormControl(),
    });
  }

  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
  }

  getPatientData(){
    this.patientData.bloodGlucose=this.UpdateDataPatientForm.get('bloodGlucose').value;
    this.patientData.carbIntake=this.UpdateDataPatientForm.get('carbIntake').value;
  }


  updateDataOnSubmit(){
    this.getPatientData();
    this.patientService.updateData(this.patientData,this.user,).subscribe(data=>{
     
})
  }
 
}
