

CREATE TABLE IF NOT EXISTS review (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       `comment` VARCHAR(255) NOT NULL,
                                       rating BIGINT NOT NULL,
                                       status BIGINT NOT NULL,
                                       user_id BIGINT NOT NULL,
                                       listing_id BIGINT NOT NULL

);
INSERT INTO review (comment,rating, status,user_id,listing_id) values ('It is a great supermarket, my one stop shop!',8, 1,(SELECT id FROM app_user WHERE username = "karuna"),(SELECT id FROM listing WHERE listingname = "Walmart Supermaket"));
