CREATE TABLE question
(
	id int auto_increment PRIMARY KEY,
	title VARCHAR(50),
	description TEXT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator int,
	comment_count INT DEFAULT 0,
	view_count int DEFAULT 0,
	like_count int DEFAULT 0,
	tags VARCHAR(256)
);