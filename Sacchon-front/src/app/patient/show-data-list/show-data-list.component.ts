import { Route } from '@angular/compiler/src/core';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from 'src/app/model/patient.model';
import { PatientData } from 'src/app/model/patientData.model';
import { StorageService } from 'src/app/storage.service';

@Component({
  selector: 'app-show-data-list',
  templateUrl: './show-data-list.component.html',
  styleUrls: ['./show-data-list.component.scss']
})
export class ShowDataListComponent implements OnInit {
  dataPatientList:PatientData[]=[
    {
      "id": 1,
      "carbIntake": 20.5,
      "bloodGlucose": 135.2,
      "date": 1603456593753,
      "avg":25,
      "consultation":"consulation1"
        },
  {
      "id": 2,
      "carbIntake": 20.6,
      "bloodGlucose": 135.2,
      "date": 1603456593753, 
      "avg":25,
      "consultation":"consulation2"
  },
  {
      "id": 3,
      "carbIntake": 20.5,
      "bloodGlucose": 135.2,
      "date": 1603456593753,
      "avg":25,
      "consultation":"consulation3"
  },
  {
      "id": 4,
      "carbIntake": 20.5,
      "bloodGlucose": 135.2,
      "date": 1603456593753,
      "avg":25,
      "consultation":"consulation4"
  }];

 patient:Patient;
  loadComponent = false;


  constructor(private storageService:StorageService) {

    
   }

  ngOnInit(): void {
    this.patient=this.storageService.getScope();

 
  }

 
  loadMyChildComponent(){
     this.loadComponent = !this.loadComponent;
  }
  

}
