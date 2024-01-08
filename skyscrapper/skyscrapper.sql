-- 2차 프로젝트
-- sql 작성

-- 4차 수정
-- drop table
DROP TABLE inquiry;
DROP TABLE product;
DROP TABLE member;


DROP SEQUENCE member_seq;
DROP SEQUENCE product_seq;
DROP SEQUENCE inquiry_seq;


-- member table
-- 회원들의 정보
CREATE TABLE member
(
    
    member_id VARCHAR2(20) PRIMARY KEY,
    member_pw VARCHAR2(100) NOT NULL,
    member_nm VARCHAR2(30) NOT NULL,
    member_type NUMBER CHECK(member_type IN(0,1,2)),
    membership number(3) DEFAULT 0,
    country VARCHAR2(30) NOT NULL,
    company_nm VARCHAR2(30) NOT NULL,
    company_tell VARCHAR2(30) NOT NULL,
    department_nm VARCHAR2(30),
    role_nm VARCHAR2(20) DEFAULT 'ROLE_USER',   -- 관리자의 경우 ROLE_ADMIN
    enabled NUMBER(1) DEFAULT 1               -- security를 위해 필요
);
CREATE SEQUENCE member_seq;

-- 제품
CREATE TABLE product
(
    product_id NUMBER(10) PRIMARY KEY,
    product_nm VARCHAR2(30) NOT NULL,
    product_info VARCHAR2(4000) NOT NULL,
    originalfile VARCHAR2(1000),
    savedFileName VARCHAR2(1000),
    member_id  VARCHAR2(20),
    category_nm VARCHAR2(30), 
    payment_term VARCHAR2(5) CHECK (payment_term IN('t/t','l/c','d/a','d/p')),
    regdate DATE DEFAULT sysdate,
    pending_status NUMBER default 0 CHECK(pending_status IN(0,1)),
    block_status NUMBER default 0 CHECK(block_status IN(0,1))
);
CREATE SEQUENCE product_seq;

-- inquiry
-- 인콰이어리 테이블 생성
CREATE TABLE inquiry
(
    inq_id VARCHAR2(20) PRIMARY KEY,
    snd_id VARCHAR2(20) NOT NULL,
    rcv_id VARCHAR2(20) NOT NULL,
    input_date DATE DEFAULT SYSDATE,
    product_id number(10),
    message VARCHAR2(3000) NOT NULL,
    pending_status NUMBER default 0 CHECK(pending_status IN(0,1)),
    block_status NUMBER default 0 CHECK(block_status IN(0,1))
);
CREATE SEQUENCE inquiry_seq;

-- 리뷰 데이터
CREATE TABLE reply_product(
    brand_name VARCHAR2(30),
    product_name VARCHAR2(50),
    shape_product VARCHAR2(5) check(shape_product in ('A','E','R','R E')),
    regdate DATE,
    rating NUMBER,
    title_main CLOB,
    channel VARCHAR2(50),
    sentiment_score NUMBER,
    probability NUMBER(8,5)
)
;

-- 수지 그래프 (07.10)
--drop table
DROP TABLE company_review;
DROP TABLE sentiment_count;

--create company_review table
CREATE TABLE company_review
(
    company_nm varchar2(50) ,
    review_ct int 
);

-- create sentiment_count table 
create table sentiment_count
(
company_num number primary key,
company_name varchar2(20),
count_1 number(38,0),
count_0 number(38,0)
);

--7/12 수지
--null 값 제거 테이블--이건 기록용 무시--
--delete from company_review
--WHERE company_nm is null and review_ct is null;
--commit;
--rollback;



DROP TABLE sentiment_count;
DROP TABLE sentiment_rate;
DROP TABLE sales_trend;
drop table star_rate;
drop table sales_trend;

create table sentiment_count
(
company_num number primary key,
company_name varchar2(20),
count_1 number(38,0),
count_0 number(38,0)
);

--sentiment_rate테이블 생성

create table sentiment_rate
(
company  varchar2(20) primary key,
rate number
);

--star_rate 테이블 생성

create table star_rate(
yr number,
star_rate number
);

--sales_trend 테이블 생성


create table sales_trend(
review_yr VARCHAR2(10),
reviews number
);
