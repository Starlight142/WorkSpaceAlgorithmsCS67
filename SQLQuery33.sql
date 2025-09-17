select*
from Employee

Alter table Employee
Add Gender char(2)

update Employee 
Set Gender = 'M'
Where EmpID in (123,124)

update Employee
Set Gender = 'F'
Where EmpID in (125,126,127)

--1. แสดงข้อมูลชื่อพนักงาน โดยเรียงลำดับจากน้อยไปมาก
select Ename
from Employee
Order by Ename ASC

--2. แสดงค่าเฉลี่ยของเงินเดือนตามกลุ่มของเพศ
select Gender, AVG(salary) AS Avg_Salary
From Employee
Group by Gender

--3. แสดงเงินเดือนที่น้อยที่สุดของแต่ละแผนก
select Employee.Dept, Min(Employee.salary) AS Min_Salary
From Employee, Department
Where Employee.Dept = Department.Dept
Group by Employee.Dept

select E.Dept, Min(E.salary) AS Min_Salary
From Employee E, Department D
Where E.Dept = D.Dept
Group by E.Dept
--=======================/////==========================
select*
from Department

insert into Department
Values
    (16, 'ComputerScience')

--4. แสดงชื่อพนักงานในแต่ละแผนก
select Ename
from Employee
    INNER JOIN Department
    ON Employee.Dept = Department.Dept

select E.*, D.Dept
from Employee E
    INNER JOIN Department D
    ON E.Dept = D.Dept

select E.*, D.Dept
from Employee E
    FULL OUTER JOIN Department D
    ON E.Dept = D.Dept

--5. แสดงแผนกที่ยังไม่มีพนักงาน
select Department.Dname, Employee.*
from Department
    LEFT JOIN Employee
    ON Department.Dept = Employee.Dept

--=================================================/////====================================================
--แบบฝึกหัด 10-9-2568
CREATE TABLE Employee
(
    --สร้างตาราง Employee
    EmpID Char(5) NOT NULL PRIMARY KEY,
    FName NVARCHAR(255),
    --ใส่ N ด้านหน้า Varchar เพื่อให้รองรับภาษาไทย 
    LName NVARCHAR(255),
    Eaddress NVARCHAR(10),
    telephone NVARCHAR(20)
);

INSERT INTO Employee
    (EmpID,FName,LName,Eaddress,telephone)
VALUES
    --ใส่ข้อมูล Employee
    ('Emp01', N'สมศรี', N'มีชัย', N'กรุงเทพฯ', '0908835778'),
    ('Emp02', N'ปีใหม่', N'มีสุข', N'นครปฐม', '0987354742'),
    ('Emp03', N'หนึ่งฤทัย', N'สุขสม', N'กรุงเทพฯ', '0865244793');
select*from Employee

Alter Table Customer
Add EmpID Char(5),
FOREIGN KEY (EmpID) REFERENCES Employee(EmpID)
select*from Customer
update Customer 
Set EmpID = 'Emp01'
Where customerID in (003,008)
update Customer
Set EmpID = 'Emp02'
Where customerID in (015,181)
update Customer
Set EmpID = 'Emp03'
Where customerID in (213)

Alter Table Orders --เพิ่มคอลัม EmpID ในตาราง Orders
Add EmpID Char(5),
FOREIGN KEY (EmpID) REFERENCES Employee(EmpID)
select*from Orders

update Orders
Set EmpID = 'Emp01'
Where customerID in (015,181)
update Orders
Set EmpID = 'Emp03'
Where customerID in (213)

CREATE TABLE Payment
(
    --สร้างตาราง Payment
    PaymentID Char(5) NOT NULL PRIMARY KEY,
    PaymentDate Date NOT NULL,
    --(ปี/เดือน/วัน)
    Attachment NVARCHAR(255) NOT NULL,
    amountMoney int NOT NULL,
    orderID Int NOT NULL,
    customerID CHAR(3) NOT NULL,
);

ALTER TABLE Payment
ALTER COLUMN orderID char(5);

ALTER TABLE Payment
ADD CONSTRAINT FK__Payment__orderID__47A6A41B
FOREIGN KEY (orderID) REFERENCES Orders(orderID);

FOREIGN KEY
(orderID) REFERENCES Orders
(orderID),
    FOREIGN KEY
(customerID) REFERENCES Customer
(customerID)

SELECT DATA_TYPE, CHARACTER_MAXIMUM_LENGTH
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'Orders' AND COLUMN_NAME = 'orderID'
    OR TABLE_NAME = 'Payment' AND COLUMN_NAME = 'orderID';




INSERT INTO Payment
    (PaymentID,PaymentDate,Attachment,amountMoney,OrderID,CustomerID)
