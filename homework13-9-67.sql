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