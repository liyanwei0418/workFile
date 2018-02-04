ALTER TABLE `gk_platform`.`sys_app`
  ADD COLUMN `source` INT(5) NULL  COMMENT '应用来源（0:系统应用 1:接入的第三方应用 2：其他应用）' AFTER `sfczxmz`;

ALTER TABLE `gk_platform`.`sys_user`
  CHANGE `photo_url` `photo_url` VARCHAR(1000) CHARSET utf8 COLLATE utf8_general_ci DEFAULT 'images/default_tou.png'   NULL  COMMENT '用户头像';
