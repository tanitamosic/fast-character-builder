import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {ButtonModule} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TableModule} from "primeng/table";
import {HttpClientModule} from "@angular/common/http";
import {MultiSelectModule} from "primeng/multiselect";
import { CompletedComponent } from './completed/completed.component';
import {DialogModule} from "primeng/dialog";
import {TooltipModule} from "primeng/tooltip";
import {CardModule} from "primeng/card";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CompletedComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ButtonModule,
        DropdownModule,
        FormsModule,
        BrowserAnimationsModule,
        TableModule,
        HttpClientModule,
        MultiSelectModule,
        DialogModule,
        TooltipModule,
        CardModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
