CREATE TABLE reservations (
  id integer PRIMARY KEY,
  request_user integer,
  room_id integer,
  requestDate date,
  startTime time,
  endTime time,
  status ENUM ('pending', 'approved', 'rejected'),
  request_snack varchar(255)
);

ALTER TABLE reservations ADD FOREIGN KEY (room_id) REFERENCES rooms (id);

ALTER TABLE reservations ADD FOREIGN KEY (request_user) REFERENCES users (id);