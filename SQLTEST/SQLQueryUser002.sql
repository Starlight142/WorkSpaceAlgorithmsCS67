select *from Employee

insert into Employee
(Emp,EName,Dept,salary,Gender)
values
(128,'Cake',14,2000000,'M')

--2.2 user03 ในการเรียกดูข้อมูลได้
Grant Select
ON Employee to user003