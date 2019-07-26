import { Component, OnInit } from '@angular/core';
import { Jobs } from '../jobs';
import { Projects } from '../projects';
import { NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})

@NgModule({
  imports: []
    
})

export class EmployeeDetailsComponent implements OnInit {
  APIURL = 'http://localhost:8090/';

  jobs : Array<Jobs>;
  projects : Array<Projects>;

  employeid: number;
	firstname: string;
	lastname: string;
	jobid: string;
  projectid: string;
	date: Date;
  status: number;


  getAllJobs = async () => {
    const rawResponse = await fetch(this.APIURL +'ListJobs', {
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

handleAddEmployee = () => {


  

  console.log(`First Name ${this.firstname}`);
  console.log(`Last Name ${this.lastname}`);
  console.log(`Job ID ${this.jobid}`);
  console.log(`Project ID ${this.projectid}`);
  console.log(`Status ${this.status}`);
  console.log(`Date ${this.date.toISOString().slice(0, 10)}`);
  
  

  if (typeof(this.firstname) == "undefined") {
    alert("Please add first name!");
    return;
  }
  if (typeof(this.lastname) == "undefined") {
    alert("Please add last name!");
    return;
  }
  if (typeof(this.jobid) == "undefined") {
    alert("Please add designation!");
    return;
  }
  if (typeof(this.projectid) == "undefined") {
    alert("Please add project!");
    return;
  }
  if (typeof(this.date) == "undefined") {
    alert("Please add date!");
    return;
  }
 
  let mystatus = 0;
  if(this.status){
    mystatus = 1;
  }  
  
  this.RegisterEmployee(0, this.firstname, this.lastname, this.jobid, this.projectid, this.date, mystatus).then(
    res=>{
      console.log("inserted? ",res);
    }
  );
}


getAllProjects = async () => {
  const rawResponse = await fetch(this.APIURL +'ListProjects', {
    method: 'GET',
    headers: {
  'Access-Control-Allow-Origin':'*',
  //'Content-Type':'multipart/form-data; application/json', 
  'Accept':'application/json',
  'Content-Type': 'application/json'
  } 
  });

  


const content = await rawResponse.json();
// console.log(content);
return content;
}

getEmployeeByID = async (employeid) =>{
  const rawResponse =await fetch(this.APIURL +'GetEmployee', {
    method: 'POST',
    headers: {
    'Access-Control-Allow-Origin':'*',
    //'Content-Type': 'multipart/form-data ', 
    'Accept':'application/json',
    'Content-Type':'application/json'
  }, 
  body: JSON.stringify({
    "in_employeeid": employeid
  })
  });
  const content = await rawResponse.json();
  console.log(content);
  
  return content;
  }



RegisterEmployee = async (
  employeid: number,
	firstname: string,
	lastname: string,
	jobid: string,
  projectid: string,
	date: Date,
  status: number
  ) =>{
  const rawResponse =await fetch(this.APIURL +'AddRemoveEmployee', {
    method: 'POST',
    headers: {
    'Access-Control-Allow-Origin':'*',
    //'Content-Type': 'multipart/form-data ', 
    'Accept':'application/json',
    'Content-Type':'application/json'
  }, 
  

  
  

  body: JSON.stringify({
    "in_employeeid":0,
    "in_firstname":firstname,
    "in_lastname":lastname,
    "in_jobid":jobid,
    "in_projectid":projectid,
    "in_startDate": date.toISOString().slice(0, 10),
    "in_status":status,
    "in_type" : "A"
  })
  });
  const content = await rawResponse.json();
  console.log(content);
  
  return content;
  }

  updateEmployee = async (
    employeid: number,
    firstname: string,
    lastname: string,
    jobid: string,
    projectid: string,
    date: Date,
    status: number
    ) =>{
    const rawResponse =await fetch(this.APIURL +'AddRemoveEmployee', {
      method: 'PUT',
      headers: {
      'Access-Control-Allow-Origin':'*',
      //'Content-Type': 'multipart/form-data ', 
      'Accept':'application/json',
      'Content-Type':'application/json'
    }, 
    
  
    
    
  
    body: JSON.stringify({
      "in_employeeid":employeid,
      "in_firstname":firstname,
      "in_lastname":lastname,
      "in_jobid":jobid,
      "in_projectid":projectid,
      "in_startDate": date.toISOString().slice(0, 10),
      "in_status":status,
      "in_type" : "U"
    })
    });
    const content = await rawResponse.json();
    console.log(content);
    
    return content;
    }

    public currentLink: string = "";


  constructor(private router: Router, private route: ActivatedRoute) { 
  }

  
  ngOnInit() {

    let myempid;
    let counter;

    this.getAllJobs().then(res=>{
      console.log(res);
      this.jobs = res.List_of_Jobs;
    });

    this.getAllProjects().then(res=>{
      console.log(res);
      this.projects = res.List_of_Projects;
    });

    this.route.paramMap.subscribe(params=>{
      console.log('params ',params);
      myempid = params.get("employeid");
      console.log(`employed id is ${myempid}`);

      this.getEmployeeByID(myempid).then(res=>{
        console.log("employee info here ==>",res.GetEmployee);
       // counter = this.jobs[res.GetEmployee[0].jobid].designation;
        this.firstname = res.GetEmployee[0].firstname;
        this.lastname = res.GetEmployee[0].lastname;
        // console.log(`omra ${res.GetEmployee[0].jobid}`);
        // console.log(`OSCAR ${this.jobs}`);
      //  this.jobid = counter;
      //  console.log("this is the log",this.jobs);
      //  this.jobs[res.GetEmployee[0].jobid].designation;
        // counter = this.jobs[res.GetEmployee[0].jobid];
        //this.jobid.select('1');//+res.GetEmployee[0].jobid);
        // this.jobid = counter;
        this.jobid = res.GetEmployee[0].jobid;
        this.projectid = res.GetEmployee[0].projectid;
        this.date = res.GetEmployee[0].date;
      });
      // this.getEmployeeByID(myempid).then(res=>{
      //   console.log(`this is the employee ${res}`);
       });





    this.currentLink = this.router.url; 
    console.log(this.currentLink)
    if (this.currentLink == 'A/newEmployee')
    {
      // console.log("This is the add employee scenario!");
    }
    else {
      // console.log("This is the update employee scenario!");
      console.log()
    }
    
   
  }

}
