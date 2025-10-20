package br.edu.fatecl.av2labbd.av2_labbd.controller;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CandidatoDTO;
import br.edu.fatecl.av2labbd.av2_labbd.dto.CuriosidadeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CuriosidadeService curiosidadeService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CandidatoService candidatoService;

    @GetMapping("/login")
    public String loginForm() {

        return "loginAdmin";

    }

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String senha, Model model) {

        try {
            adminService.autenticar(login, senha);
            return "redirect:/admin/menu";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return "loginAdmin";
        }

    }

    @GetMapping("/menu")
    public String menu() {

        return "adminMenu";

    }

    @GetMapping("/cadastraTipo")
    public String cadastraTipo(Model model) {

        model.addAttribute("curiosidade", new CuriosidadeDTO());
        model.addAttribute("curiosidades", curiosidadeService.listarTodasCuriosidades());
        model.addAttribute("times", timeService.listarTodosTimes());

        return "cadastraTipo";

    }

//    @GetMapping("/consultaCandidatos")
//    public String consultaCandidatos(Model model) {
//
//        model.addAttribute("filtro", new CandidatoDTO());
//        model.addAttribute("cursos", cursoService.listarTodosCursos());
//        model.addAttribute("bairros", candidatoService.listarBairrosDistintos());
//
//        return "consultaCandidatos";
//
//    }

    @GetMapping("/consultaCandidatos")
    public String consultaCandidatos(Model model) {

        model.addAttribute("candidatos", candidatoService.listarTodosCandidatos());
        model.addAttribute("cursos", cursoService.listarTodosCursos());
        model.addAttribute("bairros", candidatoService.listarBairrosDistintos()); // se tiver o filtro por bairro

        return "consultaCandidatos"; // nome do template Thymeleaf
    }


    // TODO tirar metodo filtrar
    @PostMapping("/cadastra-mensagem")
    public String handleAction(@ModelAttribute("curiosidade") CuriosidadeDTO curiosidade, @RequestParam("action") String action, Model model) {

        String saida = null;
        List<CuriosidadeDTO> lista = null;

        try {
            switch (action.toLowerCase()) {
                case "inserir":
                case "cadastrar":
                    saida = curiosidadeService.criarCuriosidade(curiosidade);
                    break;

                case "atualizar":
                    saida = curiosidadeService.atualizarCuriosidade(curiosidade);
                    break;

                case "listar":
                    lista = curiosidadeService.listarTodasCuriosidades();
                    break;

                case "filtrar":
                    if (curiosidade.getTime() != null && curiosidade.getTime().getId() != null) {
                        lista = curiosidadeService.listarCuriosidadesPorTime(curiosidade.getTime().getId());
                    } else {
                        lista = curiosidadeService.listarTodasCuriosidades();
                    }
                    break;

                default:
                    saida = "Ação desconhecida.";
                    break;
            }

            if (saida != null && !saida.isBlank()) {
                model.addAttribute("sucesso", saida);
            }

        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("curiosidade", curiosidade);
        }

        model.addAttribute("curiosidades", lista != null ? lista : curiosidadeService.listarTodasCuriosidades());

        model.addAttribute("times", timeService.listarTodosTimes());

        if (!"buscar".equalsIgnoreCase(action)) {
            model.addAttribute("curiosidade", new CuriosidadeDTO());
        }

        return "cadastraTipo";

    }

    // TODO resolver
    @PostMapping("/cadastra-mensagem/filtrar")
    public String filtrarMensagens(@RequestParam Long timeId, Model model) {
        List<CuriosidadeDTO> mensagens = curiosidadeService.listarCuriosidadesPorTime(timeId);
        model.addAttribute("mensagens", mensagens);
        return "cadastraTipo";
    }


    @GetMapping("/candidatos/curso")
    public String consultarCandidatosPorCurso(@RequestParam("cursoId") Long curso, Model model) {

        try {
            List<CandidatoDTO> candidatos = adminService.listarCandidatosPorCurso(curso);
            model.addAttribute("candidatos", candidatos);
            model.addAttribute("tipoConsulta", "Por Curso: " + curso);
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
        }

        model.addAttribute("cursos", cursoService.listarTodosCursos());
        model.addAttribute("bairros", adminService.listarBairrosDistintos());

        return "consultaCandidatos";

    }

    @GetMapping("/candidatos/bairro")
    public String consultarCandidatosPorBairro(@RequestParam String bairro, Model model) {

        try {
            List<CandidatoDTO> candidatos = adminService.listarCandidatosPorBairro(bairro);
            model.addAttribute("candidatos", candidatos);
            model.addAttribute("tipoConsulta", "Por Bairro: " + bairro);
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
        }

        model.addAttribute("cursos", cursoService.listarTodosCursos());
        model.addAttribute("bairros", adminService.listarBairrosDistintos());

        return "consultaCandidatos";

    }

    @GetMapping("/candidatos/ordenar-curso")
    public String ordenarCandidatosPorCurso(Model model) {

        try {
            List<CandidatoDTO> candidatos = adminService.listarCandidatosOrdenadosPorCurso();
            model.addAttribute("candidatos", candidatos);
            model.addAttribute("tipoConsulta", "Todos ordenados por Curso");
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
        }

        model.addAttribute("cursos", cursoService.listarTodosCursos());
        model.addAttribute("bairros", adminService.listarBairrosDistintos());

        return "consultaCandidatos";

    }

    @GetMapping("/candidatos/ordenar-bairro")
    public String ordenarCandidatosPorBairro(Model model) {

        try {
            List<CandidatoDTO> candidatos = adminService.listarCandidatosOrdenadosPorBairro();
            model.addAttribute("candidatos", candidatos);
            model.addAttribute("tipoConsulta", "Todos ordenados por Bairro");
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
        }

        model.addAttribute("cursos", cursoService.listarTodosCursos());
        model.addAttribute("bairros", adminService.listarBairrosDistintos());

        return "consultaCandidatos";

    }

    @GetMapping("/candidatos/primeiros")
    public String consultar10Primeiros(Model model) {

        try {
            List<CandidatoDTO> candidatos = adminService.listar10PrimeirosCandidatos();
            model.addAttribute("candidatos", candidatos);
            model.addAttribute("tipoConsulta", "10 Primeiros Cadastrados");
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
        }

        model.addAttribute("cursos", cursoService.listarTodosCursos());
        model.addAttribute("bairros", adminService.listarBairrosDistintos());

        return "consultaCandidatos";

    }

    @GetMapping("/candidatos/ultimos")
    public String consultar10Ultimos(Model model) {

        try {
            List<CandidatoDTO> candidatos = adminService.listar10UltimosCandidatos();
            model.addAttribute("candidatos", candidatos);
            model.addAttribute("tipoConsulta", "10 Últimos Cadastrados");
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
        }

        model.addAttribute("cursos", cursoService.listarTodosCursos());
        model.addAttribute("bairros", adminService.listarBairrosDistintos());

        return "consultaCandidatos";

    }

}