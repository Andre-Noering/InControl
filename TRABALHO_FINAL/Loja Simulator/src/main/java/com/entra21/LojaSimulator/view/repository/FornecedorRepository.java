package com.entra21.LojaSimulator.view.repository;


import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity,Long> {
}
