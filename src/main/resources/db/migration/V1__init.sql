CREATE TABLE IF NOT EXISTS app_user (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        enabled INT NOT NULL
);

INSERT INTO app_user (username, password, enabled) values ('karuna', '{bcrypt}$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6', 1);

CREATE TABLE IF NOT EXISTS `role` (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        `name` VARCHAR(255) NOT NULL
    );

INSERT INTO `role` (`name`) values ('SUPER_ADMIN');
INSERT INTO `role` (`name`) values ('USER');
INSERT INTO `role` (`name`) values ('VISITOR');

CREATE TABLE IF NOT EXISTS user_role (
                                      user_id BIGINT NOT NULL,
                                      role_id BIGINT NOT NULL
    );

INSERT INTO user_role (user_id, role_id) values ((SELECT id FROM app_user WHERE username = "karuna"),(SELECT id FROM `role` WHERE name = "SUPER_ADMIN"));