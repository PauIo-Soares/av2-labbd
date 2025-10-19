package br.edu.fatecl.av2labbd.av2_labbd.service;

import br.edu.fatecl.av2labbd.av2_labbd.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private CandidatoService candidatoService;

//    public boolean isAutenticado(){
//
//    }
//





}