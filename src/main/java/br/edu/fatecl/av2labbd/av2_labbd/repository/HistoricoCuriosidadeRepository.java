package br.edu.fatecl.av2labbd.av2_labbd.repository;

import br.edu.fatecl.av2labbd.av2_labbd.model.HistoricoCuriosidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoCuriosidadeRepository extends JpaRepository<HistoricoCuriosidade, Long> {
}