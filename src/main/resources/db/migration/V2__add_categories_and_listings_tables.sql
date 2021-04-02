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
                                       average_rating BIGINT NOT NULL,
                                       user_id BIGINT NOT NULL,
                                       category BIGINT NOT NULL

);
INSERT INTO listing (listingname,active, address,latitude,longitude,banner_url,icon_url,approved,average_rating,user_id,category) values ('Walmart Supermaket',1, '1000 N 4th st fairfield IA 5227','12736449.122','22736449.122','https://someawslocation/maharishi_banner.png','https://someawslocation/maharishi_icon.png',0,1,(SELECT id FROM app_user WHERE username = "karuna"),(SELECT id FROM category WHERE name = "Education"));
