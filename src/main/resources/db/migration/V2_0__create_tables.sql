create table if not exists universitygroup
(
    id varchar(255) primary key,
    name varchar(255)
);

create table if not exists student
(
    id varchar(255) primary key,
    firstname varchar(255),
    lastname varchar(255),
    age integer,
    dateofadmission timestamp(6) without time zone,
    universitygroup_id varchar(255)
);

create table if not exists subject
(
    id varchar(255) primary key,
    code varchar(255),
    name varchar(255),
    lecturer_id varchar(255)
);

create table if not exists lecturer
(
    id varchar(255) primary key,
    firstname varchar(255),
    lastname varchar(255),
    age integer,
    subject_id varchar(255)
);

create table if not exists grade
(
    id varchar(255) primary key,
    value integer,
    student_id varchar(255),
    subject_id varchar(255)
);