CREATE SCHEMA IF NOT EXISTS "dbo";

DROP TABLE IF EXISTS "OutPay_Header";
DROP SEQUENCE IF EXISTS "seq_outpay_header";
CREATE SEQUENCE "seq_outpay_header";
CREATE TABLE "OutPay_Header" (
    "Outpay_Header_ID" int NOT NULL,
    "Clntnum" char(8) NOT NULL,
    "Chdrnum" char(8) NOT NULL,
    "LetterType" char(12) NOT NULL,
    "PrintDate" datetime NOT NULL,
    "DataID" char(6),
    "ClntName" nvarchar(80),
    "ClntAddress" nvarchar(80),
    "RegDate" datetime,
    "BenPercent" decimal(6, 2),
    "Role1" char(2),
    "Role2" char(2),
    "CownNum" char(8),
    "CownName" nvarchar(80),
    "Notice01" nvarchar(80),
    "Notice02" nvarchar(80),
    "Notice03" nvarchar(80),
    "Notice04" nvarchar(80),
    "Notice05" nvarchar(80),
    "Notice06" nvarchar(80),
    "Claim_ID" char(9),
    "TP2ProcessDate" datetime
);

DROP TABLE IF EXISTS "dbo"."SurValues";;
DROP SEQUENCE IF EXISTS "dbo"."seq_survalues";
CREATE SEQUENCE "dbo"."seq_survalues";
CREATE TABLE "dbo"."SurValues" (
    "ID" int NOT NULL,
    "Chdrnum" char(8) NOT NULL,
    "Survalue" decimal(15, 2) NOT NULL,
    "Company" char(1) NOT NULL,
    "Currency" char(3),
    "ValidDate" char(10)
);

DROP TABLE IF EXISTS "dbo"."Policy";
DROP SEQUENCE IF EXISTS "dbo"."seq_policy";
CREATE SEQUENCE "dbo"."seq_policy";
CREATE TABLE "dbo"."Policy" (
    "ID" int NOT NULL,
    "Chdrnum" char(8) NOT NULL,
    "Cownnum" char(8) NOT NULL,
    "OwnerName" nvarchar(100),
    "LifcNum" char(8),
    "LifcName" nvarchar(100),
    "Aracde" char(3),
    "Agntnum" char(5),
    "MailAddress" nvarchar(100)
);
