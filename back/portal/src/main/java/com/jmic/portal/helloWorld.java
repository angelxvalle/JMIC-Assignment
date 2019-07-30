package com.jmic.portal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import javax.json.*;

@RestController
public class helloWorld {

	@RequestMapping("/")
	public String index() {
		return "Hello World!";
	}


	//List Employees
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value = "/ListEmployees", method = RequestMethod.GET)
		public String ListEmployees() throws InstantiationException, IllegalAccessException, SQLException {
	    	/*
	CALL sp_ListEmployees;
		
			
	   	 */
			//Step 1
			//System.out.println("UserID " + payload.get("in_userid"));
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			}
			catch(ClassNotFoundException e) {
				System.out.println("Driver Class Not Found");
			}
			//Step 2
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/portal?", "admin", "password1");
			//Step 3
			CallableStatement cstmt = null;
			//Step 4

			cstmt = con.prepareCall("{call sp_ListEmployees}");
			ResultSet rs;
			rs = cstmt.executeQuery();
			JsonArrayBuilder RecordSet = Json.createArrayBuilder();
			while(rs.next()) {
				RecordSet.add(Json.createObjectBuilder()
						
						
								
								.add("employeid",rs.getInt(1))
								.add("firstname",rs.getString(2))
								.add("lastname",rs.getString(3))
								.add("jobid",rs.getInt(4))
								.add("projectid",rs.getInt(5))
								.add("date",rs.getString(6))
								.add("status",rs.getInt(7)))
								
								
									/*	String.valueOf(rs.getInt(2)))) */
								;
								
			}
			rs.close();
			cstmt.close();
			con.close();

			JsonObject Message = Json.createObjectBuilder()
					.add("List_of_Employees", RecordSet.build())
					.build();

			return Message.toString();
		}
		
		
		//List Jobs
				@CrossOrigin(origins = "http://localhost:4200")
				@RequestMapping(value = "/ListJobs", method = RequestMethod.GET)
				public String ListJobs() throws InstantiationException, IllegalAccessException, SQLException {
			    	/*
			CALL sp_ListJobs;
				
					
			   	 */
					//Step 1
					//System.out.println("UserID " + payload.get("in_userid"));
					try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
					}
					catch(ClassNotFoundException e) {
						System.out.println("Driver Class Not Found");
					}
					//Step 2
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/portal?", "admin", "password1");
					//Step 3
					CallableStatement cstmt = null;
					//Step 4

					cstmt = con.prepareCall("{call sp_ListJobs}");
					ResultSet rs;
					rs = cstmt.executeQuery();
					JsonArrayBuilder RecordSet = Json.createArrayBuilder();
					while(rs.next()) {
						RecordSet.add(Json.createObjectBuilder()
								
										
										.add("jobid",rs.getInt(1))
										.add("designation",rs.getString(2)))
										
											/*	String.valueOf(rs.getInt(2)))) */
										;
										
					}
					rs.close();
					cstmt.close();
					con.close();

					JsonObject Message = Json.createObjectBuilder()
							.add("List_of_Jobs", RecordSet.build())
							.build();

					return Message.toString();
				}
				
				
				//List Projects
				@CrossOrigin(origins = "http://localhost:4200")
				@RequestMapping(value = "/ListProjects", method = RequestMethod.GET)
				public String ListProjects() throws InstantiationException, IllegalAccessException, SQLException {
			    	/*
			CALL sp_ListProjects;
				
					
			   	 */
					//Step 1
					//System.out.println("UserID " + payload.get("in_userid"));
					try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
					}
					catch(ClassNotFoundException e) {
						System.out.println("Driver Class Not Found");
					}
					//Step 2
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/portal?", "admin", "password1");
					//Step 3
					CallableStatement cstmt = null;
					//Step 4

					cstmt = con.prepareCall("{call sp_ListProjects}");
					ResultSet rs;
					rs = cstmt.executeQuery();
					JsonArrayBuilder RecordSet = Json.createArrayBuilder();
					while(rs.next()) {
						RecordSet.add(Json.createObjectBuilder()
								
										
										.add("projectid",rs.getInt(1))
										.add("description",rs.getString(2)))
										
											/*	String.valueOf(rs.getInt(2)))) */
										;
										
					}
					rs.close();
					cstmt.close();
					con.close();

					JsonObject Message = Json.createObjectBuilder()
							.add("List_of_Projects", RecordSet.build())
							.build();

					return Message.toString();
				}
				
				
				
				
				//Edit employee details
				@CrossOrigin(origins = "http://localhost:4200")
				@RequestMapping(value = "/AddRemoveEmployee", method = RequestMethod.POST)
				public String AddRemoveEmployee(@RequestBody  Map<String,Object> payload) throws InstantiationException, IllegalAccessException, SQLException {
			    	/*
				CALL sp_AddRemoveEmployee(1, 'Angel', 'Papi' ,'3', '7', '2019-11-29', 1, 'a');
				
					
					 IN in_employeeid INT ,
		 IN in_firstname varchar(50),
		 IN in_lastname varchar(50),  
		 IN in_jobid varchar(5),  
		 IN in_projectid varchar(5),  
		 IN in_startDate date,
		 IN in_status INT,
		 IN in_type char(1)
			   	 {
					"in_employeeid":"1",
					"in_firstname":"Angel",
					"in_jobid":1,
					"in_projectid":1,
					"in_startDate": "2018-01-23",
					"in_status":1,
					"in_type" : "A"
				 }
			   	 */
					
					//Step 1
					//System.out.println("UserID " + payload.get("in_userid"));
					try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
					}
					catch(ClassNotFoundException e) {
						System.out.println("Driver Class Not Found");
					}
					//Step 2
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/portal?", "admin", "password1");
					//Step 3
					CallableStatement cstmt = null;
					//Step 4

					cstmt = con.prepareCall("{call sp_AddRemoveEmployee(?,?,?,?,?,?,?,?)}");
					cstmt.setInt(1, Integer.parseInt(payload.get("in_employeeid").toString()));
					cstmt.setString(2, payload.get("in_firstname").toString());
					cstmt.setString(3, payload.get("in_lastname").toString());
					cstmt.setInt(4, Integer.parseInt(payload.get("in_jobid").toString()));
					cstmt.setInt(5, Integer.parseInt(payload.get("in_projectid").toString()));
					cstmt.setString(6, payload.get("in_startDate").toString());
					cstmt.setInt(7, Integer.parseInt(payload.get("in_status").toString()));
					cstmt.setString(8, payload.get("in_type").toString());

					ResultSet rs;
					rs = cstmt.executeQuery();
					JsonArrayBuilder RecordSet = Json.createArrayBuilder();
					
