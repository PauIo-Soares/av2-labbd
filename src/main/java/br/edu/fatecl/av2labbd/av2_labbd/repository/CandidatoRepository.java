package br.edu.fatecl.av2labbd.av2_labbd.repository;

import br.edu.fatecl.av2labbd.av2_labbd.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    @Query(value = "Select TOP 10 * from tb_candidatos ORDER BY id DESC", nativeQuery = true)
    List<Candidato> listaUltimosCandidatos();

    @Query(value = "Select TOP 10 * from tb_candidatos ORDER BY id ASC", nativeQuery = true)
    List<Candidato> listaPrimeirosCandidatos();

    @Query("Select c from Candidato c WHERE c.cursoInteresse.nome = :nomeCurso")
    List<Candidato> listaPorNomeCurso(@Param("nomeCurso") String nomeCurso);

    @Query("Select c from Candidato c WHERE c.bairro = :nomeBairro")
    List<Candidato> listaPorBairro(@Param("nomeBairro") String nomeBairro);

    @Query("Select c from Candidato c ORDER BY c.cursoInteresse.nome ASC")
    List<Candidato> listaTodosPorCurso();

    @Query("Select c from Candidato c ORDER BY c.bairro ASC")
    List<Candidato> listaTodosPorBairro();

}