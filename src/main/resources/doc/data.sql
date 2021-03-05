???prompt PL/SQL Developer import file
prompt Created on 2021年3月5日 by Administrator
set feedback off
set define off
prompt Creating API_APP...
create table API_APP
(
  id            VARCHAR2(50) not null,
  secret        VARCHAR2(300) not null,
  status        VARCHAR2(2) default 1 not null,
  name          VARCHAR2(300) not null,
  description   VARCHAR2(600),
  crt_time      DATE,
  crt_user      VARCHAR2(255),
  crt_name      VARCHAR2(255),
  crt_host      VARCHAR2(255),
  upd_time      DATE,
  upd_user      VARCHAR2(255),
  upd_name      VARCHAR2(255),
  upd_host      VARCHAR2(255),
  attr1         VARCHAR2(255),
  attr2         VARCHAR2(255),
  attr3         VARCHAR2(255),
  attr4         VARCHAR2(255),
  appid         VARCHAR2(100),
  main_url      VARCHAR2(300),
  contact_name  VARCHAR2(100),
  contact_phone VARCHAR2(40)
)
tablespace WORKHUB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table API_APP
  is '应用注册表';
comment on column API_APP.id
  is 'appid';
comment on column API_APP.secret
  is '密码';
comment on column API_APP.status
  is '状态1有效0无效';
comment on column API_APP.name
  is '名称';
comment on column API_APP.description
  is '描述';
comment on column API_APP.appid
  is '应用id';
comment on column API_APP.main_url
  is '主要URL一直到端口：如http://127.0.0.1:8080';
comment on column API_APP.contact_name
  is '联系人';
comment on column API_APP.contact_phone
  is '联系人电话';
create unique index UNIQUE_ID on API_APP (ID)
  tablespace WORKHUB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating API_APPLY...
create table API_APPLY
(
  id         VARCHAR2(50) not null,
  app_id     VARCHAR2(50) not null,
  service_id VARCHAR2(50) not null,
  status     VARCHAR2(4) default 1,
  crt_time   DATE,
  crt_user   VARCHAR2(255),
  crt_name   VARCHAR2(255),
  crt_host   VARCHAR2(255),
  upd_time   DATE,
  upd_user   VARCHAR2(255),
  upd_name   VARCHAR2(255),
  upd_host   VARCHAR2(255),
  attr1      VARCHAR2(255),
  attr2      VARCHAR2(255),
  attr3      VARCHAR2(255),
  attr4      VARCHAR2(255)
)
tablespace WORKHUB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table API_APPLY
  is '服务器调用申请表';
comment on column API_APPLY.id
  is 'id';
comment on column API_APPLY.app_id
  is 'app的id';
comment on column API_APPLY.service_id
  is '所申请的调用服务的id';
comment on column API_APPLY.status
  is '1启用0不启用';

prompt Creating API_ROLE...
create table API_ROLE
(
  id       VARCHAR2(64),
  name     VARCHAR2(300),
  crt_time DATE,
  crt_user VARCHAR2(255),
  crt_name VARCHAR2(255),
  crt_host VARCHAR2(255),
  upd_time DATE,
  upd_user VARCHAR2(255),
  upd_name VARCHAR2(255),
  upd_host VARCHAR2(255),
  attr1    VARCHAR2(255),
  attr2    VARCHAR2(255),
  attr3    VARCHAR2(255),
  attr4    VARCHAR2(255)
)
tablespace WORKHUB
  pctfree 10
  initrans 1
  maxtrans 255;

prompt Creating API_SERVICE...
create table API_SERVICE
(
  id           VARCHAR2(50) not null,
  app_id       VARCHAR2(50) not null,
  request_type VARCHAR2(10),
  request_url  VARCHAR2(4000),
  status       VARCHAR2(4) default 1,
  crt_time     DATE,
  crt_user     VARCHAR2(255),
  crt_name     VARCHAR2(255),
  crt_host     VARCHAR2(255),
  upd_time     DATE,
  upd_user     VARCHAR2(255),
  upd_name     VARCHAR2(255),
  upd_host     VARCHAR2(255),
  attr1        VARCHAR2(255),
  attr2        VARCHAR2(255),
  attr3        VARCHAR2(255),
  attr4        VARCHAR2(255),
  black_ip     VARCHAR2(600),
  white_ip     VARCHAR2(600)
)
tablespace WORKHUB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table API_SERVICE
  is 'app提供的服务列表';
