import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Doctor } from 'src/app/model/doctor.model';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-doctors-with-no-activity',
  templateUrl: './doctors-with-no-activity.component.html',
  styleUrls: ['./doctors-with-no-activity.component.scss']
})
export class DoctorsWithNoActivityComponent implements OnInit {

  datesForm: FormGroup;
  doctors: Array<Doctor>

  constructor(private noActivityDoctorsService: ChiefDoctorServiceService) { }

  ngOnInit(): void {
    this.datesForm = new FormGroup({
      fromDate: new FormControl(),
      toDate: new FormControl()
    })
  }

  formSubmit(form: FormGroup){
    event.preventDefault();
    console.log(form.value);
    let fromDate = `${form.value.fromDate.year}/${form.value.fromDate.month}/${form.value.fromDate.day}`
    let toDate = `${form.value.toDate.year}/${form.value.toDate.month}/${form.value.toDate.day}`
    this.noActivityDoctorsService.getDoctorsWithNoActivity({fromDate: fromDate, toDate: toDate}).subscribe((data) => {
      this.doctors = data
    });
  }

}
