ALTER TABLE app_users
    ADD country VARCHAR(255);

ALTER TABLE app_users
    ALTER COLUMN role SET NOT NULL;