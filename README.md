# Employee Portal
An employee portal to add and get a list of all employees
It makes use of In-memory H2 database to store the employee details. 
An in-memory authentication for a user with username : **_`user`_** and password : _**`password`**_

Request authenticated using through basic authentication. Expects username and password to be present in the
Authorization header as basic

`Authorization : Basic <encoded_username>:<encoded_password>`

Consists of 2 REST API endpoints.

1. **Add Employee**

    Adds employee data to the database. Requires valid information in the payload.
   
    Endpoint : `POST /employee/add`

    Header : `Authorization : Basic <encoded_username>:<encoded_password>`
   
    Payload : `{
   "firstName": "FirstName",
   "lastName": "LastName",
   "dateOfBirth": "<DOB>",
   "department": "<Department>"
   }`
   
2. **Get All Employees**

    Returns a list of all employees currently present in the Database. Sorted by their First Name.

    Endpoint : `GET /employee/getall`

   Header : `Authorization : Basic <encoded_username>:<encoded_password>`


To run the project in local

1. Clone and import the project in any IDE (STS, IntelliJ)
2. Install Maven dependencies
3. Run as Spring boot project


**Currently the project is Back-end only. Putting Continues effort to create an Angular Frontend**
