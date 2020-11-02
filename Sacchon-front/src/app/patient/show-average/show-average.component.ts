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
  isVisible: boolean = false;
  user:User=this.storageService.getScopeUser();
  constructor(private storageService:StorageService,private patientService:PatientService) { }
  dataAverage:Average={
    id:null,
    avgCarbs:null,
    avgGlycose:null
  };
  errorMessage:string='';
  ngOnInit(): void {

    this.datesForm = new FormGroup({
      fromDate: new FormControl(),
      toDate: new FormControl()
    })
  }

  onSubmitForm(form: FormGroup){
    let fromDate = `${form.value.fromDate.year}/${form.value.fromDate.month}/${form.value.fromDate.day}`
    let toDate = `${form.value.toDate.year}/${form.value.toDate.month}/${form.value.toDate.day}`
    this.patientService.avgPatientData({fromDate: fromDate, toDate: toDate},this.user).subscribe(data=>{
      console.log(data);
     this.dataAverage.avgCarbs=data.avgCarbs;
     this.dataAverage.avgGlycose=data.avgGlycose;
    
    },
    (error)=>{
      this.errorMessage='You dont have data for these dates';
    if (this.isVisible) { // if the alert is visible return
      return;
    } 
    this.isVisible = true;
    setTimeout(()=> this.isVisible=false,1500); // hide the alert after 2.5s
}) 
     }
     

}
