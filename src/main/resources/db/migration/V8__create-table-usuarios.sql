CREATE TABLE usuarios (
                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          usuario VARCHAR(250) NOT NULL,
                          senha VARCHAR(250) NOT NULL
);
