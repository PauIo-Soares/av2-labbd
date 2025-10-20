package br.edu.fatecl.av2labbd.av2_labbd.controller;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CursoDTO;
import br.edu.fatecl.av2labbd.av2_labbd.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public String criarCurso(@RequestBody CursoDTO curso) {

        return cursoService.criarCurso(curso);

    }

    @GetMapping("/{id}")
    public CursoDTO buscarCurso(@PathVariable Long id) {

        return cursoService.buscarCursoPorId(id);

    }

    @PutMapping
    public String modificarCurso(@RequestBody CursoDTO curso) {

        return cursoService.atualizarCurso(curso);

    }

    @DeleteMapping("/{id}")
    public String excluirCurso(@PathVariable Long id) {

        return cursoService.deletarCurso(id);

    }

    @GetMapping
    public List<CursoDTO> listarCursos() {

        return cursoService.listarTodosCursos();

    }

}