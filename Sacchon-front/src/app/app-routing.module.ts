import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './Auth/auth.guard.service';
import { LoginComponent } from './Auth/login/login.component';
import { RegisterComponent } from './Auth/register/register.component';
import { DoctorsWithNoActivityComponent } from './chief-doctor/doctors-with-no-activity/doctors-with-no-activity.component';
import { DoctorDataViewComponent } from './doctor/doctor-data-view/doctor-data-view.component';
import { AddPatientDataComponent } from './patient/add-patient-data/add-patient-data.component';
import { ShowDataListComponent } from './patient/show-data-list/show-data-list.component';

const routes: Routes = [
  {path:'',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path: 'doctors-with-no-activity', component:DoctorsWithNoActivityComponent},
  {path:'patientData',component:ShowDataListComponent},//,canActivate:[AuthGuard]},
  {path:'addPatientData',component:AddPatientDataComponent},//,canActivate:[AuthGuard]}
  {path:'doctor-data-view', component:DoctorDataViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
