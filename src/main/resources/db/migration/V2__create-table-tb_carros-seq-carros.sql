CREATE SEQUENCE SEQ_CARROS
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TBL_CARROS (
    CARRO_ID INTEGER DEFAULT SEQ_CARROS.NEXTVAL NOT NULL,
    MARCA VARCHAR(100) NOT NULL,
    MODELO VARCHAR(100) NOT NULL,
    PLACA VARCHAR(8) NOT NULL
);