DROP TABLE IF EXISTS message;

CREATE TABLE message (
    id uuid default random_uuid()  PRIMARY KEY,
    sender VARCHAR(250) NOT NULL,
    recipient VARCHAR(250) NOT NULL,
    text VARCHAR(250) NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp()
);

INSERT INTO message (sender, recipient, text, created_at) VALUES
('Adam', 'Blake', 'Hi There!', '2018-07-14T00:34:37.000+00:00'),
('Blake', 'Adam', 'Hi!', CURRENT_TIME),
('Adam', 'Blake', 'Hi There!', CURRENT_TIME + INTERVAL '10' MINUTE),
('Blake', 'Adam', 'How are you?', CURRENT_TIME + INTERVAL '11' MINUTE),
('John', 'Cole', 'Where are you?', current_time  + interval '12' MINUTE),
('Carl', 'Adam', 'Hola', current_time + interval '20' MINUTE),
('Blake', 'Adam', 'Long Time No See!', current_time + interval '25' MINUTE )