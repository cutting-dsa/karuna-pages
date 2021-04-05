ALTER TABLE question ADD COLUMN user_id BIGINT(255) NOT NULL DEFAULT 1 AFTER category;

ALTER TABLE question ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES app_user(id);
