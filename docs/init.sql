create database if not exists `Itinerary-Planning-System`;

use `Itinerary-Planning-System`;

DROP TABLE IF EXISTS `itinerary`;
CREATE TABLE `itinerary`(
                            `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `user_id` BIGINT UNSIGNED NOT NULL COMMENT '创建人id',
                            `route_id` BIGINT UNSIGNED NOT NULL COMMENT '行程信息id',
                            `create_time` DATETIME NOT NULL DEFAULT Now() COMMENT '创建时间',
                            `update_time` DATETIME NOT NULL DEFAULT Now() on update NOW() COMMENT '修改时间',
                            `status` INT NOT NULL DEFAULT '0' COMMENT '行程状态 ( 0 未完成 / 1 已完成 / 2 已失效 )'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '行程表' ROW_FORMAT = Dynamic;
ALTER TABLE
    `itinerary` ADD INDEX `itinerary_user_id_index`(`user_id`);
ALTER TABLE
    `itinerary` ADD INDEX `itinerary_user_id_status_index`(`user_id`, `status`);

DROP TABLE IF EXISTS `route`;
CREATE TABLE `route`(
                        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        `start_location` BIGINT UNSIGNED NOT NULL COMMENT '出发地',
                        `end_location` BIGINT UNSIGNED NOT NULL COMMENT '目的地',
                        `price` INT NOT NULL COMMENT '价格 (格式：000000 前4个零代表整数部分，后2个零代表小数部分)',
                        `duration` TIME NOT NULL COMMENT '耗时',
                        `create_time` DATETIME NOT NULL DEFAULT Now() COMMENT '创建时间',
                        `update_time` DATETIME NOT NULL DEFAULT Now() on update NOW() COMMENT '修改时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '行程信息表' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
                       `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       `name` VARCHAR(255) NOT NULL COMMENT '用户名',
                       `password` VARCHAR(255) NOT NULL COMMENT '密码',
                       `create_time` DATETIME NOT NULL DEFAULT Now() COMMENT '创建时间',
                       `update_time` DATETIME NOT NULL DEFAULT Now() on update NOW() COMMENT '修改时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `destination`;
CREATE TABLE `destination`(
                              `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              `name` VARCHAR(255) NOT NULL COMMENT '目的地名称',
                              `create_time` DATETIME NOT NULL DEFAULT Now() COMMENT '创建时间',
                              `update_time` DATETIME NOT NULL DEFAULT Now() on update NOW() COMMENT '修改时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '目的地表' ROW_FORMAT = Dynamic;


ALTER TABLE
    `itinerary` ADD CONSTRAINT `itinerary_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `route` ADD CONSTRAINT `route_end_location_foreign` FOREIGN KEY(`end_location`) REFERENCES `destination`(`id`);
ALTER TABLE
    `itinerary` ADD CONSTRAINT `itinerary_route_id_foreign` FOREIGN KEY(`route_id`) REFERENCES `route`(`id`);
ALTER TABLE
    `route` ADD CONSTRAINT `route_start_location_foreign` FOREIGN KEY(`start_location`) REFERENCES `destination`(`id`);