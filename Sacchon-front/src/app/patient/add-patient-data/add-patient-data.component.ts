import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/model/patient.model';
import { StorageService } from 'src/app/storage.service';

@Component({
  selector: 'app-add-patient-data',
  templateUrl: './add-patient-data.component.html',
  styleUrls: ['./add-patient-data.component.scss']
})
export class AddPatientDataComponent implements OnInit {
  patient:Patient;
  url:string;
  constructor(private storageService:StorageService) {

    
   }



  ngOnInit(): void {
    this.patient=this.storageService.getScope();
    console.log(this.patient);
    this.url="www.google.com/"+this.patient.id;
    console.log(this.url);
  }
  
}
