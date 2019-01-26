create table if not exists friends(
	user_id bigint not null
		constraint fk43t41y3ifyaj2u398y82twk8a
			references users,
	friend_id bigint not null
		constraint fk7o3980stmrphchidesxi1pt3d
			references users,
	constraint friend_pkey
		primary key (user_id, friend_id)
);