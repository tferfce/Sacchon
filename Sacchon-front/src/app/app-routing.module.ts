import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './Auth/auth.guard.service';
import { LoginComponent } from './Auth/login/login.component';
import { RegisterComponent } from './Auth/register/register.component';
import { ChiefAfterLoginComponent } from './chief-doctor/chief-after-login/chief-after-login.component';
import { ShowDoctorConsultationsComponent } from './chief-doctor/show-doctor-consultations/show-doctor-consultations.component';
import { AddConsultationComponent } from './consultation/add-consultation/add-consultation.component';
import { DoctorsWithNoActivityComponent } from './chief-doctor/doctors-with-no-activity/doctors-with-no-activity.component';
import { DoctorDataViewComponent } from './doctor/doctor-data-view/doctor-data-view.component';
import { AddPatientDataComponent } from './patient/add-patient-data/add-patient-data.component';
import { ShowDataListComponent } from './patient/show-data-list/show-data-list.component';
import { PatientsWithNoDoctorComponent } from './doctor/patients-with-no-doctor/patients-with-no-doctor.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';


const routes: Routes = [
  {path:'',component:WelcomePageComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'patientData',component:ShowDataListComponent},
  {path:'addPatientData',component:AddPatientDataComponent},
  {path: 'chief-doctor', component:ChiefAfterLoginComponent},
  {path: 'add-consultation', component:AddConsultationComponent},
  {path: 'show-doctor-consult', component:ShowDoctorConsultationsComponent},
  {path: 'doctors-with-no-activity', component:DoctorsWithNoActivityComponent},
  {path:'doctor-data-view', component:DoctorDataViewComponent},
  {path:'show-patients-with-no-doctor',component:PatientsWithNoDoctorComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes,{ onSameUrlNavigation: 'reload' })],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
