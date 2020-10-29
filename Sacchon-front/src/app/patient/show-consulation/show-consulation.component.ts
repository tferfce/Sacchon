import { Component, OnInit } from '@angular/core';
import { Consultation } from 'src/app/model/consultations.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-show-consulation',
  templateUrl: './show-consulation.component.html',
  styleUrls: ['./show-consulation.component.scss']
})
export class ShowConsulationComponent implements OnInit {
  dataConsulations:Consultation[]=[];
  user:User;
  constructor(private patientService:PatientService,private storageService:StorageService) { }

  ngOnInit(): void {
      this.user=this.storageService.getScopeUser();
      console.log(this.user);
      this.patientService.getConsultations(this.user).subscribe(data=>{
        this.dataConsulations=data;
      })
  }



}
