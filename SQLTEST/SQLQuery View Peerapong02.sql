--1.จงสร้างวิว เพื่อแสดงสินค้าที่มีการซื้อเยอะที่สุด โดยเรียงลำดับจากมากไปน้อย
Create View Order_sale 
--(ProID,PName,Dept,Gender)
AS
select TOP 10 p.productID,p.productName, SUM(od.quantity) AS total_quantity  From OrderDetail od
inner join Product p ON od.productID = p.productID
GROUP BY p.productID, p.productName
ORDER BY total_quantity DESC

select *from Order_sale



--2.2.จงสร้างวิว ตรวจสอบคลังสินค้า โดยมีเงื่อนไข ดังนี้
--เมื่อสินค้า มีจำนวน  <=200กำหนดเป็นLow inventory
--เมื่อสินค้า มีจำนวน  201-300กำหนดเป็นMedium items
--นอกเหนือจากเงื่อนไขข้างต้นกำหนดเป็น product has a lot

Create View Check_Product
 (productName,amount,result)
 AS
 select productName,amount,
 case
 when amount <= 200 Then 'Low inventory'
 when amount between 201 and 300 Then 'Medium items'
 Else 'product has a lot'
 END
 from Product

 select *from Check_Product