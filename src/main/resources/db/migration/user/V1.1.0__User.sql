
CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY ,
  imei VARCHAR(255),
  vk_id BIGINT NOT NULL ,
  username VARCHAR(255),
  last_latitude DOUBLE PRECISION,
  last_longitude DOUBLE PRECISION,
  is_visible boolean default true,
  is_visible_message boolean default true,
  last_seen_date timestamp,
  token VARCHAR (255),
  recovery_code VARCHAR (8)
);