ALTER TABLE app_users
    ADD otp_code VARCHAR(255);

ALTER TABLE app_users
    ADD otp_expiry_time TIMESTAMP WITHOUT TIME ZONE;