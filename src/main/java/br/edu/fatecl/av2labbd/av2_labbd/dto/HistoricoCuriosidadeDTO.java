package br.edu.fatecl.av2labbd.av2_labbd.dto;

import br.edu.fatecl.av2labbd.av2_labbd.model.Curiosidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoCuriosidadeDTO {

    private Long id;

    private Curiosidade curiosidade;

    private LocalDate dataHoraExibicao;

}