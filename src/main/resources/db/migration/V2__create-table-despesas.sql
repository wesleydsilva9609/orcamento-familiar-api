create table despesas(
despesas_id bigint not null auto_increment,
despesas_descricao varchar(250) not null,
despesas_valor DECIMAL(10,2) not null,
despesas_data DATE not null,

primary key(despesas_id)

);