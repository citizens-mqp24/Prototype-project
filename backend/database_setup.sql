PRAGMA foreign_keys = ON;

drop table if exists Message;
drop table if exists User;
drop table if exists Test;

CREATE TABLE User (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

CREATE TABLE Message (
      message_id INTEGER PRIMARY KEY AUTOINCREMENT,
      user_id INTEGER NOT NULL,
      message_text TEXT NOT NULL,
      timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES User(user_id)
);

create table Test (
    testID number
);

insert into Test values (123);
insert into Test values (9000000);
insert into Test values (456);

insert into User (name) values ('Alice');
INSERT INTO User (name) VALUES ('Bob');
INSERT INTO User (name) VALUES ('Charlie');

INSERT INTO Message (user_id, message_text) VALUES (1, 'Hello, this is Alice!');
INSERT INTO Message (user_id, message_text) VALUES (2, 'Hey Alice, this is Bob.');
INSERT INTO Message (user_id, message_text) VALUES (3, 'Hi everyone, Charlie here.');

select * from Test;
select * from User;
select * from Message;
