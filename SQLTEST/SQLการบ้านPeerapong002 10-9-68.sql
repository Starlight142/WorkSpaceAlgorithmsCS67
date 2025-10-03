Create table Payment
(paymentID char(5) not null,
paymentDate date not null,
attachment varchar(70) not null ,
amountMoney float not null,
OrderID char(5) not null,
CustomerID char(3) not null,
Constraint FK1_OrderID Foreign key(OrderID)References Orders(OrderID),
Constraint FK2_CustomerID Foreign key(CustomerID)References Customer(CustomerID))

Create table Employee
(empID char(5)not null primary key,
FName Nvarchar(50)not null,
LName Nvarchar(50)not null,
address Nvarchar(50) not null,
telephone char(10) not null)

select * from Customer


Alter table Customer
ADD empID char(5) ,
Foreign key(empID)References Employee(empID)

Alter table Orders
ADD empID char(5),
Foreign key(empID)References Employee(empID)

--4.แสดงข้อมูลการสั่งซื้อที่ถูกดูแลโดยพนักงานที่มีชื่อว่า "หนึ่งฤทัย สุขสม"
SELECT Orders.*
FROM Orders
inner join Employee E
ON E.empID = E.empID
where E.FName = N'หนึ่งฤทัย' AND E.LName = N'สุขสม'

--5จงเเสดงผลรวมของสินค้าที่สั่งซื้อ
SELECT SUM(quantity) As Total
FROM OrderDetail

--6จงแสดงข้อมูลสินค้าที่มีจำนวนคงเหลือ น้อยกว่า 200 เล่ม
SELECT *
FROM Product
WHERE amount < 200

--7.จงแสดงชื่อสินค้าที่มีราคาต่อหน่วยมากที่สุด และราคาต่อหน่วยน้อยที่สุด
SELECT ProductName,unitPrice
FROM Product
Where unitPrice = (SELECT MIN(unitPrice)FROM Product) OR unitprice = (SELECT Max(unitPrice) From Product)

-- 8. แสดงข้อมูลลูกค้าที่ไม่มีการสั่งซื้อสินค้า
SELECT Customer.*
From Customer
left JOIN Orders
on Orders.customerID = Customer.customerID
where Orders.orderID is null

-- 9. จงหาจำนวนของสินค้าที่มีอยู่ในคลังสินค้าทั้งหมด
SELECT SUM(amount) as TotalProduct
From Product

-- 10. แสดงข้อมูลการสั่งซื้อที่ถูกดูแลโดยพนักงานที่มีชื่อว่า “สมศรี มีชัย”
SELECT Orders.*
FROM Orders
inner join Employee E
ON E.empID = E.empID
where E.FName = N'สมศรี' AND E.LName = N'มีชัย'
