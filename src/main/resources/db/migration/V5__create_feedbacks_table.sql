CREATE TABLE feedbacks (
  id integer,
  reservation_id integer,
  attendances varchar(255),
  meeting_notes text
);

ALTER TABLE feedbacks ADD FOREIGN KEY (reservation_id) REFERENCES reservations (id);