package br.edu.fatecl.av2labbd.av2_labbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controllers mínimos apenas para visualizar as telas do front-end
 * durante o desenvolvimento. Substitua por implementações reais depois.
 */
@Controller
public class FrontendTestController {

    // ==================== PÁGINA INICIAL ====================
    @GetMapping("/")
    public String index() {
        return "index_html";
    }

    // ==================== ESCOLHA DO TIME ====================
    @GetMapping("/escolhaTime")
    public String escolhaTime() {
        return "escolhaTime_html";
    }

    // ==================== CURIOSIDADE ====================
    @GetMapping("/curiosidade")
    public String curiosidade(@RequestParam(required = false) Long timeId, Model model) {
        // Dados fake para teste
        String nomeTime = "Corinthians"; // Você pode mudar baseado no timeId
        String curiosidade = "O Corinthians foi o primeiro time brasileiro a conquistar o Mundial de Clubes da FIFA, em 2000, vencendo o Vasco da Gama na final.";

        if (timeId != null) {
            switch (timeId.intValue()) {
                case 1: nomeTime = "Corinthians"; break;
                case 2: nomeTime = "Palmeiras";
                    curiosidade = "O Palmeiras é o maior campeão do Campeonato Brasileiro, com 11 títulos conquistados.";
                    break;
                case 3: nomeTime = "Santos";
                    curiosidade = "O Santos FC revelou Pelé, considerado por muitos o maior jogador de futebol de todos os tempos.";
                    break;
                case 4: nomeTime = "São Paulo";
                    curiosidade = "O São Paulo é o clube brasileiro com mais títulos internacionais oficiais, com 12 conquistas.";
                    break;
            }
        }

        model.addAttribute("nomeTime", nomeTime);
        model.addAttribute("curiosidade", curiosidade);
        return "curiosidade";
    }

    // ==================== CADASTRO DE CANDIDATO ====================
    @GetMapping("/cadastroCandidato")
    public String cadastroCandidatoForm(Model model) {
        // Objeto vazio para o formulário
        model.addAttribute("candidato", new CandidatoMock());

        // Lista fake de cursos para testar o select
        List<CursoMock> cursos = new ArrayList<>();
        cursos.add(new CursoMock(1L, "Análise e Desenvolvimento de Sistemas"));
        cursos.add(new CursoMock(2L, "Gestão Empresarial"));
        cursos.add(new CursoMock(3L, "Logística"));
        cursos.add(new CursoMock(4L, "Comércio Exterior"));
        cursos.add(new CursoMock(5L, "Gestão da Produção Industrial"));

        model.addAttribute("cursos", cursos);
        return "cadastroCandidato";
    }

    @GetMapping("/cadastroCandidato/sucesso")
    public String cadastroSucesso(Model model) {
        model.addAttribute("candidato", new CandidatoMock());
        model.addAttribute("cursos", new ArrayList<>());
        model.addAttribute("sucesso", true);
        return "cadastroCandidato";
    }

    // ==================== LOGIN ADMIN ====================
    @GetMapping("/loginAdmin")
    public String loginAdmin() {
        return "loginAdmin";
    }

    // ==================== MENU ADMIN ====================
    @GetMapping("/adminMenu")
    public String adminMenu(Model model) {
        // Estatísticas fake
        model.addAttribute("totalCandidatos", 42L);
        model.addAttribute("totalCuriosidades", 60L);
        model.addAttribute("totalVisualizacoes", 15L);
        return "adminMenu";
    }

    // ==================== CADASTRO TIPO (CURIOSIDADES) ====================
    @GetMapping("/cadastroTipo")
    public String cadastroTipo(Model model) {
        // Objeto vazio para cadastro
        model.addAttribute("curiosidade", new CuriosidadeMock());

        // Lista fake de times
        List<TimeMock> times = new ArrayList<>();
        times.add(new TimeMock(1L, "Corinthians"));
        times.add(new TimeMock(2L, "Palmeiras"));
        times.add(new TimeMock(3L, "Santos"));
        times.add(new TimeMock(4L, "São Paulo"));
        model.addAttribute("times", times);

        // Lista fake de curiosidades cadastradas
        List<CuriosidadeMock> curiosidades = new ArrayList<>();
        CuriosidadeMock c1 = new CuriosidadeMock();
        c1.setId(1L);
        c1.setTime(times.get(0));
        c1.setMensagem("O Corinthians foi fundado em 1910 por trabalhadores do bairro do Bom Retiro.");
        curiosidades.add(c1);

        CuriosidadeMock c2 = new CuriosidadeMock();
        c2.setId(2L);
        c2.setTime(times.get(1));
        c2.setMensagem("O Palmeiras tem o maior número de títulos nacionais entre os clubes brasileiros.");
        curiosidades.add(c2);

        model.addAttribute("curiosidades", curiosidades);
        return "cadastroTipo";
    }

    @GetMapping("/cadastroTipo/editar/{id}")
    public String editarCuriosidade(@PathVariable Long id, Model model) {
        // Simula carregar uma curiosidade para edição
        CuriosidadeMock curiosidade = new CuriosidadeMock();
        curiosidade.setId(id);
        curiosidade.setMensagem("Curiosidade de exemplo para edição");

        List<TimeMock> times = new ArrayList<>();
        times.add(new TimeMock(1L, "Corinthians"));
        times.add(new TimeMock(2L, "Palmeiras"));
        curiosidade.setTime(times.get(0));

        model.addAttribute("curiosidade", curiosidade);
        model.addAttribute("times", times);
        model.addAttribute("curiosidades", new ArrayList<>());
        return "cadastroTipo";
    }

