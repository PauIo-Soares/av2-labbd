package br.edu.fatecl.av2labbd.av2_labbd.service;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CandidatoDTO;
import br.edu.fatecl.av2labbd.av2_labbd.model.Candidato;
import br.edu.fatecl.av2labbd.av2_labbd.repository.CandidatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Transactional
    public String inserirCandidato(CandidatoDTO dto) {

        Candidato candidato = new Candidato();

        candidato.setNome(dto.getNome());
        candidato.setEmail(dto.getEmail());
        candidato.setTelefone(dto.getTelefone());
        candidato.setBairro(dto.getBairro());
        candidato.setCursoInteresse(dto.getCursoInteresse());
        candidato.setDataHoraCadastro(LocalDateTime.now());
        candidatoRepository.save(candidato);

        return "Candidato Inserido com Sucesso";

    }

    public CandidatoDTO buscarCandidatoPorId(Long codigo) {

        Candidato candidato = candidatoRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        return new CandidatoDTO(codigo, candidato.getNome(), candidato.getEmail(), candidato.getTelefone(), candidato.getBairro(), candidato.getCursoInteresse(), candidato.getDataHoraCadastro());

    }

    @Transactional
    public String modificarCandidato(CandidatoDTO dto) {

        Candidato candidato = candidatoRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        if (dto.getNome() != null) candidato.setNome(dto.getNome());
        if (dto.getEmail() != null) candidato.setEmail(dto.getEmail());
        if (dto.getTelefone() != null) candidato.setTelefone(dto.getTelefone());
        if (dto.getBairro() != null) candidato.setBairro(dto.getBairro());
        if (dto.getCursoInteresse() != null) candidato.setCursoInteresse(dto.getCursoInteresse());

        candidatoRepository.save(candidato);

        return "Candidato Modificado com Sucesso";

    }

    @Transactional
    public String deletarCandidato(Long codigo) {

        candidatoRepository.deleteById(codigo);

        return "Candidato Deletado com Sucesso";

    }

    public List<CandidatoDTO> listarTodosCandidatos() {


        List<Candidato> listaEntidades = candidatoRepository.findAll();
        List<CandidatoDTO> resposta = new ArrayList<>();

        for (Candidato i : listaEntidades) {
            resposta.add(new CandidatoDTO(i.getId(), i.getNome(), i.getEmail(), i.getTelefone(), i.getBairro(), i.getCursoInteresse(), i.getDataHoraCadastro()));
        }

        return resposta;

    }

    public List<CandidatoDTO> listarCandidatosPorCurso(Long curso) {

        List<Candidato> lista = candidatoRepository.listarCandidatosPorCursoId(curso);
        List<CandidatoDTO> resposta = new ArrayList<>();

        for (Candidato i : lista) {
            resposta.add(new CandidatoDTO(i.getId(), i.getNome(), i.getEmail(), i.getTelefone(), i.getBairro(), i.getCursoInteresse(), i.getDataHoraCadastro()));
        }

        return resposta;

    }

    public List<CandidatoDTO> listarCandidatosPorBairro(String bairro) {

        List<Candidato> lista = candidatoRepository.findByBairro(bairro);
        List<CandidatoDTO> resposta = new ArrayList<>();

        for (Candidato i : lista) {
            resposta.add(new CandidatoDTO(i.getId(), i.getNome(), i.getEmail(), i.getTelefone(), i.getBairro(), i.getCursoInteresse(), i.getDataHoraCadastro()));
        }

        return resposta;

    }

    public List<CandidatoDTO> listarCandidatosOrdenadosPorCurso() {

        List<Candidato> lista = candidatoRepository.listarCandidatosOrdenadosPorCurso();
        List<CandidatoDTO> resposta = new ArrayList<>();

        for (Candidato i : lista) {
            resposta.add(new CandidatoDTO(i.getId(), i.getNome(), i.getEmail(), i.getTelefone(), i.getBairro(), i.getCursoInteresse(), i.getDataHoraCadastro()));
        }

        return resposta;

    }

    public List<CandidatoDTO> listarCandidatosOrdenadosPorBairro() {

        List<Candidato> lista = candidatoRepository.listarCandidatosOrdenadosPorBairro();
        List<CandidatoDTO> resposta = new ArrayList<>();

        for (Candidato i : lista) {
            resposta.add(new CandidatoDTO(i.getId(), i.getNome(), i.getEmail(), i.getTelefone(), i.getBairro(), i.getCursoInteresse(), i.getDataHoraCadastro()));
        }

        return resposta;

    }

    public List<CandidatoDTO> listar10PrimeirosCandidatos() {

        List<Candidato> lista = candidatoRepository.listar10PrimeirosCandidatos();
        List<CandidatoDTO> resposta = new ArrayList<>();

        for (Candidato i : lista) {
            resposta.add(new CandidatoDTO(i.getId(), i.getNome(), i.getEmail(), i.getTelefone(), i.getBairro(), i.getCursoInteresse(), i.getDataHoraCadastro()));
        }

        return resposta;

    }

    public List<CandidatoDTO> listar10UltimosCandidatos() {

        List<Candidato> lista = candidatoRepository.listar10UltimosCandidatos();
        List<CandidatoDTO> resposta = new ArrayList<>();

        for (Candidato i : lista) {
            resposta.add(new CandidatoDTO(i.getId(), i.getNome(), i.getEmail(), i.getTelefone(), i.getBairro(), i.getCursoInteresse(), i.getDataHoraCadastro()));
        }

        return resposta;

    }

    public List<String> listarBairrosDistintos() {

        return candidatoRepository.findDistinctBairros();

    }

}