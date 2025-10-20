package br.edu.fatecl.av2labbd.av2_labbd.controller;

import br.edu.fatecl.av2labbd.av2_labbd.service.CuriosidadeService;
import br.edu.fatecl.av2labbd.av2_labbd.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @Autowired
    private CuriosidadeService curiosidadeService;

    @GetMapping("/escolha")
    public String escolherTime(Model model) {

        curiosidadeService.validarTabelasPopuladas();

        return "escolhaTime";

    }

}