//					while(rs.next()) {
//						//System.out.println("User " + rs.getString(1) + " Stock " + rs.getString(2));
//						RecordSet.add(Json.createObjectBuilder()
//								
//								
//										.add(rs.getString(1),"0"))
//											/*	String.valueOf(rs.getInt(2)))) */
//										;
//										
//					}
					rs.close();
					cstmt.close();
					con.close();

					JsonObject Message = Json.createObjectBuilder()
							.add("AddRemoveEmployee", RecordSet.build())
							.build();

					return Message.toString();
				}
				
				
				
				//Get Employee
				@CrossOrigin(origins = "http://localhost:4200")
				@RequestMapping(value = "/GetEmployee", method = RequestMethod.POST)
				public String GetEmployee(@RequestBody  Map<String,Object> payload) throws InstantiationException, IllegalAccessException, SQLException {
			    	/*
				CALL sp_GetEmployee(1);
				
					
					 IN in_employeeid INT ,
			   	 {
					"in_employeeid":"1"
				 }
			   	 */
					
					//Step 1
//					System.out.println("UserID " + payload.get("in_employeeid"));
					try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
					}
					catch(ClassNotFoundException e) {
						System.out.println("Driver Class Not Found");
					}
					//Step 2
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/portal?", "admin", "password1");
					//Step 3
					CallableStatement cstmt = null;
					//Step 4

					cstmt = con.prepareCall("{call sp_GetEmployee(?)}");
					cstmt.setInt(1, Integer.parseInt(payload.get("in_employeeid").toString()));

					ResultSet rs;
					rs = cstmt.executeQuery();
					JsonArrayBuilder RecordSet = Json.createArrayBuilder();
					
					while(rs.next()) {
						//System.out.println("User " + rs.getString(1) + " Stock " + rs.getString(2));
						RecordSet.add(Json.createObjectBuilder()
								
								
								.add("employeid",rs.getInt(1))
								.add("firstname",rs.getString(2))
								.add("lastname",rs.getString(3))
								.add("jobid",rs.getInt(4))
								.add("projectid",rs.getInt(5))
								.add("date",rs.getString(6))
								.add("status",rs.getInt(7)));
										
					}
					rs.close();
					cstmt.close();
					con.close();

					JsonObject Message = Json.createObjectBuilder()
							.add("GetEmployee", RecordSet.build())
							.build();

					return Message.toString();
				}
				
				
				
				
				
				//Edit Jobs
				@CrossOrigin(origins = "http://localhost:4200")
				@RequestMapping(value = "/AddRemoveJobs", method = RequestMethod.POST)
				public String AddRemoveJobs(@RequestBody  Map<String,Object> payload) throws InstantiationException, IllegalAccessException, SQLException {
			    	/*
				CALL AddRemoveJobs(1, 'Microsoft', 'A');
				
					
					IN in_jobid int,
					IN in_designation varchar(50),
					IN in_type char(1)
					
			   	 {
					"in_jobid":"1",
					"in_designation":"Microsoft",
					"in_type":A,
				 }
			   	 */
					
					//Step 1
					//System.out.println("UserID " + payload.get("in_userid"));
					try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
					}
					catch(ClassNotFoundException e) {
						System.out.println("Driver Class Not Found");
					}
					//Step 2
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/portal?", "admin", "password1");
					//Step 3
					CallableStatement cstmt = null;
					//Step 4

					cstmt = con.prepareCall("{call sp_AddRemoveJobs(?,?,?)}");
					cstmt.setInt(1, Integer.parseInt(payload.get("in_jobid").toString()));
					cstmt.setString(2, payload.get("in_designation").toString());
					cstmt.setString(3, payload.get("in_type").toString());

					ResultSet rs;
					rs = cstmt.executeQuery();
					JsonArrayBuilder RecordSet = Json.createArrayBuilder();
					
