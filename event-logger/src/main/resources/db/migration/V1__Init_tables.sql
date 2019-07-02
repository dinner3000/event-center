CREATE TABLE IF NOT EXISTS event(
  id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
#   id BIGINT NOT NULL   COMMENT 'ID 使用snowflake算法产生64位ID' ,
  type_id BIGINT    COMMENT '类型ID' ,
  message VARCHAR(1024)    COMMENT '内容' ,
  app_code VARCHAR(64)    COMMENT '源应用系统代码' ,
  status INT    COMMENT '状态' ,
  PRIMARY KEY (ID)
) COMMENT = '事件 存储事件信息，每个事件作为一条记录';

ALTER TABLE event ADD INDEX idx_type(type_id);
ALTER TABLE event ADD INDEX idx_bus(app_code);
ALTER TABLE event COMMENT '事件';


CREATE TABLE IF NOT EXISTS event_type(
  id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
  name VARCHAR(64)    COMMENT '名称' ,
  `desc` VARCHAR(128)    COMMENT '说明' ,
  level INT(1)    COMMENT '级别' ,
  app_code VARCHAR(64)    COMMENT '源应用系统代码' ,
  fw_needed VARCHAR(1)    COMMENT '是否需要推送 Y/N' ,
  fw_config_ids VARCHAR(1024)    COMMENT '推送配置 JSON数组' ,
  status INT    COMMENT '状态' ,
  PRIMARY KEY (ID)
) COMMENT = '事件类型 ';

ALTER TABLE event_type ADD INDEX idx_name(name);
ALTER TABLE event_type ADD INDEX idx_status(status);
ALTER TABLE event_type COMMENT '事件类型';


CREATE TABLE IF NOT EXISTS forward_config(
  id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
  name VARCHAR(64)    COMMENT '名称' ,
  `desc` VARCHAR(1024)    COMMENT '说明' ,
  api_url VARCHAR(3072)    COMMENT '推送接口地址' ,
  type VARCHAR(32)    COMMENT '推送类型 如：SMS，VOICE' ,
  target_list VARCHAR(3072)    COMMENT '推送目标列表 JSON数组' ,
  status INT    COMMENT '状态' ,
  PRIMARY KEY (ID)
) COMMENT = '事件推送配置 ';

ALTER TABLE forward_config ADD INDEX idx_name(name);
ALTER TABLE forward_config ADD INDEX idx_status(status);
ALTER TABLE forward_config COMMENT '事件推送配置';

