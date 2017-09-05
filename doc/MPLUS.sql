/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/5 18:59:16                            */
/*==============================================================*/


DROP TABLE IF EXISTS ELEMENT;

DROP TABLE IF EXISTS FILE;

DROP TABLE IF EXISTS GROUP_ROLE_REL;

DROP TABLE IF EXISTS MENU;

DROP TABLE IF EXISTS OPERATION;

DROP TABLE IF EXISTS PRIVILEGE;

DROP TABLE IF EXISTS PRIVI_ELEMENT_REL;

DROP TABLE IF EXISTS PRIVI_FILE_REL;

DROP TABLE IF EXISTS PRIVI_MENU_REL;

DROP TABLE IF EXISTS PRIVI_OP_REL;

DROP TABLE IF EXISTS ROLE;

DROP TABLE IF EXISTS ROLE_PRIVI_REL;

DROP TABLE IF EXISTS USER;

DROP TABLE IF EXISTS USER_GROUP;

DROP TABLE IF EXISTS USER_GROUP_REL;

DROP TABLE IF EXISTS USER_ROLE_REL;

/*==============================================================*/
/* Table: ELEMENT                                               */
/*==============================================================*/
CREATE TABLE ELEMENT
(
   ELEMENT_ID           NUMERIC(8,0) NOT NULL,
   LELMENT_CODE         VARCHAR(20) NOT NULL,
   PRIMARY KEY (ELEMENT_ID)
);

ALTER TABLE ELEMENT COMMENT 'ҳ��Ԫ�ر�';

/*==============================================================*/
/* Table: FILE                                                  */
/*==============================================================*/
CREATE TABLE FILE
(
   FILE_ID              NUMERIC(8,0) NOT NULL,
   FILE_NAME            VARCHAR(100) NOT NULL,
   FILE_LOCATION        VARCHAR(100) NOT NULL,
   PRIMARY KEY (FILE_ID)
);

ALTER TABLE FILE COMMENT '�ļ���';

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
CREATE TABLE ROLE
(
   ROLE_ID              NUMERIC(8,0) NOT NULL,
   ROLE_NAME            VARCHAR(100) NOT NULL,
   PRIMARY KEY (ROLE_ID)
);

ALTER TABLE ROLE COMMENT '��ɫ��';

/*==============================================================*/
/* Table: USER_GROUP                                            */
/*==============================================================*/
CREATE TABLE USER_GROUP
(
   GROUP_ID             NUMERIC(8,0) NOT NULL,
   GROUP_NAME           VARCHAR(100) NOT NULL,
   PARENT_GROUP_ID      NUMERIC(8,0) NOT NULL,
   PRIMARY KEY (GROUP_ID)
);

ALTER TABLE USER_GROUP COMMENT '�û���';

