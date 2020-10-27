import { Component, OnInit } from '@angular/core';
import { Consultation } from 'src/app/model/consultations.model';

@Component({
  selector: 'app-show-consulation',
  templateUrl: './show-consulation.component.html',
  styleUrls: ['./show-consulation.component.scss']
})
export class ShowConsulationComponent implements OnInit {
  dataConsulations:Consultation[]=[
    {
      "id": 1,
      "consultation":"First Consulation",
      "date":1603456593753
        },
  {
    "id": 2,
    "consultation":"Second Consulation",
    "date":1603456593753
  },
  {
    "id": 3,
    "consultation":"Third Consulation",
    "date":1603456593753
  },
  {
    "id": 4,
    "consultation":"Second Consulation",
    "date":1603456593753
  }];
  constructor() { }

  ngOnInit(): void {
  }

}
