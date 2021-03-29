# OpenApi-server
接口开放管理平台-后台服务

# 数据库设计
数据库：oracle
维护应用基本信息、系统间关系及接口访问权限

* API_APP           应用基本信息表
* API_APPLY         服务器调用申请表
* API_SERVICE       app提供的服务列表
* API_TOKEN         token保存
* BUSINESS_NOTICE_HEADER            协议头
* BUSINESS_NOTICE_BODY_OPERATE      协议内容-操作
* BUSINESS_NOTICE_BODY_SHOW         协议内容-消息展示
* BUSINESS_NOTICE_BODY_UPDATE       协议内容-数据更新
* BUSINESS_NOTICE_BODY_RECOMMEND    协议内容-推荐
应用流程
