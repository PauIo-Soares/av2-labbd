package br.edu.fatecl.av2labbd.av2_labbd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tb_candidatos")
@Data
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cursoInteresse;

    @Column(nullable = false)
    private LocalDate dataCadastro;

}