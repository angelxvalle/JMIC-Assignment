import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { ListEmployeeComponent } from './list-employee/list-employee.component';


const routes: Routes = [
  { path: '',
   component: ListEmployeeComponent } ,

  { path: 'A/newEmployee',
   component: EmployeeDetailsComponent } ,
   
  { path: 'U/:employeid',
   component: EmployeeDetailsComponent } 
];  

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
