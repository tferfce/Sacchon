import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Patient } from 'src/app/model/patient.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-show-patient-data',
  templateUrl: './show-patient-data.component.html',
  styleUrls: ['./show-patient-data.component.scss']
})
export class ShowPatientDataComponent implements OnInit {
  user:User={
    id:null,
    username:'',
    password:'',
    role:''
  }
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
  patientId: number;
  patients: Patient[];
  datalength: number;

  constructor(private showPatientDataService: ChiefDoctorServiceService, private modalService: NgbModal,private storageService:StorageService) { }

  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
    this.showPatientDataService.getPatients(this.user).subscribe((data) => {
      this.patients = data
    });

    
  }

  openModal(targetModal, patientId) {
    this.fromDate = null;
    this.toDate = null;
    this.datalength = null;
    this.patientId= patientId;
    this.modalService.open(targetModal, {
     centered: true,
     backdrop: 'static'
    });
   }

   findData(){
    let fromDate = `${this.fromDate.year}/${this.fromDate.month}/${this.fromDate.day}`
    let toDate = `${this.toDate.year}/${this.toDate.month}/${this.toDate.day}`
    this.showPatientDataService.getPatientData(fromDate, toDate, this.patientId,this.user).subscribe(
      (data) => {
      this.datalength = data.length;
    },
    (error) => {                              //Error callback
      console.error('error caught in component')
      this.datalength = 0;

      throw error;
    }
    );
   }

}
