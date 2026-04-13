use employeeData;

insert into employees (empid, Fname, Lname, email, HireDate, Salary) values
(1, 'Nolan', 'Grayson', 'omniman@yahoo.com', '2000-05-15', 200000),
(2, 'Walter', 'White', 'heisenberg@email.com', '2005-09-20', 40000),
(3, 'Mark', 'Grayson', 'invincible@gmail.com', '2023-01-10', 100000);

insert into division (ID, Name, city, addressLine1, addressLine2, state, country, postalCode) values
(10, 'Imperialism', 'Atlanta', '100 Aggression Ave', NULL, 'GA', 'USA', '30301'),
(20, 'Manufacturing', 'Atlanta', '200 Sketchy St', NULL, 'GA', 'USA', '30302'),
(30, 'Hero Helping', 'Atlanta', '300 Money Rd', NULL, 'GA', 'USA', '30303');

insert into job_titles (job_title_id, job_title) values
(1, 'Conqueror'),
(2, 'The Cook'),
(3, 'The Hero');

insert into employee_division (empid, div_ID) values
(1, 10),  -- Nolan -> Imperialism
(2, 20),  -- Walter -> Manufacturing
(3, 30);  -- Mark -> Hero Helping

insert into employee_job_titles (empid, job_title_id) values
(1, 1),  -- Nolan -> Conqueror
(2, 2),  -- Walter -> The Cook
(3, 3);  -- Mark -> The Hero

/*
fed_tax = 16%
fed_med = 2%
fed_SS = 6.2%
state_tax = 4.8%
401k = 4%
health = 3%
*/

insert into payroll (payID, pay_date, earnings, fed_tax, fed_med, fed_SS,state_tax, 
retire_401k, health_care, empid) values
(1001, '2026-04-17', 7692.31, 1230.77, 153.85, 476.92, 369.23, 307.69, 230.77, 1),
(1002, '2026-04-17', 1538.46, 246.15, 30.77, 95.38, 73.85, 61.54, 46.15, 2),
(1003, '2026-04-17', 3846.15, 615.38, 76.92, 238.46, 184.62, 153.85, 115.38, 3);
