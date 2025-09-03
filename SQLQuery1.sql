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


Create table Product
(pro_id int not null primary key,
pro_name varchar(30) not null,
pro_price int not null,
amount int not null);

insert into Product (pro_id,pro_name,pro_price,amount) values
(1,'Pen',5,10),
(2,'Pencil',3,20),
(3,'Eraser',5,30),
(4,'Ruler',7,40);

--1.จงแสดงชื่อสินค้าและผลรวมของราคาต่อหน่วยกับจำนวนในตารางสินค้า
select pro_name,pro_price*amount AS SUM
From Product

select pro_name,pro_price*amount
From Product

--2.จงแสดงรหัสสินค้า ชื่อสินค้าที่มีราคาต่อหน่วยมากกว่าหรือเท่ากับ 5 และจำนวนมากกว่าหรือเท่ากับ 20
select pro_name,pro_price*amount 
From Product
Where (pro_price >=5) and (amount >=20)
--3.จงเพิ่มค่า Commission ให้กับพนักงานคนละ 5%
select Ename,salary*0.05 AS Commision
from Employee


--4.จงแสดงข้อมูลพนักงาน ที่มีชื่อนำหน้าตัวอักษร 'M'
select *
from Employee
where Ename like 'M%'

--5.จงแสดงข้อมูลพนักงาน ที่มีเงินเดือนระหว่าง 50000 ถึง 70000
select Ename, salary
From Employee
Where salary between 50000 and 70000

--6.จงแสดงข้อมูลพนักงาน ที่อยู่ในรหัสแผนก 13 กับ 14
select *
from Employee
where Dept in (13,14)

--7.แสดงข้อมูลของพนักงาน ที่อยู่ในแผกนเดียวกัน
Select Employee.*,Department.*
From Employee, Department
Where Employee.Dept = Department.Dept

--8.แสดงข้อมูลของพนักงาน ที่อยู่แผนกเดียวกัน
Select * From Employee,Department
Where Employee.Dept = Department.Dept and Department.Dname = 'Accounts'

--9.ต้องการหารายชื่อพนักงานที่อยุ่แผนกเดียวกับ Mark
select Ename
From Employee
Where Dept = (select Dept From Employee where Ename ='Mark')
--10.ต้องการทราบชื่อพนักงานที่มีเงินเดือนน้อยที่สุด และมากที่สุด
Select MIN(salary) AS MinSalary,Max(salary) AS MaxSalary
From Employee
--11.ต้องการทราบจำนวนของพนักงานทั้งหมด
Select Count(Emp) As CountEmp
From Employee





--1. จงแสดงข้อมูลสินค้าที่มีจำนวนคงเหลือ น้อยกว่า 200 เล่ม

--2. แสดงรายการสั่งซื้อที่มียอดของการสั่งซื้อมากกว่า 10,000

--3. แสดงข้อมูลสินค้าที่อยู่ในประเภทสินค้า คอมพิวเตอร์

--4. แสดงข้อมูลลูกค้าที่ไม่มีการสั่งซื้อสินค้า

--5. ลูกค้าที่ชื่อว่า The Book Shop ชื้อหนังสือเล่มไหนบ้าง

--6. แสดงรายการสินค้าที่สั่งซื้อของใบสั่งซื้อที่ 10258

--7. จงแสดงการสั่งซื้อสินค้าระหว่างวันที่ 21-10-2022 ถึง 27-10-2022

--8. จงแสดงราคาต่อหน่วยมากที่สุด และราคาต่อหน่วยน้อยที่สุด

--9. จงแสดงจำนวนของสินค้าที่มีอยู่ในคลังสินค้าทั้งหมด

--10. จงหาผลรวมของจำนวนสินค้าที่สั่งซื้อ