comment on column API_SERVICE.id
  is 'id';
comment on column API_SERVICE.app_id
  is 'app的id';
comment on column API_SERVICE.request_type
  is '请求类型get、post、delete、put等等';
comment on column API_SERVICE.request_url
  is '请求url(不包括ip端口)';
comment on column API_SERVICE.status
  is '状态1使用0停用';
comment on column API_SERVICE.black_ip
  is '黑名单';
comment on column API_SERVICE.white_ip
  is '白名单';

prompt Creating API_USER...
create table API_USER
(
  id       VARCHAR2(64),
  name     VARCHAR2(300),
  pwd      VARCHAR2(3000),
  crt_time DATE,
  crt_user VARCHAR2(255),
  crt_name VARCHAR2(255),
  crt_host VARCHAR2(255),
  upd_time DATE,
  upd_user VARCHAR2(255),
  upd_name VARCHAR2(255),
  upd_host VARCHAR2(255),
  attr1    VARCHAR2(255),
  attr2    VARCHAR2(255),
  attr3    VARCHAR2(255),
  attr4    VARCHAR2(255),
  token    VARCHAR2(255)
)
tablespace WORKHUB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating API_USER_ROLE...
create table API_USER_ROLE
(
  id       VARCHAR2(64),
  user_id  VARCHAR2(64),
  role_id  VARCHAR2(64),
  crt_time DATE,
  crt_user VARCHAR2(255),
  crt_name VARCHAR2(255),
  crt_host VARCHAR2(255),
  upd_time DATE,
  upd_user VARCHAR2(255),
  upd_name VARCHAR2(255),
  upd_host VARCHAR2(255),
  attr1    VARCHAR2(255),
  attr2    VARCHAR2(255),
  attr3    VARCHAR2(255),
  attr4    VARCHAR2(255)
)
tablespace WORKHUB
  pctfree 10
  initrans 1
  maxtrans 255;

