create table class_info (
	class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (class_id, class_year)
);

create table cpp_notice_board (					# c++ 공지 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table mfc_notice_board (					# mfc 공지 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table graphics_notice_board (			# 그래픽스 공지 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table ar_notice_board (					# 증강현실 공지 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table cpp_data_board (					# c++ 자료 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table mfc_data_board (					# mfc 자료 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table graphics_data_board (				# graphics 자료 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table ar_data_board (					# 증강현실 자료 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table cpp_practical_board (				# c++ 실습 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table mfc_practical_board (				# mfc 실습 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table graphics_practical_board (			# graphics 실습 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table ar_practical_board (				# 증강현실 실습 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table cpp_assign_board (				# c++ 과제 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table mfc_assign_board (				# mfc 과제 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table graphics_assign_board (				# graphics 과제 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table ar_assign_board (				# 증강현실 과제 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table cpp_presentation_board (				# c++ 발표 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table mfc_presentation_board (				# mfc 발표 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table graphics_presentation_board (			# graphics 발표 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table ar_presentation_board (				# 증강현실 발표 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    class_id integer,							# 수업id
    class_year integer,							# 수업년도
    primary key (id),
    foreign key (class_id, class_year) references class_info (class_id, class_year)
    on delete cascade on update cascade
);

create table tmpPrj_notice_board (				# 프로젝트 공지 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    primary key (id)
);

create table tmpPrj_paper_board (				# 프로젝트 논문 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    primary key (id)
);

create table tmpPrj_data_board (				# 프로젝트 자료 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    primary key (id)
);

create table lab_notice_board (					# 일반 공지 게시판
	id integer auto_increment,					# 게시글id
    title varchar(50) not null,					# 게시글제목
    content varchar(200) not null,				# 게시글내용
    hits integer not null,						# 조회수
    reg_date date not null,						# 작성일
    mod_date date,								# 수정일
    author varchar(30) not null,				# 작성자
    attachments varchar(200),					# 첨부파일
    primary key (id)
);

drop table class_info;
drop table cpp_notice_board;
show tables;