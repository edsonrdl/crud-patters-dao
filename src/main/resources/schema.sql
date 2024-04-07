CREATE TABLE IF NOT EXISTS `client` (
  `code_cliente` bigint AUTO_INCREMENT PRIMARY KEY,
  `nome` varchar(255) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `idade` int NOT NULL
);

INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('João da Silva', '12345678901', 30);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Maria Souza', '98765432109', 25);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Pedro Oliveira', '45678912345', 40);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Ana Santos', '01234567890', 35);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Carlos Pereira', '54321678901', 28);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Juliana Lima', '32109876543', 42);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Marcos Silva', '67890123456', 31);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Fernanda Costa', '45678901234', 27);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Lucas Martins', '89012345678', 38);
INSERT INTO `client` (`nome`, `cpf`, `idade`) VALUES ('Patrícia Sousa', '23456789012', 33);