VALUES
    --ใส่ข้อมูล Employee
    ('PAY01', '2023-10-23', 'Slip.jpg', '13912', '10258', '015'),
    ('PAY02', '2023-10-22', 'Pic.jpg', '9577', '10265', '181'),
    ('PAY03', '2023-11-27', 'Pic.png', '11925', '10278', '213');
select*
from Payment

Alter Table Orders
Add EmpID Char(5),
FOREIGN KEY (EmpID) REFERENCES Employee(EmpID)
select*
from Orders

--4. แสดงข้อมูลการสั่งซื้อที่ถูกดูแลโดยพนักงานที่มีชื่อว่า “หนึ่งฤทัย สุขสม” 
SELECT Orders.*
FROM Orders
    JOIN Employee ON Orders.empID = Employee.empID
WHERE CONCAT(Employee.FName, ' ', Employee.LName) = N'หนึ่งฤทัย สุขสม';
-- 5. แสดงผลรวมของจำนวนสินค้าที่สั่งซื้อ
SELECT SUM(quantity) AS totalQuantity
FROM OrderDetail;

-- 6. แสดงข้อมูลสินค้าที่มีจำนวนคงเหลือน้อยกว่า 200 เล่ม
SELECT *
FROM Product
WHERE amount < 200;

-- 7. แสดงชื่อสินค้าที่มีราคาต่อหน่วยมากที่สุด และน้อยที่สุด
SELECT productName, unitPrice
FROM Product
WHERE unitPrice = (SELECT MAX(unitPrice)
    FROM Product)
    OR unitPrice = (SELECT MIN(unitPrice)
    FROM Product);

-- 8. แสดงข้อมูลลูกค้าที่ไม่มีการสั่งซื้อสินค้า
SELECT *
FROM Customer c
WHERE NOT EXISTS (
    SELECT 1
FROM Orders o
WHERE o.customerID = c.customerID
);

-- 9. หาจำนวนของสินค้าที่มีอยู่ในคลังสินค้าทั้งหมด
SELECT SUM(amount) AS totalStock
FROM Product;

-- 10. แสดงข้อมูลการสั่งซื้อที่ถูกดูแลโดย "สมศรี มีชัย"
SELECT o.*
FROM Orders o
    JOIN Employee e ON o.empID = e.empID
WHERE CONCAT(e.FName, ' ', e.LName) = 'สมศรี มีชัย';

SELECT Customer.*
FROM Customer
    LEFT JOIN Orders ON Customer.customerID = Orders.customerID
WHERE Orders.customerID IS NULL;
alter table Customer drop constraint FK__Customer__EmpID__49C3F6B7

alter table Customer add telephone VARCHAR(20)

ALTER TABLE Customer
ALTER COLUMN telephone VARCHAR(20) NOT NULL;
--=================================================/////====================================================


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

select *
From Employee
--3.0 จงสร้างวิวแสดงข้อมูลพนักงานในแต่ละแผนก
Create View Emp_Dept
(
    Emp,
    Ename,
    Dept,
    Gender
)
As
    (select Employee.Emp, Employee.Ename, Employee.Dept, Employee.Gender
    From Employee
        inner join Department ON Employee.Dept = Department.Dept)

select*
from Employee

CREATE VIEW CHECKProduct(pro_name,amount,result)
AS
    SELECT p.pro_name,amount,
        case
when amount <= 10 then 'Low inventory'
When amount between 11 and 30 then 'Medium items'
Else 'product has a lot'
end
    from Product p

select * from CHECKProduct
from Product p,
    CASE
--=================================================/////====================================================

--แบบฝึกหัด 17-9-68

-- 1.จงสร้างวิว เพื่อแสดงสินค้าที่มีการซื้อเยอะที่สุด โดยเรียงลำดับจากมากไปน้อย


Create View CHECKProductSell AS
SELECT TOP 10 -- สามารถเพิ่มหรือลดจำนวนสินค้าที่ต้องการแสดง
    p.productName,
    SUM(od.Quantity) AS TotalQuantitySold
FROM
    Product AS p
JOIN
    OrderDetail AS od ON p.productID = od.productID
GROUP BY
    p.productName
ORDER BY
    TotalQuantitySold DESC;

SELECT *
FROM CHECKProductSell;

-- 2.จงสร้างวิว ตรวจสอบคลังสินค้า โดยมีเงื่อนไข ดังนี้
-- –เมื่อสินค้า มีจำนวน  <=200กำหนดเป็นLow inventory
-- –เมื่อสินค้า มีจำนวน  201-300กำหนดเป็นMedium items
-- –นอกเหนือจากเงื่อนไขข้างต้นกำหนดเป็น product has a lot

Create view InventoryStatusMaxMin 
(productName,amount,result) 
AS
select p.productID,p.productName,p.amount,
    CASE
        WHEN amount <= 200 THEN 'Low inventory'
        WHEN amount BETWEEN 201 AND 300 THEN 'Medium items'
        ELSE 'Product has a lot'
    END
FROM Product p;