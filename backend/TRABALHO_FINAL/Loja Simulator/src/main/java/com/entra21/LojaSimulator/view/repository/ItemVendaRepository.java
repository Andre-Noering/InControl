package com.entra21.LojaSimulator.view.repository;

import com.entra21.LojaSimulator.model.entity.ItemVendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVendaEntity, Long> {

    List<ItemVendaEntity> findAllByVenda_Id(Long id);
}
