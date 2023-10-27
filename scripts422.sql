CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    driver_license BOOLEAN NOT NULL
);
CREATE TABLE auto (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL,
    user_id SERIAL REFERENCES user (id),
);