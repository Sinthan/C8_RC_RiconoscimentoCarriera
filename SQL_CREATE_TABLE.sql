DROP DATABASE IF EXISTS englishvalidation;
CREATE DATABASE englishvalidation COLLATE 'utf8_general_ci';
USE englishvalidation;

CREATE TABLE USER(
	EMAIL varchar(50) not null,
	NAME varchar(50) not null,
	SURNAME varchar(50) not null,
	SEX char not null,
	PASSWORD varchar(50) not null,
	USER_TYPE tinyint(1) not null,
	DATE_REGISTRATION date null,  /*Attributo inserito per la gestione degli account temporanei*/
	primary key (EMAIL)
);

/*TABELLE AGGIUNTE PER RC _____________________________________________________________________________________________________*/
CREATE TABLE UC( 
	EMAIL varchar(50) not null,
	PASSWORD varchar(50) not null,    /* <---- CONTROLLARE TABELLA ERRORE INT*/
    TELEPHONE VARCHAR(10) not null,   /* <---- MODIFICATO IN VARCHAR*/
    FAX VARCHAR(10) default null,     /* <---- MODIFICATO IN VARCHAR*/
    primary key (EMAIL)
);

CREATE TABLE REPORT( 
	ID_REPORT int(20) not null AUTO_INCREMENT,
    NOTE varchar(5000) default null,
    primary key (ID_REPORT)
);
alter table REPORT AUTO_INCREMENT=1;

CREATE TABLE UNIVERSITY(
	NAME varchar(100) not null,
    primary key (NAME)
);

CREATE TABLE REQUEST_RC(
	ID_REQUEST int(20) not null AUTO_INCREMENT,
    DATE_REQUEST date not null,
    STATE int(20) not null,
    FK_UNIVERSITY varchar(50) not null,
    FK_USER varchar(50) not null,
    FK_REPORT int(20),     
    FK_EMAIL_UC varchar(50) not null,
    foreign key (FK_UNIVERSITY) references UNIVERSITY(NAME),
    foreign key (FK_USER) references USER(EMAIL),
    foreign key (FK_REPORT) references REPORT(ID_REPORT),
    foreign key (FK_EMAIL_UC) references UC(EMAIL),
    primary key (ID_REQUEST)
);
alter table REQUEST_RC AUTO_INCREMENT=1;

CREATE TABLE VALIDATE_EXAM(
	ID_EXAM_VALIDATE int(20) not null AUTO_INCREMENT,
    NAME_EXAM varchar(50) not null,
    CFU_CONVALIDATED tinyint(1) not null,
    MODE_VALIDATION varchar(50) not null,
    FK_ID_REPORT int(20) not null,
    foreign key (FK_ID_REPORT) references REPORT(ID_REPORT),
	primary key (ID_EXAM_VALIDATE)
);
alter table VALIDATE_EXAM AUTO_INCREMENT=1;

CREATE TABLE FILE_PDF(
	ID_PDF int(20) not null AUTO_INCREMENT,
    LINK_PDF  varchar(100) not null,
    FK_ID_REQUEST_RC int(20) not null,
    foreign key (FK_ID_REQUEST_RC) references REQUEST_RC(ID_REQUEST),
    primary key(ID_PDF)
);
alter table FILE_PDF AUTO_INCREMENT=1;

CREATE TABLE EXAM(
	ID_EXAM int(20) AUTO_INCREMENT,
    NAME varchar(50) not null,
    CFU tinyint(1) not null,
    LINK_PROGRAM varchar(256) not null,
    primary key(ID_EXAM)
);
alter table EXAM AUTO_INCREMENT=1;

CREATE TABLE CONTAINS(
	FK_REQUEST_RC int(20) not null,
    FK_EXAM int(20) not null,
    foreign key (FK_REQUEST_RC) references REQUEST_RC(ID_REQUEST),
    foreign key (FK_EXAM) references EXAM(ID_EXAM),
    primary key (FK_REQUEST_RC, FK_EXAM)
);

CREATE TABLE SUGESTION(
NAME_UNIVERSITY varchar(50) not null,
NAME_EXAM_EXTERN varchar(50) not null,
NUMBER_CFU_EXTERN varchar(30) not null,
MODE_VALIDATION varchar(50) not null,
DATE_VALIDATION date not null

);
/*FINE TABELLE UTILIZZATE IN RC _____________________________________________________________________________________________________*/


CREATE TABLE SYSTEM_ATTRIBUTE (
	SLUG varchar(50) not null, 
	VALUE varchar(100) not null, 
	FK_USER varchar(50) not null,
	primary key (SLUG),
	foreign key (FK_USER) references USER(EMAIL)
);

CREATE TABLE REQUEST (
	ID_REQUEST int(20) not null AUTO_INCREMENT,
	CERTIFICATE_SERIAL VARCHAR(50) not null,
	LEVEL varchar(7) not null,
	RELEASE_DATE date not null, 
	EXPIRY_DATE date not null, 
	YEAR year not null, 
	REQUESTED_CFU tinyint(2) not null, 
	SERIAL int(10) not null, 
	VALIDATED_CFU tinyint(2) not null, 
	FK_USER varchar(50) not null,
	FK_CERTIFIER int(20) not null, 
	FK_STATE int(20) not null, 
	primary key(ID_REQUEST)
);

CREATE TABLE ATTACHED (
	ID_ATTACHED int(20) not null AUTO_INCREMENT,
	FILENAME varchar(50) not null,
	FK_REQUEST int(20) not null,
	FK_USER varchar(50) not null,
	primary key(ID_ATTACHED)
);

CREATE TABLE ENTE (
	ID_ENTE int(20) not null AUTO_INCREMENT,
	EMAIL varchar(50) not null,
	NAME varchar(100) not null,
	SITE varchar(50) not null,
	STATO TINYINT not null default 0,
	primary key (ID_ENTE)
);

CREATE TABLE STATE (
	ID_STATE int(20) not null AUTO_INCREMENT, 
	DESCRIPTION varchar(100) not null,
	primary key (ID_STATE)
);