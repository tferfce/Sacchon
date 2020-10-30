import { Component, OnInit } from '@angular/core';
import { Doctor } from 'src/app/model/doctor.model';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-show-doctor-consultations',
  templateUrl: './show-doctor-consultations.component.html',
  styleUrls: ['./show-doctor-consultations.component.scss']
})
export class ShowDoctorConsultationsComponent implements OnInit {

  doctors: Doctor[];

  constructor(private showDoctorConsultService: ChiefDoctorServiceService) { }

  ngOnInit(): void {
    this.showDoctorConsultService.getDoctorConsult().subscribe((data) => {
      this.doctors = data
    });
  }

}
