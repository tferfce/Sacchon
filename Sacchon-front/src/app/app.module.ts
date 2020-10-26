import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AuthModule } from './Auth/auth.module';
import { NavigationModule } from './navigation/navigation.module';
import { ShowDataListComponent } from './patient/show-data-list/show-data-list.component';
import { AddPatientDataComponent } from './patient/add-patient-data/add-patient-data.component';
import { ShowAverageComponent } from './patient/show-average/show-average.component';
import { ShowConsulationComponent } from './patient/show-consulation/show-consulation.component';
import { DoctorDataViewComponent } from './doctor/doctor-data-view/doctor-data-view.component';

@NgModule({
  declarations: [
    AppComponent,
    ShowDataListComponent,
    AddPatientDataComponent,
    ShowAverageComponent,
    ShowConsulationComponent,
    DoctorDataViewComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NavigationModule,
    AuthModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
