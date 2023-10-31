USE [master]
GO
/****** Object:  Database [FastFoodStore]    Script Date: 10/31/2023 9:23:22 PM ******/
CREATE DATABASE [FastFoodStore]
 CONTAINMENT = NONE
 ON  PRIMARY
( NAME = N'FastFoodStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\FastFoodStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON
( NAME = N'FastFoodStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\FastFoodStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [FastFoodStore] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FastFoodStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FastFoodStore] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [FastFoodStore] SET ANSI_NULLS OFF
GO
ALTER DATABASE [FastFoodStore] SET ANSI_PADDING OFF
GO
ALTER DATABASE [FastFoodStore] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [FastFoodStore] SET ARITHABORT OFF
GO
ALTER DATABASE [FastFoodStore] SET AUTO_CLOSE ON
GO
ALTER DATABASE [FastFoodStore] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [FastFoodStore] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [FastFoodStore] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [FastFoodStore] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [FastFoodStore] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [FastFoodStore] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [FastFoodStore] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [FastFoodStore] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [FastFoodStore] SET  ENABLE_BROKER
GO
ALTER DATABASE [FastFoodStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [FastFoodStore] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [FastFoodStore] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [FastFoodStore] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [FastFoodStore] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [FastFoodStore] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [FastFoodStore] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [FastFoodStore] SET RECOVERY SIMPLE
GO
ALTER DATABASE [FastFoodStore] SET  MULTI_USER
GO
ALTER DATABASE [FastFoodStore] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [FastFoodStore] SET DB_CHAINING OFF
GO
ALTER DATABASE [FastFoodStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [FastFoodStore] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [FastFoodStore] SET DELAYED_DURABILITY = DISABLED
GO
ALTER DATABASE [FastFoodStore] SET ACCELERATED_DATABASE_RECOVERY = OFF
GO
ALTER DATABASE [FastFoodStore] SET QUERY_STORE = OFF
GO
USE [FastFoodStore]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 10/31/2023 9:23:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
    [account_id] [int] IDENTITY(1,1) NOT NULL,
    [blog_id] [int] NOT NULL,
    [username] [nvarchar](100) NULL,
    [password] [nvarchar](100) NULL,
    [address] [text] NULL,
    [phone] [varchar](50) NULL,
    [avata] [nvarchar](100) NULL,
    [token] [nvarchar](100) NULL,
    [active] [bit] NULL,
    CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED
(
[account_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Action]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Action](
    [action_id] [int] NOT NULL,
    [name] [nvarchar](50) NULL,
    CONSTRAINT [PK_Action] PRIMARY KEY CLUSTERED
(
[action_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Authority]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Authority](
    [Id] [int] IDENTITY(1,1) NOT NULL,
    [Username] [int] NOT NULL,
    [Roleid] [nvarchar](10) NOT NULL,
    CONSTRAINT [PK_Authority] PRIMARY KEY CLUSTERED
(
[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Blog]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Blog](
    [blog_id] [int] NOT NULL,
    [name] [nvarchar](50) NULL,
    [description] [nvarchar](800) NULL,
    [blog_date] [date] NULL,
    CONSTRAINT [PK_Blog] PRIMARY KEY CLUSTERED
(
[blog_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Cart_Items]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Cart_Items](
    [cart_item_id] [int] NOT NULL,
    [cart_id] [int] NOT NULL,
    [quantity] [float] NULL,
    [price] [float] NULL,
    [subtotal] [float] NULL,
     CONSTRAINT [PK_Cart_Items] PRIMARY KEY CLUSTERED
    (
[cart_item_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Carts]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Carts](
    [cart_id] [int] NOT NULL,
    [account_id] [int] NOT NULL,
    [product_add_date] [date] NULL,
     CONSTRAINT [PK_Carts] PRIMARY KEY CLUSTERED
    (
[cart_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Categories]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Categories](
    [category_id] [int] NOT NULL,
    [name] [nvarchar](50) NULL,
    [description] [nvarchar](800) NULL,
    [image_url] [nvarchar](100) NULL,
    CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED
(
[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Favorites]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Favorites](
    [favorite_id] [int] NOT NULL,
    [account_id] [int] NOT NULL,
    [product_id] [int] NOT NULL,
    [date_favorite] [date] NULL,
     CONSTRAINT [PK_Favorite] PRIMARY KEY CLUSTERED
    (
[favorite_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[History]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[History](
    [history_id] [int] NOT NULL,
    [account_id] [int] NOT NULL,
    [product_id] [int] NOT NULL,
    [action_id] [int] NOT NULL,
    [timestamp] [datetime] NULL,
     CONSTRAINT [PK_History] PRIMARY KEY CLUSTERED
    (
[history_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Image_product]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Image_product](
    [image_product_id] [int] IDENTITY(1,1) NOT NULL,
    [product_id] [int] NOT NULL,
    [url_img] [nvarchar](100) NULL,
    [alt_text] [nvarchar](100) NULL,
    CONSTRAINT [PK_Image_product] PRIMARY KEY CLUSTERED
(
[image_product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Order_Items]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Order_Items](
    [order_item_id] [int] NOT NULL,
    [order_id] [int] NOT NULL,
    [size_product_id] [int] NOT NULL,
    [quantity] [float] NULL,
    [subtotal] [float] NULL,
    [price] [float] NULL,
    [name] [nvarchar](100) NULL,
    CONSTRAINT [PK_Order_Items] PRIMARY KEY CLUSTERED
(
[order_item_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Orders]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Orders](
    [order_id] [int] NOT NULL,
    [account_id] [int] NOT NULL,
    [status_id] [int] NOT NULL,
    [order_time] [datetime] NULL,
    [total_amount] [float] NULL,
    [delivery_address] [text] NULL,
    [delivery_time] [datetime] NULL,
    [payment_date] [date] NULL,
    [payment_method] [varchar](100) NULL,
    [phone] [varchar](50) NULL,
    CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED
(
[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Products]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Products](
    [product_id] [int] IDENTITY(1,1) NOT NULL,
    [category_id] [int] NOT NULL,
    [name] [nvarchar](100) NULL,
    [price] [float] NULL,
    [prices] [float] NULL,
    [description] [nvarchar](max) NULL,
    [description_an] [nvarchar](max) NULL,
    [quantity] [float] NULL,
    [image] [varchar](max) NULL,
    [sale] [int] NULL,
    [highlight] [bit] NULL,
    [create_at] [datetime] NULL,
    [update_at] [datetime] NULL,
    [views] [int] NULL,
    CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED
(
[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Reviews]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Reviews](
    [review_id] [int] NOT NULL,
    [product_id] [int] NOT NULL,
    [account_id] [int] NOT NULL,
    [rating] [nvarchar](50) NULL,
    [comment] [text] NULL,
    CONSTRAINT [PK_Reviews] PRIMARY KEY CLUSTERED
(
[review_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Roles]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Roles](
    [Id] [nvarchar](10) NOT NULL,
    [Name] [nvarchar](50) NULL,
    CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED
(
[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Size]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Size](
    [size_id] [int] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](50) NULL,
    CONSTRAINT [PK_Size] PRIMARY KEY CLUSTERED
(
[size_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Size_Product]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Size_Product](
    [size_product_id] [int] IDENTITY(1,1) NOT NULL,
    [product_id] [int] NOT NULL,
    [size_id] [int] NOT NULL,
    [price] [float] NULL,
    CONSTRAINT [PK_Size_Product] PRIMARY KEY CLUSTERED
(
[size_product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Status]    Script Date: 10/31/2023 9:23:22 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Status](
    [status_id] [int] NOT NULL,
    [name] [nvarchar](50) NULL,
    CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED
(
[status_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
    SET IDENTITY_INSERT [dbo].[Account] ON

    INSERT [dbo].[Account] ([account_id], [blog_id], [username], [password], [address], [phone], [avata], [token], [active]) VALUES (1, 1, N'admin@gmail.com', N'$2a$10$qWe08Z/SOl4VKjG6Dgtc.ellmbIQrhh6N.wPLMwdilXZ1sM8dThn2', N'cà mau', N'0943925652', NULL, NULL, NULL)
    INSERT [dbo].[Account] ([account_id], [blog_id], [username], [password], [address], [phone], [avata], [token], [active]) VALUES (2, 1, N'admin1@gmail.com', N'123', N'ct', N'000000000', NULL, NULL, NULL)
    INSERT [dbo].[Account] ([account_id], [blog_id], [username], [password], [address], [phone], [avata], [token], [active]) VALUES (5, 1, N'admin2@gmail.com', N'$2a$10$RIMmtDMSOdEbFt3jWZ3J6uugF4zMeXoD9MOv5WUdN/YOaDiPuM7ay', NULL, NULL, NULL, NULL, 1)
    INSERT [dbo].[Account] ([account_id], [blog_id], [username], [password], [address], [phone], [avata], [token], [active]) VALUES (6, 1, N'admin4@gmail.com', N'$2a$10$qBlXKDiMIxTXMM4l0lGgAO2ahhdEMAEUHhdzB3kVlo1Jx4moxdYNy', NULL, NULL, NULL, N'12f42319-3ab9-4989-bd97-30eb64d79e9c:1698670444658', 0)
    INSERT [dbo].[Account] ([account_id], [blog_id], [username], [password], [address], [phone], [avata], [token], [active]) VALUES (7, 1, N'bang@gmail.com', N'$2a$10$HWPfVtPdCIW.HrwVFd0oSeebT3xe7GoVGuZGmABXxrXHaloruveXi', NULL, NULL, NULL, N'493472bb-1c2c-4874-9f0e-793e35a418f4:1698670524656', 0)
    INSERT [dbo].[Account] ([account_id], [blog_id], [username], [password], [address], [phone], [avata], [token], [active]) VALUES (8, 1, N'lechibangcmu15@gmail.com', N'$2a$10$Oq4yWqwzGmVnXLlTb0f5POgbhh8EzRArqhO5nOa9AME8.uIbgjDaC', NULL, NULL, NULL, N'fb80ef09-9c64-41ea-980c-4b50638bd42e:1698670617913', 0)
    SET IDENTITY_INSERT [dbo].[Account] OFF
    GO
    SET IDENTITY_INSERT [dbo].[Authority] ON

    INSERT [dbo].[Authority] ([Id], [Username], [Roleid]) VALUES (7, 6, N'1')
    INSERT [dbo].[Authority] ([Id], [Username], [Roleid]) VALUES (8, 7, N'1')
    INSERT [dbo].[Authority] ([Id], [Username], [Roleid]) VALUES (9, 8, N'1')
    SET IDENTITY_INSERT [dbo].[Authority] OFF
    GO
    INSERT [dbo].[Blog] ([blog_id], [name], [description], [blog_date]) VALUES (1, N'Bán Th?c Ăn Nhanh', N'Th?c ăn nhanh m?i ngày', CAST(N'2023-04-02' AS Date))
    GO
    INSERT [dbo].[Categories] ([category_id], [name], [description], [image_url]) VALUES (1, N'Hamburger', N'Hamburger', N'main_img.png')
    INSERT [dbo].[Categories] ([category_id], [name], [description], [image_url]) VALUES (2, N'Pizza', N'Pizza', N'banner_pizza.png')
    INSERT [dbo].[Categories] ([category_id], [name], [description], [image_url]) VALUES (3, N'Mỳ', N'Mỳ', NULL)
    INSERT [dbo].[Categories] ([category_id], [name], [description], [image_url]) VALUES (4, N'Tráng Miệng', N'Tráng Miệng', NULL)
    GO
    SET IDENTITY_INSERT [dbo].[Image_product] ON

    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (1, 1, N'main_img.png', N'burger1')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (2, 1, N'hamburger_banner1.png', N'burger2')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (3, 1, N'hamburger_banner2.png', N'burger3')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (4, 2, N'cheeseburger.png', N'burger4')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (5, 2, N'cheeseburger_1.png', N'burger5')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (6, 2, N'Cheeseburger_2.png', N'burger6')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (7, 3, N'hamburger_chicken.png', N'burger7')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (8, 3, N'hamburger_chicken_3.png', N'burger8')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (9, 3, N'hamburger_chicken_1.png', N'burger9')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (10, 4, N'hamburger_bbq.png', N'burger10')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (11, 4, N'hamburger_bbq_1.png', N'burger11')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (12, 4, N'hamburger_bbq_2.png', N'burger12')
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (13, 5, NULL, NULL)
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (14, 5, NULL, NULL)
    INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (15, 5, NULL, NULL)
    SET IDENTITY_INSERT [dbo].[Image_product] OFF
    GO
    SET IDENTITY_INSERT [dbo].[Products] ON

    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (1, 1, N'Hamburger Classic', 35000, 40000, N' Bao gồm bánh mì, thịt bò, rau và sốt.', NULL, 5, N'main_img.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (2, 1, N'Cheeseburger', 45000, 55000, N'Loại hamburger cổ điển nhưng được thêm phần phô mai trên lớp thịt nướng để nóng chảy', NULL, 5, N'cheeseburger.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (3, 1, N'Hamburger gà', 45000, 55000, N'Sử dụng thịt gà xay thay cho thịt bò. Đôi khi có thêm gia vị và sốt tương đặc biệt.', NULL, 5, N'hamburger_chicken.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (4, 1, N'Hamburger BBQ', 55000, 65000, N'Thịt bò nướng với gia vị BBQ và sốt BBQ. Có thể kèm theo phô mai và các loại rau sống khác.', NULL, 5, N'hamburger_bbq.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (5, 1, N'Hamburger cá', 35000, 40000, N'Sử dụng thịt cá hoặc cá viên.', NULL, 5, N'hamburger-fish.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (6, 1, N'Hamburger rau', 30000, 40000, N'Không có thịt, chỉ có rau và sốt.', NULL, 5, N'hamburger_vegetable.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (7, 2, N'Pizza Margherita', 65000, 75000, N'Một loại pizza cổ điển từ Ý, bao gồm cà chua, pho mát Mozarella, basil và dầu ô-liu.', NULL, 5, N'pizza_margherita.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (8, 2, N'Pizza Pepperoni', 60000, 65000, N'Có lớp pho mát Mozarella và ớt Pepperoni (xúc xích mỡ).', NULL, 5, N'pizza_pepperoni.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (9, 2, N'Pizza Hawaiian', 55000, 60000, N'Gồm cà chua, pho mát Mozarella, jambon và dứa.', NULL, 5, N'pizza_hawaiian.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (10, 2, N'Pizza BBQ Chicken', 85000, 90000, N'Có lớp sốt BBQ, gà nướng, pho mát Mozarella, hành và rau mùi.', NULL, 5, N'pizza_bbq_chicken.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (11, 2, N'Pizza Vegetarian', 50000, 55000, N'Không có thịt, thay vào đó là các loại rau, cà chua, hành tây, nấm, ớt và pho mát.', NULL, 5, N'pizza_vegetarian.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (12, 2, N'Pizza Seafood', 75000, 75000, N'Có các loại hải sản như tôm, cá hồi, mực và nghêu.', NULL, 5, N'pizza_seafood.png', NULL, NULL, NULL, NULL, NULL)
    INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (14, 2, N'Pizza Qtro Stagioni', 65000, 70000, N'Được chia thành bốn phần riêng biệt đại diện cho bốn mùa trong năm, bao gồm nấm, cà chua, jambon, cà rốt, hành tây và cây lá.', NULL, 5, N'pizza_quattro_stagioni.png', NULL, NULL, NULL, NULL, NULL)
    SET IDENTITY_INSERT [dbo].[Products] OFF
    GO
    INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'1', N'admin')
    INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'2', N'user')
    GO
    SET IDENTITY_INSERT [dbo].[Size] ON

    INSERT [dbo].[Size] ([size_id], [name]) VALUES (1, N'S')
    INSERT [dbo].[Size] ([size_id], [name]) VALUES (2, N'M')
    INSERT [dbo].[Size] ([size_id], [name]) VALUES (3, N'L')
    SET IDENTITY_INSERT [dbo].[Size] OFF
    GO
    SET IDENTITY_INSERT [dbo].[Size_Product] ON

    INSERT [dbo].[Size_Product] ([size_product_id], [product_id], [size_id], [price]) VALUES (1, 1, 1, 40000)
    INSERT [dbo].[Size_Product] ([size_product_id], [product_id], [size_id], [price]) VALUES (2, 1, 2, 45000)
    INSERT [dbo].[Size_Product] ([size_product_id], [product_id], [size_id], [price]) VALUES (3, 1, 3, 50000)
    INSERT [dbo].[Size_Product] ([size_product_id], [product_id], [size_id], [price]) VALUES (4, 2, 1, 55000)
    INSERT [dbo].[Size_Product] ([size_product_id], [product_id], [size_id], [price]) VALUES (5, 2, 2, 60000)
    INSERT [dbo].[Size_Product] ([size_product_id], [product_id], [size_id], [price]) VALUES (6, 2, 3, 65000)
    SET IDENTITY_INSERT [dbo].[Size_Product] OFF
    GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Blog] FOREIGN KEY([blog_id])
    REFERENCES [dbo].[Blog] ([blog_id])
    GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Blog]
    GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK9ml4ybpmmjn6om5684f0mxrau] FOREIGN KEY([blog_id])
    REFERENCES [dbo].[Blog] ([blog_id])
    GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK9ml4ybpmmjn6om5684f0mxrau]
    GO
ALTER TABLE [dbo].[Cart_Items]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Items_Carts] FOREIGN KEY([cart_id])
    REFERENCES [dbo].[Carts] ([cart_id])
    GO
ALTER TABLE [dbo].[Cart_Items] CHECK CONSTRAINT [FK_Cart_Items_Carts]
    GO
ALTER TABLE [dbo].[Cart_Items]  WITH CHECK ADD  CONSTRAINT [FKpcttvuq4mxppo8sxggjtn5i2c] FOREIGN KEY([cart_id])
    REFERENCES [dbo].[Carts] ([cart_id])
    GO
ALTER TABLE [dbo].[Cart_Items] CHECK CONSTRAINT [FKpcttvuq4mxppo8sxggjtn5i2c]
    GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK_Carts_Account] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK_Carts_Account]
    GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FKtbh18csnlmy9mre0klfe4m941] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FKtbh18csnlmy9mre0klfe4m941]
    GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Favorites_Account] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK_Favorites_Account]
    GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Favorites_Products] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK_Favorites_Products]
    GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK6sgu5npe8ug4o42bf9j71x20c] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK6sgu5npe8ug4o42bf9j71x20c]
    GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD  CONSTRAINT [FKbqsp0h5gswfy61ybvpr8evogi] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FKbqsp0h5gswfy61ybvpr8evogi]
    GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_Account] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_Account]
    GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_Action] FOREIGN KEY([action_id])
    REFERENCES [dbo].[Action] ([action_id])
    GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_Action]
    GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_Products] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_Products]
    GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK2mpn4nxqqsu7euii4hwhbjeg8] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK2mpn4nxqqsu7euii4hwhbjeg8]
    GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK52h2a149n46ux9rhbef2idk6a] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK52h2a149n46ux9rhbef2idk6a]
    GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FKqrc5amhef551oogrsug3vk1xs] FOREIGN KEY([action_id])
    REFERENCES [dbo].[Action] ([action_id])
    GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FKqrc5amhef551oogrsug3vk1xs]
    GO
