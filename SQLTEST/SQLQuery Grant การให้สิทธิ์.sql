--1.จงสร้างผู้ใช้ในระบบเพิ่ม โดยกำหนดชื่อเป็น user01,user02,user03 เเเละกำหนด password 1234

--2.จงกำหนดสิทธิให้ user01 สามารถ เรียกดู ในตารางพนักงานได้ เเละกำหนดสิทธิให้
Grant Select
ON Employee to user001
--2.1user02 มีสิทธิในการเพิ่มข้อมูล,เรียกดูข้อมูลได้ เเละ user02 สามารถกำหนดสิทธิให้
Grant Select, insert
ON Employee TO user002 with grant option
--2.2 user03 ในการเรียกดูข้อมูลได้
Grant Select
ON Employee to user003

select *From Employee


--3.จงสร้างวิวเเสดงข้อมูลพนักงานในเเต่ละเเผนก
Create View Emp_Dept1
(Emp,EName,Dept,Gender)
AS
(select Employee.Emp,Employee.EName,Employee.Dept,Employee.Gender 
From Employee
inner join Department 
ON Employee.Dept = Department.Dept)


select *from Emp_Dept1


--4.จงสร้างวิว ตรวจสอบคลังสินค้า โดยมีเงื่อนไข ดังนี้
--เมื่อสินค้า มีจำนวน <= 10 กำหนดเป็น Low inventory
--เมื่อสินค้า มีจำนวน 11-40 กำหนดเป็น Medium items
--นอกเหนือจากเงื่อนไขข้างต้นกำหนดเป็น product has a lot

 Create View CheckProduct
 (pro_name,amount,result)
 AS
 select pro_name,amount,
 case
 when amount <= 10 Then 'Low inventory'
 when amount between 11 and 30 Then 'Medium items'
 Else 'product has a lot'
 END
 from Product

 select *from CheckProduct