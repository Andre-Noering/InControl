package com.entra21.LojaSimulator.view.repository;
import com.entra21.LojaSimulator.model.entity.ItemFornecedorEntity;
import com.entra21.LojaSimulator.model.entity.PedidoCompraItemFornecedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCompraItemFornecedorRepository extends JpaRepository<PedidoCompraItemFornecedorEntity, Long>{

}