
CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY ,
  imei BIGINT NOT NULL ,
  vk_id BIGINT NOT NULL ,
  username VARCHAR(255),
  last_latitude DOUBLE PRECISION,
  last_longitude DOUBLE PRECISION,
  is_visible boolean default false,
  last_seen_date timestamp,
  token VARCHAR (255),
  recovery_code VARCHAR (8)
);