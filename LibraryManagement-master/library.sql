CREATE DATABASE library;

use library;

CREATE TABLE Books ( book_id varchar(10)  PRIMARY KEY, book_name varchar(60) NOT NULL, author varchar(60) NOT NULL, status int NOT NULL, issued_to varchar(10) );

CREATE TABLE Transactions ( trans_id int NOT NULL AUTO_INCREMENT, book_id varchar(10) NOT NULL,sroll varchar(15) NOT NULL,  sName varchar(100) NOT NULL, cur_status int NOT NULL, due_date DATE,PRIMARY KEY(trans_id), FOREIGN KEY (book_id) REFERENCES Books(book_id) );


 insert into Transactions(book_id,sroll,sName,cur_status,due_date) values('2','1','Harsh',1,'2019-09-29');




