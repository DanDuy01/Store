create database swp_restaurant
USE [swp_restaurant]
GO

/****** Object:  Table [dbo].[User]    Script Date: 9/25/2024 1:52:10 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[User](
	[userId] [int] NOT NULL, 
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](1000) NULL,
	[fullName] [nvarchar](50) NULL,
	[dob] [date] NULL,
	[gender] [nvarchar](30) NULL,
	[avatar] [nvarchar](max) NULL,
	[phone] [varchar](20) NULL,
	[email] [varchar](40) NULL,
	[address] [nvarchar](200) NULL,	
	[role] [nvarchar](50) NULL,
	[active] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

