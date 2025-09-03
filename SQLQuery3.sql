create database Montien005


CREATE TABLE Customer (
    customerID CHAR(3) NOT NULL PRIMARY KEY,
    customerName VARCHAR(100) NOT NULL,
    customerAddress VARCHAR(255) NOT NULL,
    zipCode CHAR(5) NOT NULL,
    telephone VARCHAR(20) NOT NULL
);

 CREATE TABLE Orders (
    orderID CHAR(5) NOT NULL PRIMARY KEY,
    orderDate DATE NOT NULL,
    shippedDate DATE NOT NULL,
    totalAmount DECIMAL(10,2) NOT NULL,
    customerID CHAR(3) NOT NULL,
    FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

CREATE TABLE Product (
    productID CHAR(4) NOT NULL PRIMARY KEY,
    productName VARCHAR(255) NOT NULL,
    unitPrice DECIMAL(10,2) NOT NULL,
    vat CHAR(3) NOT NULL,
    amount INT NOT NULL,
    categoryID CHAR(2) NOT NULL
);

CREATE TABLE Categories (
    categoryID CHAR(2) NOT NULL PRIMARY KEY,
    categoryName VARCHAR(100) NOT NULL,
    categoryDescription VARCHAR(255) NOT NULL
);

CREATE TABLE OrderDetail (
    orderDetailID INT NOT NULL PRIMARY KEY,
    quantity INT NOT NULL,
    discount DECIMAL(5,2) NOT NULL,
    orderID CHAR(5) NOT NULL,
    productID CHAR(4) NOT NULL,
    FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    FOREIGN KEY (productID) REFERENCES Product(productID)
);


INSERT INTO Customer VALUES
('003', 'PK Store', '1st Floor, Sermmit Tower, 159 Sukhumvit 21, Bangkok', '10330', '02-506-0750'),
('015', 'Happy Shop', 'French Association, 29 South Sathorn Rd., Thung Maha Mek, Bangkok', '10110', '02-240-2865-75'),
('181', 'The Book Shop', 'Asia Hotel, 256 Phaya Thai Rd., Bangkok', '10330', '02-267-8850-5'),
('213', 'PS Line', '2675 Ladprao Soi 95, Wang Thonglang, Bangkapi, Bangkok', '10330', '02-530-1230'),
('008', 'The Bookseller', '428 Rama I Road, Siam Square, Bangkok', '10500', '02-331-1717');

INSERT INTO Orders VALUES
('10258', '2023-10-21', '2023-10-28', 13911.70, '015'),
('10265', '2023-10-21', '2023-10-27', 9576.40, '181'),
('10278', '2023-11-24', '2023-11-30', 11925.00, '213');

INSERT INTO Product VALUES
('0005', 'Guidebook for Leaders in the 2000s', 219.63, 'Yes', 409, '01'),
('0100', 'Speaking with Eloquence', 219.63, 'Yes', 155, '01'),
('0023', 'Japanese 1, LIYC', 278.00, 'No', 326, '04'),
('0055', 'Japanese 2, LIYC', 278.00, 'No', 159, '04'),
('0051', 'SQL Server 2012', 319.00, 'No', 245, '02'),
('0010', 'System Analysis and Design', 298.00, 'No', 205, '02'),
('0125', 'Microsoft Studio .NET 2010', 700.93, 'No', 169, '02'),
('0008', 'Panasonic Video Camera Set, VHS Model NV-VX3', 260.00, 'Yes', 177, '03');

INSERT INTO Categories VALUES
('01', 'Business', 'Books related to business operations or business techniques'),
('02', 'Computer', 'Books related to computers, including programming, database management, or networking'),
('03', 'Multimedia', 'Books related to cameras and various advertising media'),
('04', 'Foreign Languages', 'Books related to foreign languages');

INSERT INTO OrderDetail VALUES
(1, 8, 25.00, '10258', '0005'),
(2, 8, 0.00, '10258', '0008'),
(3, 15, 0.00, '10258', '0125'),
(4, 31, 25.00, '10265', '0005'),
(5, 15, 0.00, '10265', '0010'),
(6, 16, 0.00, '10278', '0010'),
(7, 31, 25.00, '10278', '0008'),
(8, 4, 0.00, '10278', '0023');



--1. จงแสดงข้อมูลสินค้าที่มีจำนวนคงเหลือ น้อยกว่า 200 เล่ม
SELECT * 
from Product
WHERE amount < 200

--2. แสดงรายการสั่งซื้อที่มียอดของการสั่งซื้อมากกว่า 10,000
SELECT *
FROM Orders
WHERE totalAmount > 10000;

--3. แสดงข้อมูลสินค้าที่อยู่ในประเภทสินค้า คอมพิวเตอร์
SELECT P.productName, P.unitPrice
FROM Product P
JOIN Categories C ON P.categoryID = C.categoryID
WHERE C.categoryName = 'Computer';

--4. แสดงข้อมูลลูกค้าที่ไม่มีการสั่งซื้อสินค้า
SELECT C.customerID, C.customerName
FROM Customer C
LEFT JOIN Orders O ON C.customerID = O.customerID
WHERE O.orderID IS NULL;
-- WHERE O.orderID is NOT NULL;

-- SELECT *
-- from Orders

--5. ลูกค้าที่ชื่อว่า The Book Shop ชื้อหนังสือเล่มไหนบ้าง
SELECT DISTINCT P.productName
FROM Customer C
JOIN Orders O ON C.customerID = O.customerID
JOIN OrderDetail OD ON O.orderID = OD.orderID
JOIN Product P ON OD.productID = P.productID
WHERE C.customerName = 'The Book Shop';

--6. แสดงรายการสินค้าที่สั่งซื้อของใบสั่งซื้อที่ 10258
SELECT P.productName, OD.quantity
FROM OrderDetail OD
JOIN Product P ON OD.productID = P.productID
WHERE OD.orderID = '10258';

--7. จงแสดงการสั่งซื้อสินค้าระหว่างวันที่ 21-10-2022 ถึง 27-10-2022
SELECT *
FROM Orders
WHERE orderDate BETWEEN '2023-10-21' AND '2023-10-27';

--8. จงแสดงราคาต่อหน่วยมากที่สุด และราคาต่อหน่วยน้อยที่สุด
SELECT MAX(unitPrice) AS highestPrice, MIN(unitPrice) AS lowestPrice
FROM Product;

--9. จงแสดงจำนวนของสินค้าที่มีอยู่ในคลังสินค้าทั้งหมด
SELECT SUM(amount) AS totalStock
FROM Product;

--10. จงหาผลรวมของจำนวนสินค้าที่สั่งซื้อ
SELECT SUM(quantity) AS totalOrderedItems
FROM OrderDetail;