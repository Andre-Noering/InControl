package com.entra21.LojaSimulator.view.repository;

import com.entra21.LojaSimulator.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>{

}
