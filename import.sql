BEGIN;
DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE IF NOT EXISTS products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int, user_id bigserial);
INSERT INTO products (title, cost, user_id) VALUES
                                     ('milk', 80, 1),
                                     ('bread', 24, 1),
                                     ('butter', 220, 2),
                                     ('cheese', 350, 2),
                                     ('coca-cola', 69, 1);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE IF NOT EXISTS users (id bigserial PRIMARY KEY, login VARCHAR(255));
INSERT INTO users (login) VALUES ('kon'), ('pet');
COMMIT;