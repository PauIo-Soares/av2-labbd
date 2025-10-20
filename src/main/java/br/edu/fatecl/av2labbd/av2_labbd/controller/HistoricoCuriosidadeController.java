package br.edu.fatecl.av2labbd.av2_labbd.controller;

import br.edu.fatecl.av2labbd.av2_labbd.dto.HistoricoCuriosidadeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.service.HistoricoCuriosidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/HistoricoCuriosidade")
public class HistoricoCuriosidadeController {

    @Autowired
    private HistoricoCuriosidadeService historicoCuriosidade;

    @PostMapping
    public String criarHistoricoCuriosidade(@RequestBody HistoricoCuriosidadeDTO HistoricoCuriosidade) {

        return historicoCuriosidade.criarHistoricoCuriosidade(HistoricoCuriosidade);

    }

    @GetMapping("/{id}")
    public HistoricoCuriosidadeDTO buscarHistoricoCuriosidade(@PathVariable Long id) {

        return historicoCuriosidade.buscarHistoricoCuriosidadePorId(id);

    }

    @PutMapping
    public String modificarHistoricoCuriosidade(@RequestBody HistoricoCuriosidadeDTO HistoricoCuriosidade) {

        return historicoCuriosidade.atualizarHistoricoCuriosidade(HistoricoCuriosidade);

    }

    @DeleteMapping("/{id}")
    public String excluirHistoricoCuriosidade(@PathVariable Long id) {

        return historicoCuriosidade.deletarHistoricoCuriosidade(id);

    }

    @GetMapping
    public List<HistoricoCuriosidadeDTO> listarHistoricoCuriosidades() {

        return historicoCuriosidade.listarHistoricoCuriosidades();

    }

}