ALTER TABLE [dbo].[Image_product]  WITH CHECK ADD  CONSTRAINT [FK_Image_product_Products] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[Image_product] CHECK CONSTRAINT [FK_Image_product_Products]
    GO
ALTER TABLE [dbo].[Image_product]  WITH CHECK ADD  CONSTRAINT [FKr01evkpk9msgd6a4j55hv73ja] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[Image_product] CHECK CONSTRAINT [FKr01evkpk9msgd6a4j55hv73ja]
    GO
ALTER TABLE [dbo].[Order_Items]  WITH CHECK ADD  CONSTRAINT [FK_Order_Items_Orders] FOREIGN KEY([order_id])
    REFERENCES [dbo].[Orders] ([order_id])
    GO
ALTER TABLE [dbo].[Order_Items] CHECK CONSTRAINT [FK_Order_Items_Orders]
    GO
ALTER TABLE [dbo].[Order_Items]  WITH CHECK ADD  CONSTRAINT [FK_Order_Items_Size_Product] FOREIGN KEY([size_product_id])
    REFERENCES [dbo].[Size_Product] ([size_product_id])
    GO
ALTER TABLE [dbo].[Order_Items] CHECK CONSTRAINT [FK_Order_Items_Size_Product]
    GO
ALTER TABLE [dbo].[Order_Items]  WITH CHECK ADD  CONSTRAINT [FKbioxgbv59vetrxe0ejfubep1w] FOREIGN KEY([order_id])
    REFERENCES [dbo].[Orders] ([order_id])
    GO