    @GetMapping("/cadastroTipo/excluir/{id}")
    public String excluirCuriosidade(@PathVariable Long id) {
        // Aqui você implementaria a exclusão real
        // curiosidadeService.excluir(id);
        return "redirect:/cadastroTipo";
    }

    // ==================== CONSULTA CANDIDATOS ====================
    @GetMapping("/consultaCandidatos")
    public String consultaCandidatos(Model model) {
        // Lista fake de cursos para o filtro
        List<CursoMock> cursos = new ArrayList<>();
        cursos.add(new CursoMock(1L, "Análise e Desenvolvimento de Sistemas"));
        cursos.add(new CursoMock(2L, "Gestão Empresarial"));
        cursos.add(new CursoMock(3L, "Logística"));

        model.addAttribute("cursos", cursos);
        return "consultaCandidatos";
    }

    @GetMapping("/consultaCandidatos/curso")
    public String consultaPorCurso(@RequestParam Long cursoId, Model model) {
        List<CursoMock> cursos = new ArrayList<>();
        cursos.add(new CursoMock(1L, "Análise e Desenvolvimento de Sistemas"));

        // Lista fake de candidatos
        List<CandidatoMock> candidatos = new ArrayList<>();
        CandidatoMock c1 = new CandidatoMock();
        c1.setId(1L);
        c1.setNome("João Silva");
        c1.setEmail("joao@email.com");
        c1.setTelefone("(11) 98765-4321");
        c1.setBairro("Vila Matilde");
        c1.setCurso(cursos.get(0));
        c1.setDataCadastro(LocalDateTime.now());
        candidatos.add(c1);

        CandidatoMock c2 = new CandidatoMock();
        c2.setId(2L);
        c2.setNome("Maria Santos");
        c2.setEmail("maria@email.com");
        c2.setTelefone("(11) 99876-5432");
        c2.setBairro("Penha");
        c2.setCurso(cursos.get(0));
        c2.setDataCadastro(LocalDateTime.now());
        candidatos.add(c2);

        model.addAttribute("candidatos", candidatos);
        model.addAttribute("cursos", cursos);
        model.addAttribute("tipoConsulta", "Por Curso: ADS");
        return "consultaCandidatos";
    }

    @GetMapping("/consultaCandidatos/todos-por-curso")
    public String todosPorCurso(Model model) {
        model.addAttribute("cursos", new ArrayList<>());
        model.addAttribute("candidatos", getCandidatosFake());
        model.addAttribute("tipoConsulta", "Todos ordenados por Curso");
        return "consultaCandidatos";
    }

    @GetMapping("/consultaCandidatos/todos-por-bairro")
    public String todosPorBairro(Model model) {
        model.addAttribute("cursos", new ArrayList<>());
        model.addAttribute("candidatos", getCandidatosFake());
        model.addAttribute("tipoConsulta", "Todos ordenados por Bairro");
        return "consultaCandidatos";
    }

    @GetMapping("/consultaCandidatos/primeiros")
    public String primeiros(Model model) {
        model.addAttribute("cursos", new ArrayList<>());
        model.addAttribute("candidatos", getCandidatosFake());
        model.addAttribute("tipoConsulta", "10 Primeiros Cadastrados");
        return "consultaCandidatos";
    }

    @GetMapping("/consultaCandidatos/ultimos")
    public String ultimos(Model model) {
        model.addAttribute("cursos", new ArrayList<>());
        model.addAttribute("candidatos", getCandidatosFake());
        model.addAttribute("tipoConsulta", "10 Últimos Cadastrados");
        return "consultaCandidatos";
    }

    // ==================== CLASSES MOCK (temporárias) ====================

    // Classe interna temporária para simular Candidato
    public static class CandidatoMock {
        private Long id;
        private String nome;
        private String email;
        private String telefone;
        private String bairro;
        private CursoMock curso;
        private LocalDateTime dataCadastro;

        public CandidatoMock() {
            this.curso = new CursoMock();
        }

        // Getters e Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getTelefone() { return telefone; }
        public void setTelefone(String telefone) { this.telefone = telefone; }
        public String getBairro() { return bairro; }
        public void setBairro(String bairro) { this.bairro = bairro; }
        public CursoMock getCurso() { return curso; }
        public void setCurso(CursoMock curso) { this.curso = curso; }
        public LocalDateTime getDataCadastro() { return dataCadastro; }
        public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    }

    // Classe interna temporária para simular Curso
    public static class CursoMock {
        private Long id;
        private String nome;

        public CursoMock() {}
        public CursoMock(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
    }

    // Classe interna temporária para simular Time
    public static class TimeMock {
        private Long id;
        private String nome;

        public TimeMock() {}
        public TimeMock(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
    }

    // Classe interna temporária para simular Curiosidade
    public static class CuriosidadeMock {
        private Long id;
        private TimeMock time;
        private String mensagem;

        public CuriosidadeMock() {
            this.time = new TimeMock();
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public TimeMock getTime() { return time; }
        public void setTime(TimeMock time) { this.time = time; }
        public String getMensagem() { return mensagem; }
        public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    }

    // Método auxiliar para gerar candidatos fake
    private List<CandidatoMock> getCandidatosFake() {
        List<CandidatoMock> candidatos = new ArrayList<>();
        CursoMock curso = new CursoMock(1L, "ADS");

        for (int i = 1; i <= 5; i++) {
            CandidatoMock c = new CandidatoMock();
            c.setId((long) i);
            c.setNome("Candidato " + i);
            c.setEmail("candidato" + i + "@email.com");
            c.setTelefone("(11) 9" + String.format("%04d", i) + "-" + String.format("%04d", i));
            c.setBairro("Bairro " + i);
            c.setCurso(curso);
            c.setDataCadastro(LocalDateTime.now().minusDays(i));
            candidatos.add(c);
        }

        return candidatos;
    }
}