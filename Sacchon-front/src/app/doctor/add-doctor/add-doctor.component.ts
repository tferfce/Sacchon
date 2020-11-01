import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { User } from 'src/app/model/user.model';
import { StorageService } from 'src/app/storage.service';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.scss']
})
export class AddDoctorComponent implements OnInit {
  user:User={
    id:null,
    username:'',
    password:'',
    role:''
  }
  
  successMessage:string;
  isSuccesfullVisible=false;
  errorMessage:string='';
  isVisible: boolean = false;
  
  addDoctorForm: FormGroup;
  doctorIsAdded: boolean;

  constructor(private doctorService: DoctorService,private storageService:StorageService) { }

  ngOnInit(): void {

    this.addDoctorForm = new FormGroup({
      firstName: new FormControl(),
      lastName: new FormControl(),
      username: new FormControl(),
      password: new FormControl()
    })
    this.user=this.storageService.getScopeUser();
    this.doctorIsAdded = false;
  }

  formSubmit(form: FormGroup){
    event.preventDefault();
    console.log(form.value);
    this.doctorService.addDoctor(form.value,this.user).subscribe((data) => {
      console.log(data);
      this.doctorIsAdded = true;
      this.successMessage='Succesfully add a doctor!'
      if (this.isSuccesfullVisible) { 
        return;
      } 
      this.isSuccesfullVisible = true;
      setTimeout(()=> this.isSuccesfullVisible=false,1500); 
    },(error)=>{
      this.errorMessage='Error with Server You cant add a doctor!';
      if (this.isVisible) { 
        return;
      } 
      this.isVisible = true;
      setTimeout(()=> this.isVisible=false,1500); 
    });
    this.addDoctorForm.reset();
  }

}
