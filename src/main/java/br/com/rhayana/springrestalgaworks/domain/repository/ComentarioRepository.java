package br.com.rhayana.springrestalgaworks.domain.repository;

import br.com.rhayana.springrestalgaworks.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