ALTER TABLE [dbo].[Order_Items] CHECK CONSTRAINT [FKbioxgbv59vetrxe0ejfubep1w]
    GO
ALTER TABLE [dbo].[Order_Items]  WITH CHECK ADD  CONSTRAINT [FKct3lneorc00afvkkredox9xwf] FOREIGN KEY([size_product_id])
    REFERENCES [dbo].[Size_Product] ([size_product_id])
    GO
ALTER TABLE [dbo].[Order_Items] CHECK CONSTRAINT [FKct3lneorc00afvkkredox9xwf]
    GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Account] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Account]
    GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Status] FOREIGN KEY([status_id])
    REFERENCES [dbo].[Status] ([status_id])
    GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Status]
    GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK3c7gbsfawn58r27cf5b2km72f] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK3c7gbsfawn58r27cf5b2km72f]
    GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FKnoettwqr56yaai4i8nwxg4huo] FOREIGN KEY([status_id])
    REFERENCES [dbo].[Status] ([status_id])
    GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FKnoettwqr56yaai4i8nwxg4huo]
    GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Categories] FOREIGN KEY([category_id])
    REFERENCES [dbo].[Categories] ([category_id])
    GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Categories]
    GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FKog2rp4qthbtt2lfyhfo32lsw9] FOREIGN KEY([category_id])
    REFERENCES [dbo].[Categories] ([category_id])
    GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FKog2rp4qthbtt2lfyhfo32lsw9]
    GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD  CONSTRAINT [FK_Reviews_Account] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[Reviews] CHECK CONSTRAINT [FK_Reviews_Account]
    GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD  CONSTRAINT [FK_Reviews_Products] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[Reviews] CHECK CONSTRAINT [FK_Reviews_Products]
    GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD  CONSTRAINT [FK6wik3howqayl69l5vf356n3hh] FOREIGN KEY([account_id])
    REFERENCES [dbo].[Account] ([account_id])
    GO
