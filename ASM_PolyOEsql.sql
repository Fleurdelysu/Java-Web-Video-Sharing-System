CREATE DATABASE ASM_PolyOE;
GO

USE ASM_PolyOE;
GO

-------------------------------------------------
-- USERS
-------------------------------------------------

CREATE TABLE Users(
    Id NVARCHAR(20) PRIMARY KEY,
    Password NVARCHAR(100) NOT NULL,
    Fullname NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    Admin BIT NOT NULL DEFAULT 0
);

-------------------------------------------------
-- VIDEOS
-------------------------------------------------

CREATE TABLE Videos(
    Id NVARCHAR(20) PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL,
    Poster NVARCHAR(255),
    Views INT NOT NULL DEFAULT 0,
    Description NVARCHAR(MAX),
    Active BIT NOT NULL DEFAULT 1
);

-------------------------------------------------
-- FAVORITES
-------------------------------------------------

CREATE TABLE Favorites(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId NVARCHAR(20) NOT NULL,
    VideoId NVARCHAR(20) NOT NULL,
    LikeDate DATETIME DEFAULT GETDATE(),

    CONSTRAINT FK_Favorite_User
        FOREIGN KEY(UserId)
        REFERENCES Users(Id),

    CONSTRAINT FK_Favorite_Video
        FOREIGN KEY(VideoId)
        REFERENCES Videos(Id)
);

-------------------------------------------------
-- SHARES
-------------------------------------------------

CREATE TABLE Shares(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId NVARCHAR(20) NOT NULL,
    VideoId NVARCHAR(20) NOT NULL,
    Emails NVARCHAR(255) NOT NULL,
    ShareDate DATETIME DEFAULT GETDATE(),

    CONSTRAINT FK_Share_User
        FOREIGN KEY(UserId)
        REFERENCES Users(Id),

    CONSTRAINT FK_Share_Video
        FOREIGN KEY(VideoId)
        REFERENCES Videos(Id)
);
-------------------------------------------------
-- USERS
-------------------------------------------------

INSERT INTO Users VALUES
('admin','123','Administrator','admin@poly.edu.vn',1),
('khanh','123','Vu Ngoc Khanh','khanh@gmail.com',0),
('user01','123','Nguyen Van A','a@gmail.com',0);

-------------------------------------------------
-- VIDEOS
-------------------------------------------------

INSERT INTO Videos
(Id,Title,Poster,Views,Description,Active)
VALUES
('V001',
N'Hài Trấn Thành',
'v001.jpg',
150,
N'Video hài Trấn Thành',
1),

('V002',
N'Hài Trường Giang',
'v002.jpg',
220,
N'Video hài Trường Giang',
1),

('V003',
N'Hài Hoài Linh',
'v003.jpg',
350,
N'Video hài Hoài Linh',
1),

('V004',
N'Hài Xuân Bắc',
'v004.jpg',
180,
N'Video hài Xuân Bắc',
1),

('V005',
N'Hài Tự Long',
'v005.jpg',
90,
N'Video hài Tự Long',
1),

('V006',
N'Hài Vân Sơn',
'v006.jpg',
500,
N'Video hài Vân Sơn',
1);
select * from Videos