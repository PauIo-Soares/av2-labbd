package br.edu.fatecl.av2labbd.av2_labbd.repository;

import br.edu.fatecl.av2labbd.av2_labbd.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

    Time findByNome(String nomeTime);

}