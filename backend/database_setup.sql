PRAGMA foreign_keys = ON;

drop table if exists Message;
drop table if exists User;
drop table if exists Test;
drop table if exists likes;

CREATE TABLE User (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    EMAIL TEXT NOT NULL UNIQUE,
    PICTURE TEXT NOT NULL
);

CREATE TABLE likes (
    user_id INTEGER,
    message_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (message_id) REFERENCES Message(message_id)
);

CREATE TABLE Message (
      message_id INTEGER PRIMARY KEY AUTOINCREMENT,
      likes INTEGER DEFAULT 0 NOT NULL,
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

insert into User (name,EMAIL,PICTURE) values ('Alice','Alice@gmail.com','');
INSERT INTO User (name,EMAIL,PICTURE)VALUES ('Bob','Bob@gmail.com','');
INSERT INTO User (name,EMAIL,PICTURE) VALUES ('Charlie','Charlie@gmail.com','');

INSERT INTO Message (user_id, likes, message_text) VALUES ( 1,1,'Hello, this is Alice!');
INSERT INTO Message (user_id, likes, message_text) VALUES ( 2,2,'Hey Alice, this is Bob.');
INSERT INTO Message (user_id, likes, message_text) VALUES ( 3,3,'Hi everyone, Charlie here.');

INSERT INTO likes (user_id, message_id) VALUES (1,1);
INSERT INTO likes (user_id, message_id) VALUES (1,2);
INSERT INTO likes (user_id, message_id) VALUES (2,2);
INSERT INTO likes (user_id, message_id) VALUES (1,3);
INSERT INTO likes (user_id, message_id) VALUES (2,3);
INSERT INTO likes (user_id, message_id) VALUES (3,3);

select * from Test;
select * from User;
select * from Message;
