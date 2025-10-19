package br.edu.fatecl.av2labbd.av2_labbd.dto;

import br.edu.fatecl.av2labbd.av2_labbd.model.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuriosidadeDTO {

    private Long id;

    private String mensagem;

    private Time time;

}