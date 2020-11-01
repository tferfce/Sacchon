import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Doctor } from 'src/app/model/doctor.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-doctors-with-no-activity',
  templateUrl: './doctors-with-no-activity.component.html',
  styleUrls: ['./doctors-with-no-activity.component.scss']
})
export class DoctorsWithNoActivityComponent implements OnInit {
  user:User={
    id:null,
    username:'',
    password:'',
    role:''
  }
  datesForm: FormGroup;
  doctors: Array<Doctor>

  errorMessage:string='';
  isVisible: boolean = false;

  constructor(private noActivityDoctorsService: ChiefDoctorServiceService,private storageService:StorageService) { }

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
    this.noActivityDoctorsService.getDoctorsWithNoActivity({fromDate: fromDate, toDate: toDate},this.user).subscribe((data) => {
      this.doctors = data
    },(error)=>{
      this.errorMessage='Error with Server You cant get patients that wait for consult!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
    });
  }

}