//					while(rs.next()) {
//						//System.out.println("User " + rs.getString(1) + " Stock " + rs.getString(2));
//						RecordSet.add(Json.createObjectBuilder()
//								
//								
//										.add(rs.getString(1),"0"))
//											/*	String.valueOf(rs.getInt(2)))) */
//										;
//										
//					}
					rs.close();
					cstmt.close();
					con.close();

					JsonObject Message = Json.createObjectBuilder()
							.add("AddRemoveJobs", RecordSet.build())
							.build();

					return Message.toString();
				}
				
				
				
				//Edit Projects
				@CrossOrigin(origins = "http://localhost:4200")
				@RequestMapping(value = "/AddRemoveProjects", method = RequestMethod.POST)
				public String AddRemoveProjects(@RequestBody  Map<String,Object> payload) throws InstantiationException, IllegalAccessException, SQLException {
			    	/*
				CALL AddRemoveProjects(1, 'Sony', 'A');
				
					
					IN in_projectid int,
					IN in_description varchar(50),
					IN in_type char(1)
					
			   	 {
					"in_projectid":"1",
					"in_description":"Sony",
					"in_type":A,
				 }
			   	 */
					
					//Step 1
					//System.out.println("UserID " + payload.get("in_userid"));
					try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
					}
					catch(ClassNotFoundException e) {
						System.out.println("Driver Class Not Found");
					}
					//Step 2
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/portal?", "admin", "password1");
					//Step 3
					CallableStatement cstmt = null;
					//Step 4

					cstmt = con.prepareCall("{call sp_AddRemoveProjects(?,?,?)}");
					cstmt.setInt(1, Integer.parseInt(payload.get("in_projectid").toString()));
					cstmt.setString(2, payload.get("in_description").toString());
					cstmt.setString(3, payload.get("in_type").toString());

					ResultSet rs;
					rs = cstmt.executeQuery();
					JsonArrayBuilder RecordSet = Json.createArrayBuilder();
					
//					while(rs.next()) {
//						//System.out.println("User " + rs.getString(1) + " Stock " + rs.getString(2));
//						RecordSet.add(Json.createObjectBuilder()
//								
//								
//										.add(rs.getString(1),"0"))
//											/*	String.valueOf(rs.getInt(2)))) */
//										;
//										
//					}
					rs.close();
					cstmt.close();
					con.close();

					JsonObject Message = Json.createObjectBuilder()
							.add("AddRemoveProjects", RecordSet.build())
							.build();

					return Message.toString();
				}
	
}
