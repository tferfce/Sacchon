import { Component, OnInit } from '@angular/core';
import { DoctorServiceService } from '../doctor-service.service';
import { StorageService } from '../../storage.service';
import { User } from '../../model/user.model';
import { Patient } from 'src/app/model/patient.model';
import { Consultation } from 'src/app/model/consultations.model';
import { Doctor } from 'src/app/model/doctor.model';


@Component({
  selector: 'app-patients-with-no-consult',
  templateUrl: './patients-with-no-consult.component.html',
  styleUrls: ['./patients-with-no-consult.component.scss']
})
export class PatientsWithNoConsultComponent implements OnInit {

  user : User
  patientsWithNoConsults : Array <Patient> 



  constructor(private doctorService: DoctorServiceService, private storage: StorageService) { }

  ngOnInit(): void {

     this.user=this.storage.getScopeUser();
     debugger
     this.getPatientsWithNoConsult();

  }

  getPatientsWithNoConsult(){
    
    this.doctorService.getPatientsWithNoConsult(this.user.id).subscribe((data) => {
      this.patientsWithNoConsults = data
      console.log(data)
    });

    }

  addConsult(patient: Patient){
  const consultation: Consultation ={
    patientId: patient.id, 
    doctorId: this.user.id,
    consult: "This is the first Consult.",
    date: null


  }

    this.doctorService.addConsult(consultation).subscribe((data) => {
       console.log(data)
    });
  }

}