/*==============================================================*/
/* Table: GROUP_ROLE_REL                                        */
/*==============================================================*/
CREATE TABLE GROUP_ROLE_REL
(
   GROUP_ID             NUMERIC(8,0),
   ROLE_ID              NUMERIC(8,0),
   CONSTRAINT FK_GR_REF_ROLE FOREIGN KEY (ROLE_ID)
      REFERENCES ROLE (ROLE_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_GR_REF_GROUP FOREIGN KEY (GROUP_ID)
      REFERENCES USER_GROUP (GROUP_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE GROUP_ROLE_REL COMMENT '�û������ɫ������';

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
CREATE TABLE MENU
(
   MENU_ID              NUMERIC(8,0) NOT NULL,
   MENU_NAME            VARCHAR(100) NOT NULL,
   MENU_URL             VARCHAR(100),
   PARENT_MENU_ID       NUMERIC(8,0),
   PRIMARY KEY (MENU_ID)
);

ALTER TABLE MENU COMMENT '�˵���';

/*==============================================================*/
/* Table: OPERATION                                             */
/*==============================================================*/
CREATE TABLE OPERATION
(
   OPERATION_ID         NUMERIC(8,0) NOT NULL,
   OPERATION_NAME       VARCHAR(100) NOT NULL,
   OPERATION_CODE       VARCHAR(20) NOT NULL,
   URL_PREFIX           VARCHAR(100),
   PARENT_OPERATION_ID  NUMERIC(8,0),
   PRIMARY KEY (OPERATION_ID)
);

ALTER TABLE OPERATION COMMENT '���ܲ�����';

/*==============================================================*/
/* Table: PRIVILEGE                                             */
/*==============================================================*/
CREATE TABLE PRIVILEGE
(
   PRIVI_ID             NUMERIC(8,0) NOT NULL,
   PRIVI_TYPE           VARCHAR(20) NOT NULL,
   PRIMARY KEY (PRIVI_ID)
);

ALTER TABLE PRIVILEGE COMMENT 'Ȩ�ޱ�';

/*==============================================================*/
/* Table: PRIVI_ELEMENT_REL                                     */
/*==============================================================*/
CREATE TABLE PRIVI_ELEMENT_REL
(
   ELEMENT_ID           NUMERIC(8,0),
   PRIVI_ID             NUMERIC(8,0),
   CONSTRAINT FK_PE_REF_ELEMENT FOREIGN KEY (ELEMENT_ID)
      REFERENCES ELEMENT (ELEMENT_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_PE_REF_PRIVILEGE FOREIGN KEY (PRIVI_ID)
      REFERENCES PRIVILEGE (PRIVI_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE PRIVI_ELEMENT_REL COMMENT 'Ȩ��ҳ��Ԫ�ع�����';

/*==============================================================*/
/* Table: PRIVI_FILE_REL                                        */
/*==============================================================*/
CREATE TABLE PRIVI_FILE_REL
(
   PRIVI_ID             NUMERIC(8,0),
   FILE_ID              NUMERIC(8,0),
   CONSTRAINT FK_PF_REF_FILE FOREIGN KEY (FILE_ID)
      REFERENCES FILE (FILE_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_PF_REF_PRIVILEGE FOREIGN KEY (PRIVI_ID)
      REFERENCES PRIVILEGE (PRIVI_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE PRIVI_FILE_REL COMMENT 'Ȩ���ļ�������';

/*==============================================================*/
/* Table: PRIVI_MENU_REL                                        */
/*==============================================================*/
CREATE TABLE PRIVI_MENU_REL
(
   MENU_ID              NUMERIC(8,0),
   PRIVI_ID             NUMERIC(8,0),
   CONSTRAINT FK_PM_REF_MENU FOREIGN KEY (MENU_ID)
      REFERENCES MENU (MENU_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_PM_REF_PRIVILEGE FOREIGN KEY (PRIVI_ID)
      REFERENCES PRIVILEGE (PRIVI_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE PRIVI_MENU_REL COMMENT 'Ȩ�޲˵�������';

/*==============================================================*/
/* Table: PRIVI_OP_REL                                          */
/*==============================================================*/
CREATE TABLE PRIVI_OP_REL
(
   OPERATION_ID         NUMERIC(8,0),
   PRIVI_ID             NUMERIC(8,0),
   CONSTRAINT FK_PO_REF_PRIVILEGE FOREIGN KEY (PRIVI_ID)
      REFERENCES PRIVILEGE (PRIVI_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_PO_REF_OPERATION FOREIGN KEY (OPERATION_ID)
      REFERENCES OPERATION (OPERATION_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE PRIVI_OP_REL COMMENT 'Ȩ�޲���������';

/*==============================================================*/
/* Table: ROLE_PRIVI_REL                                        */
/*==============================================================*/
CREATE TABLE ROLE_PRIVI_REL
(
   PRIVI_ID             NUMERIC(8,0),
   ROLE_ID              NUMERIC(8,0),
   CONSTRAINT FK_RP_REF_ROLE FOREIGN KEY (ROLE_ID)
      REFERENCES ROLE (ROLE_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_RP_REF_PRIVILEGE FOREIGN KEY (PRIVI_ID)
      REFERENCES PRIVILEGE (PRIVI_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE ROLE_PRIVI_REL COMMENT '��ɫȨ�޹�����';

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
CREATE TABLE USER
(
   USER_ID              NUMERIC(8,0) NOT NULL,
   USER_NAME            VARCHAR(100) NOT NULL,
   PRIMARY KEY (USER_ID)
);

ALTER TABLE USER COMMENT '�û���';

/*==============================================================*/
/* Table: USER_GROUP_REL                                        */
/*==============================================================*/
CREATE TABLE USER_GROUP_REL
(
   USER_ID              NUMERIC(8,0),
   GROUP_ID             NUMERIC(8,0),
   CONSTRAINT FK_UG_REF_USER FOREIGN KEY (USER_ID)
      REFERENCES USER (USER_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_UG_REF_GROUP FOREIGN KEY (GROUP_ID)
      REFERENCES USER_GROUP (GROUP_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE USER_GROUP_REL COMMENT '�û������û�������';

/*==============================================================*/
/* Table: USER_ROLE_REL                                         */
/*==============================================================*/
CREATE TABLE USER_ROLE_REL
(
   ROLE_ID              NUMERIC(8,0),
   USER_ID              NUMERIC(8,0),
   CONSTRAINT FK_UR_REF_USER FOREIGN KEY (USER_ID)
      REFERENCES USER (USER_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT FK_UR_REF_ROLE FOREIGN KEY (ROLE_ID)
      REFERENCES ROLE (ROLE_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE USER_ROLE_REL COMMENT '�û���ɫ������';

