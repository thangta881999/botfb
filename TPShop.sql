-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: dbminishop
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CHITIETHOADON`
--
drop table if exists `danhmucsanpham`;
DROP TABLE IF EXISTS `chitietsanpham`;
DROP TABLE IF EXISTS `CHITIETHOADON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CHITIETHOADON` (
  `mahoadon` int(11) NOT NULL,
  `machitietsanpham` int(11) NOT NULL,
  `soluong` int(11) DEFAULT NULL,
  `giatien` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`mahoadon`,`machitietsanpham`),
  KEY `FK_CHITIETHOADON_CHITIETSANPHAM` (`machitietsanpham`),
  CONSTRAINT `FK_CHITIETHOADON_CHITIETSANPHAM` FOREIGN KEY (`machitietsanpham`) REFERENCES `chitietsanpham` (`machitietsanpham`),
  CONSTRAINT `FK_CHITIETHOADON_HOADON` FOREIGN KEY (`mahoadon`) REFERENCES `hoadon` (`mahoadon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHITIETHOADON`
--

LOCK TABLES `CHITIETHOADON` WRITE;
/*!40000 ALTER TABLE `CHITIETHOADON` DISABLE KEYS */;
/*!40000 ALTER TABLE `CHITIETHOADON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CHITIETKHUYENMAI`
--

DROP TABLE IF EXISTS `CHITIETKHUYENMAI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CHITIETKHUYENMAI` (
  `makhuyenmai` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  PRIMARY KEY (`makhuyenmai`,`masanpham`),
  KEY `FK_CHITIETKHUYENMAI_SANPHAM` (`masanpham`),
  CONSTRAINT `FK_CHITIETKHUYENMAI_KHUYENMAI` FOREIGN KEY (`makhuyenmai`) REFERENCES `khuyenmai` (`makhuyenmai`),
  CONSTRAINT `FK_CHITIETKHUYENMAI_SANPHAM` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHITIETKHUYENMAI`
--

LOCK TABLES `CHITIETKHUYENMAI` WRITE;
/*!40000 ALTER TABLE `CHITIETKHUYENMAI` DISABLE KEYS */;
/*!40000 ALTER TABLE `CHITIETKHUYENMAI` ENABLE KEYS */;
UNLOCK TABLES;


-- Table structure for table `CHITIETSANPHAM`
--

DROP TABLE IF EXISTS `CHITIETSANPHAM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CHITIETSANPHAM` (
  `machitietsanpham` int(11) NOT NULL AUTO_INCREMENT,
  `masanpham` int(11) DEFAULT NULL,
  `masize` int(11) DEFAULT NULL,
  `mamau` int(11) DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `ngaynhap` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`machitietsanpham`),
  KEY `FK_CHITIET_SANPHAM` (`masanpham`),
  KEY `FK_CHITIET_SIZE` (`masize`),
  KEY `FK_CHITIET_MAUSANPHAM` (`mamau`),
  CONSTRAINT `FK_CHITIET_MAUSANPHAM` FOREIGN KEY (`mamau`) REFERENCES `mausanpham` (`mamau`),
  CONSTRAINT `FK_CHITIET_SANPHAM` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`),
  CONSTRAINT `FK_CHITIET_SIZE` FOREIGN KEY (`masize`) REFERENCES `sizesanpham` (`masize`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHITIETSANPHAM`
--

