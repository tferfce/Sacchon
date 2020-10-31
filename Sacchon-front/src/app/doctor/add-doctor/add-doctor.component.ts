import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.scss']
})
export class AddDoctorComponent implements OnInit {

  addDoctorForm: FormGroup;
  doctorIsAdded: boolean;

  constructor(private doctorService: DoctorService) { }

  ngOnInit(): void {

    this.addDoctorForm = new FormGroup({
      firstName: new FormControl(),
      lastName: new FormControl(),
      username: new FormControl(),
      password: new FormControl()
    })

    this.doctorIsAdded = false;
  }

  formSubmit(form: FormGroup){
    event.preventDefault();
    console.log(form.value);
    this.doctorService.addDoctor(form.value).subscribe((data) => {
      console.log(data);
      this.doctorIsAdded = true;
    });
    this.addDoctorForm.reset();
  }

}
