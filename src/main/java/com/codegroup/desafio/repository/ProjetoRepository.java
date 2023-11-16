package com.codegroup.desafio.repository;

import com.codegroup.desafio.model.ProjetoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<ProjetoModel, Long> {
}
