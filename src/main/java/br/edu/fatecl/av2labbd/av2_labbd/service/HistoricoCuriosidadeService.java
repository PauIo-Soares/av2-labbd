package br.edu.fatecl.av2labbd.av2_labbd.service;

import br.edu.fatecl.av2labbd.av2_labbd.dto.HistoricoCuriosidadeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.model.HistoricoCuriosidade;
import br.edu.fatecl.av2labbd.av2_labbd.repository.HistoricoCuriosidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoCuriosidadeService {

    @Autowired
    private HistoricoCuriosidadeRepository historicoCuriosidadeRepository;

    public String criarHistoricoCuriosidade(HistoricoCuriosidadeDTO dto) {

        HistoricoCuriosidade historicoCuriosidade = new HistoricoCuriosidade();

        historicoCuriosidade.setCuriosidade(dto.getCuriosidade());
        historicoCuriosidade.setDataHoraExibicao(dto.getDataHoraExibicao());

        historicoCuriosidadeRepository.save(historicoCuriosidade);

        return "Historico de Curiosidade Criado com Sucesso";

    }

    public HistoricoCuriosidadeDTO buscarHistoricoCuriosidadePorId(Long codigo) {

        HistoricoCuriosidade historicoCuriosidade = historicoCuriosidadeRepository.findById(codigo).orElseThrow(() -> new RuntimeException("HistoricoCuriosidade não encontrado"));

        return new HistoricoCuriosidadeDTO(codigo, historicoCuriosidade.getCuriosidade(), historicoCuriosidade.getDataHoraExibicao());

    }

    public String atualizarHistoricoCuriosidade(HistoricoCuriosidadeDTO dto) {

        HistoricoCuriosidade historicoCuriosidade = historicoCuriosidadeRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("HistoricoCuriosidade não encontrado"));

        if (dto.getCuriosidade() != null) historicoCuriosidade.setCuriosidade(dto.getCuriosidade());
        if(dto.getDataHoraExibicao() != null) historicoCuriosidade.setDataHoraExibicao(dto.getDataHoraExibicao());

        historicoCuriosidadeRepository.save(historicoCuriosidade);

        return "Historico de Curiosidade Atualizado com Sucesso";

    }

    public String deletarHistoricoCuriosidade(Long codigo) {

        historicoCuriosidadeRepository.deleteById(codigo);

        return "Historico de Curiosidade Deletado com Sucesso";

    }

    public List<HistoricoCuriosidadeDTO> listarHistoricoCuriosidades() {

        List<HistoricoCuriosidade> listaEntidades = historicoCuriosidadeRepository.findAll();
        List<HistoricoCuriosidadeDTO> resposta = new ArrayList<>();

        for (HistoricoCuriosidade i : listaEntidades) {
            resposta.add(new HistoricoCuriosidadeDTO(i.getId(), i.getCuriosidade(), i.getDataHoraExibicao()));
        }

        return resposta;

    }

}