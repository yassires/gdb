CREATE DATABASE gdb;

USE gdb;

CREATE TABLE author (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255)
);

CREATE TABLE book (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       author_id INT,
                       isbn VARCHAR(13) unique,
                       category VARCHAR(255),
                       release_date DATE,
                       quantity INT,
                       available INT,
                       borrow INT,
                       lost INT,
                       FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE borrower (
                          id INT auto_increment primary key,
                          name varchar(255) not null ,
                          uid varchar(255) not null,
                          cin varchar(255)
);

CREATE TABLE borrowingRecords(
                                 id int auto_increment PRIMARY KEY,
                                 bookIsbn varchar(13),
                                 uid varchar(255),
                                 borrowingDate DATE,
                                 returnBorrowingDate DATE,
                                 FOREIGN KEY (bookIsbn) REFERENCES book(isbn),
                                 FOREIGN KEY (uid) REFERENCES borrower(uid)
);

CREATE TRIGGER tt
    before insert on borrower
    for each row
    set new.name = UPPER(new.name);



