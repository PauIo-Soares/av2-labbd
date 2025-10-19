package br.edu.fatecl.av2labbd.av2_labbd.dto;

import br.edu.fatecl.av2labbd.av2_labbd.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoDTO {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String bairro;

    private Curso cursoInteresse;

    private LocalDateTime dataHoraCadastro;

}
