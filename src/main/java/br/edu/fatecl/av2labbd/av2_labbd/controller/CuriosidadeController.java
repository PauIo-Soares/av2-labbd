package br.edu.fatecl.av2labbd.av2_labbd.controller;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CuriosidadeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.service.CuriosidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/curiosidade")
public class CuriosidadeController {

    @Autowired
    private CuriosidadeService curiosidadeService;

    @GetMapping("/{timeId}")
    public String mostrarCuriosidade(@PathVariable Long timeId, Model model) {

        CuriosidadeDTO curiosidade = curiosidadeService.mostrarCuriosidadeAleatoria(timeId);
        model.addAttribute("curiosidade", curiosidade.getMensagem());

        return "curiosidade";

    }

}