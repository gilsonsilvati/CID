insert into usuario (nome, email, cpf, senha, data_criacao, data_modificacao) values ('GILSON SILVA', 'gilsonsilvati@gmail.com', '997.997.901-15', sha2('121212', 256), now(), now());
insert into usuario (nome, email, cpf, senha, data_criacao, data_modificacao) values ('LIDIANE SANTOS', 'santoslid@gmail.com', '992.172.601-34', sha2('123456', 256), now(), now());
insert into usuario (nome, email, cpf, senha, data_criacao, data_modificacao) values ('GABRIEL ALVES', 'biel.show@live.com', '233.963.681-77', sha2('654321', 256), now(), now());

insert into permissao_usuario (codigo_usuario, email, data_criacao, data_modificacao, permissao) values (1, 'gilsonsilvati@gmail.com', now(), now(), 'ADMIN');
insert into permissao_usuario (codigo_usuario, email, data_criacao, data_modificacao, permissao) values (2, 'santoslid@gmail.com', now(), now(), 'USER');
insert into permissao_usuario (codigo_usuario, email, data_criacao, data_modificacao, permissao) values (3, 'biel.show@live.com', now(), now(), 'MEDICO');