# CRUDJDBC
CRUD, JDBC, DAO
Canal usado como referência: https://www.youtube.com/@ProfessorJosedeAssis

Script SQL:

create database dbagenda;
use dbagenda;
create table contatos(
idcon int primary key auto_increment,
nome varchar(50) not null,
fone varchar(15) not null,
email varchar(50)
);

/*CRUD CREATE */
insert into contatos (nome,fone,email) values ('Marcos Soares', '1234-5678', 'marcos@gmail.com');

/*CRUD READ */
select * from contatos;
select * from contatos order by nome;
select * from contatos where idcon = 2;

/* CRUD UPDATE */
update contatos set nome='Adriano Rodrigues' where idcon = 2;
update contatos set nome='Adriano Rodrigues Júnior', fone = '1111-2222', email = 'adriano@gmail.com' where idcon = 2;

/* CRUD DELETE */
delete from contatos where idcon =4;
