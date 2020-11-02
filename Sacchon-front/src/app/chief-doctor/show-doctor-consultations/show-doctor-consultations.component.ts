import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Consultation } from 'src/app/model/consultations.model';
import { Doctor } from 'src/app/model/doctor.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
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
  
  user:User={
    id:null,
    username:'',
    password:'',
    role:''
  }
  doctorId: number;
  doctors: Doctor[];
  consultations: Consultation[];
  consultsLength: number;

  constructor(private showDoctorConsultService: ChiefDoctorServiceService, private modalService: NgbModal,private storageService:StorageService) { }

  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
    this.showDoctorConsultService.getDoctors(this.user).subscribe((data) => {
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
    this.showDoctorConsultService.getDoctorConsultations(fromDate, toDate, this.doctorId,this.user).subscribe(
      (data) => {                           //Next callback
        console.log('response received')
        this.consultsLength = data.length;
      },
      (error) => {                              //Error callback
        console.error('error caught in component')
        this.consultsLength = 0;

        throw error;
      }
    );
   }

  

}
