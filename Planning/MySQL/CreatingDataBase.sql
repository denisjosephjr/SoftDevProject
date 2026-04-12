create database employeeData;

use employeeData;

create table employees (
    empid INT primary key,
    Fname VARCHAR(255),
    Lname VARCHAR(255),
    email VARCHAR(255),
    HireDate DATE,
    Salary DECIMAL
);

create table division (
    ID INT primary key,
    Name VARCHAR(255),
    city VARCHAR(255),
    addressLine1 VARCHAR(255),
    addressLine2 VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255),
    postalCode VARCHAR(255)
);

create table job_titles (
    job_title_id INT primary key,
    job_title VARCHAR(255)
);

/* Dont worry if there are red highlights. They show that some columns don't exist yet, 
   but they will when you run the whole script.

   New concepts: composite primary keys and foreign keys
   I need to review table relationships (many to many, one to many, etc.)
*/
create table employee_division (
    empid INT,
    div_ID INT,
    primary key (empid, div_ID),
    foreign key (empid) references employees(empid),
    foreign key (div_ID) references division(ID)
);

create table employee_job_titles (
    empid INT,
    job_title_id INT,
    primary key (empid, job_title_id),
    foreign key (empid) references employees(empid),
    foreign key (job_title_id) references job_titles(job_title_id)
);

create table payroll (
    payID INT,
    pay_date DATE,
    earnings DECIMAL,
    fed_tax DECIMAL,
    fed_med DECIMAL,
    fed_SS DECIMAL,
    state_tax DECIMAL,
    retire_401k DECIMAL,
    health_care DECIMAL,
    empid INT,
    foreign key (empid) references employees(empid) 
);