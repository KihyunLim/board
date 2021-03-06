create table board.member (
	memberid varchar(50) primary key,
	name varchar(50) not null,
	password varchar(10) not null,
	regdate datetime not null
) engine=InnoDB default character set = utf8;

create table board.article (
	article_no int auto_increment primary key,
    writer_id 	varchar(50) not null,
    writer_name varchar(50) not null,
    title varchar(255) not null,
    regdate datetime not null,
    moddate datetime not null,
    read_cnt int
) engine=InnoDB default character set = utf8;

create table board.atricle_content (
	article_no int primary key,
    content text
) engine=InnoDB default character set = utf8;

rename table board.atricle_content to board.article_content;

select * from board.article;
select * from board.article_content;

SET SQL_SAFE_UPDATES=0;
delete from board.article;
delete from board.article_content;

-- 데이터를 삭제해도 자동으로 증가한 값이 남아있어 초기화 필요
alter table board.article auto_increment = 1;

alter table board.article add del_flag boolean default false;
update board.article set del_flag=true where article_no=2;