select * From Employee


Alter table Employee
ADD Gender char(2)

Update Employee
SET Gender = 'F'
Where Emp in (125, 126,127)

--1.เเสดงข้อมูลพนักงาน โดยเรียงลำดับจากน้อยไปมาก
SELECT EName
FROM Employee
Order by EName ASC
--2.เเสดงค่าเฉลี่ยของเงินเดือนตามกลุ่มของเพศ
SELECT Gender,AVG(salary) AS avg_salary
FROM Employee
Group by Gender

--3.เเสดงเงินเดือนที่น้อยที่สุดของเเต่ละเเผนก
SELECT Employee.Dept,MIN(Employee.salary) AS min_salary
FROM Employee, Department
WHERE Employee.Dept = Department.Dept
Group by Employee.Dept



SELECT * From Department


INSERT INTO  Department
values
(16,'ComputerScience')

--4.เเสดงชื่อพนักงานในเเต่ละเเผนก
SELECT E.*, D.Dept
FROM Employee E
INNER JOIN Department D
ON E.Dept = D.Dept

SELECT E.*, D.Dept
FROM Employee E
FULL OUTER JOIN Department D
ON E.Dept = D.Dept

--5.เเสดงเเผนกที่ยังไม่มีพนักงาน
SELECT Department.DName,Employee.*
FROM Department
LEFT JOIN Employee 
ON Department.Dept = Employee.Dept 

SELECT Department.DName,Employee.*
FROM Department
RIGHT JOIN Employee 
ON Department.Dept = Employee.Dept