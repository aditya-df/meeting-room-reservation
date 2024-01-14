CREATE TABLE room_items (
  id integer,
  room_id integer,
  name varchar(255)
);

ALTER TABLE room_items ADD FOREIGN KEY (room_id) REFERENCES rooms (id);