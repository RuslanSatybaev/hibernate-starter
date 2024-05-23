CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY ,
    firstname  VARCHAR(128),
    lastname   VARCHAR(128),
    birth_date DATE,
    username   VARCHAR(128) UNIQUE,
    role       VARCHAR(32),
    info       JSONB,
    company_id INT REFERENCES company (id)
);

CREATE TABLE profile
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users (id),
    street VARCHAR(128),
    language CHAR(2)
);

CREATE TABLE company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);