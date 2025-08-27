--Create Database Mydatabase

----สร้างตาราง Department
--Create table Department
--(Dept int not null primary key,
--Dname char(30) not null) 


----สร้างตาราง Employee
--Create table Employee
--(Emp int not null primary key,
--Dname char(30) not null,
--Dept int,
--Constraint Emp_Dept Foreign key (Dept) references Department (Dept) ) 

----เพิ่ม Attribute ในตาราง Department
--ALTER table Department
--ADD AddressDept varchar(50)
 
----แก้ไข Attribute ชื่อของพนักงาน ให้สามารถรับข้อมูลได้ 50 ตัวอักษร
--ALTER table Employee
--ALTER Column Dname varchar(50) not null
 
----ลบ Attribute ในตาราง Department
--ALTER table Department
--Drop Column AddressDept
 
----จงเพิ่มข้อมูลในตาราง Department
--insert into Department
--(Dept,Dname)
--Values
--(13,'Marketing')

----จงเพิ่มข้อมูลในตาราง Department
--insert into Department
--(Dept,Dname)
--Values
--(14,'Sales'),
--(15,'Accounts')


----แสดงข้อมูลในตาราง Department
--select * from Department

----ต้องการแก้ข้อมูลที่กรอกในตาราง Department
--UPDATE Department
--Set Dname = 'Sales'
--WHERE Dept = 14

----ต้องการลบแผนก Sales ออก 
--delete From Department
--WHERE Dept = 14

-- --แสดงข้อมูลในตาราง Employee
-- select * from Employee

-- --เพิ่ม Attribute salary ลงในตารางพนักงาน
-- alter table Employee
-- add salary int

--จงเพิ่มข้อมูลในตาราง Department
insert into Employee
(Emp,Dname,Dept,salary)
Values
(128,'Mark',13,13000)

--1. จงแสดงข้อมูลพนักงานทั้งหมด
SELECT * From Employee

--2. ให้แสดงชื่อพนักงานกับเงินเดือนพนักงาน
SELECT Dname, salary From Employee

--3. แสดงข้อมูลพนักงานที่มีชื่อว่า Mark
SELECT * From Employee
WHERE Dname = 'Mark'