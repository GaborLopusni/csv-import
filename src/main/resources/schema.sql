CREATE SCHEMA IF NOT EXISTS "dbo";
DROP TABLE IF EXISTS "OutPay_Header";
DROP SEQUENCE IF EXISTS "seq_outpay_header";
CREATE SEQUENCE "seq_outpay_header";
CREATE TABLE "OutPay_Header" (
    "Outpay_Header_ID" int NOT NULL,
    "Clntnum" char(8) NOT NULL,
    "Chdrnum" char(8) NOT NULL,
    "LetterType" char(12) NOT NULL,
    "PrintDate" timestamp NOT NULL,
    "DataID" char(6),
    "ClntName" varchar(80),
    "ClntAddress" varchar(80),
    "RegDate" timestamp,
    "BenPercent" decimal(6, 2),
    "Role1" char(2),
    "Role2" char(2),
    "CownNum" char(8),
    "CownName" varchar(80),
    "Notice01" varchar(80),
    "Notice02" varchar(80),
    "Notice03" varchar(80),
    "Notice04" varchar(80),
    "Notice05" varchar(80),
    "Notice06" varchar(80),
    "Claim_ID" char(9),
    "TP2ProcessDate" timestamp,
    PRIMARY KEY ("Outpay_Header_ID")
);

DROP TABLE IF EXISTS "dbo"."SurValues";
DROP SEQUENCE IF EXISTS "dbo"."seq_survalues";
CREATE SEQUENCE "dbo"."seq_survalues";
CREATE TABLE "dbo"."SurValues" (
    "ID" int NOT NULL,
    "Chdrnum" char(8) NOT NULL,
    "Survalue" decimal(15, 2) NOT NULL,
    "Company" char(1) NOT NULL,
    "Currency" char(3),
    "ValidDate" char(10),
    PRIMARY KEY ("ID")
);

DROP TABLE IF EXISTS "dbo"."Policy";
DROP SEQUENCE IF EXISTS "dbo"."seq_policy";
CREATE SEQUENCE "dbo"."seq_policy";
CREATE TABLE "dbo"."Policy" (
    "ID" int NOT NULL,
    "Chdrnum" char(8) NOT NULL,
    "Cownnum" char(8) NOT NULL,
    "OwnerName" varchar(100),
    "LifcNum" char(8),
    "LifcName" varchar(100),
    "Aracde" char(3),
    "Agntnum" char(5),
    "MailAddress" varchar(100),
    PRIMARY KEY ("ID")
);
