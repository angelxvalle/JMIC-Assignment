import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatDatepickerModule} from '@angular/material/datepicker';



import { FormsModule } from '@angular/forms';
import { ListEmployeeComponent } from './list-employee/list-employee.component';
import { MaterialModule } from './material/material.module';
import { MatSortModule, MatNativeDateModule, MatInputModule, MatFormFieldModule} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeDetailsComponent,
    ListEmployeeComponent
  ],
  imports: [
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatSortModule,  
    MatTableModule,
    MaterialModule,
    BrowserModule,
    AppRoutingModule,
    MatNativeDateModule,
    FormsModule
  ],
  exports: [
    MatDatepickerModule,
  ],
  
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
