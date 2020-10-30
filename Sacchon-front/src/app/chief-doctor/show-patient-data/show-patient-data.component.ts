import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Patient } from 'src/app/model/patient.model';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-show-patient-data',
  templateUrl: './show-patient-data.component.html',
  styleUrls: ['./show-patient-data.component.scss']
})
export class ShowPatientDataComponent implements OnInit {

  fromDate: String;
  toDate: String;
  patientId: number;
  patients: Patient[];
  datalength: number;

  constructor(private showPatientDataService: ChiefDoctorServiceService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.showPatientDataService.getPatients().subscribe((data) => {
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
    this.showPatientDataService.getPatientData(this.fromDate, this.toDate, this.patientId).subscribe((data) => {
      this.datalength = data.length;
    });
   }

}
