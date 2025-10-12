package br.edu.fatecl.av2labbd.av2_labbd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_mensagens")
@Data
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String conteudo;

    @Column(nullable = false)
    private String categoria;

}