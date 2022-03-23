# ĐỒ ÁN CHUYÊN ĐỀ CÔNG NGHỆ PHẦN MỀM - PTITHCM - ĐẶT LỆNH CHỨNG KHOÁN(ĐỀ SỐ 2)


__GIỚI THIỆU__


-) Là một bài tập cũng như đồ án cuối kì cho môn CDCNPM giúp các sinh viên
hiểu rõ hơn về ứng dụng cursor trong sql server trong việc truy vấn và xử lí
dữ liệu.
 
 
__CÁC PHẦN MỀM THƯ VIỆN DÙNG TRONG ĐỒ ÁN:__


-) Môi trường, lưu trữ và xử lí dữ liệu:
	
	+) Eclipse IDE for Enterprise Java and Web Developers(LATEST)
	+) Spring boot famework (ver 2.6.4)
	+) JDK 16
	+) Microsoft sql server 2019


-) Hiện thị trên website:
	
	+) HTML5
	+) CSS3
	+) Thymeleaf
	+) Bootstrap
	+) Jquery
	+) Datatable	

## HÌNH ẢNH DEMO ĐỒ ÁN
![Website đặt lệnh chứng khoán](https://github.com/DucNguyen3112/CDCNPM-PTIT/blob/master/readmeImg/Screenshot%20.png)

### HƯỚNG DẪN CÀI ĐẶT VÀ CHẠY
__I) CHUẨN BỊ DATABASE__