ALTER TABLE [dbo].[Reviews] CHECK CONSTRAINT [FK6wik3howqayl69l5vf356n3hh]
    GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD  CONSTRAINT [FKpl51cejpw4gy5swfar8br9ngi] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[Reviews] CHECK CONSTRAINT [FKpl51cejpw4gy5swfar8br9ngi]
    GO
ALTER TABLE [dbo].[Size_Product]  WITH CHECK ADD  CONSTRAINT [FK_Size_Product_Products] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[Size_Product] CHECK CONSTRAINT [FK_Size_Product_Products]
    GO
ALTER TABLE [dbo].[Size_Product]  WITH CHECK ADD  CONSTRAINT [FK_Size_Product_Size] FOREIGN KEY([size_id])
    REFERENCES [dbo].[Size] ([size_id])
    GO
ALTER TABLE [dbo].[Size_Product] CHECK CONSTRAINT [FK_Size_Product_Size]
    GO
ALTER TABLE [dbo].[Size_Product]  WITH CHECK ADD  CONSTRAINT [FK1cel861ivevbm589o6uyes1h2] FOREIGN KEY([size_id])
    REFERENCES [dbo].[Size] ([size_id])
    GO
ALTER TABLE [dbo].[Size_Product] CHECK CONSTRAINT [FK1cel861ivevbm589o6uyes1h2]
    GO
ALTER TABLE [dbo].[Size_Product]  WITH CHECK ADD  CONSTRAINT [FK7ajjfnyx41xy5vkm5x52efive] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Products] ([product_id])
    GO
ALTER TABLE [dbo].[Size_Product] CHECK CONSTRAINT [FK7ajjfnyx41xy5vkm5x52efive]
    GO
    USE [master]
    GO
ALTER DATABASE [FastFoodStore] SET  READ_WRITE
GO
