
CREATE TABLE store (
    store_id VARCHAR(255) PRIMARY KEY,                -- Assuming store id is a Long
    name VARCHAR(255),                    -- Assuming store has a name
    location VARCHAR(255)                 -- Assuming store has a location
);

CREATE TABLE product (
    article_id VARCHAR(255) PRIMARY KEY,  -- Assuming articleId is a String
    description VARCHAR(255),             -- Assuming description is a String
    brand VARCHAR(255),                   -- Assuming brand is a String
    model VARCHAR(255),                   -- Assuming model is a String
    uom VARCHAR(255),                      -- Assuming uom (unit of measure) is a String
    store_id VARCHAR(255),                      -- Foreign key to Store table
    CONSTRAINT fk_store FOREIGN KEY (store_id) REFERENCES store(store_id)  -- Foreign key constraint
);

CREATE TABLE price (
    id BIGINT PRIMARY KEY,                -- Assuming price has a Long id
    type VARCHAR(255),                    -- Assuming price type is a String
    subtype VARCHAR(255),                 -- Assuming price subtype is a String
    currency VARCHAR(255),                -- Assuming price currency is a String
    amount DOUBLE PRECISION,              -- Assuming price amount is a Double
    valid_from TIMESTAMP,                 -- Assuming valid_from is a Timestamp (LocalDateTime)
    valid_to TIMESTAMP,                   -- Assuming valid_to is a Timestamp (LocalDateTime)
    product_article_id VARCHAR(255),      -- Foreign key to Product table
    CONSTRAINT fk_product FOREIGN KEY (product_article_id) REFERENCES product(article_id)  -- Foreign key constraint
);

INSERT INTO store (store_id, name, location) VALUES ('134567890', 'Store A', 'New York');
INSERT INTO store (store_id, name, location) VALUES ('24567890', 'Store B', 'Los Angeles');
INSERT INTO store (store_id, name, location) VALUES ('35467890', 'Store C', 'Chicago');


INSERT INTO product (article_id, description, brand, model, uom, store_id)
VALUES ('P001', 'Product 1 Description', 'Brand X', 'Model A', 'pcs', '134567890');
INSERT INTO product (article_id, description, brand, model, uom, store_id)
VALUES ('P002', 'Product 2 Description', 'Brand Y', 'Model B', 'box', '24567890');
INSERT INTO product (article_id, description, brand, model, uom, store_id)
VALUES ('P003', 'Product 3 Description', 'Brand Z', 'Model C', 'pcs', '35467890');

INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (1, 'Retail', 'Standard', 'USD', 89.99, '2023-12-12T23:59:59Z', '9999-12-31T23:59:59Z', 'P001');
INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (2, 'Wholesale', 'Bulk', 'USD', 89.99, '2023-12-21T23:59:59Z', '2025-12-31T23:59:58Z', 'P001');
INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (3, 'Retail', 'Standard', 'USD', 129.99, '2023-12-21T23:59:59Z', '2025-12-25T23:59:58Z', 'P001');
INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (4, 'Retail', 'Standard', 'USD', 99.99, '2023-12-31T23:59:59Z', '9999-12-31T23:59:59Z', 'P002');
INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (5, 'Wholesale', 'Bulk', 'USD', 89.99, '2023-12-21T23:59:59Z', '2025-12-31T23:59:58Z', 'P002');
INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (6, 'Retail', 'Standard', 'USD', 129.99, '2023-12-21T23:59:59Z', '2025-12-25T23:59:58Z', 'P002');

INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (7, 'Retail', 'Standard', 'USD', 99.99, '2023-12-31T23:59:59Z', '9999-12-31T23:59:59Z', 'P003');
INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (8, 'Wholesale', 'Bulk', 'USD', 89.99, '2023-12-21T23:59:59Z', '2025-12-31T23:59:58Z', 'P003');
INSERT INTO price (id, type, subtype, currency, amount, valid_from, valid_to, product_article_id)
VALUES (9, 'Retail', 'Standard', 'USD', 129.99, '2023-12-21T23:59:59Z', '2025-12-25T23:59:58Z', 'P003');


--insert into Price values(1,'retail','regular', 'CAD', 30.0, '2023-12-31T23:59:59Z', '9999-12-31T23:59:59Z' );
--insert into Price values(2,'retail','discounted', 'CAD', 27.0, '2023-12-31T23:59:59Z', '2025-12-31T23:59:58Z' );
--insert into Price values(3,'retail','discounted', 'CAD', 26.5, '2023-12-21T23:59:59Z', '2025-12-25T23:59:58Z' );
--
--
--
--insert into Product values ('1000102674', 'WH Halifax Passage Lever in Satin Nickel', 'Weiser', '9GLA1010', 'EA', '7001');
--
--
--insert into Store values ('7001' )