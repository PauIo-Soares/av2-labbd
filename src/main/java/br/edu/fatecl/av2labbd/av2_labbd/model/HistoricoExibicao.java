package br.edu.fatecl.av2labbd.av2_labbd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tb_candidatos")
@Data
public class HistoricoExibicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataHoraExibicao;

}