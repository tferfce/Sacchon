import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Consultation } from 'src/app/model/consultations.model';
import { ConsultationService } from '../consultation.service';

@Component({
  selector: 'app-add-consultation',
  templateUrl: './add-consultation.component.html',
  styleUrls: ['./add-consultation.component.scss']
})
export class AddConsultationComponent implements OnInit {

  addConsultationForm: FormGroup;
  consultationIsAdded: boolean;
  newConsultation: {};

  constructor(private consultationService: ConsultationService) { }

  ngOnInit(): void {

    this.addConsultationForm = new FormGroup({
      consultation: new FormControl(),
      patientId: new FormControl()
    })

    this.consultationIsAdded = false;
  }

  // formSubmit(form: FormGroup){
  //   event.preventDefault();
  //   console.log(form.value);
  //   this.newConsultation = {consult: form.value.consult, patient: {id: form.value.id}, consultation: " ", date: " ", id: null};
  //   this.consultationService.addConsultation(this.newConsultation).subscribe((data) => {
  //     console.log(data);
  //     this.consultationIsAdded = true;
  //   });
  //   this.addConsultationForm.reset();
  // }

}
