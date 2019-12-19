USE englishvalidation;

INSERT INTO UNIVERSITY VALUES("Università degli Studi di BARI ALDO MORO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi della BASILICATA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di BOLOGNA");
INSERT INTO UNIVERSITY VALUES("Libera Università di BOLZANO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di CAGLIARI");
INSERT INTO UNIVERSITY VALUES("Università della CALABRIA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di CAMERINO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di CATANIA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di FERRARA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di FIRENZE");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di GENOVA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi insurbia VARESE-COMO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi de L'AQUILA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di MESSINA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di MILANO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di MILANO-BICOCCA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di MODENA e REGGIO EMILIA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi del MOLISE");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di NAPOLI Federico II");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di NAPOLI Parthenope");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di PADOVA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di PALERMO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di PARMA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi del PIEMONTE ORIENTALE Amedeo Avogadro-Vercelli");
INSERT INTO UNIVERSITY VALUES("Università di PISA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di ROMA La Sapienza");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di ROMA Tor Vergata");
INSERT INTO UNIVERSITY VALUES("Libera Università degli Studi Maria SS.Assunta - LUMSA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di SALERNO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di TORINO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di TRENTO");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di UDINE");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di URBINO Carlo Bo");
INSERT INTO UNIVERSITY VALUES("Università Ca Foscari VENEZIA");
INSERT INTO UNIVERSITY VALUES("Università degli Studi di VERONA");

