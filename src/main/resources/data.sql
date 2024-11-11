-- Добавление авторов
INSERT INTO author (id, first_name, last_name, birth_date)
VALUES (1, 'George', 'Orwell', '1903-06-25'),
       (2, 'Aldous', 'Huxley', '1894-07-26');

-- Добавление книг
INSERT INTO book (id, title, publication_year)
VALUES (1, '1984', 1949),
       (2, 'Brave New World', 1932);

-- Связь авторов и книг
INSERT INTO author_books (author_id, book_id)
VALUES (1, 1),
       (2, 2);

-- Добавление читателей
INSERT INTO reader (phone_number, first_name, last_name, gender, birth_date)
VALUES ('1234567890', 'John', 'Doe', 'Male', '1985-02-15'),
       ('0987654321', 'Jane', 'Smith', 'Female', '1990-08-24');

-- Добавление транзакций
INSERT INTO transaction (transaction_type, transaction_date_time, book_id, client_id)
VALUES ('BORROW', '2024-01-15 10:00:00', 1, '1234567890'),
       ('RETURN', '2024-01-20 15:00:00', 1, '1234567890'),
       ('BORROW', '2024-02-10 14:30:00', 2, '0987654321');