LOCK TABLES `CHITIETSANPHAM` WRITE;
/*!40000 ALTER TABLE `CHITIETSANPHAM` DISABLE KEYS */;
INSERT INTO `CHITIETSANPHAM` VALUES (23,1,1,1,15,'11/05/2020'),(24,1,2,2,16,'11/05/2020'),(25,1,3,3,8,'11/05/2020'),(26,2,1,1,30,'11/05/2020'),(27,2,1,2,20,'11/05/2020'),(28,2,2,2,10,'11/05/2020'),(29,3,1,1,5,'11/05/2020'),(30,3,1,2,3,'11/05/2020'),(33,4,1,1,4,'11/05/2020');
INSERT INTO CHITIETSANPHAM VALUES (34,5,1,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (35,5,2,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (36,5,3,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (37,12,4,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (38,12,5,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (39,12,6,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (40,12,1,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (41,12,2,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (42,6,3,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (43,12,4,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (44,6,5,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (45,12,6,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (46,6,1,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (47,12,2,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (48,12,3,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (49,6,4,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (50,12,5,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (51,12,6,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (52,13,1,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (53,13,2,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (54,8,3,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (55,13,4,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (56,8,5,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (57,13,6,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (58,13,1,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (59,7,2,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (60,13,3,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (61,13,4,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (62,8,5,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (63,13,6,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (64,14,1,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (65,14,2,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (66,14,3,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (67,14,1,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (68,14,2,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (69,14,3,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (70,10,3,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (71,10,4,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (72,10,5,2,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (73,10,3,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (74,10,4,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (75,10,5,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (76,10,3,4,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (77,10,4,4,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (78,10,5,4,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (79,9,1,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (80,9,2,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (81,9,3,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (82,9,1,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (83,9,2,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (84,9,3,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (85,9,1,4,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (86,9,2,4,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (87,9,3,4,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (88,14,2,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (89,14,3,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (90,14,4,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (91,14,2,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (92,14,3,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (93,14,4,6,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (94,14,2,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (95,14,3,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (96,14,4,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (97,15,4,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (98,15,5,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (99,15,6,1,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (100,15,4,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (101,15,5,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (102,15,6,3,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (103,15,4,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (104,15,5,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (105,15,6,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (106,16,2,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (107,16,3,5,15,'16/07/2020');
INSERT INTO CHITIETSANPHAM VALUES (108,16,4,3,15,'16/07/2020');


/*!40000 ALTER TABLE `CHITIETSANPHAM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
insert into role(code,name) values('ADMIN','Quản trị');
insert into role(code,name) values('USER','Người dùng');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

-- user_role
DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `userid` int(5) ,
  `roleid` int(5) 

  
);
/*!40101 SET character_set_client = @saved_cs_client */;
LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(3,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  -- `diachi` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  -- `gioitinh` bit(1) DEFAULT NULL,
  -- `cmnd` varchar(14) COLLATE utf8_unicode_ci DEFAULT NULL,
  -- `machucvu` int(11) DEFAULT NULL,
   `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(1) default 1,
  PRIMARY KEY (`id`)
 
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
insert into user(username,password,fullname,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','Tấn Phát',1);
insert into user(username,password,fullname,status)
values('user','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyễn văn A',1);
insert into user(username,password,fullname,status)
values('nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyễn văn B',1);
insert into user(username,password)
values('nguyenvanC','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
--
-- Table structure for table `DANHMUCSANPHAM`
--

DROP TABLE IF EXISTS `DANHMUCSANPHAM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `DANHMUCSANPHAM` (
  `madanhmuc` int(11) NOT NULL AUTO_INCREMENT,
  `tendanhmuc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_madanhmuc` int(11) default 0,
  
  PRIMARY KEY (`madanhmuc`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DANHMUCSANPHAM`
--

LOCK TABLES `DANHMUCSANPHAM` WRITE;
/*!40000 ALTER TABLE `DANHMUCSANPHAM` DISABLE KEYS */;
INSERT INTO `DANHMUCSANPHAM`(`madanhmuc`,`tendanhmuc`) VALUES (1,'Áo Sơ Mi'),(2,'Áo Thun'),(3,'Quần Short'),(4,'Áo khoác'),(5,'Túi đeo'),(6,'Quần Jean'),(7,'Quần kaki');
/*!40000 ALTER TABLE `DANHMUCSANPHAM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HOADON`
--

DROP TABLE IF EXISTS `HOADON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `HOADON` (
  `mahoadon` int(11) NOT NULL AUTO_INCREMENT,
  `tenkhachhang` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci ,
  `sodt` char(12) COLLATE utf8_unicode_ci,
  `diachigiaohang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci ,
  `tinhtrang` bit(1) DEFAULT b'0',
  `hinhthucgiaohang` varchar(50) DEFAULT NULL,
  `thanhtoan` bit(1) default b'0', 
  `tongtien` varchar(20) default 0,
  `ghichu` text DEFAULT NULL,
  `createdDate` DATETIME DEFAULT NULL,
  `updatedDate` DATETIME DEFAULT NULL,
  
  PRIMARY KEY (`mahoadon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HOADON`
--

LOCK TABLES `HOADON` WRITE;




/*!40000 ALTER TABLE `HOADON` DISABLE KEYS */;
/*!40000 ALTER TABLE `HOADON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KHUYENMAI`
--

DROP TABLE IF EXISTS `KHUYENMAI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `KHUYENMAI` (
  `makhuyenmai` int(11) NOT NULL AUTO_INCREMENT,
  `tenkhuyenmai` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `thoigianbatdau` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `thoigianketthuc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mota` text COLLATE utf8_unicode_ci,
  `hinhkhuyenmai` text COLLATE utf8_unicode_ci,
  `giamgia` int(11) DEFAULT NULL,
  PRIMARY KEY (`makhuyenmai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KHUYENMAI`
--

LOCK TABLES `KHUYENMAI` WRITE;
/*!40000 ALTER TABLE `KHUYENMAI` DISABLE KEYS */;
/*!40000 ALTER TABLE `KHUYENMAI` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAUSANPHAM`
--

DROP TABLE IF EXISTS `MAUSANPHAM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MAUSANPHAM` (
  `mamau` int(11) NOT NULL AUTO_INCREMENT,
  `tenmau` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`mamau`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAUSANPHAM`
--

LOCK TABLES `MAUSANPHAM` WRITE;
/*!40000 ALTER TABLE `MAUSANPHAM` DISABLE KEYS */;
INSERT INTO `MAUSANPHAM` VALUES (1,'Xanh Dương'),(2,'Xám'),(3,'Trắng'),(4,'Hồng'),(5,'Xanh Lá'),(6,'Xanh Ngọc');
/*!40000 ALTER TABLE `MAUSANPHAM` ENABLE KEYS */;
UNLOCK TABLES;

--

--


-- Table structure for table `SANPHAM`
--

DROP TABLE IF EXISTS `SANPHAM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SANPHAM` (
  `masanpham` int(11) NOT NULL AUTO_INCREMENT,
  `madanhmuc` int(11) DEFAULT 1,
  `tensanpham` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL ,
  `giatien` varchar(14) COLLATE utf8_unicode_ci  NOT NULL ,
  `mota` text COLLATE utf8_unicode_ci,
  `hinhsanpham` text COLLATE utf8_unicode_ci NOT NULL,
  `danhcho` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`masanpham`),
  KEY `FK_SANPHAM_DANHMUC` (`madanhmuc`),
  CONSTRAINT `FK_SANPHAM_DANHMUC` FOREIGN KEY (`madanhmuc`) REFERENCES `danhmucsanpham` (`madanhmuc`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SANPHAM`
--

LOCK TABLES `SANPHAM` WRITE;
/*!40000 ALTER TABLE `SANPHAM` DISABLE KEYS */;
 INSERT INTO `SANPHAM` VALUES (1,1,'Sơ Mi Nam Y2010 TD A04','185.000','Chất liệu: Vải Ford <br>Thành phần: 60% cotton 40% poly <br>- Mềm mại, mát mẻ<br>- Thoải mái và bền','1.jpg','Nam'),
 (2,1,'Sơ Mi Nam S234 TN 23','199.000','Chất liệu: Kate<br>Thành phần: 12% modal và 88% superfine<br>- Ít nhăn và dễ ủi<br>- Nhanh khô và thoáng mát<br>HDSD:<br>- Áo sơ mi màu phơi trong bóng râm để tránh bạc màu phần vai áo<br>- Áo sơ mi trắng có thể phơi ngoài nắng để áo trắng sáng hơn ( không phơi quá lâu) ','2.jpg','Nam'),
(3,1,'Sơ Mi Nam Y2010 TN J01','185.000',
' Chất liệu:  Ford<br>Thành phần:  60% cotton 40% poly<br>- Vải ford mặc mát<br>- Thấm hút mồ hôi rất tốt<br>HDSD:<br>- Giặc vò nhẹ tay <br>- Áo sơ mi màu phơi trong bóng râm để tránh bạc màu phần vai áo<br>- Áo sơ mi trắng có thể phơi ngoài nắng để áo trắng sáng hơn ( không phơi quá lâu ) ','3.jpg','Nam'),
(4,1,'Sơ Mi Nam Y2010 BD-A95','185.000',
'Chất liệu: Kate<br>Thành phần: Cotton và Polyester.<br>- Vải ít nhăn, mặt vải phẳng và mịn<br>- Thấm hút ầm tốt<br>- Dễ dàng giặt ủi.<br>HDSD:<br>- Áo sơ mi màu phơi trong bóng râm để tránh bạc màu phần vai áo<br>- Áo sơ mi trắng có thể phơi ngoài nắng để áo trắng sáng hơn ( không phơi quá lâu )','4.jpg','Nam'),
(5,1,'Sơ Mi Nam Y2010 TN E02','185.000',
' Chất liệu: Kate <br>Thành phần: Cotton và Polyester.<br>- Vải ít nhăn, mặt vải phẳng và mịn<br>- Thấm hút ầm tốt<br>- Dễ dàng giặt ủi.<br>HDSD:<br>- Áo sơ mi màu phơi trong bóng râm để tránh bạc màu phần vai áo<br>- Áo sơ mi trắng có thể phơi ngoài nắng để áo trắng sáng hơn ( không phơi quá lâu )','5.jpg','Nam'),
(6,1,'Sơ Mi Nam Y2010 Caro TD A04','225.000',
'  Chất liệu: Kate <br>Thành phần: Cotton và Polyester.<br>- Vải ít nhăn, mặt vải phẳng và mịn<br>- Thấm hút ầm tốt<br>- Dễ dàng giặt ủi.<br>HDSD:<br>- Áo sơ mi màu phơi trong bóng râm để tránh bạc màu phần vai áo<br>- Áo sơ mi trắng có thể phơi ngoài nắng để áo trắng sáng hơn ( không phơi quá lâu )','6.jpg','Nam'),
(7,1,'Sơ Mi Nam Y2010 BD-A60','350.000',
' Chất liệu: YW 3782 -Trắng 17- nâu 3<br>Thành phần : 65% Polyester 35% Cotton<br>- Độ bền vải cao<br>- Tính hút ẩm cao<br>- Thoáng mát','7.jpg','Nam'),
(8,1,'Sơ Mi Nam Y2010 BD-A48','450.000',
' Chất liệu:  Ford<br>Thành phần:  TC 65% Polyester + 35% Cotton<br>- Vải ford mặc mát<br>- Thấm hút mồ hôi rất tốt<br>HDSD:<br>- Giặc vò nhẹ tay <br>- Áo sơ mi màu phơi trong bóng râm để tránh bạc màu phần vai áo<br>- Áo sơ mi trắng có thể phơi ngoài nắng để áo trắng sáng hơn ( không phơi quá lâu )','8.jpg','Nam'),
(9,1,'Sơ Mi Nam Y2010 Caro TD C02','285.000',
'Chất liệu: Caro<br>Thành phần: 100% Cotton<br>- Mềm mại, mát mẻ, <br>- Thoải mái và bền<br>HDSD:<br>- Áo sơ mi màu phơi trong bóng râm để tránh bạc màu phần vai áo<br>- Áo sơ mi trắng có thể phơi ngoài nắng để áo trắng sáng hơn ( không phơi quá lâu )','9.jpg','Nam'),
(10,2,'Áo Thun Nam Y2010 3 Lỗ B01','120.000',
'Chất liệu: Cotton 2 chiều<br>Thành phần: 100% Cotton<br>- Co dãn 2 chiều<br>- Thấm hút mồ hôi tốt mang lại cảm giác thoáng mát<br>HDSD:<br>- Giặt tay để sản phẩm giữ được độ bền cao<br>- Ủi sản phẩm bằng bàn ủi hơi nước hoặc ủi khi sản phẩm còn ẩm để dễ dàng làm phẳng','10.jpg','Nam'),
(11,2,'Áo Thun Nam Y2010 BD-B05','185.000',
'Chất liệu: Cotton 2 chiều<br>Thành phần: 100% Cotton<br>- Co dãn 2 chiều<br>- Thấm hút mồ hôi tốt mang lại cảm giác thoáng mát<br>HDSD:<br>- Giặt tay để sản phẩm giữ được độ bền cao<br>- Ủi sản phẩm bằng bàn ủi hơi nước hoặc ủi khi sản phẩm còn ẩm để dễ dàng làm phẳng','11.jpg','Nam'),
(12,3,'Quần Short Thun Ma Bư AB03','225.000',
'','12.jpg','Nam'),
(13,4,'Áo khoác kaki 2N39','185.000',
'Chất liệu: kaki<br>Thành phần: 98%cotton 2%spandex<br>Cách nhiệt tốt, độ bền màu cao.<br>Co giãn, hút ẩm tốt và thấm hút mồ hôi.<br>HDSD:<br>- Giặt ở chế độ bình thường, với đồ có màu tương tự.<br>- Không được dùng hóa chất tẩy.<br>- Hạn chế sử dụng máy sấy, ủi ở nhiệt độ thích hợp.','13.jpg','Nam'),
(14,5,'Túi Đeo Y2010 B08','185.000',
'BALO ĐEO CHÉO B08<br>- Kiểu dáng thiết kế thời trang và tinh tế.<br>- Logo YaMe đính nổi sang trọng<br>- Nhiều ngăn tiện dụng: <br>  + Ngăn chính trước.<br>  + Ngăn chính sau rộng, có ngăn chứa ipad chống sốc, ngăn lưới tiện dụng.<br>- Kích thước: 30x19x7,5 cm<br>  + Ngăn phụ trước tinh tế và thời trang.<br>  + Ngăn phụ sau có đệm chống sốc tuyệt đối.<br>- Chất liệu: 600D Twill PVC+ Lót 210D Lưới xám<br>- Đệm lưng và quai đeo bằng Air mesh +EVA 5mm+ PE foam 8mm<br>- Dây kéo YKK #5 2 chiều mượt bền','14.jpg','Nam'),
(15,6,'Quần Jean Slimfit Y2010 B21','185.000',
'Chất liệu: Jean Cotton<br>Thành phần: 86% cotton 14%poly<br>- Độ bền cao<br>- Vải ít nhắn','15.jpg','Nam'),
(16,7,'Quần Kaki Y2010 C01','350.000',
' Chất liệu: Kaki Thun<br>Thành phần: 97% cotton 3% spandex<br>- Êm ái và thấm hút cao<br>- Co dãn tốt<br>HDSD:<br>_ Không dùng chất tẩy trắng<br>_ Ủi ở nhiệt độ tối đa 110 độ C<br>_ Giặt chung với sản phẩm cùng màu','16.jpg','Nam');

INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('22', '5', 'Túi Đeo Y2010 BD-A20', '299.000', 'TÚI ĐEO DOUPLE - 2IN1<br>Túi thiết kế mới lạ với 2 phần có thể tháo rời.<br>Bộ túi bao gồm: <br>- Túi lớn 19 x 17 x 5 cm<br>- Túi nhỏ 11 x 17 x 5 cm<br>- Dây đai đeo bụng<br>- Dây đeo chéo<br>Chất liệu:<br>- Vải chính: 320D Nylon Blond<br>- Lót 210D+ PE foam 5mm<br>- Dây kéo YKK #5<br>- Khóa buckle &amp; Velcro 25mm<br>- Dây dù 3mm đen<br><br> ', '22.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('23', '5', 'Túi Đeo Y2010 B26', '249.000', 'MODERN BAG B26<br>Chất liệu:<br>- Vải chính: 1000D Cordura<br>- Lót 210D Đen <br>- Đệm và lưới Airmesh<br>- Dây kéo YKK #8<br>- Khóa buckle 38mm<br>- Logo nhựa chữ Y<br>- Khoen vuông 38mm<br>Thiết kế:<br>- Viền phản quang 25mm<br>- Nhiều ngăn tiện dụng, sức chứa lớn.<br>- Kiểu dáng trẻ trung <br>- Màu sắc đa dạng.', '23.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('24', '5', 'Túi Đeo Y2010 B25 BD-A74', '249.000', 'MODERN BELT BAG B25<br>Chất liệu: <br>- Vải chính: 1000D Cordura<br>- Lót 210D màu Cam<br>- Dây Kéo YKK #5<br>- Khóa buckle<br>- Logo nhựa chữ Y<br>- Khoen vuông 38mm<br>Thiết kế: <br>- Màu sắc đa dạng.<br>- Kiểu dáng trẻ trung<br>- Nhiều ngăn tiện dụng<br>- Nhỏ gọn và thời trang', '24.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('25', '3', 'Quần Short Kaki Y2010 BD-A63', '255.000', 'Chất liệu: Kaki<br>Thành phần: 100% Cotton<br>- Vải kaki có tính chất chung là bền, mát, co giãn', '25.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('26', '3', 'Quần Short Thun Y2010 C02', '350.000', 'Chất liệu: Vảy cá chéo<br>Thành phần: 95%cotton 5%spandex<br>Co dãn 4 chiều nên tạo được sự thoải mái khi mặc<br>Vải thấm hút mồ hôi tốt, thoáng khí', '26.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `hinhsanpham`, `danhcho`) VALUES ('27', '3', 'Quần Short Jean Y2010 B10', '295.000', '27.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('28', '2', 'Áo Thun Nam Y2010 BD-B17', '185.000', 'Chất liệu: Cotton 2 chiều<br>Thành phần: 100% Cotton<br>- Co dãn 2 chiều<br>- Thấm hút mồ hôi tốt mang lại cảm giác thoáng mát<br>HDSD:<br>- Giặt tay để sản phẩm giữ được độ bền cao<br>- Ủi sản phẩm bằng bàn ủi hơi nước hoặc ủi khi sản phẩm còn ẩm để dễ dàng làm phẳng', '28.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('29', '2', 'Áo Thun Nam Y2010 BD-B06', '185.000', '  Chất liệu: Cotton 2 chiều<br>Thành phần: 100% Cotton<br>- Co dãn 2 chiều<br>- Thấm hút mồ hôi tốt mang lại cảm giác thoáng mát<br>HDSD:<br>- Giặt tay để sản phẩm giữ được độ bền cao<br>- Ủi sản phẩm bằng bàn ủi hơi nước hoặc ủi khi sản phẩm còn ẩm để dễ dàng làm phẳng', '29.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('30', '6', 'Quần Jean Slimfit Y2010 B30', '425.000', 'Chất liệu: Jean Cotton<br>Thành phần: 100% cotton<br>- Độ bền cao<br>- Mặc rất thoải mái.<br>HDSD:<br>- Giặt qua lần đầu trước khi mặc. Công đoạn giặt lần đầu này sẽ làm giảm bớt bụi vải <br>- Hãy lộn mặt trái của sản phẩm để giặt. Hành động này sẽ giữ màu tốt hơn cho trang phục làm từ vải jeans mà bạn đang sở hữu', '30.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('31', '6', 'Quần Jean Slimfit Y2010 B11', '425.000', 'Chất liệu: Jean Cotton<br>Thành phần: 100% cotton<br>- Độ bền cao<br>- Mặc rất thoải mái.<br>HDSD:<br>- Giặt qua lần đầu trước khi mặc. Công đoạn giặt lần đầu này sẽ làm giảm bớt bụi vải <br>- Hãy lộn mặt trái của sản phẩm để giặt. Hành động này sẽ giữ màu tốt hơn cho trang phục làm từ vải jeans mà bạn đang sở hữu', '31.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('32', '6', 'Quần Jean Slimfit Y2010 B11', '425.000', 'Chất liệu: Jean Cotton<br>Thành phần: 100% cotton<br>- Độ bền cao<br>- Mặc rất thoải mái.<br>HDSD:<br>- Giặt qua lần đầu trước khi mặc. Công đoạn giặt lần đầu này sẽ làm giảm bớt bụi vải <br>- Hãy lộn mặt trái của sản phẩm để giặt. Hành động này sẽ giữ màu tốt hơn cho trang phục làm từ vải jeans mà bạn đang sở hữu', '32.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('33', '7', 'Quần Kaki Y2010 C01', '350.000', ' Chất liệu: Kaki Thun<br>Thành phần: 97% cotton 3% spandex<br>- Êm ái và thấm hút cao<br>- Co dãn tốt<br>HDSD:<br>_ Không dùng chất tẩy trắng<br>_ Ủi ở nhiệt độ tối đa 110 độ C<br>_ Giặt chung với sản phẩm cùng màu', '33.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('34', '7', 'Quần Kaki Y2010 C04', '350.000', 'Chất liệu: Kaki Thun<br>Thành phần: 97% cotton 3% spandex<br>- Êm ái và thấm hút cao<br>- Co dãn tốt<br>HDSD:<br>_ Không dùng chất tẩy trắng<br>_ Ủi ở nhiệt độ tối đa 110 độ C<br>_ Giặt chung với sản phẩm cùng màu', '34.jpg', 'Nam');
INSERT INTO `dbminishop`.`sanpham` (`masanpham`, `madanhmuc`, `tensanpham`, `giatien`, `mota`, `hinhsanpham`, `danhcho`) VALUES ('35', '7', 'Quần Kaki Y2010 F01', '350.000', 'Chất liệu : Kaki Thun<br>Thành phần : 97% cotton 3% spandex<br>- Vải kaki có tính chất chung là bền, mát<br>- Vải có pha thêm sợi spandex để tăng độ co giãn của vải đem lại cảm giác thoải mái cho người mặc', '35.jpg', 'Nam');



/*!40000 ALTER TABLE `SANPHAM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SIZESANPHAM`
--

DROP TABLE IF EXISTS `SIZESANPHAM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SIZESANPHAM` (
  `masize` int(11) NOT NULL AUTO_INCREMENT,
  `size` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`masize`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SIZESANPHAM`
--

LOCK TABLES `SIZESANPHAM` WRITE;
/*!40000 ALTER TABLE `SIZESANPHAM` DISABLE KEYS */;
INSERT INTO `SIZESANPHAM` VALUES (1,'S'),(2,'M'),(3,'L'),(4,'XL'),(5,'XXL'),(6,'XXXL');
/*!40000 ALTER TABLE `SIZESANPHAM` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-13 10:10:53
