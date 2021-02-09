INSERT INTO CARGO(nome) values ('Diretor'), ('Analista de Sistemas'), ('RH');
INSERT INTO PERFIL(nome) values ('Admin'), ('T.I'), ('RH'), ('Convidado');

INSERT INTO USUARIO(nome, cpf, data_nascimento, sexo, data_criacao, cargo_id) VALUES ('Ramon Lisboa', '12345678910','1989-12-07', 'M', NOW(), 2);
