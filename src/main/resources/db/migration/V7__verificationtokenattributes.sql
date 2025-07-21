CREATE TABLE verification_token
(
    uuid        VARCHAR(255) NOT NULL,
    token       VARCHAR(255),
    user_id     UUID,
    expiry_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_verificationtoken PRIMARY KEY (uuid)
);

ALTER TABLE verification_token
    ADD CONSTRAINT uc_verificationtoken_user UNIQUE (user_id);

ALTER TABLE verification_token
    ADD CONSTRAINT FK_VERIFICATIONTOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES app_users (id);