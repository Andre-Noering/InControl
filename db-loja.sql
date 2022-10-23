-- loja_simulator.fornecedor definition

CREATE TABLE `fornecedor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `razao_social` varchar(50) NOT NULL,
  `cnpj` char(14) NOT NULL,
  `contato` varchar(50) NOT NULL,
  `id_loja` bigint NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `id_loja` (`id_loja`),
  CONSTRAINT `fornecedor_ibfk_1` FOREIGN KEY (`id_loja`) REFERENCES `loja` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.funcionario definition

CREATE TABLE `funcionario` (
  `login` varchar(250) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `id_pessoa` bigint NOT NULL,
  `ativo` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id_pessoa`),
  UNIQUE KEY `funcionario_un` (`login`),
  CONSTRAINT `funcionario_FK` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.item definition

CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `valor` decimal(8,2) NOT NULL,
  `qtde_estoque` int NOT NULL,
  `qtde_alerta_estoque` int DEFAULT NULL,
  `id_loja` bigint NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `id_loja` (`id_loja`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_loja`) REFERENCES `loja` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.item_fornecedor definition

CREATE TABLE `item_fornecedor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_fornecedor` bigint NOT NULL,
  `id_item` bigint NOT NULL,
  `valor_compra` decimal(8,2) NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `id_fornecedor` (`id_fornecedor`),
  KEY `id_item` (`id_item`),
  CONSTRAINT `item_fornecedor_ibfk_1` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id`),
  CONSTRAINT `item_fornecedor_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.item_venda definition

CREATE TABLE `item_venda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_venda` bigint NOT NULL,
  `id_item` bigint NOT NULL,
  `qtde` int NOT NULL,
  `valor_unitario` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_venda` (`id_venda`),
  KEY `id_item` (`id_item`),
  CONSTRAINT `item_venda_ibfk_1` FOREIGN KEY (`id_venda`) REFERENCES `venda` (`id`),
  CONSTRAINT `item_venda_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.loja definition

CREATE TABLE `loja` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `razao_social` varchar(50) NOT NULL,
  `cnpj` char(14) NOT NULL,
  `contato` varchar(50) NOT NULL,
  `valor_caixa` decimal(8,2) NOT NULL,
  `id_funcionario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_funcionario` (`id_funcionario`),
  CONSTRAINT `loja_ibfk_1` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.pedido_compra definition

CREATE TABLE `pedido_compra` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `id_funcionario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pedido_compra_FK` (`id_funcionario`),
  CONSTRAINT `pedido_compra_FK` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.pedido_compra_item_fornecedor definition

CREATE TABLE `pedido_compra_item_fornecedor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_item_fornecedor` bigint NOT NULL,
  `id_pedido_compra` bigint NOT NULL,
  `valor_unitario` decimal(8,2) NOT NULL,
  `qtde` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_item_fornecedor` (`id_item_fornecedor`),
  KEY `id_pedido_compra` (`id_pedido_compra`),
  CONSTRAINT `pedido_compra_item_fornecedor_ibfk_1` FOREIGN KEY (`id_item_fornecedor`) REFERENCES `item_fornecedor` (`id`),
  CONSTRAINT `pedido_compra_item_fornecedor_ibfk_2` FOREIGN KEY (`id_pedido_compra`) REFERENCES `pedido_compra` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.pessoa definition

CREATE TABLE `pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  `cpf` char(11) NOT NULL,
  `id_loja` bigint DEFAULT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `id_loja` (`id_loja`),
  CONSTRAINT `pessoa_ibfk_1` FOREIGN KEY (`id_loja`) REFERENCES `loja` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- loja_simulator.venda definition

CREATE TABLE `venda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `id_cliente` bigint NOT NULL,
  `id_vendedor` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `venda_FK` (`id_vendedor`),
  CONSTRAINT `venda_FK` FOREIGN KEY (`id_vendedor`) REFERENCES `funcionario` (`id_pessoa`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;