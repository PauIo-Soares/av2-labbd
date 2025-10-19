package br.edu.fatecl.av2labbd.av2_labbd.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface IAdminRepository {

    @Procedure(name = "sp_validar_admin")
    boolean isAutenticado(@Param("login") String login, @Param("senha") String senha);

}