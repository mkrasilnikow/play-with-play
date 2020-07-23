CREATE TABLE IF NOT EXISTS brand (
	id          INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор' PRIMARY KEY ,
	name        VARCHAR NOT NULL                    COMMENT 'Краткое название организации',
	inn         VARCHAR(12) NOT NULL                COMMENT 'ИНН организации',
	isActive    BOOLEAN NOT NULL DEFAULT 'FALSE'    COMMENT 'Статус организации: активна/ликвидирована',
	fullName    VARCHAR NOT NULL                    COMMENT 'Полное название организации',
	kpp         VARCHAR(9) NOT NULL                 COMMENT 'КПП организации',
	address     VARCHAR NOT NULL                    COMMENT 'Официальный адрес юридического лица',
	phone       VARCHAR                             COMMENT 'Основной телефон организации' UNIQUE,
	version     INTEGER NOT NULL                    COMMENT 'Служебная метка версионирования'
);
COMMENT ON TABLE organization IS 'Список организаций';

CREATE TABLE IF NOT EXISTS office (
	id          INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор' PRIMARY KEY ,
	name        VARCHAR                             COMMENT 'Краткое наименование офиса',
	phone       VARCHAR                             COMMENT 'Телефон приемной/секретаря' UNIQUE,
	isActive    BOOLEAN NOT NULL  DEFAULT 'FALSE'   COMMENT 'Статус офиса: функционирует/упразднен',
	address     VARCHAR NOT NULL                    COMMENT 'Адрес офиса',
	orgId       INTEGER NOT NULL                    COMMENT 'Идентификатор организации, которой принадлежит офис',
	version     INTEGER NOT NULL                    COMMENT 'Служебная метка версионирования'
);
COMMENT ON TABLE office IS 'Офисы организации';

CREATE TABLE IF NOT EXISTS user (
	id          INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор сотрудника' PRIMARY KEY ,
	firstName   VARCHAR NOT NULL                    COMMENT 'Имя сотрудника',
	secondName  VARCHAR NOT NULL                    COMMENT 'Фамилия сотрудника',
	middleName  VARCHAR                             COMMENT 'Отчество сотрудника',
	phone       VARCHAR                             COMMENT 'Рабочий телефон сотрудника' UNIQUE,
	citizenship VARCHAR NOT NULL                    COMMENT 'Идентификатор гражданства сотрудника',
	position    INTEGER NOT NULL                    COMMENT 'Идентификатор должности сотрудника',
	version     INTEGER NOT NULL                    COMMENT 'Служебная метка версионирования'
);
COMMENT ON TABLE user IS 'Сотрудники организации';

CREATE TABLE IF NOT EXISTS user_office (
	id          INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор' PRIMARY KEY ,
	user_id     INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор сотрудника',
	office_id   INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор офиса',
	primary_office BOOLEAN NOT NULL DEFAULT 'FALSE' COMMENT 'Метка основного офиса сотрудника'
);
COMMENT ON TABLE user_office IS 'Таблица связи сотрудников и офисов';

CREATE TABLE IF NOT EXISTS user_doc (
	id          INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор' PRIMARY KEY ,
	doc_code    VARCHAR NOT NULL                    COMMENT 'Уникальный идентификатор типа документа',
	doc_number  VARCHAR NOT NULL                    COMMENT 'Номер документа',
	user_id     INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор сотрудника',
	isIdentified BOOLEAN NOT NULL DEFAULT 'FALSE'   COMMENT 'Метка подтвержденного документа',
	doc_date    DATE                                COMMENT 'Дата выдачи документа'
);
COMMENT ON TABLE user_doc IS 'Документы сотрудника';

CREATE TABLE IF NOT EXISTS document (
	code        VARCHAR NOT NULL                    COMMENT 'Уникальный код типа документа' PRIMARY KEY,
	name        VARCHAR NOT NULL                    COMMENT 'Русскоязычное название документа'
);
COMMENT ON TABLE document IS 'Справочник типов документов';

CREATE TABLE IF NOT EXISTS country (
	code        VARCHAR NOT NULL                    COMMENT 'Уникальный код страны' PRIMARY KEY,
	name        VARCHAR NOT NULL                    COMMENT 'Официальное название страны'
);
COMMENT ON TABLE country IS 'Справочник стран';

CREATE TABLE IF NOT EXISTS title (
	id          INTEGER NOT NULL                    COMMENT 'Уникальный идентификатор' PRIMARY KEY,
	title_name  VARCHAR NOT NULL                    COMMENT 'Название должности'
);
COMMENT ON TABLE title IS 'Справочник должностей';


CREATE INDEX IX_office_orgId ON office (orgId);
ALTER TABLE office ADD FOREIGN KEY     (orgId)         REFERENCES organization(id);

ALTER TABLE user ADD FOREIGN KEY       (citizenship)   REFERENCES country(code);
ALTER TABLE user ADD FOREIGN KEY       (position)      REFERENCES title(id);
ALTER TABLE user_doc ADD FOREIGN KEY   (doc_code)      REFERENCES document(code);

CREATE INDEX IX_user_doc_id ON user_doc (user_id);
ALTER TABLE user_doc ADD FOREIGN KEY   (user_id)       REFERENCES user(id);

CREATE INDEX IX_user_office_userId ON user_office (user_id);
ALTER TABLE user_office ADD FOREIGN KEY (user_id)      REFERENCES user(id);

CREATE INDEX IX_user_office_officeId ON user_office (office_id);
ALTER TABLE user_office ADD FOREIGN KEY (office_id)    REFERENCES office(id);

CREATE SEQUENCE ID_SEQ START WITH 10 INCREMENT BY 1 MINVALUE 10 NOMAXVALUE NOCYCLE NOCACHE;
