CREATE TABLE todos (
    id int unsigned AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(100) NOT NULL,
    done bit
);

INSERT INTO todos (text, done) VALUES ('Done todo', 1);
INSERT INTO todos (text, done) VALUES ('Undone todo', 0);