CREATE TABLE category (
  id INTEGER IDENTITY PRIMARY KEY,
  description VARCHAR(50) NOT NULL,
  day_of_debit INTEGER NOT NULL
);

CREATE TABLE registration (
  id INTEGER IDENTITY PRIMARY KEY,
  registration_date TIMESTAMP NOT NULL
);

CREATE TABLE cost (
  id INTEGER IDENTITY PRIMARY KEY,
  amount DECIMAL(4,2),
  category_id INTEGER NOT NULL,
  registation_id INTEGER NOT NULL
);

ALTER TABLE cost ADD FOREIGN KEY (category_id) REFERENCES category(id);

ALTER TABLE cost ADD FOREIGN KEY (registation_id) REFERENCES registration(id);

ALTER TABLE cost ADD CONSTRAINT cost_uk1 UNIQUE (id, category_id);

ALTER TABLE cost ADD CONSTRAINT cost_uk2 UNIQUE (id, registation_id);