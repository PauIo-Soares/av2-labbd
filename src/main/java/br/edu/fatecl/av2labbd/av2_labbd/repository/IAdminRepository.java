package br.edu.fatecl.av2labbd.av2_labbd.repository;

public interface IAdminRepository {

    boolean validarLogin(String login, String senha);

}