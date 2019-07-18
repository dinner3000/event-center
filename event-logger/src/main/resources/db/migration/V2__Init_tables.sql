
CREATE TABLE IF NOT EXISTS event(
  `id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID',
  `trace_id` VARCHAR(64) COMMENT '追踪ID',
  `app_code` VARCHAR(64) COMMENT '应用系统代码',
  `type_id` BIGINT COMMENT '类型ID',
  `level` int(1) DEFAULT NULL COMMENT '级别',
  `title` VARCHAR(64) COMMENT '标题' ,
  `message` VARCHAR(1024) COMMENT '内容' ,
  `status` INT(1) COMMENT '状态' ,
  `occur_time` datetime DEFAULT NULL COMMENT '发生时间',
  `resolve_time` datetime DEFAULT NULL COMMENT '解决时间',
  `log_time` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件 存储事件信息，每个事件作为一条记录';

ALTER TABLE event ADD UNIQUE idx_trace_id(trace_id, app_code);
ALTER TABLE event ADD INDEX idx_search_1(app_code,type_id,occur_time);
ALTER TABLE event ADD INDEX idx_search_2(occur_time,app_code,type_id);


CREATE TABLE IF NOT EXISTS event_status_log(
  `id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
  `event_id` BIGINT COMMENT '事件ID',
  `prev_status` INT(1) COMMENT '原状态',
  `curr_status` INT(1) COMMENT '当前状态',
  `overtime` INT(1) DEFAULT NULL COMMENT '是否超时',
  `log_time` datetime DEFAULT NULL COMMENT '记录时间',
  `owner` VARCHAR(64) DEFAULT NULL COMMENT '负责人',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件状态变更记录';

ALTER TABLE event_status_log ADD INDEX idx_1(event_id);
ALTER TABLE event_status_log ADD INDEX idx_2(log_time, curr_status, overtime);


CREATE TABLE IF NOT EXISTS event_status_stat(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
  `status` INT(1) COMMENT '状态',
  `overtime` INT(1) DEFAULT NULL COMMENT '是否超时',
  `count` INT DEFAULT NULL COMMENT '统计数量',
  `stat_time` datetime DEFAULT NULL COMMENT '统计时间',
  `time_span` INT(2) DEFAULT NULL COMMENT '时间范围/间隔（分钟）',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件状态变更记录';

ALTER TABLE event_status_stat ADD INDEX idx_1(stat_time, status, overtime, time_span);


CREATE TABLE IF NOT EXISTS event_forward_config(
  `id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID',
  `name` VARCHAR(64) COMMENT '名称',
  `desc` VARCHAR(128) COMMENT '说明',
  `app_code` VARCHAR(64) COMMENT '源应用系统代码',
  `type_id` BIGINT COMMENT '类型ID',
  `level` INT(1) COMMENT '级别',
  `fw_enabled` INT(1) COMMENT '是否需要推送',
  `fw_type` VARCHAR(32) COMMENT '推送类型 如：SMS，VOICE',
  `fw_targets` VARCHAR(3072) COMMENT '推送目标列表 JSON数组',
  `fw_tpl_text` VARCHAR(3072) COMMENT '推送内容模版',
  `fw_tpl_id` BIGINT COMMENT '推送内容模版id(外部)',
  `status` INT(1) COMMENT '状态',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件推送设置';


CREATE TABLE IF NOT EXISTS event_forward_log(
  `id` BIGINT NOT NULL COMMENT '事件ID',
  `config_id` BIGINT COMMENT '推送设置ID',
  `targets` VARCHAR(3072) COMMENT '推送目标列表 JSON数组',
  `tpl_text` VARCHAR(2048) COMMENT '内容模板',
  `tpl_params` VARCHAR(1024) COMMENT '模板变量',
  `text` VARCHAR(3072) COMMENT '内容',
  `status` INT(1) COMMENT '状态',
  `retries` INT(1) COMMENT '重试次数',
  `fw_time` datetime DEFAULT NULL COMMENT '推送时间',
  `fw_result` VARCHAR(1024) COMMENT '推送结果',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件推送记录';

ALTER TABLE event_forward_log ADD INDEX idx_status(status,retries,fw_time);

CREATE TABLE IF NOT EXISTS event_type(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
--  `app_code` BIGINT COMMENT '应用系统代码',
--  `code` BIGINT COMMENT '类型代码',
  `name` VARCHAR(64) COMMENT '类型名称',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '事件类型名称代码';

--ALTER TABLE event_type ADD UNIQUE idx_1(code);

CREATE TABLE IF NOT EXISTS event_app_code(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` VARCHAR(32) COMMENT '应用系统代码',
  `name` VARCHAR(64) COMMENT '应用系统名称',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '应用系统名称代码';

ALTER TABLE event_app_code ADD UNIQUE idx_1(code);

