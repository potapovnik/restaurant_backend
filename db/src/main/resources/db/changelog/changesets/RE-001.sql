CREATE TABLE ingredients(
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(50) NOT NULL,
                            measure VARCHAR(50) NOT NULL,
                            value DOUBLE PRECISION NOT NULL,
                            expirationDate TIMESTAMP NOT NULL
);
CREATE TABLE dishes(
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       cost DOUBLE PRECISION NOT NULL,
                       type VARCHAR(50) NOT NULL,
                       ismenu BOOLEAN NOT NULL
);
CREATE TABLE dish_ingredient(
                                id SERIAL PRIMARY KEY,
                                dishId INTEGER NOT NULL,
                                value DOUBLE PRECISION NOT NULL,
                                ingredientId INTEGER NOT NULL,
                                FOREIGN KEY (dishId) REFERENCES dishes (id),
                                FOREIGN KEY (ingredientId) REFERENCES ingredients (id)
);
