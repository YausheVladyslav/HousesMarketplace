CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    second_name VARCHAR NOT NULL,
    email       VARCHAR NOT NULL,
    balance     BIGINT,
    role        VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

CREATE TABLE activate_code
(
    id         BIGSERIAL PRIMARY KEY,
    user_email VARCHAR(128) NOT NULL,
    code       VARCHAR(32)  NOT NULL,
    created_on DATE         NOT NULL
);

CREATE TABLE penthouse
(
    id                   BIGSERIAL PRIMARY KEY,
    user_id              BIGINT REFERENCES users,
    title                VARCHAR,
    price                BIGINT,
    penthouse_type       VARCHAR,
    country              VARCHAR,
    city                 VARCHAR,
    street               VARCHAR,
    size                 INT,
    room                 INT,
    bedroom              INT,
    bathroom             INT,
    furnishing           VARCHAR,
    year_of_construction INT,
    created_on           DATE,
    has_terrace          BOOLEAN DEFAULT FALSE,
    has_bar              BOOLEAN DEFAULT FALSE,
    has_balcony          BOOLEAN DEFAULT FALSE,
    has_pool             BOOLEAN DEFAULT FALSE,
    has_hot_tub          BOOLEAN DEFAULT FALSE
);