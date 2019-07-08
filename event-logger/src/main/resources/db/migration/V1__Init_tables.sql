
CREATE TABLE IF NOT EXISTS event(
  `id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
  `type_id` BIGINT COMMENT '类型ID' ,
  `message` VARCHAR(1024) COMMENT '内容' ,
  `app_code` VARCHAR(64) COMMENT '源应用系统代码' ,
  `status` INT COMMENT '状态' ,
  `type_name` varchar(64) DEFAULT NULL,
  `level` int(1) DEFAULT NULL,
  `occur_time` datetime DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  `geo_info` varchar(64) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件 存储事件信息，每个事件作为一条记录';

ALTER TABLE event ADD INDEX idx_search_1(app_code,type_id,occur_time);
ALTER TABLE event ADD INDEX idx_search_2(occur_time,app_code,type_id);


CREATE TABLE IF NOT EXISTS event_type(
  id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
  name VARCHAR(64) COMMENT '名称' ,
  `desc` VARCHAR(128) COMMENT '说明' ,
  app_code VARCHAR(64) COMMENT '源应用系统代码' ,
  level INT(1)    COMMENT '级别' ,
  fw_enabled INT(1) COMMENT '是否需要推送' ,
  fw_url VARCHAR(3072) COMMENT '推送接口地址' ,
  fw_type VARCHAR(32) COMMENT '推送类型 如：SMS，VOICE' ,
  fw_targets VARCHAR(3072) COMMENT '推送目标列表 JSON数组' ,
  fw_text_tpl VARCHAR(128) COMMENT '推送内容模版' ,
  status INT(1) COMMENT '状态' ,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件推送设置';


CREATE TABLE IF NOT EXISTS event_forward_log(
  `id` BIGINT NOT NULL COMMENT '事件ID' ,
  `type_id` BIGINT COMMENT '推送设置ID' ,
  `fw_url` VARCHAR(3072) COMMENT '推送接口地址' ,
  `fw_targets` VARCHAR(3072) COMMENT '推送目标列表 JSON数组' ,
  `text` VARCHAR(1024) COMMENT '内容' ,
  `status` INT(1) COMMENT '状态' ,
  `retry` INT(1) COMMENT '重试次数' ,
  `fw_time` datetime DEFAULT NULL,
  `fw_result` VARCHAR(1024) COMMENT '推送结果',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件推送记录';

ALTER TABLE event_forward_log ADD INDEX idx_status(status,retry,fw_time);


