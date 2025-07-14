ALTER TABLE app_users
    ADD verification_token VARCHAR(255);

ALTER TABLE app_users
    ADD verified BOOLEAN;

ALTER TABLE app_users
    ALTER COLUMN verified SET NOT NULL;

ALTER TABLE app_users
    DROP COLUMN otp_code;

ALTER TABLE app_users
    DROP COLUMN otp_expiry_time;