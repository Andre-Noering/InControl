package com.entra21.LojaSimulator.view.repository;

import com.entra21.LojaSimulator.model.entity.FuncionarioEntity;
import com.entra21.LojaSimulator.model.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaRepository extends JpaRepository<LojaEntity, Long> {
    LojaEntity findByRazaoSocial(String razaoSocial);

    void deleteByRazaoSocial(String razaoSocial);

    boolean existsByRazaoSocial(String razaoSocial);

    List<LojaEntity> findAllByGerente(FuncionarioEntity funcionarioEntity);
}
