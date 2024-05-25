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

CREATE TABLE users_chat
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT REFERENCES users (id),
    chat_id BIGINT REFERENCES chat (id),
    created_at TIMESTAMP NOT NULL ,
    created_by VARCHAR(128) NOT NULL,
    UNIQUE (user_id, chat_id)
);

CREATE TABLE chat
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
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