INSERT INTO REQUEST VALUES (1,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova@unisa.it',2,2);
INSERT INTO REQUEST VALUES (2,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova2@unisa.it',2,3);
INSERT INTO REQUEST VALUES (3,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova3@unisa.it',3,1);
INSERT INTO REQUEST VALUES (4,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova4@unisa.it',4,2);
INSERT INTO REQUEST VALUES (5,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova5@unisa.it',3,4);
INSERT INTO REQUEST VALUES (6,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova6@unisa.it',3,3);

INSERT INTO `user` VALUES ('prova2@unisa.it','Giuseppino','Bisoio','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9e',0, null);
INSERT INTO `user` VALUES ('prova3@unisa.it','Giulia','Serio','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9c',0, null);
INSERT INTO `user` VALUES ('prova@unisa.it','Lollo','Mat','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9d',0,null);
INSERT INTO `user` VALUES ('prova4@unisa.it','Giuseppino','Bisoio','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9f',0,null);
INSERT INTO `user` VALUES ('prova5@unisa.it','Giulia','Serio','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9g',0,null);
INSERT INTO `user` VALUES ('prova6@unisa.it','Lollo','Mat','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9h',0,null);
INSERT INTO `user` VALUES ('fferrucci@unisa.it','Filomena','Ferrucci','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',2,null),('segreteria@unisa.it','Segreteria','Studenti','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',1,null);

/*USER RC*/
INSERT INTO `user` VALUES ('lollo@gmail.com','PROVA1','RC1','M','123456789',0,'01-1-10');
INSERT INTO `user` VALUES ('provaRC1@unisa.it','PROVA1','RC1','M','12345678',0,'01-1-10');
INSERT INTO `user` VALUES ('provaRC2@unisa.it','PROVA2','RC2','M','12345678',0,'2020-10-20');
/*UC*/
INSERT INTO UC VALUES('EMAILUC@gmail.it','12356789','3331231233','3331231233');
/*REPORT*/
INSERT INTO REPORT VALUES(1 ,'La sua carriera ok okok');
INSERT INTO REPORT VALUES(2 ,'La sua carriera NON è ok okok');
INSERT INTO REPORT VALUES(3 ,'La sua carriera NON è ok ok');
/*UNIVERSITY*/
INSERT INTO UNIVERSITY VALUES('UNINA');
INSERT INTO UNIVERSITY VALUES('UNIBO');
/*REQUEST*/
INSERT INTO REQUEST_RC VALUES(1, '2019-3-11', 1,'UNINA','provaRC1@unisa.it',1,'EMAILUC@gmail.it');
INSERT INTO REQUEST_RC VALUES(2, '2020-1-30', 2,'UNIBO','provaRC2@unisa.it',2,'EMAILUC@gmail.it');
INSERT INTO REQUEST_RC VALUES(3, '2020-4-10', 3,'UNIBO','prova2@unisa.it',1,'EMAILUC@gmail.it');

/*VALIDATE EXAM*/
INSERT INTO VALIDATE_EXAM VALUES(1,'PROGRAMMAZIONE 1', 9, 'è stato validato bene',1);
INSERT INTO VALIDATE_EXAM VALUES(2,'ANALISI 1', 0, 'non sono stati validati cfu',2);
INSERT INTO VALIDATE_EXAM VALUES(3,'ANALISI 1', 0, 'non sono stati validati cfu',3);
/*PDF*/
INSERT INTO FILE_PDF VALUES(1, 'FILEPDFCARR1',1);
INSERT INTO FILE_PDF VALUES(2, 'FILEPDFDOC1',1); 
INSERT INTO FILE_PDF VALUES(3, 'FILEPDFCARR2',2);
INSERT INTO FILE_PDF VALUES(4, 'FILEPDFDOC2',2);
/*EXAM*/
INSERT INTO EXAM VALUES(1, 'Programmazione 1', 12, 'www.sito.it/dati/esame/prog1');
INSERT INTO EXAM VALUES(2, 'Analisi 1', 9, 'www.sito.it/dati/esame/anal1');
insert into exam values (3,'IS',9,'www.sito.it/dati/esame/IS');
/*CONTAINS*/ 
insert into contains values (1,1);
insert into contains values (1,3);
insert into contains values (2,2);

INSERT INTO `attached` VALUES (1,'certificato.pdf',1,'prova@unisa.it');

INSERT INTO STATE 
VALUES (1,'Parzialmente Completata');
INSERT INTO STATE 
VALUES (2,'In elaborazione dalla Segreteria');
INSERT INTO STATE 
VALUES (3,'In elaborazione dall&quot; Amministratore');
INSERT INTO STATE 
VALUES (4,'Accettata e In elaborazione dal Consiglio Didattico');
INSERT INTO STATE 
VALUES (5,'Rifiutata e In elaborazione dal Consiglio Didattico');
INSERT INTO STATE 
VALUES (6,'Conclusa e Accettata');
INSERT INTO STATE 
VALUES (7,'Conclusa e Rifiutata');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-partially-completed', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-secretary', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-admin', '3','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-educational-advice-1', '4','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-educational-advice-2', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-accepted', '6','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-refused', '7','fferrucci@unisa.it');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-number-max-upload', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-allowed-extension-upload', '.pdf','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-matriculation-year-range', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-min-cfu', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-max-cfu', '12','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-upload-path', '//home//vale//newWorkspace//EV_EnglishValidation//uploads//','fferrucci@unisa.it');

INSERT INTO ENTE
VALUES ('1', '','Cambridge Assessment English','', 1);
INSERT INTO ENTE
VALUES ('2', '','City and Guilds (Pitman)','', 1);
INSERT INTO ENTE
VALUES ('3', '','Edexcel /Pearson Ltd','', 1);
INSERT INTO ENTE
VALUES ('4', '','Educational Testing Service (ETS)','', 1);
INSERT INTO ENTE
VALUES ('5', '','English Speaking Board (ESB)','', 1);
INSERT INTO ENTE
VALUES ('6', '','International English Language Testing System (IELTS)','', 1);
INSERT INTO ENTE
VALUES ('7', '','Pearson - LCCI','', 1);
INSERT INTO ENTE
VALUES ('8', '','Pearson - EDI','', 1);
INSERT INTO ENTE
VALUES ('9', '','Trinity College London (TCL)','', 1);
INSERT INTO ENTE
VALUES ('10', '','Department of English, Faculty of Arts - University of Malta','', 1);
INSERT INTO ENTE
VALUES ('11', '','NQAI - ACELS','', 1);
INSERT INTO ENTE
VALUES ('12', '','Ascentis','', 1);
INSERT INTO ENTE
VALUES ('13', '','AIM Awards','', 1);
INSERT INTO ENTE
VALUES ('14', '','Learning Resource Network (LRN)','', 1);
INSERT INTO ENTE
VALUES ('15', '','British Institutes','', 1);
INSERT INTO ENTE
VALUES ('16', '','Gatehouse Awards Ltd','', 1);
INSERT INTO ENTE
VALUES ('17', '','LanguageCert','', 1);
