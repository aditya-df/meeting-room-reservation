CREATE TABLE feedbacks (
  id integer AUTO_INCREMENT PRIMARY KEY,
  reservation_id integer,
  attendances varchar(255),
  meeting_notes text
);

ALTER TABLE feedbacks ADD FOREIGN KEY (reservation_id) REFERENCES reservations (id);