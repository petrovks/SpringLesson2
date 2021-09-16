DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE IF NOT EXISTS products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
                                     ('milk', 80),
                                     ('bread', 24),
                                     ('butter', 220),
                                     ('cheese', 350),
                                     ('coca-cola', 69);
