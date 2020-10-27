import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Average } from 'src/app/model/average.model';

@Component({
  selector: 'app-show-average',
  templateUrl: './show-average.component.html',
  styleUrls: ['./show-average.component.scss']
})
export class ShowAverageComponent implements OnInit {
  @Output() showAverage=new EventEmitter<void>();
  constructor() { }
  dataAverage:Average[]=[
    {
      "id": 1,
      "avgGlycose":150.0,
      "avgCarbs":3500.0
        },
  {
    "id": 2,
    "avgGlycose":150.0,
    "avgCarbs":3500.0
  },
  {
    "id": 3,
    "avgGlycose":150.0,
    "avgCarbs":3500.0
  },
  {
    "id": 4,
    "avgGlycose":150.0,
    "avgCarbs":3500.0
  }];
  ngOnInit(): void {
  }

}
