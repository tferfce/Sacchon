import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Average } from 'src/app/model/average.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-show-average',
  templateUrl: './show-average.component.html',
  styleUrls: ['./show-average.component.scss']
})
export class ShowAverageComponent implements OnInit {
  datesForm: FormGroup;

  user:User=this.storageService.getScopeUser();
  constructor(private storageService:StorageService,private patientService:PatientService) { }
  dataAverage:Average={
    id:null,
    avgCarbs:null,
    avgGlycose:null
  };
  ngOnInit(): void {

    this.datesForm = new FormGroup({
      fromDate: new FormControl(),
      toDate: new FormControl()
    })
  }

  onSubmitForm(form: FormGroup){
    this.patientService.avgPatientData(form.value,this.user).subscribe(data=>{
      console.log(data);
     this.dataAverage.avgCarbs=data.avgCarbs;
     this.dataAverage.avgGlycose=data.avgGlycose;
      
    })
     }

}
