Create Database myDatabase

--สร้างตาราง Department
Create table Department
(Dept int not null primary key,
DName char(30) not null)

--สร้างตาราง Employee
Create table Employee
(Emp int not null primary key,
EName char(30) not null,
Dept int,
Constraint Emp_Dept Foreign key (Dept) references Department(Dept))

--เพิ่ม Attribute ในตาราง Department
ALTER table Department
ADD AddressDept varchar(50)

--เเก้ไข Attribute ชื่อของพนักงาน ให้สามารถรับข้อมูลได้ 50 ตัวอักษร
ALTER table Employee
ALTER Column EName varchar(50) not null

--การลบออกจากตาราง
ALTER table Department
Drop Column AddressDept
--การลบ
ALTER table Employee
Drop Column AddressDept

--จงเพิ่มข้อมูลในตาราง Department
insert into Department
(Dept,DName)
values
(13,'Marketing')

--จงเพิ่มข้อมูลในตาราง Department
insert into Department
(Dept,DName)
values
(14,'Sales'),
(15,'Accounts')

--เเสดงข้อมูลในตาราง Department
select * from Department

--ต้องการเเก้ข้อมูลที่กรอก เเล้วผิดดพลาด
UPDATE Department
SET DName = 'Sales'
WHERE Dept = 14

--ต้องการลบเเผนก Accounting ออก
Delete From Department
WHERE Dept = 14

--เเสดงข้อมูลในตาราง Department
select * from Employee

--เพิ่มAttribute salary ลงในตารางพนักงาน
ALTER table Employee
ADD  salary int

--1.จงเเสดงข้อมูลพนักงานทั้งหมด
select * from Employee
--2.ให้เเสดง ชื่อพนักงาน กับเงินเดือน
select EName,salary from Employee
--3.เเสดงข้อมูลพนักงานที่มีชื่อว่า Mark
select * from Employee
WHERE EName = 'Mark'

