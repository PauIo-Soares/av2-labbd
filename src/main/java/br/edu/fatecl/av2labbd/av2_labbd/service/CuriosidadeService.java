package br.edu.fatecl.av2labbd.av2_labbd.service;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CuriosidadeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.model.Curiosidade;
import br.edu.fatecl.av2labbd.av2_labbd.repository.CuriosidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuriosidadeService {

    @Autowired
    private CuriosidadeRepository curiosidadeRepository;

    @Transactional
    public String criarCuriosidade(CuriosidadeDTO dto) {

        Curiosidade curiosidade = new Curiosidade();

        curiosidade.setMensagem(dto.getMensagem());
        curiosidade.setTime(dto.getTime());
        curiosidadeRepository.save(curiosidade);

        return "Curiosidade Criada com Sucesso";

    }

    public CuriosidadeDTO buscarCuriosidadePorId(Long codigo) {

        Curiosidade curiosidade = curiosidadeRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Curiosidade não encontrada"));

        return new CuriosidadeDTO(codigo, curiosidade.getMensagem(), curiosidade.getTime());
    }

    @Transactional
    public String atualizarCuriosidade(CuriosidadeDTO dto) {

        Curiosidade curiosidade = curiosidadeRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Curiosidade não encontrado"));

        if (dto.getMensagem() != null) curiosidade.setMensagem(dto.getMensagem());
        if (dto.getTime() != null) curiosidade.setTime(dto.getTime());

        curiosidadeRepository.save(curiosidade);

        return "Curiosidade Atualizada com Sucesso";

    }

    @Transactional
    public String deletarCuriosidade(Long codigo) {

        curiosidadeRepository.deleteById(codigo);

        return "Curiosidade Deletada com Sucesso";

    }

    public List<CuriosidadeDTO> listarTodasCuriosidades() {

        List<Curiosidade> listaEntidades = curiosidadeRepository.findAll();
        List<CuriosidadeDTO> resposta = new ArrayList<>();

        for (Curiosidade i : listaEntidades) {
            resposta.add(new CuriosidadeDTO(i.getId(), i.getMensagem(), i.getTime()));
        }

        return resposta;

    }

}