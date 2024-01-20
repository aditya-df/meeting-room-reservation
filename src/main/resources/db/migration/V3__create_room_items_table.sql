CREATE TABLE room_items (
  id integer AUTO_INCREMENT PRIMARY KEY,
  room_id integer,
  name varchar(255)
);

ALTER TABLE room_items ADD FOREIGN KEY (room_id) REFERENCES rooms (id);