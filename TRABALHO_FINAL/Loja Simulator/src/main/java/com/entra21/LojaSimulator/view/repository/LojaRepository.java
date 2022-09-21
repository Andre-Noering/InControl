package com.entra21.LojaSimulator.view.repository;

import com.entra21.LojaSimulator.model.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<LojaEntity, Long> {
    LojaEntity findByRazaoSocial(String razaoSocial);

    void deleteByRazaoSocial(String razaoSocial);
}
