CREATE TABLE IF NOT EXISTS answer (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        `answer` VARCHAR(255) NOT NULL,
                                        `created_at` DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS question (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        `text` VARCHAR(255) NOT NULL,
    `status` INT NOT NULL,
    category BIGINT NOT NULL,
    `created_at` DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS question_answer (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         user_id BIGINT NOT NULL,
                                         question_id BIGINT NOT NULL,
                                         answer_id BIGINT NOT NULL
);

INSERT INTO question (text,status, category, created_at) values ('Question 1',1,(SELECT id FROM category WHERE name = "Education"), NOW());

INSERT INTO answer (answer, created_at) values ('Answer 1', NOW());

INSERT INTO question_answer (user_id, question_id,answer_id) values ((SELECT id FROM app_user WHERE username = "karuna"),(SELECT id FROM question WHERE text = "Question 1"),(SELECT id FROM `answer` WHERE answer = "Answer 1"));