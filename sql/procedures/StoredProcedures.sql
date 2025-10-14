USE db_av2
GO

Create trigger t_modificaUltimaHist on tb_historico_curiosidade
For INSERT
AS
BEGIN
	DECLARE @qtdCuriosidades INT, @maiorID int
	Declare @id bigint, @datahora date, @curiosidadeID bigint

	SELECT @qtdCuriosidades =  COUNT(*) from tb_historico_curiosidade
	IF (@qtdCuriosidades > 12)
	BEGIN
		SELECT @maiorID = MAX(id) from tb_historico_curiosidade
		Select @id = id, @datahora = data_hora_exibicao, @curiosidadeID = curiosidade_id from inserted

		UPDATE tb_historico_curiosidade
		SET id = @id, data_hora_exibicao = @datahora, curiosidade_id = @curiosidadeID
		where id = @maiorID
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
