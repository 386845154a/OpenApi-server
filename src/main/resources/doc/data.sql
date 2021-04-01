???prompt PL/SQL Developer import file
prompt Created on 2021年4月1日 by Administrator
set feedback off
set define off
prompt Creating API_APP...
create table API_APP
(
  id            VARCHAR2(50) not null,
  appid         VARCHAR2(100) not null,
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
  main_url      VARCHAR2(300),
  contact_name  VARCHAR2(100),
  contact_phone VARCHAR2(40),
  pwd           VARCHAR2(300),
  code          VARCHAR2(300)
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
comment on column API_APP.appid
  is '应用id';
comment on column API_APP.secret
  is 'app秘钥';
comment on column API_APP.status
  is '状态1有效0无效';
comment on column API_APP.name
  is '名称';
comment on column API_APP.description
  is '描述';
comment on column API_APP.main_url
  is '主要URL一直到端口：如http://127.0.0.1:8080';
comment on column API_APP.contact_name
  is '联系人';
comment on column API_APP.contact_phone
  is '联系人电话';
comment on column API_APP.pwd
  is '前端登录使用它md5加密';
comment on column API_APP.code
  is '身份证前端登录使用';
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

prompt Creating API_LOG...
create table API_LOG
(
  id         VARCHAR2(32) not null,
  appid      VARCHAR2(32),
  service_id VARCHAR2(32),
  time       DATE default sysdate,
  res        VARCHAR2(4) default '1' not null,
  msg        VARCHAR2(4000),
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
  attr4      VARCHAR2(255),
  param      VARCHAR2(4000),
  body       VARCHAR2(4000),
  return_obj VARCHAR2(4000)
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
comment on table API_LOG
  is '调用日志';
comment on column API_LOG.id
  is 'ID';
comment on column API_LOG.appid
  is 'app';
comment on column API_LOG.service_id
  is '服务id';
comment on column API_LOG.time
  is '调用时间';
comment on column API_LOG.res
  is '结果1成功0失败';
comment on column API_LOG.msg
  is '详细';
comment on column API_LOG.param
  is '参数';
comment on column API_LOG.body
  is 'body';
comment on column API_LOG.return_obj
  is '返回结果';
alter table API_LOG
  add constraint LOGMAIN primary key (ID)
  using index 
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
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table API_ROLE
  is '该表暂时废弃';

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
  white_ip     VARCHAR2(600),
  mark         VARCHAR2(4000),
  version      VARCHAR2(30) default '1.0',
  encode       VARCHAR2(30) default 'UTF-8',
  param        VARCHAR2(4000),
  attch_file   VARCHAR2(4000),
  content_type VARCHAR2(200) default 'application/json',
  name         VARCHAR2(600)
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
comment on column API_SERVICE.mark
  is '备注';
comment on column API_SERVICE.version
  is '版本';
comment on column API_SERVICE.encode
  is '编码';
comment on column API_SERVICE.param
  is '参数';
comment on column API_SERVICE.attch_file
  is '附件';
comment on column API_SERVICE.content_type
  is 'application/json application/x-www-form-urlencoded';
comment on column API_SERVICE.name
  is '接口名称';

prompt Creating API_TOKEN...
create table API_TOKEN
(
  id       VARCHAR2(64) not null,
  app_id   VARCHAR2(64),
  token    VARCHAR2(512),
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
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table API_TOKEN
  is 'token保存';
comment on column API_TOKEN.app_id
  is 'appid';
comment on column API_TOKEN.token
  is 'token';
alter table API_TOKEN
  add constraint TOKENPRIMARY primary key (ID)
  using index 
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
comment on table API_USER
  is '该表暂时废弃';

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
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table API_USER_ROLE
  is '该表暂时废弃';

prompt Creating BUSINESS_NOTICE_BODY_OPERATE...
create table BUSINESS_NOTICE_BODY_OPERATE
(
  id          VARCHAR2(64) not null,
  detail_url  VARCHAR2(600),
  type        VARCHAR2(8),
  content_abs VARCHAR2(600),
  call_back   VARCHAR2(600),
  msg         VARCHAR2(600),
  img_url     VARCHAR2(600),
  bz          VARCHAR2(600),
  crt_time    DATE,
  crt_user    VARCHAR2(255),
  crt_name    VARCHAR2(255),
  crt_host    VARCHAR2(255),
  upd_time    DATE,
  upd_user    VARCHAR2(255),
  upd_name    VARCHAR2(255),
  upd_host    VARCHAR2(255),
  attr1       VARCHAR2(255),
  attr2       VARCHAR2(255),
  attr3       VARCHAR2(255),
  attr4       VARCHAR2(255),
  business_id VARCHAR2(64)
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
comment on table BUSINESS_NOTICE_BODY_OPERATE
  is '协议内容-操作';
comment on column BUSINESS_NOTICE_BODY_OPERATE.id
  is '主键';
comment on column BUSINESS_NOTICE_BODY_OPERATE.detail_url
  is '业务详情';
comment on column BUSINESS_NOTICE_BODY_OPERATE.type
  is '类型';
comment on column BUSINESS_NOTICE_BODY_OPERATE.content_abs
  is '事件描述（摘要）';
comment on column BUSINESS_NOTICE_BODY_OPERATE.call_back
  is '同意、驳回接口，回调地址，参数固定';
comment on column BUSINESS_NOTICE_BODY_OPERATE.msg
  is '理由回填（同意、驳回）';
comment on column BUSINESS_NOTICE_BODY_OPERATE.img_url
  is '图片地址';
comment on column BUSINESS_NOTICE_BODY_OPERATE.bz
  is '备注';
comment on column BUSINESS_NOTICE_BODY_OPERATE.business_id
  is '业务单号，应用内唯一，云雀不使用查看审批详情使用';
alter table BUSINESS_NOTICE_BODY_OPERATE
  add constraint OPERATEPRIMARY primary key (ID)
  using index 
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

prompt Creating BUSINESS_NOTICE_BODY_RECOMMEND...
create table BUSINESS_NOTICE_BODY_RECOMMEND
(
  id        VARCHAR2(64) not null,
  url       VARCHAR2(600),
  titile    VARCHAR2(450),
  time      DATE,
  source    VARCHAR2(600),
  author    VARCHAR2(300),
  domain    VARCHAR2(450),
  rsource   VARCHAR2(600),
  relevancy NUMBER,
  bz        VARCHAR2(600),
  crt_time  DATE,
  crt_user  VARCHAR2(255),
  crt_name  VARCHAR2(255),
  crt_host  VARCHAR2(255),
  upd_time  DATE,
  upd_user  VARCHAR2(255),
  upd_name  VARCHAR2(255),
  upd_host  VARCHAR2(255),
  attr1     VARCHAR2(255),
  attr2     VARCHAR2(255),
  attr3     VARCHAR2(255),
  attr4     VARCHAR2(255)
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
comment on table BUSINESS_NOTICE_BODY_RECOMMEND
  is '协议内容-推荐';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.id
  is '主键';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.url
  is '链接地址';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.titile
  is '内容标题';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.time
  is '内容产生时间';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.source
  is '内容来源地';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.author
  is '作者';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.domain
  is '领域';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.rsource
  is '推荐源（根据什么推荐）';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.relevancy
  is '相关度';
comment on column BUSINESS_NOTICE_BODY_RECOMMEND.bz
  is '备注';
alter table BUSINESS_NOTICE_BODY_RECOMMEND
  add constraint RECOMMEDPRIMAY primary key (ID)
  using index 
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

prompt Creating BUSINESS_NOTICE_BODY_SHOW...
create table BUSINESS_NOTICE_BODY_SHOW
(
  id             VARCHAR2(64) not null,
  detail_url     VARCHAR2(600),
  type           VARCHAR2(8),
  content_abs    VARCHAR2(600),
  content_detail VARCHAR2(600),
  img_url        VARCHAR2(600),
  bz             VARCHAR2(600),
  crt_time       DATE,
  crt_user       VARCHAR2(255),
  crt_name       VARCHAR2(255),
  crt_host       VARCHAR2(255),
  upd_time       DATE,
  upd_user       VARCHAR2(255),
  upd_name       VARCHAR2(255),
  upd_host       VARCHAR2(255),
  attr1          VARCHAR2(255),
  attr2          VARCHAR2(255),
  attr3          VARCHAR2(255),
  attr4          VARCHAR2(255),
  business_id    VARCHAR2(64)
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
comment on table BUSINESS_NOTICE_BODY_SHOW
  is '协议内容-消息展示';
comment on column BUSINESS_NOTICE_BODY_SHOW.id
  is '主键';
comment on column BUSINESS_NOTICE_BODY_SHOW.detail_url
  is '查看详情地址';
comment on column BUSINESS_NOTICE_BODY_SHOW.type
  is '类型';
comment on column BUSINESS_NOTICE_BODY_SHOW.content_abs
  is '事件描述（摘要）';
comment on column BUSINESS_NOTICE_BODY_SHOW.content_detail
  is '事件详情（可扩展采用 key：value json格式）';
comment on column BUSINESS_NOTICE_BODY_SHOW.img_url
  is '图片url';
comment on column BUSINESS_NOTICE_BODY_SHOW.bz
  is '备注';
comment on column BUSINESS_NOTICE_BODY_SHOW.business_id
  is '业务单号';
alter table BUSINESS_NOTICE_BODY_SHOW
  add constraint SHOWPRIMARY primary key (ID)
  using index 
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

prompt Creating BUSINESS_NOTICE_BODY_UPDATE...
create table BUSINESS_NOTICE_BODY_UPDATE
(
  id           VARCHAR2(64) not null,
  project_name VARCHAR2(450),
  project_id   VARCHAR2(300),
  post_data    VARCHAR2(900),
  change_data  VARCHAR2(900),
  version      VARCHAR2(300),
  correlation  NUMBER,
  bz           VARCHAR2(600),
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
  attr4        VARCHAR2(255)
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
comment on table BUSINESS_NOTICE_BODY_UPDATE
  is '协议内容-数据更新';
comment on column BUSINESS_NOTICE_BODY_UPDATE.id
  is '主键';
comment on column BUSINESS_NOTICE_BODY_UPDATE.project_name
  is '项目名称';
comment on column BUSINESS_NOTICE_BODY_UPDATE.project_id
  is '项目id（回调查看项目相关信息使用）';
comment on column BUSINESS_NOTICE_BODY_UPDATE.post_data
  is '原始数据';
comment on column BUSINESS_NOTICE_BODY_UPDATE.change_data
  is '变更后数据';
comment on column BUSINESS_NOTICE_BODY_UPDATE.version
  is '数据版本（最新）';
comment on column BUSINESS_NOTICE_BODY_UPDATE.correlation
  is '关联性（强1、弱0关联）';
comment on column BUSINESS_NOTICE_BODY_UPDATE.bz
  is '备注';
alter table BUSINESS_NOTICE_BODY_UPDATE
  add constraint UPDATEPRIMARY primary key (ID)
  using index 
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

prompt Creating BUSINESS_NOTICE_HEADER...
create table BUSINESS_NOTICE_HEADER
(
  id              VARCHAR2(64) not null,
  notice_read     NUMBER default 0,
  sender_name     VARCHAR2(300),
  receiver_id     VARCHAR2(64),
  receiver_name   VARCHAR2(300),
  sender_org_name VARCHAR2(300),
  source_name     VARCHAR2(300),
  source_ip       VARCHAR2(100),
  sender_type     NUMBER,
  time            DATE default sysdate,
  status          NUMBER,
  receipt         NUMBER,
  notice_level    NUMBER,
  bz              VARCHAR2(900),
  detail_id       VARCHAR2(64),
  crt_time        DATE,
  crt_user        VARCHAR2(255),
  crt_name        VARCHAR2(255),
  crt_host        VARCHAR2(255),
  upd_time        DATE,
  upd_user        VARCHAR2(255),
  upd_name        VARCHAR2(255),
  upd_host        VARCHAR2(255),
  attr1           VARCHAR2(255),
  attr2           VARCHAR2(255),
  attr3           VARCHAR2(255),
  attr4           VARCHAR2(255)
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
comment on column BUSINESS_NOTICE_HEADER.id
  is '通知主键（研讨内部唯一id）';
comment on column BUSINESS_NOTICE_HEADER.notice_read
  is '是否已读 （默认0未读，1已读，2已同意，3已驳回，4已阅）';
comment on column BUSINESS_NOTICE_HEADER.sender_name
  is '通知人姓名';
comment on column BUSINESS_NOTICE_HEADER.receiver_id
  is '接收人';
comment on column BUSINESS_NOTICE_HEADER.receiver_name
  is '接收人姓名';
comment on column BUSINESS_NOTICE_HEADER.source_name
  is '通知来源（业务/应用名称）';
comment on column BUSINESS_NOTICE_HEADER.source_ip
  is '来源IP';
comment on column BUSINESS_NOTICE_HEADER.sender_type
  is '801：审批；802：系统消息；803：数据更新；804：推荐';
comment on column BUSINESS_NOTICE_HEADER.time
  is '通知发送时间';
comment on column BUSINESS_NOTICE_HEADER.status
  is '紧急状态（0重要、1非重要）';
comment on column BUSINESS_NOTICE_HEADER.receipt
  is '回执（研讨内部使用，标注消息已阅，取消强提醒）';
comment on column BUSINESS_NOTICE_HEADER.notice_level
  is '密级 1：非密，2秘密，3机密';
comment on column BUSINESS_NOTICE_HEADER.bz
  is '备注';
comment on column BUSINESS_NOTICE_HEADER.detail_id
  is '详细id';
alter table BUSINESS_NOTICE_HEADER
  add constraint UKEY primary key (ID)
  using index 
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

prompt Disabling triggers for API_APP...
alter table API_APP disable all triggers;
prompt Disabling triggers for API_APPLY...
alter table API_APPLY disable all triggers;
prompt Disabling triggers for API_LOG...
alter table API_LOG disable all triggers;
prompt Disabling triggers for API_ROLE...
alter table API_ROLE disable all triggers;
prompt Disabling triggers for API_SERVICE...
alter table API_SERVICE disable all triggers;
prompt Disabling triggers for API_TOKEN...
alter table API_TOKEN disable all triggers;
prompt Disabling triggers for API_USER...
alter table API_USER disable all triggers;
prompt Disabling triggers for API_USER_ROLE...
alter table API_USER_ROLE disable all triggers;
prompt Disabling triggers for BUSINESS_NOTICE_BODY_OPERATE...
alter table BUSINESS_NOTICE_BODY_OPERATE disable all triggers;
prompt Disabling triggers for BUSINESS_NOTICE_BODY_RECOMMEND...
alter table BUSINESS_NOTICE_BODY_RECOMMEND disable all triggers;
prompt Disabling triggers for BUSINESS_NOTICE_BODY_SHOW...
alter table BUSINESS_NOTICE_BODY_SHOW disable all triggers;
prompt Disabling triggers for BUSINESS_NOTICE_BODY_UPDATE...
alter table BUSINESS_NOTICE_BODY_UPDATE disable all triggers;
prompt Disabling triggers for BUSINESS_NOTICE_HEADER...
alter table BUSINESS_NOTICE_HEADER disable all triggers;
prompt Loading API_APP...
insert into API_APP (id, appid, secret, status, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, main_url, contact_name, contact_phone, pwd, code)
values ('oIV6HEYH', 'roKiiAXn', 'd5206eee30013aa65fa406ed9fe5bb24', '1', 'openApi', '2222222', to_date('23-03-2021 16:18:59', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 16:18:59', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'http://127.0.0.1:8030', '张三丰', '131111111111', '$2a$10$NiiU5x0zeFznqAKXphlRBeSM/PGafceyjQli4Qlqg3USXF697T7qK', '2102117854919990174');
insert into API_APP (id, appid, secret, status, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, main_url, contact_name, contact_phone, pwd, code)
values ('RSnLoDcV', 'Nztv3WuN', 'dfa2c86c4094f0d4f4507752d850f436', '1', 'thirdTest', '2222222', to_date('23-03-2021 16:24:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 16:24:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'http://127.0.0.1:9001', '张三丰', '13222222222222', '$2a$10$RPrXJB3zZPcPjgJKkzE1h.AisLN3KUyuu/fbbDfMq9uKUCje2jxJq', null);
commit;
prompt 2 records loaded
prompt Loading API_APPLY...
insert into API_APPLY (id, app_id, service_id, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('a050Od65', 'Nztv3WuN', 's050Od68', '1', null, null, null, null, null, null, null, null, null, null, null, null);
insert into API_APPLY (id, app_id, service_id, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('KLhHco8s', 'roKiiAXn', 's050Od68', '1', to_date('29-10-2020 09:05:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, null);
commit;
prompt 2 records loaded
prompt Loading API_LOG...
insert into API_LOG (id, appid, service_id, time, res, msg, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, param, body, return_obj)
values ('QxYmZMc5', 'Nztv3WuN', 's050Od68', to_date('01-04-2021 14:37:23', 'dd-mm-yyyy hh24:mi:ss'), '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '{}', '{senderName=发送人, receiverId=123456789, receiverName=接收人, senderOrgName=二院, sourceName=app, senderType=804, status=0, noticeLevel=1, bz=备注说明, msgContent={url=wwww.baidu.com, titile=大促销, time=2021-01-03 15:59:03, source=www.source.com, author=王六六, domain=baidu.com, rsource=resource.com, relevancy=0.9, bz=最新数据}}', null);
insert into API_LOG (id, appid, service_id, time, res, msg, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, param, body, return_obj)
values ('AWXh21yf', 'Nztv3WuN', 's050Od68', to_date('01-04-2021 14:44:32', 'dd-mm-yyyy hh24:mi:ss'), '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '{}', '{senderName=发送人, receiverId=123456789, receiverName=接收人, senderOrgName=二院, sourceName=app, senderType=804, status=0, noticeLevel=1, bz=备注说明, msgContent={url=wwww.baidu.com, titile=大促销, time=2021-01-03 15:59:03, source=www.source.com, author=王六六, domain=baidu.com, rsource=resource.com, relevancy=0.9, bz=最新数据}}', '{"result":{"msg":"操作成功","code":"1"},"rel":true,"status":200,"timestamp":"1617259471209"}');
commit;
prompt 2 records loaded
prompt Loading API_ROLE...
insert into API_ROLE (id, name, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('1', 'ROLE_ADMIN', null, null, null, null, null, null, null, null, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading API_SERVICE...
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('s050Od68', 'roKiiAXn', 'post', '/OpenApi/notice/sendNotice', '1', to_date('29-10-2020 08:58:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('29-10-2020 08:58:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('iD3Amulc', 'RoCY84PY', 'get', '/test/getTest1', '1', to_date('05-03-2021 16:43:12', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('05-03-2021 16:43:12', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('GMlAnqhY', 'c7jNNCIx', 'get', ' /test/getTest1', '1', to_date('19-03-2021 16:31:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('19-03-2021 16:31:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('13H8pbxr', '67KpSREk', 'put', '/test/putTest2', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('14H8pbxr', '67KpSREk', 'delete', '/test/deleteTest2?param1={param1}&param2={param2}', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('6KH8pbxr', '67KpSREk', 'get', '/test/getTest1', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('7KH8pbxr', '67KpSREk', 'get', '/test/getTest2?param1={param1}&param2={param2}', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('8KH8pbxr', '67KpSREk', 'get', '/test/getTest3/{param1}', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('9KH8pbxr', '67KpSREk', 'post', '/test/postTest1', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('10H8pbxr', '67KpSREk', 'post', '/test/postTest2', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('11H8pbxr', '67KpSREk', 'delete', '/test/deleteTest1?param1={param1}', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('12H8pbxr', '67KpSREk', 'put', '/test/putTest1', '1', to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('22-06-2020 16:38:33', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('pYYfHP6e', 'lkx5ltJw', 'get', '/test/getTest1', '1', to_date('07-08-2020 14:03:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('07-08-2020 14:03:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
insert into API_SERVICE (id, app_id, request_type, request_url, status, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, black_ip, white_ip, mark, version, encode, param, attch_file, content_type, name)
values ('s050Od67', 'lQ1JLazI', 'post', '/backGroundMessage/notice', '1', to_date('29-10-2020 08:58:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('29-10-2020 08:58:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, '1.0', 'UTF-8', null, null, 'application/json', null);
commit;
prompt 14 records loaded
prompt Loading API_TOKEN...
insert into API_TOKEN (id, app_id, token, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('xSBuDkNu', 'roKiiAXn', 'oeadmOczKMmUw2jnDoimdSZEWIAHqTxDwDkYiBy9uPscrLHx', to_date('23-03-2021 16:20:58', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 16:20:58', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into API_TOKEN (id, app_id, token, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('frKI8Tyi', 'Nztv3WuN', 'biER15KVD3TWKd0iFxtSXbvYNVbNjR1An9YAKYMYBw6aMYR0', to_date('23-03-2021 16:25:32', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 16:25:32', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
commit;
prompt 2 records loaded
prompt Loading API_USER...
insert into API_USER (id, name, pwd, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, token)
values ('nybDt7bs', 'admin', '$2a$10$xRjdXS7yRzggWYhmIHVi2uhdkK8eYQJ97ZDxpgSe2WduamAb6i4z2', to_date('04-03-2021 09:44:58', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('04-03-2021 15:21:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b20iLCJjcmVhdGVkIjoxNjE0ODQyNTA0MjY2LCJleHAiOjE2MTQ4NDQzMDR9.j3kwidH_XMNum1H75v4nZ8uEtxdDv_D8LLlRkFq-glmYuK0vQUkxxZZOdCHtYoD9iER9VItIetNehXxbOuLd_g');
insert into API_USER (id, name, pwd, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, token)
values ('nybDt7bT', 'tom', '$2a$10$xRjdXS7yRzggWYhmIHVi2uhdkK8eYQJ97ZDxpgSe2WduamAb6i4z2', to_date('04-03-2021 09:44:58', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('04-03-2021 15:21:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b20iLCJjcmVhdGVkIjoxNjE0ODQyNTA0MjY2LCJleHAiOjE2MTQ4NDQzMDR9.j3kwidH_XMNum1H75v4nZ8uEtxdDv_D8LLlRkFq-glmYuK0vQUkxxZZOdCHtYoD9iER9VItIetNehXxbOuLd_g');
insert into API_USER (id, name, pwd, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, token)
values ('2cfplkcX', 'jerry', '$2a$10$pfpgLlW7zoIm0gJcbRjITuOkKUBW7yiKrWG7qYA//J6DJcqeBJf1O', to_date('05-03-2021 16:10:48', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('05-03-2021 16:10:48', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null);
commit;
prompt 3 records loaded
prompt Loading API_USER_ROLE...
insert into API_USER_ROLE (id, user_id, role_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('1', 'nybDt7bT', '1', null, null, null, null, null, null, null, null, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading BUSINESS_NOTICE_BODY_OPERATE...
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('BCyGZ84T', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('23-03-2021 10:05:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 10:05:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('MY2UgRSK', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('23-03-2021 15:05:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 15:05:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('QoMwp9hg', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('23-03-2021 15:09:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 15:09:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('E613bsod', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:11:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:11:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('49HZt3WJ', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:12:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:12:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('UbSHbuaV', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:15:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:15:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('rLDp9FJn', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:15:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:15:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('DDiFbGRx', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:37:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:37:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('dsZgSWwh', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:42:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:42:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('dfCyNuuZ', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:42:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:42:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('a0kgDac9', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:42:38', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:42:38', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
insert into BUSINESS_NOTICE_BODY_OPERATE (id, detail_url, type, content_abs, call_back, msg, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('a6x8J0xg', 'www.baidu.com', '1', '事件描述', 'www.back.com', '可以', 'www.tupian.com', '紧急审批', to_date('24-03-2021 09:48:41', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:48:41', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
commit;
prompt 12 records loaded
prompt Loading BUSINESS_NOTICE_BODY_RECOMMEND...
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('sMWFz05K', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('25-03-2021 15:28:20', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('25-03-2021 15:28:20', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('dmpGfdnC', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('30-03-2021 15:37:05', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('30-03-2021 15:37:05', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('Cq4IgPOm', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('30-03-2021 15:37:06', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('30-03-2021 15:37:06', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('n5nczIoF', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('01-04-2021 14:29:15', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:29:15', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('tG2ltwtI', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('01-04-2021 14:29:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:29:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('XZK4oJxz', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('01-04-2021 14:30:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:30:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('6sMWp0ps', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('01-04-2021 14:32:31', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:32:35', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('sIp34mJ6', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('01-04-2021 14:33:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:33:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('0Jy8liHj', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('01-04-2021 14:37:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:37:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_BODY_RECOMMEND (id, url, titile, time, source, author, domain, rsource, relevancy, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('c4T78NdR', 'wwww.baidu.com', '大促销', to_date('03-01-2021 15:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'www.source.com', '王六六', 'baidu.com', 'resource.com', .9, '最新数据', to_date('01-04-2021 14:44:31', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:44:31', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
commit;
prompt 10 records loaded
prompt Loading BUSINESS_NOTICE_BODY_SHOW...
insert into BUSINESS_NOTICE_BODY_SHOW (id, detail_url, type, content_abs, content_detail, img_url, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, business_id)
values ('uPA9VRZl', 'www.baidu.com', '1', '事件描述', '商店大促销', 'www.tupian.com', '紧急审批', to_date('25-03-2021 15:13:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('25-03-2021 15:13:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, 'ywdhid');
commit;
prompt 1 records loaded
prompt Loading BUSINESS_NOTICE_BODY_UPDATE...
insert into BUSINESS_NOTICE_BODY_UPDATE (id, project_name, project_id, post_data, change_data, version, correlation, bz, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('t0Zsf8Wa', '飞行项目', 'proId', '{id=id1, name=test}', '{id=id1, name=test2}', 'v1.1', 1, '最新数据', to_date('25-03-2021 15:21:26', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('25-03-2021 15:21:26', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading BUSINESS_NOTICE_HEADER...
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('RCtOjHN9', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 802, to_date('25-03-2021 15:13:13', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'uPA9VRZl', to_date('25-03-2021 15:13:13', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('25-03-2021 15:13:13', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('NC4ph4iK', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 803, to_date('25-03-2021 15:21:28', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 't0Zsf8Wa', to_date('25-03-2021 15:21:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('25-03-2021 15:21:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('UljmLAG7', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('25-03-2021 15:28:20', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'sMWFz05K', to_date('25-03-2021 15:28:20', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('25-03-2021 15:28:20', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('mIvOWApp', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, null, 0, null, 1, '备注说明', 'BCyGZ84T', to_date('23-03-2021 10:06:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 10:06:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('ispAFtN7', 0, '发送人', 'yanzhenqing', '接收人', '二院', 'app', null, 801, to_date('23-03-2021 15:05:17', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'MY2UgRSK', to_date('23-03-2021 15:05:17', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 15:05:17', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('raq8JhaS', 0, '发送人', 'yanzhenqing', '接收人', '二院', 'app', null, 801, to_date('23-03-2021 15:09:29', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'QoMwp9hg', to_date('23-03-2021 15:09:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('23-03-2021 15:09:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('06wDCCxo', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:11:02', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'E613bsod', to_date('24-03-2021 09:11:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:11:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('1lkE66DG', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:12:08', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', '49HZt3WJ', to_date('24-03-2021 09:12:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:12:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('8v1gGyUf', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:15:28', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'UbSHbuaV', to_date('24-03-2021 09:15:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:15:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('OW0qceqz', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:15:40', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'rLDp9FJn', to_date('24-03-2021 09:15:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:15:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('AqfZzbJg', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:37:08', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'DDiFbGRx', to_date('24-03-2021 09:37:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:37:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('o6ywY5Lr', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:42:01', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'dsZgSWwh', to_date('24-03-2021 09:42:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:42:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('MmUr2qM8', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:42:14', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'dfCyNuuZ', to_date('24-03-2021 09:42:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:42:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('6zJZ4V7C', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:42:38', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'a0kgDac9', to_date('24-03-2021 09:42:38', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:42:38', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('9zF2rtHB', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 801, to_date('24-03-2021 09:48:41', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'a6x8J0xg', to_date('24-03-2021 09:48:41', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('24-03-2021 09:48:41', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('Y90xTiYZ', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('30-03-2021 15:37:05', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'dmpGfdnC', to_date('30-03-2021 15:37:05', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('30-03-2021 15:37:05', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('gZikUpG9', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('30-03-2021 15:37:06', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'Cq4IgPOm', to_date('30-03-2021 15:37:06', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('30-03-2021 15:37:06', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('dIXQbpsm', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('01-04-2021 14:29:15', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'n5nczIoF', to_date('01-04-2021 14:29:15', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:29:15', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('Hvd3jE5D', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('01-04-2021 14:29:39', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'tG2ltwtI', to_date('01-04-2021 14:29:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:29:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('fwOJOflb', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('01-04-2021 14:30:44', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'XZK4oJxz', to_date('01-04-2021 14:30:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:30:44', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('IxYyqYeU', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('01-04-2021 14:32:49', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', '6sMWp0ps', to_date('01-04-2021 14:32:49', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:32:49', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('FAFeysQa', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('01-04-2021 14:33:01', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'sIp34mJ6', to_date('01-04-2021 14:33:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:33:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('4q5g5ori', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('01-04-2021 14:37:21', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', '0Jy8liHj', to_date('01-04-2021 14:37:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:37:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
insert into BUSINESS_NOTICE_HEADER (id, notice_read, sender_name, receiver_id, receiver_name, sender_org_name, source_name, source_ip, sender_type, time, status, receipt, notice_level, bz, detail_id, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4)
values ('R7uu2ENG', 0, '发送人', '123456789', '接收人', '二院', 'app', null, 804, to_date('01-04-2021 14:44:31', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 1, '备注说明', 'c4T78NdR', to_date('01-04-2021 14:44:31', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, to_date('01-04-2021 14:44:31', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null);
commit;
prompt 24 records loaded
prompt Enabling triggers for API_APP...
alter table API_APP enable all triggers;
prompt Enabling triggers for API_APPLY...
alter table API_APPLY enable all triggers;
prompt Enabling triggers for API_LOG...
alter table API_LOG enable all triggers;
prompt Enabling triggers for API_ROLE...
alter table API_ROLE enable all triggers;
prompt Enabling triggers for API_SERVICE...
alter table API_SERVICE enable all triggers;
prompt Enabling triggers for API_TOKEN...
alter table API_TOKEN enable all triggers;
prompt Enabling triggers for API_USER...
alter table API_USER enable all triggers;
prompt Enabling triggers for API_USER_ROLE...
alter table API_USER_ROLE enable all triggers;
prompt Enabling triggers for BUSINESS_NOTICE_BODY_OPERATE...
alter table BUSINESS_NOTICE_BODY_OPERATE enable all triggers;
prompt Enabling triggers for BUSINESS_NOTICE_BODY_RECOMMEND...
alter table BUSINESS_NOTICE_BODY_RECOMMEND enable all triggers;
prompt Enabling triggers for BUSINESS_NOTICE_BODY_SHOW...
alter table BUSINESS_NOTICE_BODY_SHOW enable all triggers;
prompt Enabling triggers for BUSINESS_NOTICE_BODY_UPDATE...
alter table BUSINESS_NOTICE_BODY_UPDATE enable all triggers;
prompt Enabling triggers for BUSINESS_NOTICE_HEADER...
alter table BUSINESS_NOTICE_HEADER enable all triggers;
set feedback on
set define on
prompt Done.
