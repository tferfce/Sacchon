import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { SidenavListComponent } from './sidenav-list/sidenav-list.component';

import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserModule } from '@angular/platform-browser';




@NgModule({
  exports:[HeaderComponent,SidenavListComponent],
  declarations: [    
    HeaderComponent,
    SidenavListComponent,

  ],
  imports: [
    BrowserModule,
    CommonModule,
    FlexLayoutModule,
  ]
})
export class NavigationModule { }
