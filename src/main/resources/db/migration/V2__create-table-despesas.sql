CREATE TABLE despesas (
                          despesas_id BIGSERIAL PRIMARY KEY,
                          despesas_descricao VARCHAR(250) NOT NULL,
                          despesas_valor DECIMAL(10,2) NOT NULL,
                          despesas_data DATE NOT NULL
);
