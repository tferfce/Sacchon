import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AuthModule } from './Auth/auth.module';
import { NavigationModule } from './navigation/navigation.module';
import { ShowDataListComponent } from './patient/show-data-list/show-data-list.component';
import { AddPatientDataComponent } from './patient/add-patient-data/add-patient-data.component';
import { DoctorsWithNoActivityComponent } from './chief-doctor/doctors-with-no-activity/doctors-with-no-activity.component';
import { HttpClientModule } from '@angular/common/http';
import { ShowAverageComponent } from './patient/show-average/show-average.component';
import { ShowConsulationComponent } from './patient/show-consulation/show-consulation.component';
import { DoctorDataViewComponent } from './doctor/doctor-data-view/doctor-data-view.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddDoctorComponent } from './doctor/add-doctor/add-doctor.component';
import { ChiefAfterLoginComponent } from './chief-doctor/chief-after-login/chief-after-login.component';
import { PatientsWithNoActivityComponent } from './chief-doctor/patients-with-no-activity/patients-with-no-activity.component';
import { AddConsultationComponent } from './consultation/add-consultation/add-consultation.component';
import { ShowDoctorConsultationsComponent } from './chief-doctor/show-doctor-consultations/show-doctor-consultations.component';
import { PatientsWithNoConsultComponent } from './doctor/patients-with-no-consult/patients-with-no-consult.component';
import { UpdateDataPatientComponent } from './patient/update-data-patient/update-data-patient.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ShowPatientDataComponent } from './chief-doctor/show-patient-data/show-patient-data.component';

@NgModule({
  declarations: [
    AppComponent,
    ShowDataListComponent,
    AddPatientDataComponent,
    DoctorsWithNoActivityComponent,
    ShowAverageComponent,
    ShowConsulationComponent,
    DoctorDataViewComponent,
    AddDoctorComponent,
    ChiefAfterLoginComponent,
    PatientsWithNoActivityComponent,
    AddConsultationComponent,
    ShowDoctorConsultationsComponent,
    PatientsWithNoConsultComponent,
    
    UpdateDataPatientComponent,
    
    ShowPatientDataComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NavigationModule,
    AuthModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
