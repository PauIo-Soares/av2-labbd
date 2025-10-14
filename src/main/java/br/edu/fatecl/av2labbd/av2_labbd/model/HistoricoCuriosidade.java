package br.edu.fatecl.av2labbd.av2_labbd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tb_historico_curiosidade")
@Data
public class HistoricoCuriosidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curiosidade_id", nullable = false)
    private Curiosidade curiosidade;

    @Column(name = "data_hora_exibicao", nullable = false)
    private LocalDate dataHoraExibicao;

}