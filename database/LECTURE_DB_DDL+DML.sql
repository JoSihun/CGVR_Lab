#################################################################
#						Lecture DATABASE DDL					#
#################################################################

create table user (
	id integer primary key,						# 학번
    name varchar(10),							# 이름
    email varchar(40),							# 이메일
    contact varchar(13),						# 연락처
    role varchar(10)							# 5개 권한
);

create table attachment (
	id integer auto_increment primary key,		# 첨부파일 id
    file varchar(255)							# 내용
);

# 1 Tag로 가고 제목 앞에 숫자를 붙이는 식으로 구분짓는게 좋아보임
# 2 Tag는 여러모로 관리에 있어 불편할 것으로 예상
create table tag (
	id integer auto_increment primary key,		# 태그 id
    content varchar(10)							# 태그 내용
);

# Session에서 참조하는 값은 따로 SQL에서의 참조가 아닌
# 프로그래밍 단에서의 대입으로 이루어져야할 것으로 보임
create table notice_board(						# 공지 게시판
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date datetime not null,						# 작성일
    mod_date datetime,								# 수정일
    author varchar(30) not null,				# 작성자
    tag_id integer,								# 태그 아이디
    attachment_id integer,						# 첨부파일 아이디
    lecture_id integer,							# 학수번호
    lecture_year integer,						# 수업연도
    foreign key (tag_id) references tag(id)
    on delete cascade on update cascade,
    foreign key (attachment_id) references attachment(id)
    on delete cascade on update cascade
);

create table material_board(					# 자료 게시판
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date datetime not null,						# 작성일
    mod_date datetime,								# 수정일
    author varchar(30) not null,				# 작성자
    tag_id integer,								# 태그 아이디
    attachment_id integer,						# 첨부파일 아이디
    lecture_id integer,							# 학수번호
    lecture_year integer,						# 수업연도
    foreign key (tag_id) references tag(id)
    on delete cascade on update cascade,
    foreign key (attachment_id) references attachment(id)
    on delete cascade on update cascade
);

create table practice_board(					# 실습 게시판
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date datetime not null,						# 작성일
    mod_date datetime,								# 수정일
    author varchar(30) not null,				# 작성자
    tag_id integer,								# 태그 아이디
    attachment_id integer,						# 첨부파일 아이디
    lecture_id integer,							# 학수번호
    lecture_year integer,						# 수업연도
    foreign key (tag_id) references tag(id)
    on delete cascade on update cascade,
    foreign key (attachment_id) references attachment(id)
    on delete cascade on update cascade
);

create table assignment_board(					# 과제 게시판
	id integer auto_increment primary key,		# 게시글 id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date datetime not null,						# 작성일
    mod_date datetime,								# 수정일
    author varchar(30) not null,				# 작성자
    tag_id integer,								# 태그 아이디
    attachment_id integer,						# 첨부파일 아이디
    lecture_id integer,							# 학수번호
    lecture_year integer,						# 수업연도
    foreign key (tag_id) references tag(id)
    on delete cascade on update cascade,
    foreign key (attachment_id) references attachment(id)
    on delete cascade on update cascade
);

#################################################################
#						Lecture DATABASE DML					#
#################################################################

########################## TAG DATA INPUT #######################
insert into tag(content) values('공지');
insert into tag(content) values('자료');
insert into tag(content) values('발표');
insert into tag(content) values('실습');
insert into tag(content) values('과제');

######################### USER DATA INPUT #######################
insert into user(id, name, email, contact, role)
values (2017305045, '염동빈', 'yeomdongbin@skuniv.ac.kr', '010-4049-7305', 'admin');
insert into user(id, name, email, contact, role)
values (2017305049, '유봉환', '2017305049@skuniv.ac.kr', '010-4054-8425', 'none');

show tables;