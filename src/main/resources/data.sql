DROP TABLE IF EXISTS message;

CREATE TABLE message (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    parent_id INT default null,
    sender VARCHAR(250) NOT NULL,
    recipient VARCHAR(250) NOT NULL,
    text VARCHAR(250) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

INSERT INTO message (sender, recipient, text, parent_id, created_at) VALUES
('Adam', 'Blake', 'Hi There!', null, '2018-07-14T00:34:37.000+00:00'),
('Blake', 'Adam', 'Hi!', 1, CURRENT_TIME),
('Adam', 'Blake', 'Hi There!', 2 ,CURRENT_TIME + INTERVAL '10' MINUTE),
('Blake', 'Adam', 'How are you?', 3,CURRENT_TIME + INTERVAL '11' MINUTE),
('John', 'Cole', 'Where are you?', null, current_time  + interval '12' MINUTE),
('Carl', 'Adam', 'Hola', null, current_time + interval '20' MINUTE),
('Blake', 'Adam', 'Long Time No See!', 4, current_time + interval '25' MINUTE )