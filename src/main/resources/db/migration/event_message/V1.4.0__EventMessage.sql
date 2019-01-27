create table if not exists event_message(
	id bigserial
			primary key,
	create_time timestamp,
	description varchar(255),
	latitude DOUBLE PRECISION,
  longitude DOUBLE PRECISION,
	author_id bigint
		constraint fk_myx62ewfe86t7ecqi7wjixjwk
			references users
);