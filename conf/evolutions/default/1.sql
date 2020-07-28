-- Users schema

-- !Ups

CREATE TABLE IF NOT EXISTS brands (
    name        VARCHAR NOT NULL    PRIMARY KEY,
    origin      VARCHAR NOT NULL
);
COMMENT ON TABLE brands IS 'Brands dictionary';

CREATE TABLE IF NOT EXISTS models (
    name        VARCHAR NOT NULL    PRIMARY KEY,
    startYear   INT     NOT NULL,
    endYear     INT     NOT NULL

);
COMMENT ON TABLE models IS 'Models dictionary';

CREATE TABLE IF NOT EXISTS products (
    id          SERIAL  NOT NULL    PRIMARY KEY,
    brand       VARCHAR NOT NULL,
    model       VARCHAR NOT NULL,
    startYear   INT     NOT NULL,
    mileage     INT,
    price       NUMERIC CHECK (price > 0)

);
COMMENT ON TABLE products IS 'Product items';


CREATE INDEX IX_products_model ON products (model);
CREATE INDEX IX_products_brand ON products (brand);

ALTER TABLE products ADD FOREIGN KEY     (brand)         REFERENCES brands(name);
ALTER TABLE products ADD FOREIGN KEY     (model)         REFERENCES models(name);

-- !Downs

DROP TABLE IF EXISTS brands;
DROP TABLE IF EXISTS models;
DROP TABLE IF EXISTS products;

DROP INDEX IF EXISTS IX_products_model;
DROP INDEX IF EXISTS IX_products_brand;
