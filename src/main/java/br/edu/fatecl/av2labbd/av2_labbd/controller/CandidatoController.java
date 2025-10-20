package br.edu.fatecl.av2labbd.av2_labbd.controller;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CandidatoDTO;
import br.edu.fatecl.av2labbd.av2_labbd.dto.CursoDTO;
import br.edu.fatecl.av2labbd.av2_labbd.service.CandidatoService;
import br.edu.fatecl.av2labbd.av2_labbd.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/candidato")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {

        model.addAttribute("candidato", new CandidatoDTO());

        List<CursoDTO> cursos = cursoService.listarTodosCursos();
        model.addAttribute("cursos", cursos);

        return "cadastroCandidato";

    }

    // TODO Arrumar
    @PostMapping("/cadastro")
    public String cadastrarCandidato(@ModelAttribute CandidatoDTO candidato, Model model) {

        try {
            String mensagem = candidatoService.inserirCandidato(candidato);
            model.addAttribute("sucesso", mensagem);
            model.addAttribute("candidato", new CandidatoDTO());
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("candidato", candidato);
        }

        List<CursoDTO> cursos = cursoService.listarTodosCursos();
        model.addAttribute("cursos", cursos);

        return "cadastroCandidato";

    }

}