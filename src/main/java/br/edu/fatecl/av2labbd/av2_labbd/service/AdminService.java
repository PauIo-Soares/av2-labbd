package br.edu.fatecl.av2labbd.av2_labbd.service;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CandidatoDTO;
import br.edu.fatecl.av2labbd.av2_labbd.dto.CuriosidadeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private CuriosidadeService curiosidadeService;

    @Autowired
    private CandidatoService candidatoService;

    public void autenticar(String login, String senha) {

        if (!adminRepository.isAutenticado(login, senha)) {
            throw new RuntimeException("Login ou senha inválidos");
        }

    }

    public String cadastrarMensagem(CuriosidadeDTO mensagem) {

        return curiosidadeService.criarCuriosidade(mensagem);

    }

    public String modificarMensagem(CuriosidadeDTO mensagem) {

        return curiosidadeService.atualizarCuriosidade(mensagem);

    }

    public CuriosidadeDTO buscarMensagemPorId(Long codigo) {

        return curiosidadeService.buscarCuriosidadePorId(codigo);

    }

    public List<CuriosidadeDTO> listarMensagens() {

        return curiosidadeService.listarTodasCuriosidades();

    }

    public List<CandidatoDTO> listarCandidatosPorCurso(Long curso) {

        return candidatoService.listarCandidatosPorCurso(curso);

    }

    public List<CandidatoDTO> listarCandidatosPorBairro(String bairro) {

        return candidatoService.listarCandidatosPorBairro(bairro);

    }

    public List<CandidatoDTO> listarCandidatosOrdenadosPorCurso() {

        return candidatoService.listarCandidatosOrdenadosPorCurso();

    }

    public List<CandidatoDTO> listarCandidatosOrdenadosPorBairro() {

        return candidatoService.listarCandidatosOrdenadosPorBairro();

    }

    public List<CandidatoDTO> listar10PrimeirosCandidatos() {

        return candidatoService.listar10PrimeirosCandidatos();

    }

    public List<CandidatoDTO> listar10UltimosCandidatos() {

        return candidatoService.listar10UltimosCandidatos();

    }

    public List<String> listarBairrosDistintos() {

        return candidatoService.listarBairrosDistintos();

    }

}