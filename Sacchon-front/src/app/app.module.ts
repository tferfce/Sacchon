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
import { PatientsWithNoConsultComponent } from './doctor/patients-with-no-consult/patients-with-no-consult.component';
import { UpdateDataPatientComponent } from './patient/update-data-patient/update-data-patient.component';

@NgModule({
  declarations: [
    AppComponent,
    ShowDataListComponent,
    AddPatientDataComponent,
    DoctorsWithNoActivityComponent,
    ShowAverageComponent,
    ShowConsulationComponent,
    DoctorDataViewComponent,
    PatientsWithNoConsultComponent,
    
    UpdateDataPatientComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NavigationModule,
    AuthModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
