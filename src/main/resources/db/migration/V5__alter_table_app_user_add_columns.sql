ALTER TABLE app_user ADD COLUMN first_name VARCHAR(255) NULL AFTER username,
ADD COLUMN last_name VARCHAR(255) NULL AFTER first_name;

UPDATE app_user SET first_name="Karuna", last_name="Admin" WHERE username ="karuna";
