CREATE TABLE IF NOT EXISTS answer (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        `answer` VARCHAR(255) NOT NULL,
                                        `created_at` DATE NOT NULL,
                                        `user_id` BIGINT NOT NULL,
                                       `question_id` BIGINT NOT NULL
    );

CREATE TABLE IF NOT EXISTS question (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        `text` VARCHAR(255) NOT NULL,
    `status` INT NOT NULL,
    category BIGINT NOT NULL,
    `created_at` DATE NOT NULL
    );

INSERT INTO question (text,status, category, created_at) values ('Question 1',1,(SELECT id FROM category WHERE name = "Education"), NOW());

INSERT INTO answer (answer, created_at, user_id,question_id) values ('Answer 1', NOW(),(SELECT id FROM app_user WHERE username = "karuna"),(SELECT id FROM question WHERE text = "Question 1"));
