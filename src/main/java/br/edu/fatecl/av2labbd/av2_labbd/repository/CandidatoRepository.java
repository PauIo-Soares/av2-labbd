package br.edu.fatecl.av2labbd.av2_labbd.repository;

import br.edu.fatecl.av2labbd.av2_labbd.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    @Query("Select c from Candidato c WHERE c.cursoInteresse.nome = :nomeCurso")
    List<Candidato> listarCandidatosPorCurso(@Param("nomeCurso") String nomeCurso);

    List<Candidato> findByBairro(String bairro);

    @Query("Select c from Candidato c ORDER BY c.cursoInteresse.nome ASC")
    List<Candidato> listarCandidatosOrdenadosPorCurso();

    @Query("Select c from Candidato c ORDER BY c.bairro ASC")
    List<Candidato> listarCandidatosOrdenadosPorBairro();

    @Query(value = "Select TOP 10 * from tb_candidatos ORDER BY id ASC", nativeQuery = true)
    List<Candidato> listar10PrimeirosCandidatos();

    @Query(value = "Select TOP 10 * from tb_candidatos ORDER BY id DESC", nativeQuery = true)
    List<Candidato> listar10UltimosCandidatos();

}