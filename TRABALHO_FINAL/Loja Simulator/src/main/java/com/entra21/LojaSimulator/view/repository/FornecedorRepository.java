package com.entra21.LojaSimulator.view.repository;


import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity,Long> {
    List<FornecedorEntity> findAllByLoja_Id(Long id);

    FornecedorEntity findByRazao_Social(String razao_social);
    List<FornecedorEntity> findAllByLoja_Razao_Social(String razao_social);

    void deleteByRazao_Social(String razao_social);
}