1. Tạo database [CHUNGKHOAN] trong MSSQL Server
```sql
CREATE DATABASE [CHUNGKHOAN]
```
2. Tạo các bảng và các  stored procuedure ở trong database
```sql
USE [CHUNGKHOAN]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/****** Tạo bảng 2 bảng [LENHDAT] và [LENHKHOP]******/
CREATE TABLE [dbo].[LENHDAT](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MACP] [nchar](7) NULL,
	[NGAYDAT] [datetime] NULL,
	[LOAIGD] [nchar](10) NULL,
	[LOAILENH] [nchar](10) NULL,
	[SOLUONG] [int] NULL,
	[GIADAT] [float] NULL,
	[TRANGTHAILENH] [nvarchar](30) NULL,
 CONSTRAINT [PK_LENHDAT] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LENHKHOP]    Script Date: 23/3/2022 19:52:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LENHKHOP](
	[IDKHOP] [int] IDENTITY(1,1) NOT NULL,
	[NGAYKHOP] [datetime] NULL,
	[SOLUONGKHOP] [int] NULL,
	[GIAKHOP] [float] NULL,
	[IDLENHDAT] [int] NULL,
 CONSTRAINT [PK_LENHKHOP] PRIMARY KEY CLUSTERED 
(
	[IDKHOP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[LENHKHOP]  WITH CHECK ADD  CONSTRAINT [FK_LENHKHOP_LENHDAT] FOREIGN KEY([IDLENHDAT])
REFERENCES [dbo].[LENHDAT] ([ID])
GO
ALTER TABLE [dbo].[LENHKHOP] CHECK CONSTRAINT [FK_LENHKHOP_LENHDAT]
GO
/****** Tạo PROCEDURE  [dbo].[CursorLoaiGD]     ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[CursorLoaiGD]
  @OutCrsr CURSOR VARYING OUTPUT, 
  @macp NVARCHAR( 10), @Ngay NVARCHAR( 10),  @LoaiGD CHAR 
AS
SET DATEFORMAT DMY 
IF (@LoaiGD='M') 
  SET @OutCrsr=CURSOR KEYSET FOR 
  SELECT ID, NGAYDAT, SOLUONG, GIADAT FROM LENHDAT 
  WHERE MACP=@macp 
     AND DAY(NGAYDAT)=DAY(@Ngay)AND MONTH(NGAYDAT)= MONTH(@Ngay) 	   AND YEAR(NGAYDAT)=YEAR(@Ngay)  
     AND LOAIGD=@LoaiGD AND SOLUONG >0  
    ORDER BY GIADAT DESC, NGAYDAT 
ELSE
  SET @OutCrsr=CURSOR KEYSET FOR 
  SELECT ID, NGAYDAT, SOLUONG, GIADAT FROM LENHDAT 
  WHERE MACP=@macp 
    AND DAY(NGAYDAT)=DAY(@Ngay)AND MONTH(NGAYDAT)= MONTH(@Ngay) 	AND YEAR(NGAYDAT)=YEAR(@Ngay)  
    AND LOAIGD=@LoaiGD AND SOLUONG >0  
    ORDER BY GIADAT, NGAYDAT 
OPEN @OutCrsr
GO

/****** Tạo  StoredProcedure [dbo].[SP_KHOPLENH_LO]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KHOPLENH_LO]
	 @inMaCP NVARCHAR(10)
	,@inNgay NVARCHAR(20)
	,@inLoaiGD CHAR
	,@inSoLuong INT
	,@inGiaDat FLOAT
AS
	SET DATEFORMAT DMY
	DECLARE @CursorVariable CURSOR 
			,@id INT
			,@ngayDat NVARCHAR(20)
			,@soLuong INT
			,@giaDat FLOAT
			,@soLuongKhop INT
			,@giaKhop FLOAT

	IF (@InLoaiGD='B')
		EXEC CursorLoaiGD  @CursorVariable OUTPUT, @inMaCP,@inNgay, 'M'
	ELSE 
		EXEC CursorLoaiGD  @CursorVariable OUTPUT, @inMaCP,@inNgay, 'B'
  
	FETCH NEXT FROM @CursorVariable  INTO @ID, @NgayDat , @SoLuong , @GiaDat 
	WHILE (@@FETCH_STATUS <> -1 AND @InSoLuong >0)
	BEGIN
	IF  (@inLoaiGD='B' )
	begin
		  IF  (@inGiaDat <= @GiaDat)
	      BEGIN
			IF (@inSoLuong >= @SoLuong)
			BEGIN
				SET @soluongkhop = @SoLuong
				SET @giakhop = @GiaDat
				SET @inSoLuong = @inSoLuong - @SoLuong
				UPDATE dbo.LenhDat
				SET SOLUONG = 0,
					TRANGTHAILENH = N'Khớp Hết'
				WHERE CURRENT OF @CursorVariable 
			END
			ELSE
			BEGIN
			   SET @soluongkhop = @inSoLuong
			   SET @giakhop = @GiaDat
			   UPDATE dbo.LenhDat  
			   SET SOLUONG = SOLUONG - @inSoLuong,
					TRANGTHAILENH = N'Khớp Lệnh Một Phần'
			   WHERE CURRENT OF @CursorVariable
			   SET @inSoLuong = 0
			END
		 INSERT INTO dbo.LenhKhop(NgayKhop,SoLuongKhop,GiaKhop,idLenhDat)
		 VALUES (GETDATE(),@soluongkhop,@giakhop, @ID )
		 END 
		 ELSE 
			GOTO THOAT
	end
	ELSE 
	begin
		   IF( @inGiaDat >= @GiaDat )
		   BEGIN
			  IF( @inSoLuong >= @SoLuong)
			  BEGIN
				  SET @soluongkhop = @SoLuong
				  SET @giakhop = @GiaDat
				  SET @inSoLuong = @inSoLuong - @SoLuong

				  UPDATE dbo.LenhDat
				  SET SOLUONG = 0,
					TRANGTHAILENH = N'Khớp Hết'
				  WHERE CURRENT OF @CursorVariable
			  END
			  ELSE
			  BEGIN
				 SET @soluongkhop = @inSoLuong
				 SET @giakhop = @GiaDat
				 UPDATE dbo.LenhDat
				 SET SOLUONG = SOLUONG - @inSoLuong,
					TRANGTHAILENH = N'Khớp Lệnh Một Phần'
				 WHERE CURRENT OF @CursorVariable
				 SET @inSoLuong = 0
			  END
			  INSERT INTO dbo.LenhKhop(NgayKhop,SoLuongKhop,GiaKhop,idLenhDat)
			  VALUES (GETDATE(),@soluongkhop,@giakhop, @ID )
		   END
		   ELSE
			 GOTO THOAT
	end
	   FETCH NEXT FROM @CursorVariable INTO @ID,  @NgayDat , @SoLuong , @GiaDat
	END
THOAT:
	IF(@inSoLuong >0)
		BEGIN
			INSERT INTO dbo.LenhDat(MACP, NgayDat, LOAIGD, LOAILENH, SOLUONG, GIADAT, TRANGTHAILENH)
			VALUES(@inMaCP, GETDATE(), @inLoaiGD, N'LO', @inSoLuong, @inGiaDat, N'Chờ Khớp')
		END

	ELSE
		BEGIN
			INSERT INTO dbo.LenhDat(MACP, NgayDat, LOAIGD, LOAILENH, SOLUONG, GIADAT, TRANGTHAILENH)
			VALUES(@inMaCP, GETDATE(), @inLoaiGD, N'LO', @inSoLuong, @inGiaDat, N'Khớp hết')
		END
		
	CLOSE @CursorVariable 
	DEALLOCATE @CursorVariable
GO
```
> Tất cả các lệnh trên đã có trong file Scripts.sql


