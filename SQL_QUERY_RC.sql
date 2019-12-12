USE englishvalidation;
/*Lista delle richeste di RC*/
 SELECT* FROM REQUEST_RC;
 /*Lista dei LINK ai file pdf legati alle richiesta con id 1*/
 SELECT LINK_PDF FROM FILE_PDF WHERE FK_ID_REQUEST_RC = 1;
 /*Lista degli esami convalidati con più di 6 CFU*/
 SELECT* FROM VALIDATE_EXAM WHERE CFU_CONVALIDATED > 6;
 /*Lista delle richieste che sono state effettuate prima del 2019-10-3*/
 SELECT * FROM REQUEST_RC WHERE DATE_REQUEST<'2019-10-3';
 /*Dati UC*/
 SELECT * FROM englishvalidation.uc where EMAIL = "EMAILUC@gmail.it";
 /*Lista utenti di tipo studente (0)*/
 SELECT * FROM englishvalidation.user where USER_TYPE = 0;
 /*Dati studente data la Email*/
 SELECT * FROM englishvalidation.user where EMAIL = "prova2@unisa.it";
 /*Lista richieste nello stato ricercato*/
 SELECT * FROM englishvalidation.request_rc where STATE = 1;
/*lista esami validati*/
SELECT * FROM englishvalidation.validate_exam where FK_ID_REPORT = 1;
/*Lista esami non validati*/
SELECT * FROM englishvalidation.validate_exam where FK_ID_REPORT = 2;
/*link al programma di un esame dato il nome*/
SELECT LINK_PROGRAM FROM englishvalidation.exam where NAME = "Analisi 1";
 /*Lista utenti che hanno effettuato una richiesta per RC con stato della richiesta*/
 select NAME,SURNAME,DESCRIPTION From user join request_rc on user.EMAIL = request_rc.FK_USER left join state on STATE = ID_STATE;
 /*lista dei report degli studenti che hanno effettuato una richiesta per RC */
 select NAME,SURNAME,NOTE From user join request_rc on user.EMAIL = request_rc.FK_USER left join report on FK_REPORT = ID_REPORT;
/*lista esami validati per un report*/
select ID_EXAM_VALIDATE,NAME_EXAM,FK_USER,CFU_CONVALIDATED from request_rc join report on request_rc.ID_REQUEST = report.ID_REPORT and ID_REPORT = 1 left join validate_exam on FK_ID_REPORT = ID_REPORT;
 /*Lista esami ralativi ad una richiesta*/
 select NAME,CFU,FK_USER,ID_REQUEST FROM contains join request_RC on ID_REQUEST = FK_REQUEST_RC and FK_REQUEST_RC = 1 left join exam on ID_EXAM = FK_EXAM;
 