CREATE TABLE contact (
  contact_id INT auto_increment,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  phone_number varchar(10) DEFAULT NULL
  PRIMARY KEY  (contact_id),
)
