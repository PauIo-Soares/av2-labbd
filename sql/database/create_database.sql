CREATE DATABASE db_av2
GO

USE [db_av2]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_QtdCuriosidadesPorTime]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[fn_QtdCuriosidadesPorTime] (@timeid BIGINT)
RETURNS INT
AS
BEGIN
    DECLARE @qtd INT;

    SELECT @qtd = COUNT(*)
    FROM tb_historico_curiosidade hist
    INNER JOIN tb_curiosidades cr ON hist.curiosidade_id = cr.id
    WHERE cr.time_id = @timeid;

    RETURN ISNULL(@qtd, 0);
END
GO
/****** Object:  Table [dbo].[tb_candidatos]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_candidatos](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[bairro] [varchar](255) NOT NULL,
	[data_hora_cadastro] [datetime2](6) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[nome] [varchar](255) NOT NULL,
	[telefone] [varchar](255) NOT NULL,
	[curso_interesse_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_curiosidades]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_curiosidades](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[mensagem] [varchar](255) NOT NULL,
	[time_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_cursos]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_cursos](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_historico_curiosidade]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_historico_curiosidade](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[data_hora_exibicao] [date] NOT NULL,
	[curiosidade_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_times]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_times](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tb_candidatos]  WITH CHECK ADD  CONSTRAINT [FKexebfvq0v5uqe0fq659rwennh] FOREIGN KEY([curso_interesse_id])
REFERENCES [dbo].[tb_cursos] ([id])
GO
ALTER TABLE [dbo].[tb_candidatos] CHECK CONSTRAINT [FKexebfvq0v5uqe0fq659rwennh]
GO
ALTER TABLE [dbo].[tb_curiosidades]  WITH CHECK ADD  CONSTRAINT [FK7pid06jollxdpwhnj18a84uum] FOREIGN KEY([time_id])
REFERENCES [dbo].[tb_times] ([id])
GO
ALTER TABLE [dbo].[tb_curiosidades] CHECK CONSTRAINT [FK7pid06jollxdpwhnj18a84uum]
GO
ALTER TABLE [dbo].[tb_historico_curiosidade]  WITH CHECK ADD  CONSTRAINT [FK4lnrxss2p7x6hasoj569lg351] FOREIGN KEY([curiosidade_id])
REFERENCES [dbo].[tb_curiosidades] ([id])
GO
ALTER TABLE [dbo].[tb_historico_curiosidade] CHECK CONSTRAINT [FK4lnrxss2p7x6hasoj569lg351]
GO
/****** Object:  StoredProcedure [dbo].[sp_validar_admin]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_validar_admin] @login VARCHAR(50), @senha VARCHAR(50), @valido BIT OUTPUT
AS
	IF @login = 'admin' AND @senha = 'Jej-W+q%'
	BEGIN
		SET @valido = 1
	END
	ELSE
	BEGIN
		SET @valido = 0
	END
GO
/****** Object:  StoredProcedure [dbo].[sp_valorAleatorio]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


/*
CREATE PROCEDURE sp_valorAleatorio @timeid bigint
AS
   Select TOP 1 mensagem
   FROM tb_curiosidades
   where time_id = @timeid
   ORDER BY NEWID()
GO
*/

CREATE PROCEDURE [dbo].[sp_valorAleatorio] @timeid bigint, @saida VARCHAR(200) OUTPUT
AS
   Select TOP 1 @saida = mensagem
   FROM tb_curiosidades
   where time_id = @timeid
   ORDER BY NEWID()
GO
/****** Object:  StoredProcedure [dbo].[sp_verificaTabela]    Script Date: 20/10/2025 12:32:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_verificaTabela] @valido BIT output
AS
	IF EXISTS (SELECT * FROM tb_curiosidades)
	BEGIN
		SET @valido = 1
	END
	ELSE
	BEGIN
		SET @valido = 0
	END
GO
