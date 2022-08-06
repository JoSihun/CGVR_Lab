#################################################################
#						LAB DATABASE DDL						#
#################################################################

create table user (
	id integer primary key,						# 학번
    name varchar(10),							# 이름
    email varchar(40),							# 이메일
    contact varchar(13),						# 연락처
    role varchar(10)							# 5개 권한
);

create table tag (
	id integer auto_increment primary key,		# 태그 id
    content varchar(10)							# 태그 내용
);

create table general_notice_board(				# 일반 공지사항
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    tag_id integer,								# 태그 아이디
    foreign key (tag_id) references tag(id)
    on delete cascade on update cascade
);

create table general_notice_attachment(			# 일반 공지사항 첨부파일
	id integer,									# 게시글 id
    file varchar(200),							# 첨부파일
    primary key(id, file),
    foreign key (id) references general_notice_board(id)
    on delete cascade on update cascade
);

create table lecture_notice_board(				# 수업 공지사항
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    tag_id integer,								# 태그 아이디
    foreign key (tag_id) references tag(id)
    on delete cascade on update cascade
);

create table lecture_notice_attachment(			# 수업 공지사항 첨부파일
	id integer,									# 게시글 id
    file varchar(200),							# 첨부파일
    primary key(id, file),
    foreign key (id) references lecture_notice_board(id)
    on delete cascade on update cascade
);

create table laboratory_notice_board(			# 연구 공지사항
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    tag1_id integer,							# 1차 태그 아이디
    tag2_id integer,							# 2차 태그 아이디
    tag3_id integer,							# 3차 태그 아이디
    foreign key (tag1_id) references tag(id)
    on delete cascade on update cascade,
    foreign key (tag2_id) references tag(id)
    on delete cascade on update cascade,
    foreign key (tag3_id) references tag(id)
    on delete cascade on update cascade
);

create table laboratory_notice_attachment(		# 연구 공지사항 첨부파일
	id integer,									# 게시글 id
    file varchar(200),							# 첨부파일
    primary key(id, file),
    foreign key (id) references laboratory_notice_board(id)
    on delete cascade on update cascade
);

create table paper_board(						# 논문 게시판
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    tag1_id integer,							# 1차 태그 아이디
    tag2_id integer,							# 2차 태그 아이디
    foreign key (tag1_id) references tag(id)
    on delete cascade on update cascade,
    foreign key (tag2_id) references tag(id)
    on delete cascade on update cascade
);

create table paper_attachment(					# 논문 게시판 첨부파일
	id integer,									# 게시글 id
    file varchar(200),							# 첨부파일
    primary key(id, file),
    foreign key (id) references paper_board(id)
    on delete cascade on update cascade
);

create table material_board(					# 자료 게시판
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    tag1_id integer,							# 1차 태그 아이디
    tag2_id integer,							# 2차 태그 아이디
    foreign key (tag1_id) references tag(id)
    on delete cascade on update cascade,
    foreign key (tag2_id) references tag(id)
    on delete cascade on update cascade
);

create table material_attachment(				# 자료 게시판 첨부파일
	id integer,									# 게시글 id
    file varchar(200),							# 첨부파일
    primary key(id, file),
    foreign key (id) references material_board(id)
    on delete cascade on update cascade
);

#################################################################
#						LAB DATABASE DML						#
#################################################################

########################## TAG DATA INPUT #######################
insert into tag(content) values('일반');
insert into tag(content) values('수업');
insert into tag(content) values('연구');
insert into tag(content) values('공지');
insert into tag(content) values('논문');
insert into tag(content) values('자료');
insert into tag(content) values('프로젝트 예시');

######################### USER DATA INPUT #######################
insert into user(id, name, email, contact, role)
values (2017305045, '염동빈', 'yeomdongbin@skuniv.ac.kr', '010-4049-7305', 'admin');
insert into user(id, name, email, contact, role)
values (2017305049, '유봉환', '2017305049@skuniv.ac.kr', '010-4054-8425', 'none');

show tables;