import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './Auth/auth.guard.service';
import { LoginComponent } from './Auth/login/login.component';
import { RegisterComponent } from './Auth/register/register.component';
import { ChiefAfterLoginComponent } from './chief-doctor/chief-after-login/chief-after-login.component';
import { ShowDoctorConsultationsComponent } from './chief-doctor/show-doctor-consultations/show-doctor-consultations.component';
import { AddConsultationComponent } from './consultation/add-consultation/add-consultation.component';
import { AddPatientDataComponent } from './patient/add-patient-data/add-patient-data.component';
import { ShowDataListComponent } from './patient/show-data-list/show-data-list.component';

const routes: Routes = [
  {path:'',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'patientData',component:ShowDataListComponent,canActivate:[AuthGuard]},
  {path:'addPatientData',component:AddPatientDataComponent,canActivate:[AuthGuard]},
  {path: 'chief-doctor', component:ChiefAfterLoginComponent},
  {path: 'add-consultation', component:AddConsultationComponent},
  {path: 'show-doctor-consult', component:ShowDoctorConsultationsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
