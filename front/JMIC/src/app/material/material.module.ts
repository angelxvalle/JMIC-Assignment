import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule } from '@angular/material';




@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatDatepickerModule,
    NoopAnimationsModule,
    MatPaginatorModule,
    MatDatepickerModule,
    MatSortModule, 

  ],
  exports: [
    MatDatepickerModule
  ]
})
export class MaterialModule { }
