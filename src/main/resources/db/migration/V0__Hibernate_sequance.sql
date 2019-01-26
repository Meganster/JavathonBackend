create table if not exists hibernate_sequences(
	sequence_name varchar(255) not null
		constraint hibernate_sequences_pkey
			primary key,
	next_val bigint
);