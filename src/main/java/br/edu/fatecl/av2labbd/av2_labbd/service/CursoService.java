package br.edu.fatecl.av2labbd.av2_labbd.service;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CursoDTO;
import br.edu.fatecl.av2labbd.av2_labbd.model.Curso;
import br.edu.fatecl.av2labbd.av2_labbd.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public String criarCurso(CursoDTO dto) {

        Curso curso = new Curso();

        curso.setNome(dto.getNome());

        cursoRepository.save(curso);

        return "Curso Criado com Sucesso";

    }

    public CursoDTO buscarCursoPorId(Long codigo) {

        Curso curso = cursoRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        return new CursoDTO(codigo, curso.getNome());

    }

    public String atualizarCurso(CursoDTO dto) {

        Curso curso = cursoRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        if (dto.getNome() != null) curso.setNome(dto.getNome());

        cursoRepository.save(curso);

        return "Curso Atualizado com Sucesso";

    }

    public String deletarCurso(Long codigo) {

        cursoRepository.deleteById(codigo);

        return "Curso Deletado com Sucesso";

    }

    public List<CursoDTO> listarTodosCursos() {

        List<Curso> listaEntidades = cursoRepository.findAll();
        List<CursoDTO> resposta = new ArrayList<>();

        for (Curso i : listaEntidades) {
            resposta.add(new CursoDTO(i.getId(), i.getNome()));
        }

        return resposta;

    }

}