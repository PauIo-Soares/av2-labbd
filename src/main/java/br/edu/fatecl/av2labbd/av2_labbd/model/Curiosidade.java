package br.edu.fatecl.av2labbd.av2_labbd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_curiosidades")
@Data
public class Curiosidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

}