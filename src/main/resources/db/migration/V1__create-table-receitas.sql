CREATE TABLE receitas (
                          id BIGSERIAL PRIMARY KEY,
                          descricao VARCHAR(250) NOT NULL,
                          valor DECIMAL(10,2) NOT NULL,
                          data_receita DATE NOT NULL
);