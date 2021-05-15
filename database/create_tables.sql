CREATE TABLE
    user
(
    id            BIGINT  NOT NULL,
    first_name    VARCHAR NOT NULL,
    last_name     VARCHAR NOT NULL,
    email         VARCHAR,
    phone         VARCHAR,
    date_of_birth DATETIME,
    address       VARCHAR,

    PRIMARY KEY (id)
);

CREATE TABLE
    profile
(
    id       BIGINT  NOT NULL,
    authUserName VARCHAR NOT NULL,
    details VARCHAR NULL,
    userId BIGINT,
    FOREIGN KEY(userId) REFERENCES user(id)
    PRIMARY KEY (id)
);

CREATE TABLE
    hibernate_sequence
(
    next_val BIGINT
);