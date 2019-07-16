INSERT INTO `test`.`event_forward_config`
(`id`, `name`, `desc`, `app_code`, `type_id`, `level`, `fw_enabled`, `fw_type`, `fw_targets`, `fw_tpl_text`, `status`)
VALUES ('1', '标题', '说明', 'app-1', '3', '1', 1, 'SMS', '[\"13520573407\"]', '测试模版', '1');


INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-1', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-01 11:07:14', '2019-07-01 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-2', 0, 'title', 'app-1, configId: 0, message', 'app-1', 0, 0, '2019-07-02 11:07:14', '2019-07-02 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-3', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 2, '2019-07-03 11:07:14', '2019-07-03 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-4', 0, 'title', 'app-1, configId: 0, message', 'app-1', 0, 0, '2019-07-04 11:07:14', '2019-07-04 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-5', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 1, '2019-07-05 11:07:14', '2019-07-05 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-6', 0, 'title', 'app-1, configId: 0, message', 'app-1', 0, 0, '2019-07-05 11:07:14', '2019-07-05 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-7', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-06 11:07:14', '2019-07-06 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-8', 0, 'title', 'app-1, configId: 0, message', 'app-1', 0, 0, '2019-07-07 11:07:14', '2019-07-07 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-9', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-08 11:07:14', '2019-07-08 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-10', 0, 'title', 'app-1, configId: 0, message', 'app-1', 0, 0, '2019-07-09 11:07:14', '2019-07-09 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-11', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-10 11:07:14', '2019-07-10 10:34:33');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-12', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-11 11:07:14', '2019-07-11 10:34:43');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-13', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-12 11:07:14', '2019-07-12 10:36:15');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-14', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-13 11:07:14', '2019-07-13 10:36:22');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-15', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-14 11:07:14', '2019-07-14 10:36:32');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-16', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-15 11:07:14', '2019-07-15 10:36:42');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-17', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-16 11:07:14', '2019-07-16 10:36:52');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-18', 0, 'title', 'app-1, configId: 0, message', 'app-1', 0, 0, '2019-07-17 11:07:14', '2019-07-17 11:07:14');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-19', 1, 'title', 'app-1, configId: 1, message', 'app-1', 0, 3, '2019-07-18 11:07:14', '2019-07-18 10:59:44');
INSERT INTO test.event (trace_id, type_id, title, message, app_code, status, level, occur_time, log_time) VALUES ('app-1-20', 0, 'title', 'app-1, configId: 0, message', 'app-1', 0, 0, '2019-07-19 11:07:14', '2019-07-19 11:07:14');
