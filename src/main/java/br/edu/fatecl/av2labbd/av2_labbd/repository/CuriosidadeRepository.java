package br.edu.fatecl.av2labbd.av2_labbd.repository;

import br.edu.fatecl.av2labbd.av2_labbd.model.Curiosidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuriosidadeRepository extends JpaRepository<Curiosidade, Long> {

    @Procedure(name = "sp_buscar_curiosidade_aleatoria")
    String buscarCuriosidadeAleatoria(@Param("time_id") Long timeId);

    List<Curiosidade> findByTimeId(Long timeId);

}