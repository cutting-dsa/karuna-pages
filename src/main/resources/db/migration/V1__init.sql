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

CREATE TABLE IF NOT EXISTS category (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        active BIGINT NOT NULL,
                                        `name` VARCHAR(255) NOT NULL
    );

INSERT INTO category (active, name) values (1,'Education');

CREATE TABLE IF NOT EXISTS listing (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       `listingname` VARCHAR(255) NOT NULL,
    active BIGINT NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    `banner_url` VARCHAR(255) NOT NULL,
    `icon_url` VARCHAR(255) NOT NULL,
    approved BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    category BIGINT NOT NULL

    );
INSERT INTO listing (listingname,active, address,latitude,longitude,banner_url,icon_url,approved,user_id,category) values ('Walmart Supermaket',1, '1000 N 4th st fairfield IA 5227','12736449.122','22736449.122','https://someawslocation/maharishi_banner.png','https://someawslocation/maharishi_icon.png',0,(SELECT id FROM app_user WHERE username = "karuna"),(SELECT id FROM category WHERE name = "Education"));
