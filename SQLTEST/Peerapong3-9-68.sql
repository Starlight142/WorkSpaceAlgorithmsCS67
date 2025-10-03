--1. จงแสดงข้อมูลสินค้าที่มีจำนวนคงเหลือ น้อยกว่า 200 เล่ม
SELECT * 
FROM Product
WHERE amount < 200
--2. แสดงรายการสั่งซื้อที่มียอดของการสั่งซื้อมากกว่า 10,000
SELECT  *
FROM Orders 
WHERE totalAmount > 10000
--3. แสดงข้อมูลสินค้าที่อยู่ในประเภทสินค้า คอมพิวเตอร์
SELECT *
FROM Product
WHERE categoryID in (02)
--4. แสดงข้อมูลลูกค้าที่ไม่มีการสั่งซื้อสินค้า
SELECT *
FROM Customer
WHERE customerID NOT IN (
    SELECT customerID FROM Orders
);

--5. ลูกค้าที่ชื่อว่า The Book Shop ชื้อหนังสือเล่มไหนบ้าง
SELECT (SELECT customerName 
     FROM Customer 
     WHERE customerName = 'The Book Shop')  AS customerName,
    productName
FROM Product
WHERE productID IN (
    SELECT productID
    FROM OrderDetail
    WHERE orderID IN (
        SELECT orderID
        FROM Orders
        WHERE customerID = (
            SELECT customerID
            FROM Customer
            WHERE customerName = 'The Book Shop' 
				--SELECT customerName 
				--FROM Customer 
				-- WHERE customerName = 'The Book Shop')as customerName,
    --productName
        )
    )
);

--6. แสดงรายการสินค้าที่สั่งซื้อของใบสั่งซื้อที่ 10258
SELECT *
FROM Product
WHERE productID in (SELECT productID FROM OrderDetail WHERE orderID  = '10258')
--7. จงแสดงการสั่งซื้อสินค้าระหว่างวันที่ 21-10-2022 ถึง 27-10-2022
SELECT *
FROM Orders
WHERE orderDate between '2023-10-21' and '2023-10-21'
--8. จงแสดงราคาต่อหน่วยมากที่สุด และราคาต่อหน่วยน้อยที่สุด
SELECT MIN(unitPrice) AS MINUnitPrice, MAX(unitPrice) AS MaxUnitPrice
 FROM Product
--9. จงแสดงจำนวนของสินค้าที่มีอยู่ในคลังสินค้าทั้งหมด
SELECT SUM(amount) AS Amount
 FROM Product
--10. จงหาผลรวมของจำนวนสินค้าที่สั่งซื้อ
SELECT SUM(quantity) AS quantity
 FROM OrderDetail