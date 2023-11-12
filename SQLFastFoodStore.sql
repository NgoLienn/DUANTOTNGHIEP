USE [FastFoodStore]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[account_id] [int] IDENTITY(1,1) NOT NULL,
	[blog_id] [int] NOT NULL,
	[fullname] [nvarchar](100) NULL,
	[username] [nvarchar](100) NULL,
	[password] [nvarchar](100) NULL,
	[address] [nvarchar](max) NULL,
	[phone] [varchar](50) NULL,
	[avata] [nvarchar](100) NULL,
	[token] [nvarchar](100) NULL,
	[active] [bit] NULL,
	[resettoken] [nvarchar](50) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[account_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Action]    Script Date: 11/12/2023 2:28:55 PM ******/
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
/****** Object:  Table [dbo].[Authority]    Script Date: 11/12/2023 2:28:55 PM ******/
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
/****** Object:  Table [dbo].[Blog]    Script Date: 11/12/2023 2:28:55 PM ******/
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
/****** Object:  Table [dbo].[Cart_Items]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart_Items](
	[cart_item_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NOT NULL,
	[cart_id] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NULL,
	[subtotal] [float] NULL,
	[size_id] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Cart_Items] PRIMARY KEY CLUSTERED 
(
	[cart_item_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Carts]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carts](
	[cart_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NOT NULL,
	[product_add_date] [date] NULL,
 CONSTRAINT [PK_Carts] PRIMARY KEY CLUSTERED 
(
	[cart_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 11/12/2023 2:28:55 PM ******/
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
/****** Object:  Table [dbo].[Favorites]    Script Date: 11/12/2023 2:28:55 PM ******/
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
/****** Object:  Table [dbo].[History]    Script Date: 11/12/2023 2:28:55 PM ******/
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
/****** Object:  Table [dbo].[Image_product]    Script Date: 11/12/2023 2:28:55 PM ******/
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
/****** Object:  Table [dbo].[Order_Items]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_Items](
	[order_item_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[size_product_id] [nvarchar](10) NOT NULL,
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
/****** Object:  Table [dbo].[Orders]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NOT NULL,
	[status_id] [int] NOT NULL,
	[order_time] [datetime] NULL,
	[total_amount] [float] NULL,
	[delivery_address] [nvarchar](max) NULL,
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
/****** Object:  Table [dbo].[payment]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[payment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[amount] [int] NOT NULL,
	[bankcode] [varchar](255) NULL,
	[createdate] [varchar](255) NULL,
	[currcode] [varchar](255) NULL,
	[status] [varchar](255) NULL,
	[txnref] [varchar](255) NULL,
	[username] [varchar](255) NULL,
	[product_id] [int] NULL,
 CONSTRAINT [PK__payment__3213E83FE2E1A24D] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 11/12/2023 2:28:55 PM ******/
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
/****** Object:  Table [dbo].[Reviews]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Reviews](
	[review_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NOT NULL,
	[account_id] [int] NOT NULL,
	[rating] [int] NULL,
	[comment] [nvarchar](max) NULL,
	[date_post] [datetime] NULL,
 CONSTRAINT [PK_Reviews] PRIMARY KEY CLUSTERED 
(
	[review_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[id] [nvarchar](10) NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Size]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size](
	[size_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NOT NULL,
	[Id_table_size] [int] NULL,
 CONSTRAINT [PK_Size] PRIMARY KEY CLUSTERED 
(
	[size_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Size_Product]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size_Product](
	[size_product_id] [int] IDENTITY(1,1) NOT NULL,
	[size_id] [int] NOT NULL,
	[price] [float] NULL,
 CONSTRAINT [PK_Size_Product] PRIMARY KEY CLUSTERED 
(
	[size_product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Size_table]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size_table](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[size_name] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_SizeTable] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[status_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[status_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[transactionentity]    Script Date: 11/12/2023 2:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transactionentity](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[amount] [numeric](19, 0) NULL,
	[banktranno] [varchar](255) NULL,
	[transactionno] [varchar](255) NULL,
	[transactionstatus] [varchar](255) NULL,
	[txnref] [varchar](255) NULL,
	[id_payment] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([account_id], [blog_id], [fullname], [username], [password], [address], [phone], [avata], [token], [active], [resettoken]) VALUES (2, 1, N'Lê Chí Bằng', N'lechibangcmu15@gmail.com', N'$2a$10$TL./tx7w.YUAGNQBBqTTyusNQpL8MGAdfi3DTLYvNYmDWtBwoLqnu', N'Cái Nước - Cà Mau', N'0943925652', NULL, NULL, 1, NULL)
INSERT [dbo].[Account] ([account_id], [blog_id], [fullname], [username], [password], [address], [phone], [avata], [token], [active], [resettoken]) VALUES (3, 1, NULL, N'liennvpc04152@fpt.edu.vn', N'$2a$10$GKqCvlyB5.mGbsxdYphlye821HMSoJ4IYwYslxiMlObvR.wcQSbIK', NULL, NULL, NULL, NULL, 1, NULL)
INSERT [dbo].[Account] ([account_id], [blog_id], [fullname], [username], [password], [address], [phone], [avata], [token], [active], [resettoken]) VALUES (16, 1, NULL, N'banglcpc04088@fpt.edu.vn', N'$2a$10$L4O8GQykcPdBlYeSx4TDdeL6aD4azDrPIt0LyFklphXlqGdnmtDn6', NULL, NULL, NULL, N'1a145514-a000-40e5-868b-e3cca551a475:1699018690615', 1, NULL)
INSERT [dbo].[Account] ([account_id], [blog_id], [fullname], [username], [password], [address], [phone], [avata], [token], [active], [resettoken]) VALUES (17, 1, NULL, N'cuongncpc04122@fpt.edu.vn', N'$2a$10$nK6xOyg.rN6oZrTsv0HgYeNPc6B7QU3mfpEUOTOyWhJeNu0BOtjWm', NULL, NULL, NULL, NULL, 1, NULL)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Authority] ON 

INSERT [dbo].[Authority] ([Id], [Username], [Roleid]) VALUES (1, 2, N'1')
INSERT [dbo].[Authority] ([Id], [Username], [Roleid]) VALUES (2, 3, N'2')
INSERT [dbo].[Authority] ([Id], [Username], [Roleid]) VALUES (12, 17, N'1')
SET IDENTITY_INSERT [dbo].[Authority] OFF
GO
INSERT [dbo].[Blog] ([blog_id], [name], [description], [blog_date]) VALUES (1, N'Bán Th?c Ăn Nhanh', N'Th?c ăn nhanh m?i ngày', CAST(N'2023-04-02' AS Date))
GO
SET IDENTITY_INSERT [dbo].[Carts] ON 

INSERT [dbo].[Carts] ([cart_id], [account_id], [product_add_date]) VALUES (9, 17, CAST(N'2023-11-11' AS Date))
INSERT [dbo].[Carts] ([cart_id], [account_id], [product_add_date]) VALUES (13, 2, CAST(N'2023-11-12' AS Date))
SET IDENTITY_INSERT [dbo].[Carts] OFF
GO
INSERT [dbo].[Categories] ([category_id], [name], [description], [image_url]) VALUES (1, N'Hamburger', N'Hamburger', N'iconcatburger.png')
INSERT [dbo].[Categories] ([category_id], [name], [description], [image_url]) VALUES (2, N'Pizza', N'Pizza', N'iconpizza.png')
INSERT [dbo].[Categories] ([category_id], [name], [description], [image_url]) VALUES (3, N'Mỳ', N'Mỳ', N'iconnoodle.png')
INSERT [dbo].[Categories] ([category_id], [name], [description], [image_url]) VALUES (4, N'Tráng Miệng', N'Tráng Miệng', N'icontrangmieng.png')
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
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (12, 4, N'hamburger_bbq_3.png', N'burger12')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (13, 5, N'hamburger-fish.png', N'burger13')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (14, 5, N'hamburger-fish_1.png', N'burger14')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (15, 5, N'hamburger-fish_2.png', N'burger15')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (16, 6, N'hamburger_vegetable.png', N'burger16')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (17, 6, N'hamburger_vegetable_1.png', N'burger17')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (18, 6, N'hamburger_vegetable_2.png', N'burger18')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (19, 7, N'pizza_margherita.png', N'burger19')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (20, 7, N'pizza_margherita_1.png', N'burger20')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (21, 7, N'pizza_margherita_2.png', N'burger21')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (22, 8, N'pizza_pepperoni.png', N'burger22')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (23, 8, N'pizza_pepperoni_1.png', N'burger23')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (24, 8, N'pizza_pepperoni_2.png', N'burger24')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (25, 9, N'pizza_hawaiian.png', N'burger25')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (26, 9, N'pizza_hawaiian_2.png', N'burger26')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (27, 9, N'pizza_hawaiian_3.png', N'burger27')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (28, 10, N'pizza_bbq_chicken.png', N'burger28')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (29, 10, N'pizza_bbq_chicken_1.png', N'burger29')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (30, 10, N'pizza_bbq_chicken2.png', N'burger30')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (31, 11, N'pizza_vegetarian.png', N'burger31')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (32, 11, N'pizza_vegetarian_1.png', N'burger32')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (33, 11, N'pizza_vegetarian_2.png', N'burger33')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (34, 12, N'pizza_seafood.png', N'burger34')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (35, 12, N'pizza_seafood_1.png', N'burger35')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (36, 12, N'pizza_seafood_2.png', N'burger36')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (37, 14, N'pizza_quattro_stagioni.png', N'burger37')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (38, 14, N'pizza_quattro_stagioni_1.png', N'burger38')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (39, 14, N'pizza_quattro_stagioni_2.png', N'burger39')
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (40, 15, N'spaghetti.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (41, 15, N'spaghetti_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (42, 15, N'spaghetti_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (43, 16, N'fettuccine.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (44, 16, N'fettuccine_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (45, 16, N'fettuccine_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (46, 17, N'jjajangmyeon.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (47, 17, N'jjajangmyeon_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (48, 17, N'jjajangmyeon_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (49, 18, N'laksa.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (50, 18, N'laksa_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (51, 18, N'laksa_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (52, 19, N'linguine.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (53, 19, N'linguine_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (54, 19, N'linguine_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (55, 20, N'naengmyeon.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (56, 20, N'naengmyeon_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (57, 20, N'naengmyeon_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (58, 21, N'penne.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (59, 21, N'penne_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (60, 21, N'penne_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (61, 22, N'ramyeon.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (62, 22, N'ramyeon_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (63, 22, N'ramyeon_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (64, 23, N'kemsundae.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (65, 23, N'kemsundae_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (66, 23, N'kemsundae_3.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (67, 24, N'kemvani.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (68, 24, N'kemvani_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (69, 24, N'kemvani_3.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (70, 25, N'chocolateicecream.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (71, 25, N'chocolateicecream_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (72, 25, N'chocolateicecream_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (73, 26, N'kemsundeadau.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (74, 26, N'kemsundeadau_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (76, 26, N'kemsundeadau_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (77, 27, N'kemsocola.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (78, 27, N'kemsocola_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (79, 27, N'kemsocola_2.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (80, 28, N'khoaitaychien.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (81, 28, N'khoaitaychien_1.png', NULL)
INSERT [dbo].[Image_product] ([image_product_id], [product_id], [url_img], [alt_text]) VALUES (82, 28, N'khoaitaychien_2.png', NULL)
SET IDENTITY_INSERT [dbo].[Image_product] OFF
GO
SET IDENTITY_INSERT [dbo].[Order_Items] ON 

INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (2, 3, N'S', 7, 315000, 45000, N'Cheeseburger')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (3, 4, N'S', 7, 315000, 45000, N'Cheeseburger')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (4, 5, N'S', 7, 315000, 45000, N'Cheeseburger')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (5, 6, N'S', 1, 55000, 55000, N'Hamburger BBQ')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (6, 7, N'S', 1, 55000, 55000, N'Hamburger BBQ')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (7, 8, N'M', 1, 48000, 48000, N'Hamburger gà')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (8, 9, N'S', 1, 45000, 45000, N'Hamburger gà')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (9, 9, N'M', 1, 48000, 48000, N'Hamburger gà')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (10, 10, N'S', 1, 45000, 45000, N'Hamburger gà')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (11, 10, N'M', 1, 48000, 48000, N'Hamburger gà')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (12, 11, N'S', 1, 45000, 45000, N'Hamburger gà')
INSERT [dbo].[Order_Items] ([order_item_id], [order_id], [size_product_id], [quantity], [subtotal], [price], [name]) VALUES (13, 11, N'M', 1, 48000, 48000, N'Hamburger gà')
SET IDENTITY_INSERT [dbo].[Order_Items] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (3, 2, 1, CAST(N'2023-11-12T00:00:00.000' AS DateTime), 805000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (4, 2, 1, CAST(N'2023-11-12T00:00:00.000' AS DateTime), 805000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (5, 2, 1, CAST(N'2023-11-12T00:00:00.000' AS DateTime), 805000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (6, 2, 1, CAST(N'2023-11-12T00:00:00.000' AS DateTime), 100000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (7, 2, 1, CAST(N'2023-11-12T00:00:00.000' AS DateTime), 100000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (8, 2, 1, CAST(N'2023-11-12T00:00:00.000' AS DateTime), 93000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (9, 2, 1, CAST(N'2023-11-12T00:00:00.000' AS DateTime), 93000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (10, 2, 1, CAST(N'2023-11-12T14:02:12.407' AS DateTime), 93000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
INSERT [dbo].[Orders] ([order_id], [account_id], [status_id], [order_time], [total_amount], [delivery_address], [delivery_time], [payment_date], [payment_method], [phone]) VALUES (11, 2, 1, CAST(N'2023-11-12T14:08:20.627' AS DateTime), 93000, N'Cái Nước - Cà Mau', CAST(N'2023-11-12T00:00:00.000' AS DateTime), CAST(N'2023-11-12' AS Date), N'Thanh toán khi nh?n hàng', N'0943925652')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (1, 1, N'Hamburger Classic', 35000, 45000, N'Bao gồm bánh mì, thịt bò, rau và sốt.', NULL, 5, N'main_img.png', NULL, NULL, CAST(N'2023-11-04T00:00:00.000' AS DateTime), NULL, 560)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (2, 1, N'Cheeseburger', 45000, 55000, N'Loại hamburger cổ điển nhưng được thêm phần phô mai trên lớp thịt nướng để nóng chảy', NULL, 5, N'cheeseburger.png', NULL, NULL, NULL, NULL, 132)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (3, 1, N'Hamburger gà', 45000, 55000, N'Sử dụng thịt gà xay thay cho thịt bò. Đôi khi có thêm gia vị và sốt tương đặc biệt.', NULL, 5, N'hamburger_chicken.png', NULL, NULL, NULL, NULL, 50)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (4, 1, N'Hamburger BBQ', 55000, 65000, N'Thịt bò nướng với gia vị BBQ và sốt BBQ. Có thể kèm theo phô mai và các loại rau sống khác.', NULL, 5, N'hamburger_bbq.png', NULL, NULL, NULL, NULL, 49)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (5, 1, N'Hamburger cá', 35000, 50000, N'Sử dụng thịt cá hoặc cá viên.', NULL, 5, N'hamburger-fish.png', NULL, NULL, NULL, NULL, 7)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (6, 1, N'Hamburger rau', 30000, 45000, N'Không có thịt, chỉ có rau và sốt.', NULL, 5, N'hamburger_vegetable.png', NULL, NULL, NULL, NULL, 25)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (7, 2, N'Pizza Margherita', 65000, 75000, N'Một loại pizza cổ điển từ Ý, bao gồm cà chua, pho mát Mozarella, basil và dầu ô-liu.', NULL, 5, N'pizza_margherita.png', NULL, NULL, NULL, NULL, 6)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (8, 2, N'Pizza Pepperoni', 60000, 70000, N'Có lớp pho mát Mozarella và ớt Pepperoni (xúc xích mỡ).', NULL, 5, N'pizza_pepperoni.png', NULL, NULL, NULL, NULL, 5)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (9, 2, N'Pizza Hawaiian', 55000, 65000, N'Gồm cà chua, pho mát Mozarella, jambon và dứa.', NULL, 5, N'pizza_hawaiian.png', NULL, NULL, NULL, NULL, 4)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (10, 2, N'Pizza BBQ Chicken', 75000, 90000, N'Có lớp sốt BBQ, gà nướng, pho mát Mozarella, hành và rau mùi.', NULL, 5, N'pizza_bbq_chicken.png', NULL, NULL, NULL, NULL, 15)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (11, 2, N'Pizza Vegetarian', 50000, 60000, N'Không có thịt, thay vào đó là các loại rau, cà chua, hành tây, nấm, ớt và pho mát.', NULL, 5, N'pizza_vegetarian.png', NULL, NULL, NULL, NULL, 5)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (12, 2, N'Pizza Seafood', 65000, 75000, N'Có các loại hải sản như tôm, cá hồi, mực và nghêu.', NULL, 5, N'pizza_seafood.png', NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (14, 2, N'Pizza Qtro Stagioni', 55000, 70000, N'Được chia thành bốn phần riêng biệt đại diện cho bốn mùa trong năm, bao gồm nấm, cà chua, jambon, cà rốt, hành tây và cây lá.', NULL, 5, N'pizza_quattro_stagioni.png', NULL, NULL, NULL, NULL, 6)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (15, 3, N'Spaghetti', 45000, 60000, N'Mỳ Ý thường được làm từ mỳ trắng và có hình dạng và kích cỡ đa dạng.', NULL, 5, N'spaghetti.png', NULL, NULL, NULL, NULL, 4)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (16, 3, N'Fettuccine', 40000, 50000, NULL, NULL, 5, N'fettuccine.png', NULL, NULL, NULL, NULL, 5)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (17, 3, N'Jjajangmyeon', 40000, 50000, NULL, NULL, 5, N'jjajangmyeon.png', NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (18, 3, N'Laksa', 45000, 55000, NULL, NULL, 5, N'laksa.png', NULL, NULL, NULL, NULL, 2)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (19, 3, N'Linguine', 30000, 40000, NULL, NULL, 5, N'linguine.png', NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (20, 3, N'Naengmyeon', 55000, 65000, NULL, NULL, 5, N'naengmyeon.png', NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (21, 3, N'Penne', 30000, 40000, NULL, NULL, 5, N'penne.png', NULL, NULL, NULL, NULL, 3)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (22, 3, N'Ramyeon', 55000, 60000, NULL, NULL, 5, N'ramyeon.png', NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (23, 4, N'Troppical Sundae', 20000, 28000, NULL, NULL, 5, N'kemsundae.png', NULL, NULL, NULL, NULL, 3)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (24, 4, N'Kem Sữa Tươi (Cúp)', 5000, 10000, NULL, NULL, 5, N'kemvani.png', NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (25, 4, N'Kem Socola (Cúp)', 7000, 15000, NULL, NULL, 5, N'chocolateicecream.png', NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (26, 4, N'Kem Sundaes Dâu', 15000, 25000, NULL, NULL, 5, N'kemsundeadau.png', NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (27, 4, N'Kem Sundaes Socola', 15000, 25000, NULL, NULL, 5, N'kemsocola.png', NULL, NULL, NULL, NULL, 5)
INSERT [dbo].[Products] ([product_id], [category_id], [name], [price], [prices], [description], [description_an], [quantity], [image], [sale], [highlight], [create_at], [update_at], [views]) VALUES (28, 4, N'Khoai Tây Chiên', 25000, 40000, NULL, NULL, 5, N'khoaitaychien.png', NULL, NULL, NULL, NULL, 7)
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
SET IDENTITY_INSERT [dbo].[Reviews] ON 

INSERT [dbo].[Reviews] ([review_id], [product_id], [account_id], [rating], [comment], [date_post]) VALUES (2, 1, 2, 1, N'hello bạn nha', NULL)
INSERT [dbo].[Reviews] ([review_id], [product_id], [account_id], [rating], [comment], [date_post]) VALUES (3, 1, 2, 2, N'hihi', NULL)
INSERT [dbo].[Reviews] ([review_id], [product_id], [account_id], [rating], [comment], [date_post]) VALUES (4, 1, 2, 3, N'buybuy', NULL)
INSERT [dbo].[Reviews] ([review_id], [product_id], [account_id], [rating], [comment], [date_post]) VALUES (5, 1, 2, 4, N'jj', NULL)
INSERT [dbo].[Reviews] ([review_id], [product_id], [account_id], [rating], [comment], [date_post]) VALUES (6, 1, 2, 5, N'kk', CAST(N'2023-11-05T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Reviews] OFF
GO
INSERT [dbo].[Roles] ([id], [name]) VALUES (N'1', N'admin')
INSERT [dbo].[Roles] ([id], [name]) VALUES (N'2', N'user')
GO
SET IDENTITY_INSERT [dbo].[Size] ON 

INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (1, 1, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (2, 1, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (3, 1, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (4, 2, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (5, 2, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (6, 2, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (7, 3, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (9, 3, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (10, 3, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (12, 4, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (13, 4, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (14, 4, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (15, 5, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (16, 5, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (17, 5, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (18, 6, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (19, 6, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (20, 6, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (21, 7, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (23, 7, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (24, 7, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (25, 8, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (26, 8, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (27, 8, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (29, 9, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (30, 9, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (31, 9, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (32, 10, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (33, 10, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (35, 10, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (36, 11, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (37, 11, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (38, 11, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (39, 12, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (40, 12, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (41, 12, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (45, 14, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (46, 14, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (47, 14, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (48, 15, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (49, 15, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (50, 15, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (51, 16, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (52, 16, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (53, 16, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (54, 17, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (55, 17, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (56, 17, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (57, 18, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (58, 18, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (59, 18, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (60, 19, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (61, 19, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (62, 19, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (63, 20, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (64, 20, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (65, 20, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (66, 21, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (67, 21, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (68, 21, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (69, 22, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (70, 22, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (71, 22, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (72, 23, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (73, 23, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (74, 23, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (75, 24, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (76, 24, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (77, 24, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (78, 25, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (79, 25, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (80, 25, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (81, 26, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (82, 26, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (83, 26, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (84, 27, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (85, 27, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (86, 27, 3)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (87, 28, 1)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (88, 28, 2)
INSERT [dbo].[Size] ([size_id], [product_id], [Id_table_size]) VALUES (90, 28, 3)
SET IDENTITY_INSERT [dbo].[Size] OFF
GO
SET IDENTITY_INSERT [dbo].[Size_Product] ON 

INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (1, 1, 30000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (2, 2, 35000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (3, 3, 40000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (7, 4, 45000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (8, 5, 48000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (9, 6, 52000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (10, 7, 45000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (16, 9, 48000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (17, 10, 52000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (19, 12, 55000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (20, 13, 58000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (21, 14, 62000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (22, 15, 35000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (23, 16, 40000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (24, 17, 45000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (25, 18, 30000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (26, 19, 33000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (27, 20, 38000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (28, 21, 65000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (30, 23, 69000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (31, 24, 73000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (32, 25, 60000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (33, 26, 63000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (34, 27, 68000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (37, 29, 55000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (38, 30, 58000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (39, 31, 62000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (40, 32, 75000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (41, 33, 80000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (43, 35, 85000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (44, 36, 50000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (45, 37, 53000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (46, 38, 58000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (47, 39, 65000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (48, 40, 68000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (49, 41, 72000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (53, 45, 55000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (54, 46, 58000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (55, 47, 63000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (56, 48, 45000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (57, 49, 50000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (58, 50, 55000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (59, 51, 40000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (60, 52, 43000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (61, 53, 48000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (62, 54, 40000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (63, 55, 43000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (64, 56, 48000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (65, 57, 45000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (66, 58, 48000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (67, 59, 53000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (68, 60, 30000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (69, 61, 33000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (70, 62, 37000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (71, 63, 55000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (72, 64, 58000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (73, 65, 62000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (74, 66, 30000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (75, 67, 33000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (76, 68, 38000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (77, 69, 55000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (78, 70, 55000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (79, 71, 58000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (80, 72, 20000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (81, 73, 22000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (82, 74, 25000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (83, 75, 5000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (84, 76, 6000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (85, 77, 8000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (86, 78, 7000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (87, 79, 9000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (88, 80, 12000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (89, 81, 15000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (90, 82, 18000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (91, 83, 22000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (92, 84, 15000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (93, 85, 18000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (94, 86, 22000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (95, 87, 25000)
INSERT [dbo].[Size_Product] ([size_product_id], [size_id], [price]) VALUES (96, 88, 35000)
SET IDENTITY_INSERT [dbo].[Size_Product] OFF
GO
SET IDENTITY_INSERT [dbo].[Size_table] ON 

INSERT [dbo].[Size_table] ([id], [size_name]) VALUES (1, N'S')
INSERT [dbo].[Size_table] ([id], [size_name]) VALUES (2, N'M')
INSERT [dbo].[Size_table] ([id], [size_name]) VALUES (3, N'L')
SET IDENTITY_INSERT [dbo].[Size_table] OFF
GO
SET IDENTITY_INSERT [dbo].[Status] ON 

INSERT [dbo].[Status] ([status_id], [name]) VALUES (1, N'Chờ xác nhận')
INSERT [dbo].[Status] ([status_id], [name]) VALUES (2, N'Đã xác nhận')
INSERT [dbo].[Status] ([status_id], [name]) VALUES (3, N'Đang giao hàng')
INSERT [dbo].[Status] ([status_id], [name]) VALUES (4, N'Giao hàng thành công')
INSERT [dbo].[Status] ([status_id], [name]) VALUES (5, N'Hủy đơn hàng')
SET IDENTITY_INSERT [dbo].[Status] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK7dg083jhglrreqemeihx7c814]    Script Date: 11/12/2023 2:28:55 PM ******/
ALTER TABLE [dbo].[Authority] ADD  CONSTRAINT [UK7dg083jhglrreqemeihx7c814] UNIQUE NONCLUSTERED 
(
	[Username] ASC,
	[Roleid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
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
ALTER TABLE [dbo].[Authority]  WITH CHECK ADD  CONSTRAINT [FKduqpolfx300uhw5i9ivclhs1u] FOREIGN KEY([Roleid])
REFERENCES [dbo].[Roles] ([id])
GO
ALTER TABLE [dbo].[Authority] CHECK CONSTRAINT [FKduqpolfx300uhw5i9ivclhs1u]
GO
ALTER TABLE [dbo].[Authority]  WITH CHECK ADD  CONSTRAINT [FKoktnd7ykqujm27id4wp08o932] FOREIGN KEY([Username])
REFERENCES [dbo].[Account] ([account_id])
GO
ALTER TABLE [dbo].[Authority] CHECK CONSTRAINT [FKoktnd7ykqujm27id4wp08o932]
GO
ALTER TABLE [dbo].[Cart_Items]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Items_Carts] FOREIGN KEY([cart_id])
REFERENCES [dbo].[Carts] ([cart_id])
GO
ALTER TABLE [dbo].[Cart_Items] CHECK CONSTRAINT [FK_Cart_Items_Carts]
GO
ALTER TABLE [dbo].[Cart_Items]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Items_Products] FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([product_id])
GO
ALTER TABLE [dbo].[Cart_Items] CHECK CONSTRAINT [FK_Cart_Items_Products]
GO
ALTER TABLE [dbo].[Cart_Items]  WITH CHECK ADD  CONSTRAINT [FK1re40cjegsfvw58xrkdp6bac6] FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([product_id])
GO
ALTER TABLE [dbo].[Cart_Items] CHECK CONSTRAINT [FK1re40cjegsfvw58xrkdp6bac6]
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
ALTER TABLE [dbo].[Order_Items]  WITH CHECK ADD  CONSTRAINT [FKbioxgbv59vetrxe0ejfubep1w] FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([order_id])
GO
ALTER TABLE [dbo].[Order_Items] CHECK CONSTRAINT [FKbioxgbv59vetrxe0ejfubep1w]
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
ALTER TABLE [dbo].[payment]  WITH CHECK ADD  CONSTRAINT [FK99md7gqcsje8s9d88khvd3d01] FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([product_id])
GO
ALTER TABLE [dbo].[payment] CHECK CONSTRAINT [FK99md7gqcsje8s9d88khvd3d01]
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
ALTER TABLE [dbo].[Size]  WITH CHECK ADD  CONSTRAINT [FK3ene26b1ti2qoeis86yylyu5l] FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([product_id])
GO
ALTER TABLE [dbo].[Size] CHECK CONSTRAINT [FK3ene26b1ti2qoeis86yylyu5l]
GO
ALTER TABLE [dbo].[Size]  WITH CHECK ADD  CONSTRAINT [FKis2tny7tvoultr7q40t8j4hsh] FOREIGN KEY([Id_table_size])
REFERENCES [dbo].[Size_table] ([id])
GO
ALTER TABLE [dbo].[Size] CHECK CONSTRAINT [FKis2tny7tvoultr7q40t8j4hsh]
GO
ALTER TABLE [dbo].[Size_Product]  WITH CHECK ADD  CONSTRAINT [FK1cel861ivevbm589o6uyes1h2] FOREIGN KEY([size_id])
REFERENCES [dbo].[Size] ([size_id])
GO
ALTER TABLE [dbo].[Size_Product] CHECK CONSTRAINT [FK1cel861ivevbm589o6uyes1h2]
GO
ALTER TABLE [dbo].[transactionentity]  WITH CHECK ADD  CONSTRAINT [FK9teb0oxfttuy2rlks69qianej] FOREIGN KEY([id_payment])
REFERENCES [dbo].[payment] ([id])
GO
ALTER TABLE [dbo].[transactionentity] CHECK CONSTRAINT [FK9teb0oxfttuy2rlks69qianej]
GO