prompt Disabling triggers for API_APP...
alter table API_APP disable all triggers;
prompt Disabling triggers for API_APPLY...
alter table API_APPLY disable all triggers;
prompt Disabling triggers for API_ROLE...
alter table API_ROLE disable all triggers;
prompt Disabling triggers for API_SERVICE...
alter table API_SERVICE disable all triggers;
prompt Disabling triggers for API_USER...
alter table API_USER disable all triggers;
prompt Disabling triggers for API_USER_ROLE...
alter table API_USER_ROLE disable all triggers;
prompt Loading API_APP...
insert into API_APP (id, secret, status, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, appid, main_url, contact_name, contact_phone)
values ('GCVgIa72', '99bcaa38946ac4e02518ad4337c22333', '1', '金达开3333', '2222222', to_date('26-02-2021 09:25:36', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('26-02-2021 09:25:36', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'RoCY84PY', 'http://127.0.0.1:8080', '张三丰', '131111111111');
insert into API_APP (id, secret, status, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, appid, main_url, contact_name, contact_phone)
values ('KSKUIzZh', 'cc2163f31a465c76f193ad3b11ce557b', '1', '金达开223', '2222222', to_date('07-08-2020 14:05:43', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('07-08-2020 14:05:43', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'nGk50Hmd', 'http://192.0.0.1:8080', null, null);
insert into API_APP (id, secret, status, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, appid, main_url, contact_name, contact_phone)
values ('2mxFJBVc', '92604da1ceae36fc7ee2744c4b3d5560', '1', 'app2', '我的app2', to_date('06-07-2020 14:21:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('06-07-2020 14:21:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'CeeVXLx9', 'http://127.0.0.1:8031', null, null);
insert into API_APP (id, secret, status, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, appid, main_url, contact_name, contact_phone)
values ('nyslDupw', '7e4a6525b683a380d593593c3aa2c3a6', '1', '金达开222', '2222222', to_date('07-08-2020 13:55:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('07-08-2020 13:55:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'lkx5ltJw', 'http://127.0.0.1:8030', null, null);
insert into API_APP (id, secret, status, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, appid, main_url, contact_name, contact_phone)
values ('3JrekugM', 'ee389befb07ac8f568dd27e2318dc6e3', '1', '云雀研讨', '研讨服务', to_date('29-10-2020 08:54:35', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('29-10-2020 08:54:35', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'lQ1JLazI', 'http://127.0.0.1:8089', null, null);
insert into API_APP (id, secret, status, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, appid, main_url, contact_name, contact_phone)
values ('gyDuzG17', 'b3aed8f51f26f07c5dca9cb3405f6b7c', '1', 'app1', '测试', to_date('06-07-2020', 'dd-mm-yyyy'), null, null, null, to_date('06-07-2020', 'dd-mm-yyyy'), null, null, null, null, null, null, null, '67KpSREk', 'http://127.0.0.1:8030', null, null);
commit;
prompt 6 records loaded
prompt Loading API_APPLY...
insert into API_APPLY (id, app_id, service_id, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('kasn42le', 'nGk50Hmd', 'pYYfHP6e', '1', to_date('07-08-2020 14:07:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('07-08-2020 14:07:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into API_APPLY (id, app_id, service_id, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('KLhHco8D', 'CeeVXLx9', '6KH8pbxr', '1', to_date('06-07-2020 14:25:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('06-07-2020 14:25:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into API_APPLY (id, app_id, service_id, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('8Vpnzean', 'lkx5ltJw', 's050Od67', '1', to_date('29-10-2020 09:05:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('29-10-2020 09:05:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
commit;
prompt 3 records loaded
prompt Loading API_ROLE...
prompt Table is empty
prompt Loading API_SERVICE...
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('13H8pbxr', '67KpSREk', 'put', '/test/putTest2', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('14H8pbxr', '67KpSREk', 'delete', '/test/deleteTest2?param1={param1}&param2={param2}', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('6KH8pbxr', '67KpSREk', 'get', '/test/getTest1', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('7KH8pbxr', '67KpSREk', 'get', '/test/getTest2?param1={param1}&param2={param2}', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('8KH8pbxr', '67KpSREk', 'get', '/test/getTest3/{param1}', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('9KH8pbxr', '67KpSREk', 'post', '/test/postTest1', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('10H8pbxr', '67KpSREk', 'post', '/test/postTest2', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('11H8pbxr', '67KpSREk', 'delete', '/test/deleteTest1?param1={param1}', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('12H8pbxr', '67KpSREk', 'put', '/test/putTest1', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('pYYfHP6e', 'lkx5ltJw', 'get', '/test/getTest1', '1', to_date('07-08-2020 14:03:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('07-08-2020 14:03:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip)
values ('s050Od67', 'lQ1JLazI', 'post', '/backGroundMessage/notice', '1', to_date('29-10-2020 08:58:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('29-10-2020 08:58:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null);
commit;
prompt 11 records loaded
prompt Loading API_USER...
insert into API_USER (id, name, pwd, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, token)
values ('nybDt7bT', 'tom', '$2a$10$xRjdXS7yRzggWYhmIHVi2uhdkK8eYQJ97ZDxpgSe2WduamAb6i4z2', to_date('04-03-2021 09:44:58', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('04-03-2021 15:21:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b20iLCJjcmVhdGVkIjoxNjE0ODQyNTA0MjY2LCJleHAiOjE2MTQ4NDQzMDR9.j3kwidH_XMNum1H75v4nZ8uEtxdDv_D8LLlRkFq-glmYuK0vQUkxxZZOdCHtYoD9iER9VItIetNehXxbOuLd_g');
commit;
prompt 1 records loaded
prompt Loading API_USER_ROLE...
prompt Table is empty
prompt Enabling triggers for API_APP...
alter table API_APP enable all triggers;
prompt Enabling triggers for API_APPLY...
alter table API_APPLY enable all triggers;
prompt Enabling triggers for API_ROLE...
alter table API_ROLE enable all triggers;
prompt Enabling triggers for API_SERVICE...
alter table API_SERVICE enable all triggers;
prompt Enabling triggers for API_USER...
alter table API_USER enable all triggers;
prompt Enabling triggers for API_USER_ROLE...
alter table API_USER_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
