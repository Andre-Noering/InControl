package com.entra21.LojaSimulator.view.repository;

import com.entra21.LojaSimulator.model.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    public FuncionarioEntity findByLogin(String login);

    public List<FuncionarioEntity> findAllById(Long id);

    public boolean existsByLogin(String login);
}
