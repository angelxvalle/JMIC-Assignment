import { Component, OnInit, ViewChild } from '@angular/core';
import {PageEvent, MatTableDataSource, MatSort, MatPaginator} from '@angular/material';
import { Employee } from '../employee';


@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})


export class ListEmployeeComponent implements OnInit {
  @ViewChild(MatSort,null) sort:MatSort;
  @ViewChild(MatPaginator,null) paginator: MatPaginator;

  columnsToDisplay: string[] = ['employeid','firstname','lastname','jobid'];

  dataSource:MatTableDataSource<Employee>;
  length = 100;
  pageSize = 10;
  pageSizeOptions = [1,2,5,10];
  pageIndex = 0;

  pageEvent:PageEvent;

  // onPageChange(e) {
  //   this.pageIndex = e.pageIndex;
  //   this.pageSize = e.pageSize;
  //   this.loadData(this.pageIndex, this.pageSize);
  // }

  employees : Array<Employee>;

  constructor() { }

  APIURL = 'http://localhost:8090/';


  getAllEmployee = async () => {
    const rawResponse = await fetch(this.APIURL +'ListEmployees', {
      method: 'GET',

      headers: {
    'Access-Control-Allow-Origin':'*',
    //'Content-Type':'multipart/form-data; application/json', 
    'Accept':'application/json',
    'Content-Type': 'application/json'
    } 
    });

  const content = await rawResponse.json();
  //console.log(content);
return content;
}

onRowClicked(id) {
console.log(id);

}

  loadData = (pageIndex, pageSize) =>
  {
    this.dataSource = new MatTableDataSource<Employee>(this.employees.slice(pageIndex, pageIndex + pageSize));
  }

  ngOnInit() {
    this.getAllEmployee().then(res=>{
      console.log("Employess table",res);
      this.employees = res.List_of_Employees;
      this.loadData(0, this.pageSize);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      // setTimeout(() => this.dataSource.paginator);

      
      
    })

    
   
    // 
  }


 
}
