import { Component, OnInit } from '@angular/core';
import { ChiefDoctor } from 'src/app/model/chiefDoctor.model';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { ChiefDoctorServiceService } from '../chief-doctor-service.service';

@Component({
  selector: 'app-chief-after-login',
  templateUrl: './chief-after-login.component.html',
  styleUrls: ['./chief-after-login.component.scss']
})
export class ChiefAfterLoginComponent implements OnInit {
  user:User={
    id:null,
    username:'',
    password:'',
    role:''
  }
  chief:ChiefDoctor={
    id:null,
    username:'',
    password:'',
    customeRole:'',
    firstName:'',
    lastName:'',
    uri:''
  }

  constructor(private storageService:StorageService,private chiefService:ChiefDoctorServiceService) { }

  ngOnInit(): void {
    this.user=this.storageService.getScopeUser();
    this.chiefService.getChiefDetails(this.user).subscribe(data=>{
      this.chief=data;
    })
  }



}
