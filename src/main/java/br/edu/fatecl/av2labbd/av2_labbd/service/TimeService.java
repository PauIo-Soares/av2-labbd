package br.edu.fatecl.av2labbd.av2_labbd.service;

import br.edu.fatecl.av2labbd.av2_labbd.dto.TimeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.model.Time;
import br.edu.fatecl.av2labbd.av2_labbd.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public String criarTime(TimeDTO dto) {

        Time time = new Time();

        time.setNome(dto.getNome());

        timeRepository.save(time);

        return "Time Criado com Sucesso";

    }

    public TimeDTO buscarTimePorId(Long codigo) {

        Time time = timeRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Time não encontrado"));

        return new TimeDTO(codigo, time.getNome());

    }

    public String atualizarTime(TimeDTO dto) {

        Time time = timeRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Time não encontrado"));

        if (dto.getNome() != null) time.setNome(dto.getNome());

        timeRepository.save(time);

        return "Time Atualizado com Sucesso";

    }

    public String deletarTime(Long codigo) {

        timeRepository.deleteById(codigo);

        return "Time Deletado com Sucesso";

    }

    public List<TimeDTO> listarTodosTimes() {

        List<Time> listaEntidades = timeRepository.findAll();
        List<TimeDTO> resposta = new ArrayList<>();

        for (Time i : listaEntidades) {
            resposta.add(new TimeDTO(i.getId(), i.getNome()));
        }

        return resposta;

    }

    public long count() {

        return timeRepository.count();

    }

    public Time findByNome(String nomeTime) {

        return timeRepository.findByNome(nomeTime);

    }

}