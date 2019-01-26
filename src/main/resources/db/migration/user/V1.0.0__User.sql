
CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY ,
  imei BIGINT NOT NULL ,
  username VARCHAR(255),
  last_latitude DOUBLE PRECISION ,
  last_longitude DOUBLE PRECISION ,
  is_visible boolean default false,
  last_seen_date TIMESTAMP WITH TIME ZONE,
  friends BIGINT REFERENCES users(id)
);

-- create table if not exists friends(
-- 	user_id bigint not null
-- 		constraint fk43t41y3ifyaj2u398y82twk8a
-- 			references users,
-- 	friend_id bigint not null
-- 		constraint fk7o3980stmrphchidesxi1pt3d
-- 			references users,
-- 	constraint friends_pkey
-- 		primary key (user_id, friend_id)
-- );