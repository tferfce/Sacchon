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

  dataPatientList:PatientData[]=[];

 patient:Patient;
  loadComponent = false;
  onTableNav=false;

  constructor(private storageService:StorageService) {

    
   }

  ngOnInit(): void {
    this.patient=this.storageService.getScope();

 
  }

 
  loadMyChildComponent(){
     this.loadComponent = !this.loadComponent;
  }
  

}
