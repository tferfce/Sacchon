import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Consultation } from 'src/app/model/consultations.model';
import { Doctor } from 'src/app/model/doctor.model';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-show-doctor-consultations',
  templateUrl: './show-doctor-consultations.component.html',
  styleUrls: ['./show-doctor-consultations.component.scss']
})
export class ShowDoctorConsultationsComponent implements OnInit {

  fromDate: {
    "year": String,
    "month": String,
    "day": String
  };
  toDate: {
    "year": String,
    "month": String,
    "day": String
  };
  doctorId: number;
  doctors: Doctor[];
  consultations: Consultation[];
  consultsLength: number;

  constructor(private showDoctorConsultService: ChiefDoctorServiceService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.showDoctorConsultService.getDoctors().subscribe((data) => {
      this.doctors = data
    });

  }

  openModal(targetModal, doctorId) {
    this.fromDate = null;
    this.toDate = null;
    this.consultsLength = null;
    this.doctorId= doctorId
    this.modalService.open(targetModal, {
     centered: true,
     backdrop: 'static'
    });
   }

   findConsultations(){
    let fromDate = `${this.fromDate.year}/${this.fromDate.month}/${this.fromDate.day}`
    let toDate = `${this.toDate.year}/${this.toDate.month}/${this.toDate.day}`
    this.showDoctorConsultService.getDoctorConsultations(fromDate, toDate, this.doctorId).subscribe((data) => {
      this.consultsLength = data.length;
      console.log(this.consultsLength);
    });
   }

  

}
