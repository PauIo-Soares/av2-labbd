package br.edu.fatecl.av2labbd.av2_labbd.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository implements IAdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean isAutenticado(String login, String senha) {

        return jdbcTemplate.execute("{call sp_validar_admin(?, ?, ?)}", (CallableStatementCallback<Boolean>) cs -> {

            cs.setString(1, login);
            cs.setString(2, senha);
            cs.registerOutParameter(3, java.sql.Types.BOOLEAN);
            cs.execute();

            return cs.getBoolean(3);

        });
    }

}