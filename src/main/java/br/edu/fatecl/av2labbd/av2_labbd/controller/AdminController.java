package br.edu.fatecl.av2labbd.av2_labbd.controller;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CandidatoDTO;
import br.edu.fatecl.av2labbd.av2_labbd.dto.CuriosidadeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.service.AdminService;
import br.edu.fatecl.av2labbd.av2_labbd.service.CuriosidadeService;
import br.edu.fatecl.av2labbd.av2_labbd.service.CursoService;
import br.edu.fatecl.av2labbd.av2_labbd.service.TimeService;
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

    @GetMapping("/cadastra-mensagem")
    public String cadastraTipo() {
        // colocar na model a lista de curiosidades, times, etc
        return "cadastraTipo";

    }

    @GetMapping("/consulta-candidatos")
    public String consultaCandidatos() {
        // colocar na model as listas de cursos e bairros para os filtros
        return "consultaCandidatos";

    }

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

                case "buscar":
                    CuriosidadeDTO encontrada = curiosidadeService.buscarCuriosidadePorId(curiosidade.getId());
                    model.addAttribute("curiosidade", encontrada);
                    break;

                case "atualizar":
                    saida = curiosidadeService.atualizarCuriosidade(curiosidade);
                    break;

                case "excluir":
                    saida = curiosidadeService.deletarCuriosidade(curiosidade.getId());
                    break;

                case "listar":
                    lista = curiosidadeService.listarTodasCuriosidades();
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

    @GetMapping("/candidatos/curso")
    public String consultarCandidatosPorCurso(@RequestParam String curso, Model model) {

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