create table receitas(
id bigint not null auto_increment,
descricao varchar(250) not null,
valor DECIMAL(10,2) not null,
data_receita DATE not null,

primary key(id)

);