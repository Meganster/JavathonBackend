CREATE EXTENSION IF NOT EXISTS citext;

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY ,
  imei INTEGER NOT NULL ,
  username citext,
  last_latitude REAL,
  last_longtitude REAL ,
  is_visible boolean default false
);