CREATE TABLE CAR
(
    ID       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NOMBRE   VARCHAR(256) NOT NULL,
    MARCA    VARCHAR(256) NOT NULL,
    MODELO   VARCHAR(256) NOT NULL
);