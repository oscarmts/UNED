INSERT INTO ROL values(0, 'Sin registrar');
INSERT INTO ROL values(1, 'Jefe de proyecto');
INSERT INTO ROL values(2, 'Administrador');
INSERT INTO ROL values(3, 'Usuario');
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(1, 'Óscar', 'Montes', 'omontes', '1234','omontes@mail.com', 1);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(2, 'Bilal', 'Rosell', 'brosell', '1234', 'brosell@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(3, 'Victor', 'Losada', 'vlosada', '1234', 'vlosada@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(4, 'Clara', 'Zamora', 'czamora', '1234', 'czamora@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(5, 'Dolores', 'Blasco', 'dblasco', '1234', 'dblasco@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(6, 'Gregoria', 'Jara', 'gjara', '1234', 'gjara@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(7, 'Pedro Miguel', 'Candela', 'pmcandela', '1234', 'pmcandela@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(8, 'Unax', 'Espinosa', 'uespinosa', '1234', 'uespinosa@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(9, 'Fernando', 'Torres', 'ftorres', '1234', 'ftorres@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(10, 'Ramón', 'García', 'rgarcia', '1234', 'rgarcia@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(11, 'Pedro', 'Zamorano', 'pzamorano', '1234', 'jdoe@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(12, 'Dolores', 'Salgado', 'dsalgado', '1234', 'jnadie@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(13, 'Ana', 'Torres', 'atorres', '1234', 'atorres@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(14, 'Idoia', 'Del Pozo', 'idelpozo', '1234', 'idelpozo@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(15, 'Juan', 'Torres', 'jtorres', '1234', 'jtorres@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(16, 'Amador', 'Pineda', 'apineda', '1234', 'apineda@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(17, 'Aimar', 'Ferrero', 'aferrero', '1234', 'aferrero@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(18, 'Gregoria', 'Cordero', 'gcordero', '1234', 'gcordero@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(19, 'Paulino', 'Zamora', 'pzamora', '1234', 'pzamora@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(20, 'Tomasa', 'Gonzalez', 'tgonzalez', '1234', 'tgonzalez@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(21, 'Consuelo', 'Calleja', 'ccalleja', '1234', 'ccalleja@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(22, 'Luis', 'Zambrano', 'lzambrano', '1234', 'lzambrano@mail.com', 3);
INSERT INTO USER (ID, NAME, SURNAME, NICKNAME, PASSWORD, EMAIL, ROL_ID) values(23, 'Fernando', 'Simón', 'fsimon', '1234', 'fsimon@mail.com', 2);
INSERT INTO USER (ID, NAME, NICKNAME, ROL_ID) values(24, 'Sergi', 'Sergi', 0);
INSERT INTO USER (ID, NAME, NICKNAME, ROL_ID) values(25, 'Gonzalo', 'Gonzalo', 0);
INSERT INTO USER (ID, NAME, NICKNAME, ROL_ID) values(26, 'Faemino', 'Faemino', 0);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(1, 'Futurama', 10);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(2, 'Los Simpsons', 5);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(3, 'Stargate', 6);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(4, 'Expediente X', 8);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(5, 'Seinfield', 10);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(6, 'Doctor Who', 7);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(7, 'Lost', 7);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(8, 'Fringe', 9);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(9, 'Star Trek', 6);
INSERT INTO ROOM (ID, NAME, CAPACITY) values(10, 'Westworld', 8);
INSERT INTO TECH_RESOURCE (ID, DESCRIPTION) values(1, 'Presentación');
INSERT INTO TECH_RESOURCE (ID, DESCRIPTION) values(2, 'Megafonia');
INSERT INTO TECH_RESOURCE (ID, DESCRIPTION) values(3, 'Grabación');
INSERT INTO TECH_RESOURCE (ID, DESCRIPTION) values(4, 'Emisión en streaming');
INSERT INTO TECH_RESOURCE (ID, DESCRIPTION) values(5, 'WiFi');
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(1, 1, 1);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(2, 1, 2);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(3, 1, 3);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(4, 1, 4);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(5, 1, 5);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(6, 2, 1);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(7, 2, 2);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(8, 2, 3);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(9, 2, 4);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(10, 3, 1);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(11, 3, 2);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(12, 3, 3);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(13, 4, 1);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(14, 4, 2);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(15, 5, 1);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(16, 6, 1);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(17, 6, 3);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(18, 6, 5);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(19, 7, 2);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(20, 7, 4);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(21, 8, 2);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(22, 8, 4);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(23, 9, 4);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(24, 9, 5);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(25, 10, 1);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(26, 10, 3);
INSERT INTO ROOM_TECH_RESOURCE (ID, ROOM_ID, TECH_RESOURCE_ID) values(27, 10, 4);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(1, 1, 8);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(2, 1, 9);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(3, 1, 10);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(4, 1, 11);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(5, 1, 12);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(6, 1, 13);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(7, 1, 14);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(8, 1, 15);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(9, 1, 16);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(10, 1, 17);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(11, 1, 18);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(12, 1, 19);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(13, 1, 20);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(14, 2, 10);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(15, 2, 11);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(16, 2, 12);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(17, 2, 13);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(18, 2, 16);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(19, 3, 9);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(20, 3, 10);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(21, 3, 16);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(22, 3, 17);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(23, 4, 16);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(24, 4, 17);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(25, 4, 18);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(26, 4, 19);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(27, 5, 10);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(28, 5, 11);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(29, 5, 12);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(30, 5, 13);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(31, 6, 10);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(32, 6, 11);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(33, 6, 12);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(34, 7, 8);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(35, 7, 9);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(36, 7, 10);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(37, 7, 11);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(38, 7, 12);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(39, 7, 13);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(40, 7, 14);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(41, 8, 15);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(42, 8, 16);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(43, 8, 17);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(44, 8, 18);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(45, 8, 19);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(46, 8, 20);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(47, 9, 10);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(48, 9, 11);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(49, 9, 12);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(50, 9, 13);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(51, 9, 14);
INSERT INTO ROOM_SCHEDULE (ID, ROOM_ID, HOUR) values(52, 10, 12);
INSERT INTO EVENT (ID, DATE, HOUR, TOPIC, ROOM_ID, OWER_ID, TOKEN, CONFIGURED, FINISHED) values(1, '2020-05-10', 10, 'Evento de prueba 1', 1, 23, 'f6ffba55-3165-42f3-9ffb-42a1f103544b', TRUE, TRUE);
INSERT INTO EVENT (ID, DATE, HOUR, TOPIC, ROOM_ID, OWER_ID, TOKEN, CONFIGURED, FINISHED) values(2, '2020-07-15', 11, 'Evento de prueba 2', 2, 1, 'f6ffba55-3165-42f3-9ffb-42a1f103524c', TRUE, FALSE);
INSERT INTO EVENT (ID, DATE, HOUR, TOPIC, ROOM_ID, OWER_ID, TOKEN, CONFIGURED, FINISHED) values(3, '2020-07-16', 12, 'Evento de prueba 3', 2, 1, 'f6ffba55-3165-42f3-9ffb-42a1f103534d', TRUE, FALSE);
INSERT INTO EVENT (ID, TOPIC, ROOM_ID, OWER_ID, TOKEN, CONFIGURED, FINISHED) values(4, 'Evento de prueba 4', 3, 1, 'f6ffba55-3165-42f3-9ffb-42a1f1035d4f', FALSE, FALSE);
INSERT INTO EVENT (ID, DATE, HOUR, TOPIC, ROOM_ID, OWER_ID, TOKEN, CONFIGURED, FINISHED) values(5, '2020-07-20', 9, 'Evento de prueba 5', 3, 1, 'f6ffba55-3165-42f3-9ffb-42a1f1035c5f', TRUE, FALSE);
INSERT INTO EVENT (ID, DATE, HOUR, TOPIC, ROOM_ID, OWER_ID, TOKEN, CONFIGURED, FINISHED) values(6, '2020-05-28', 10, 'Evento de prueba 6', 3, 1, 'f6ffba55-3165-42f3-9ffb-42a1f1035c1d', TRUE, TRUE);
INSERT INTO EVENT (ID, TOPIC, ROOM_ID, OWER_ID, TOKEN, CONFIGURED, FINISHED) values(7, 'Evento de prueba 7', 3, 23, 'f6ffba55-3165-42f3-9ffb-42a1f1035c5a', FALSE, FALSE);
INSERT INTO EVENT_ENROLL (ID, EVENT_ID, USER_ID) values(1, 1, 3);
INSERT INTO EVENT_ENROLL (ID, EVENT_ID, USER_ID) values(2, 1, 4);
INSERT INTO EVENT_ENROLL (ID, EVENT_ID, USER_ID) values(3, 2, 2);
INSERT INTO EVENT_ENROLL (ID, EVENT_ID, USER_ID) values(4, 6, 3);
INSERT INTO EVENT_ENROLL (ID, EVENT_ID, USER_ID) values(5, 6, 4);
INSERT INTO EVENT_ENROLL (ID, EVENT_ID, USER_ID) values(6, 6, 2);
INSERT INTO EVENT_ENROLL (ID, EVENT_ID, USER_ID) values(7, 1, 1);
INSERT INTO EVENT_WAITING_LIST values(1, 1, 2);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(1, 1, 10, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(2, 1, 11, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(3, 1, 12, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(4, 3, 10, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(5, 3, 11, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(6, 3, 12, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(7, 3, 10, 2, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(8, 3, 11, 2, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(9, 3, 13, 2, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(10, 3, 9, 3, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(11, 3, 12, 3, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(12, 3, 15, 3, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(13, 3, 18, 4, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(14, 4, 10, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(15, 4, 11, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(16, 4, 12, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(17, 5, 10, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(18, 5, 11, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(19, 5, 12, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(20, 5, 13, 1, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, DATE, HOUR, USER_ID, OWNER_HOUR) values(21, 5, '2020-07-19', 10, 2, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, DATE, HOUR, USER_ID, OWNER_HOUR) values(22, 5, '2020-07-20', 11, 2, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, DATE, HOUR, USER_ID, OWNER_HOUR) values(23, 5, '2020-07-20', 11, 3, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, DATE, HOUR, USER_ID, OWNER_HOUR) values(24, 5, '2020-07-21', 10, 4, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, DATE, HOUR, USER_ID, OWNER_HOUR) values(25, 5, '2020-07-22', 11, 4, false);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(26, 7, 16, 23, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(27, 7, 17, 23, true);
INSERT INTO EVENT_PREFERENCE_SCHEDULE (ID, EVENT_ID, HOUR, USER_ID, OWNER_HOUR) values(28, 7, 18, 23, true);
INSERT INTO EVENT_PREFERENCE_DATE_SCHEDULE(ID, EVENT_ID, DATE) values(1, 4, '2020-07-22');
INSERT INTO EVENT_PREFERENCE_DATE_SCHEDULE(ID, EVENT_ID, DATE) values(2, 4, '2020-07-23');
INSERT INTO EVENT_PREFERENCE_DATE_SCHEDULE(ID, EVENT_ID, DATE) values(3, 7, '2020-07-23');
INSERT INTO EVENT_PREFERENCE_DATE_SCHEDULE(ID, EVENT_ID, DATE) values(4, 7, '2020-07-24');
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) VALUES (1, 4, 2, false, false);
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) VALUES (2, 4, 4, false, false);
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) values(3, 6, 3, true, true);
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) values(4, 6, 4, true, true);
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) values(5, 6, 2, true, true);
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) values(6, 7, 5, false, false);
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) values(7, 7, 6, false, false);
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) values(8, 7, 7, false, false);
INSERT INTO EVENT_INVITED (ID, EVENT_ID, USER_ID, VOTED, ENROLLED) values(9, 1, 1, true, true);
INSERT INTO EVENT_MESSAGE_CHAT (ID, DATE, EVENT_ID, USER_ID, MESSAGE) values(1, '2020-05-23 17:30:51.423', 2, 3, 'Hola, un saludo.');
INSERT INTO EVENT_MESSAGE_CHAT (ID, DATE, EVENT_ID, USER_ID, MESSAGE) values(2, '2020-05-23 17:32:51.423', 2, 3, '¿Qué tal todo?');
INSERT INTO EVENT_MESSAGE_CHAT (ID, DATE, EVENT_ID, USER_ID, MESSAGE) values(3, '2020-05-23 17:35:51.423', 2, 3, 'Por aquí todo bien');
INSERT INTO EVENT_MESSAGE_CHAT (ID, DATE, EVENT_ID, USER_ID, MESSAGE) values(4, '2020-05-28 17:30:51.423', 6, 4, 'Hola, un saludo.');
INSERT INTO EVENT_MESSAGE_CHAT (ID, DATE, EVENT_ID, USER_ID, MESSAGE) values(5, '2020-05-28 17:32:51.423', 6, 3, '¿Qué tal todo?');
INSERT INTO EVENT_MESSAGE_CHAT (ID, DATE, EVENT_ID, USER_ID, MESSAGE) values(6, '2020-05-28 17:35:51.423', 6, 4, 'Por aquí todo bien');
INSERT INTO USER_NOT_REGISTERED (ID, USER_ID, EVENT_ID) values (1, 24, 6);
INSERT INTO USER_NOT_REGISTERED (ID, USER_ID, EVENT_ID) values (2, 25, 6);
INSERT INTO USER_NOT_REGISTERED (ID, USER_ID, EVENT_ID) values (3, 26, 6);