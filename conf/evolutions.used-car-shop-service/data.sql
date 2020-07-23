-- Заполнение таблицы организаций

INSERT INTO organization (id, name, inn, isActive, fullName, kpp, address, phone, version)
VALUES (1,'Яндекс','7736207543',TRUE,'Яндекс, ООО','55187675','119021, г. Москва, ул. Льва Толстого, д. 16','8(495)1254578',1);

INSERT INTO organization (id, name, inn, isActive, fullName, kpp, address, phone, version)  
VALUES (2,'СБЕРБАНК - ТЕХНОЛОГИИ','7736632467',TRUE,'АКЦИОНЕРНОЕ ОБЩЕСТВО "СБЕРБАНК - ТЕХНОЛОГИИ','772601001','117105, МОСКВА Г, НОВОДАНИЛОВСКАЯ НАБ, 10','8(495)669-08-08',1);

INSERT INTO organization (id, name, inn, isActive, fullName, kpp, address, phone, version)
VALUES (3,'БЭЛЛ ИНТЕГРАТОР','7733180847',TRUE,'АКЦИОНЕРНОЕ ОБЩЕСТВО "БЭЛЛ ИНТЕГРАТОР"','502401001','143409, МОСКОВСКАЯ ОБЛ, КРАСНОГОРСК Г, УСПЕНСКАЯ УЛ, ДОМ 4А, ЭТАЖ/ОФИС 5/523А','8(495)913-22-62',1);

-- Заполнение справочника типов документов

INSERT INTO document (code, name)
VALUES ('21','Паспорт гражданина Российской Федерации');

INSERT INTO document (code, name)
VALUES ('10','Паспорт иностранного гражданина');

INSERT INTO document (code, name)
VALUES ('07','Военный билет');

-- Заполнение справочника стран

INSERT INTO country (code, name)
VALUES ('RUS','Россия');

INSERT INTO country (code, name)
VALUES ('BLR','Белоруссия');

INSERT INTO country (code, name)
VALUES ('KAZ','Казахстан');

-- Заполнение справочника должностей

INSERT INTO title (id, title_name)
VALUES (1,'Менеджер проекта');

INSERT INTO title (id, title_name)
VALUES (2,'ИТ-инженер');

INSERT INTO title (id, title_name)
VALUES (3,'Инженер по тестированию');

-- Заполнение таблицы работников

INSERT INTO user (id, firstName, secondName, middleName, phone, citizenship, position, version)
VALUES (1,'Константин','Константинопольский','Константинович','8(995)0001122','RUS',2,1);

INSERT INTO user (id, firstName, secondName, middleName, phone, citizenship, position, version)
VALUES (2,'Олег','Олежкин','Олегович','8(900)1112233','BLR',3,1);

INSERT INTO user (id, firstName, secondName, middleName, phone, citizenship, position, version)
VALUES (3,'Иван','Иванов','Денисович','8(901)3334455','RUS',1,1);

INSERT INTO user (id, firstName, secondName, middleName, phone, citizenship, position, version)
VALUES (4,'Петр','Петров','Константинович','8(903)4446677','RUS',2,1);

INSERT INTO user (id, firstName, secondName, middleName, phone, citizenship, position, version)
VALUES (5,'Мухаммед','Махмудов','Мухаммед оглы','8(899)1110011','KAZ',3,1);

-- Заполнение таблицы офисов

INSERT INTO office (id, name, phone, isActive, address, orgId, version)
VALUES (1,'Головной офис','8(495)1254578',TRUE,'119021, г. Москва, ул. Льва Толстого, д. 16',1,1);

INSERT INTO office (id, name, phone, isActive, address, orgId, version)
VALUES (2,'Дополнительный офис','+74957397000',TRUE,'115035, Москва, ул. Садовническая, 82, стр. 2',1,1);

INSERT INTO office (id, name, phone, isActive, address, orgId, version)
VALUES (3,'Московский офис','+7(495)9806181',TRUE,'115088, г. Москва 2-й Южнопортовый проезд 18, стр.2',3,1);

INSERT INTO office (id, name, phone, isActive, address, orgId, version)
VALUES (4,'Головной офис','8(495)6690808',TRUE,'117105, Москва, Новоданиловская наб., 10',2,1);

-- Заполнение таблицы документов сотрудника

INSERT INTO user_doc (id, doc_code, doc_number, user_id, isIdentified, doc_date)
VALUES (1,'21','9212 111222',1,TRUE,'2001-10-11');

INSERT INTO user_doc (id, doc_code, doc_number, user_id, isIdentified, doc_date)
VALUES (2,'21','9313 666777',3,TRUE,'2006-11-29');

INSERT INTO user_doc (id, doc_code, doc_number, user_id, isIdentified, doc_date)
VALUES (3,'21','9214 440222',4,TRUE,'2001-10-11');

INSERT INTO user_doc (id, doc_code, doc_number, user_id, isIdentified, doc_date)
VALUES (4,'10','8080 111222',2,FALSE,'2002-11-18');

INSERT INTO user_doc (id, doc_code, doc_number, user_id, isIdentified, doc_date)
VALUES (5,'07','AB09 2323',5,TRUE,'2004-09-21');

-- Заполнение таблицы связи сотрудников и офисов

INSERT INTO user_office (id, user_id, office_id, primary_office)
VALUES (1,1,1,TRUE);

INSERT INTO user_office (id, user_id, office_id, primary_office)
VALUES (2,1,2,FALSE);

INSERT INTO user_office (id, user_id, office_id, primary_office)
VALUES (3,5,2,TRUE);

INSERT INTO user_office (id, user_id, office_id, primary_office)
VALUES (4,3,3,TRUE);

INSERT INTO user_office (id, user_id, office_id, primary_office)
VALUES (5,2,3,TRUE);

INSERT INTO user_office (id, user_id, office_id, primary_office)
VALUES (6,4,4,TRUE);