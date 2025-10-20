package br.edu.fatecl.av2labbd.av2_labbd.service;

import br.edu.fatecl.av2labbd.av2_labbd.dto.CuriosidadeDTO;
import br.edu.fatecl.av2labbd.av2_labbd.model.Curiosidade;
import br.edu.fatecl.av2labbd.av2_labbd.model.Time;
import br.edu.fatecl.av2labbd.av2_labbd.repository.CuriosidadeRepository;
import br.edu.fatecl.av2labbd.av2_labbd.repository.TimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class CuriosidadeService {

    @Autowired
    private CuriosidadeRepository curiosidadeRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Transactional
    public String criarCuriosidade(CuriosidadeDTO dto) {

        Curiosidade curiosidade = new Curiosidade();

        curiosidade.setMensagem(dto.getMensagem());
        curiosidade.setTime(dto.getTime());
        curiosidadeRepository.save(curiosidade);

        return "Curiosidade Criada com Sucesso";

    }

    public CuriosidadeDTO buscarCuriosidadePorId(Long codigo) {

        Curiosidade curiosidade = curiosidadeRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Curiosidade não encontrada"));

        return new CuriosidadeDTO(codigo, curiosidade.getMensagem(), curiosidade.getTime());
    }

    @Transactional
    public String atualizarCuriosidade(CuriosidadeDTO dto) {

        Curiosidade curiosidade = curiosidadeRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Curiosidade não encontrado"));

        if (dto.getMensagem() != null) curiosidade.setMensagem(dto.getMensagem());
        if (dto.getTime() != null) curiosidade.setTime(dto.getTime());

        curiosidadeRepository.save(curiosidade);

        return "Curiosidade Atualizada com Sucesso";

    }

    @Transactional
    public String deletarCuriosidade(Long codigo) {

        curiosidadeRepository.deleteById(codigo);

        return "Curiosidade Deletada com Sucesso";

    }

    public List<CuriosidadeDTO> listarTodasCuriosidades() {

        List<Curiosidade> listaEntidades = curiosidadeRepository.findAll();
        List<CuriosidadeDTO> resposta = new ArrayList<>();

        for (Curiosidade i : listaEntidades) {
            resposta.add(new CuriosidadeDTO(i.getId(), i.getMensagem(), i.getTime()));
        }

        return resposta;

    }


    // Não sei pra que preciso dessa
    // É um tchan a mais, acho q vou deixar
    // Criar o findByTimeId
    // TODO
    public List<CuriosidadeDTO> listarCuriosidadesPorTime(Long timeId) {
        List<Curiosidade> lista = curiosidadeRepository.findByTimeId(timeId);
        List<CuriosidadeDTO> resposta = new ArrayList<>();

        for (Curiosidade i : lista) {
            resposta.add(new CuriosidadeDTO(i.getId(), i.getMensagem(), i.getTime()));
        }

        return resposta;
    }

    // Nao deveria ser private?
    public void validarTabelasPopuladas() {

        long countTimes = timeRepository.count();

        if (countTimes == 0) popularTxt();

    }

    // Ver se está correto
    @Transactional
    private void popularTxt() {

        try {
            popularTimes();

            popularCuriosidades("Corinthians", "corinthians.txt");
            popularCuriosidades("Palmeiras", "palmeiras.txt");
            popularCuriosidades("Santos", "santos.txt");
            popularCuriosidades("São Paulo", "saopaulo.txt");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao popular tabelas: " + e.getMessage());
        }

    }

    private void popularTimes() throws IOException {

        File arquivo = ResourceUtils.getFile("classpath:txts/times.txt");
        List<String> linhas = Files.readAllLines(arquivo.toPath());

        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                Time time = new Time();
                time.setNome(linha.trim());
                timeRepository.save(time);
            }
        }

    }


    // Popula as curiosidades de um time específico
    // Ver se está correto
    private void popularCuriosidades(String nomeTime, String nomeArquivo) throws IOException {

        Time time = timeRepository.findByNome(nomeTime); // Teria que implementar no Repo
        if (time == null) throw new RuntimeException("Time " + nomeTime + " não encontrado");

        File arquivo = ResourceUtils.getFile("classpath:txts/" + nomeArquivo);
        List<String> linhas = Files.readAllLines(arquivo.toPath());

        // Salva cada curiosidade
        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                Curiosidade curiosidade = new Curiosidade();
                curiosidade.setTime(time);
                curiosidade.setMensagem(linha.trim());
                curiosidadeRepository.save(curiosidade);
            }
        }

    }

    @Transactional
    public CuriosidadeDTO mostrarCuriosidadeAleatoria(Long timeId) {

        String texto = curiosidadeRepository.buscarCuriosidadeAleatoria(timeId);
        CuriosidadeDTO dto = new CuriosidadeDTO();
        dto.setMensagem(texto);

        return dto;

    }


    // Ver se está correto
    private void cadastrarMensagemTxt(String mensagem, String nomeTime) {

        try {
            String nomeArquivo = nomeTime + ".txt";

            File arquivo = ResourceUtils.getFile("classpath:txts/" + nomeArquivo);

            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.newLine();
            bw.write(mensagem);

            bw.close();
            fw.close();

        } catch (Exception e) {
            System.err.println("Aviso: Não foi possível cadastrar mensagem no TXT: " + e.getMessage());
        }
    }

}