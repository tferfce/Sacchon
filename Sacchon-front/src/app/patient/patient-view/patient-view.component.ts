import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-patient-view',
  templateUrl: './patient-view.component.html',
  styleUrls: ['./patient-view.component.scss']
})
export class PatientViewComponent implements OnInit {
  onHistoryData=false;
  constructor() { }

  ngOnInit(): void {
  }

}
