import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Patient } from 'src/app/model/patient.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-patients-with-no-activity',
  templateUrl: './patients-with-no-activity.component.html',
  styleUrls: ['./patients-with-no-activity.component.scss']
})
export class PatientsWithNoActivityComponent implements OnInit {
  user:User={
    id:null,
    username:'',
    password:'',
    role:''
  }
  errorMessage:string='';
  isVisible: boolean = false;

  datesForm: FormGroup;
  patients: Patient[];

  constructor(private noActivityPatientsService: ChiefDoctorServiceService,private storageService:StorageService) { }

  ngOnInit(): void {
    this.datesForm = new FormGroup({
      fromDate: new FormControl(),
      toDate: new FormControl()
    })

    this.user=this.storageService.getScopeUser();
  }

  formSubmit(form: FormGroup){
    event.preventDefault();
    console.log(form.value);
    let fromDate = `${form.value.fromDate.year}/${form.value.fromDate.month}/${form.value.fromDate.day}`
    let toDate = `${form.value.toDate.year}/${form.value.toDate.month}/${form.value.toDate.day}`
    this.noActivityPatientsService.getPatientsWithNoActivity({fromDate: fromDate, toDate: toDate},this.user).subscribe((data) => {
      this.patients = data
    },(error)=>{
      this.errorMessage='Error with Server You cant get patients';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
    });
  }

}
