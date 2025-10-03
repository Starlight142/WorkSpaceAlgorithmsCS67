CREATE TABLE Product
(Pro_id int not null,
Pro_Name varchar(30) not null,
Pro_price int not null,
amount int not null)

 select * From Product

 --1.จงเเสดงชื่อสินค้า เเละผลรวมของราคาต่อหน่วยกับจำนวนในตารางสินค้า
 SELECT Pro_Name, Pro_price*amount AS SUM 
 FROM Product
 --2.จงเเสดงรหัสสินค้า ชื่อสินค้าที่มีราคาต่อหน่วยมากกว่าหรือเท่ากับ 5 เเละจำนวนมากกว่าหรือเท่ากับ  20
 SELECT Pro_id,Pro_Name,Pro_price,amount
 FROM Product
 WHERE (Pro_price >= 5) and (amount >=  20)
 --3.จงเพิ่มค่า Commision ให้กับพนักงานคนละร้อยละ5
 SELECT EName, salary*0.05 AS Commision
 FROM Employee

 --4.จงเเสดงข้อมูลพนักงาน ที่มีชื่อนำหน้าตัวอักษร M
 SELECT Emp,EName
 FROM Employee
 WHERE  EName like 'M%'

 --5.จงเเสดงชื่อพนักงานที่มีเงินเดือนระหว่าง 50000 ถึง 70000
 SELECT EName,salary
 FROM Employee
 WHERE salary between 50000 and 70000
 
 --6.จงเเสดงข้อมูลพนักงาน ที่อยู่ในรหัสเเผนก 13 กับ 14
  SELECT *
 FROM Employee
 WHERE Dept in (13,14)

 --7.เเสดงข้อมูลของพนักงาน ที่อยู่ในเเผนกเดียวกัน
 SELECT Employee.*,Department.*
 FROM Employee,Department
 WHERE Employee.Dept = Department.Dept

 --8.เเสดงข้อมูลของพนักงาน ที่อยู่ในเเผนก"Accounts"
 SELECT Employee.*,Department.*
 FROM Employee,Department
 WHERE Employee.Dept = Department.Dept and Department.DName = 'Accounts'

 --9.ต้องการหารายชื่อพนักงานที่อยู่เเผนกเดียวกับ Mark
 SELECT *
 FROM Employee
 WHERE Dept = (SELECT Dept From Employee WHERE EName = 'Mark')

 --10.ต้องการทราบพนักงานที่มีเงินเดือนน้อยที่สุด เเละ มากที่สุด
 SELECT MIN(salary) AS MINSalary, MAX(salary) AS MaxSalary
 FROM Employee

 --11.ต้องการทราบจำนวนพนักงานทั้งหมด
 SELECT count(Emp) AS CountEmp
 FROM Product

