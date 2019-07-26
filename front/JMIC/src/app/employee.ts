export class Employee {
    employeid: number;
	firstname: string;
	lastname: string;
	jobid: string;
    projectid: string;
	date: Date;
    status: number;
    
    constructor(employeid:number,firstname:string,lastname:string,jobid:string,projectid:string,date:Date,status:number)
        {
            this.employeid = employeid;
            this.firstname = firstname;
            this.lastname = lastname;
            this.jobid = jobid;
            this.projectid = projectid;
            this.date = date;
            this.status = status;
        }
}
