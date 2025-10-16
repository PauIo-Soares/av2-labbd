USE db_av2
Go

CREATE FUNCTION fn_QtdCuriosidadesPorTime (@timeid BIGINT)
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

CREATE PROCEDURE sp_valorAleatorio @timeid bigint 
AS
   SELECT TOP 1 mensagem
   FROM tb_curiosidades
   where time_id = @timeid
   ORDER BY NEWID()
GO

CREATE PROCEDURE sp_verificaTabela @valido BIT output
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

CREATE PROCEDURE sp_validar_admin @login VARCHAR(50), @senha VARCHAR(50), @valido BIT OUTPUT
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


Create trigger t_insereHist on tb_curiosidades
FOR INSERT
AS
BEGIN
	DECLARE @curiosidadeid bigint
	SELECT @curiosidadeid = id from inserted
	INSERT INTO tb_historico_curiosidade (data_hora_exibicao, curiosidade_id) VALUES (GETDATE(), @curiosidadeid)
END
GO

Create trigger t_modificaUltimaHist on tb_historico_curiosidade
For INSERT
AS
BEGIN
	DECLARE @timeid bigint, @qtdCuriosidadeTime INT, @idAntigo bigint
	Declare @id bigint, @datahora datetime, @curiosidadeID bigint

	--SELECT @qtdCuriosidades =  COUNT(*) from tb_historico_curiosidade

	Select @id = id, @datahora = data_hora_exibicao, @curiosidadeID = curiosidade_id from inserted
	SELECT @timeid = c.time_id FROM tb_curiosidades c WHERE c.id = @curiosidadeID;

	SELECT @qtdCuriosidadeTime = dbo.fn_QtdCuriosidadesPorTime(@timeid)

	IF (@qtdCuriosidadeTime > 3)
	BEGIN
		SELECT TOP 1 @idAntigo = hist.id
		from tb_historico_curiosidade hist
		INNER JOIN tb_curiosidades cr ON hist.curiosidade_id = cr.id
		where cr.time_id = @timeid
		ORDER BY hist.data_hora_exibicao ASC
		
		UPDATE tb_historico_curiosidade
		SET id = @id, data_hora_exibicao = @datahora, curiosidade_id = @curiosidadeID
		where id = @idAntigo

		DELETE FROM tb_historico_curiosidade
		WHERE id IN (Select id from inserted)
	END
END
GO

Create trigger t_updtdelcuriosidade on tb_curiosidades
INSTEAD OF UPDATE, DELETE
AS
BEGIN
	RAISERROR('As curiosidades nao devem ser modificadas ou apagadas!', 16, 1)
END
GO

Create trigger t_updtdelcandidato on tb_candidatos
INSTEAD OF UPDATE, DELETE
AS
BEGIN
	RAISERROR('Os candidatos nao devem ser modificados ou apagados!', 16, 1)
END
GO