__II) HƯỚNG DẪN IMPORT PROJECT VÀO ECLIPSE__

1. Click file >Import
 
 
 ![](https://github.com/DucNguyen3112/CDCNPM-PTIT/blob/master/readmeImg/import1.png)


 2. trong cửa sổ import chọn Existing Maven projects
 
 
 ![](https://github.com/DucNguyen3112/CDCNPM-PTIT/blob/master/readmeImg/import2.png)


3. Click Browse... chọn thư mục mình lưu project


![](https://github.com/DucNguyen3112/CDCNPM-PTIT/blob/master/readmeImg/import3.png)
 
 
 click finish

III) Cấu hình để kết nối CSDL trong Eclipse
1. Mở file config theo đường dẫn "/src/main/resources/application.properties"
và chỉnh sửa.

![Đường dẫn tới application.properties](https://github.com/DucNguyen3112/CDCNPM-PTIT/blob/master/readmeImg/urlconfig.png)

>spring.datasource.url= jdbc:sqlserver://localhost:[SERVER PORT(default:1433)];databaseName=[TÊN DATABASE(CHUNGKHOAN)]  
spring.datasource.username= [Tên đăng nhập]  
spring.datasource.password= [Mật khẩu]  
spring.jpa.hibernate.ddl-auto=none  
logging.level.org.hibernate.SQL=DEBUG  
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE  
spring.main.allow-circular-references: true  
server.port=[WEBSITE PORT(default:8080)]  


![File application.properties](https://github.com/DucNguyen3112/CDCNPM-PTIT/blob/master/readmeImg/application_properties.png)



Mọi thao tác chuẩn bị đã hoàn tất.

__IV) CHẠY PROJECT__

Thao tác hướng dẫn chạy project

Tìm đến file "CdcnpmApplication.java" theo đường dẫn src/main/java > com.datlenhchungkhoan.cdcnpm 
click chuột phải chọn Run as > Java Application
	
![Hướng dẫn chạy project](https://github.com/DucNguyen3112/CDCNPM-PTIT/blob/master/readmeImg/run.png)

#### KẾT
 
 
 Đây chỉ là một demo nhỏ mang tính chất tham khảo có thể sẽ có một vài sai sót. Các bạn nên tìm hiểu kĩ trước khi sử dụng.
 
 Chúc các bạn có kết quả thi tốt.
 
                                                       # THE END




