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
  sucessMessage:String='';
  user:User;
  errorMessage:string='';
  isVisible: boolean = false;
  isSuccesfullVisible:boolean=false;
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
      this.sucessMessage='Succesfully Updated!';
      console.log('works');
      if (this.isSuccesfullVisible) { // if the alert is visible return
        return;
      } 
      this.isSuccesfullVisible = true;
      setTimeout(()=> this.isSuccesfullVisible=false,1500); 
},(error)=>{
  this.errorMessage='Server Problem, You cant delete Your Account';
  if (this.isVisible) { // if the alert is visible return
    return;
  } 
  this.isVisible = true;
  setTimeout(()=> this.isVisible=false,1500); // hide the alert after 2.5s

})
  }
 
}
