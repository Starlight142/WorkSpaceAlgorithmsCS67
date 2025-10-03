-- แสดงรายการสินค้าที่สั่งซื้อในใบสั่งซื้อที่มีการชำระเงินเมื่อวันที่ 23-10-23
SELECT P.productName
FROM Payment Pay
    JOIN Orders O ON Pay.orderID = O.orderID
    JOIN OrderDetail OD ON O.orderID = OD.orderID
    JOIN Product P ON OD.productID = P.productID
WHERE Pay.PaymentDate = '23-10-23';


-- จงสร้างผู้ใช้ในระบบเพิ่ม โดยกำหนดชื่อเป็น test006, test007 และกำหนด password 1234

-- จงกำหนดสิทธิ์ให้ test006 สามารถ เรียกดู เพิ่มข้อมูล และกำหนด test006 สามารถกำหนดสิทธิ์ต่อได้ในตารางของลูกค้า
Grant Select, INSERT, update
on dbo.Costomer to Test006 with grant option

-- จงกำหนดให้ test006 มีสิทธิ์เรียกดูข้อมูลในตารางของลูกค้าได้
GRANT SELECT 
ON dbo.Costomer TO test006


-- จงแสดงจำนวนข้อมูลสินค้าที่มีการสั่งซื้อมากกว่า 8 จำนวน
SELECT p.ProductName , MAX(od.Quantity) AS TotalOrdered
FROM OrderDetail OD
    JOIN Product P on od.productID = p.productID
WHERE quantity > 8
GROUP BY p.ProductName

-- จงหาจำนวนของสินค้าที่มีอยู่ในคลังสินค้าทั้งหมด โดยเรียงจากมากไปหาน้อย
SELECT pd.productName, pd.amount AS MaxtoMinQuantity
FROM Product PD
ORDER BY pd.amount DESC;

-- จงแสดงว่าพนักงานแต่ละคนมีการจัดการลูกค้ากี่คน
SELECT emps.empID, ctm.*
from Employee emps
    join Costomer ctm on emps.empID = ctm.empID
ORDER BY emps.empID

-- จงแสดงข้อมูลลูกค้าที่ไม่มีการสั่งซื้อสินค้า
SELECT *
FROM Costomer c
WHERE NOT EXISTS (
    SELECT 1
FROM Orders o
WHERE o.customerID = c.customerID
);

-- จงสร้างวิวเป็น viewProduct ของสินค้า เพื่อใช้ในการตรวจสอบจำนวนสินค้าคงเหลือในคลัง โดยมีเงื่อนไข ดังนี้
-- a.    เมื่อ จำนวนสินค้ามีค่าน้อยกว่า 100 ให้กำหนดค่าเป็น Low
-- b.    เมื่อ จำนวนสินค้ามีค่าน้อยกว่าหรือเท่ากับ 250 ให้กำหนดค่าเป็น Medium
-- c.    เมื่อ จำนวนสินค้ามีค่ามากกว่าหรือเท่ากับ 251 ให้กำหนดค่าเป็น High
-- d.    นอกเหนือจากเงื่อนไขดังกล่าว กำหนนดเป็น Fail
Create view viewProduct
(
    productName,
    amount,
    StockLevel
)
AS
    select pd.productName, pd.amount,
        CASE
        WHEN amount < 100 THEN 'Low'
        WHEN amount <= 250 THEN 'Medium'
        WHEN amount >= 251 then 'High'
        ELSE 'Fail'
    END AS StockLevel
    FROM Product pd

SELECT *
FROM viewProduct;


-- แสดงรายการสินค้าขายดีตามจำนวนชิ้น 3 อันดับแรก
SELECT TOP 3
    pd.productName, max(od.quantity) BestSellerProduct
FROM OrderDetail od
    JOIN Product pd ON od.productID = pd.productID
GROUP BY pd.productName
ORDER BY BestSellerProduct DESC;