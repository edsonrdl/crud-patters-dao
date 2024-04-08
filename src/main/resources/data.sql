CREATE TABLE client (
  code_client bigint AUTO_INCREMENT PRIMARY KEY,
  nome varchar(25) NOT NULL,
  CPF varchar(11) NOT NULL,
  idade int NOT NULL
);

INSERT INTO client (nome, CPF, idade) VALUES ('Pedro', '45678912345', 40);
INSERT INTO client (nome, CPF, idade) VALUES ('Ana', '01234567890', 35);
INSERT INTO client (nome, CPF, idade) VALUES ('Carlos', '54321678901', 28);
INSERT INTO client (nome, CPF, idade) VALUES ('Juliana', '32109876543', 42);
INSERT INTO client (nome, CPF, idade) VALUES ('Marcos', '67890123456', 31);
