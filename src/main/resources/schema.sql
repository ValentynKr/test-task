CREATE TABLE users
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age  VARCHAR(255)
);
CREATE TABLE articles
(
    id      BIGINT PRIMARY KEY,
    text    VARCHAR(255) NOT NULL,
    color   VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT fk_articles_users_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);
