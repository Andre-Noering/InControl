package com.entra21.LojaSimulator.view.repository;

import com.entra21.LojaSimulator.model.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    public FuncionarioEntity findByLogin(String login);
}
