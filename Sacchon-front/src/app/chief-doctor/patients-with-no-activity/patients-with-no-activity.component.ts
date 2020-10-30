import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Patient } from 'src/app/model/patient.model';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-patients-with-no-activity',
  templateUrl: './patients-with-no-activity.component.html',
  styleUrls: ['./patients-with-no-activity.component.scss']
})
export class PatientsWithNoActivityComponent implements OnInit {

  datesForm: FormGroup;
  patients: Patient[];

  constructor(private noActivityPatientsService: ChiefDoctorServiceService) { }

  ngOnInit(): void {
    this.datesForm = new FormGroup({
      fromDate: new FormControl(),
      toDate: new FormControl()
    })
  }

  formSubmit(form: FormGroup){
    event.preventDefault();
    console.log(form.value);
    this.noActivityPatientsService.getPatientsWithNoActivity(form.value).subscribe((data) => {
      this.patients = data
    });
  }

}
