CREATE TABLE app_users
(
    id                 UUID         NOT NULL,
    updated_at         TIMESTAMP WITHOUT TIME ZONE,
    created_at         TIMESTAMP WITHOUT TIME ZONE,
    full_name          VARCHAR(255),
    email              VARCHAR(255) NOT NULL,
    password           VARCHAR(255),
    role               VARCHAR(255),
    reset_token        VARCHAR(255),
    reset_token_expiry TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_app_users PRIMARY KEY (id)
);

CREATE TABLE booking
(
    id              UUID         NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    seat_number     VARCHAR(255),
    status          SMALLINT,
    passenger_name  VARCHAR(255) NOT NULL,
    passenger_email VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(255),
    travel_date     date,
    booking_time    TIMESTAMP WITHOUT TIME ZONE,
    amount_paid     DOUBLE PRECISION,
    currency        VARCHAR(255),
    payment_status  SMALLINT,
    bus_id          UUID,
    user_id         UUID         NOT NULL,
    schedule_id     UUID         NOT NULL,
    ticket_code     VARCHAR(255) NOT NULL,
    qr_code_data    TEXT,
    route_id        UUID,
    CONSTRAINT pk_booking PRIMARY KEY (id)
);

CREATE TABLE bus
(
    id                  UUID         NOT NULL,
    updated_at          TIMESTAMP WITHOUT TIME ZONE,
    created_at          TIMESTAMP WITHOUT TIME ZONE,
    plate_number        VARCHAR(255) NOT NULL,
    model               SMALLINT,
    status              SMALLINT     NOT NULL,
    type                SMALLINT     NOT NULL,
    driver_name         VARCHAR(255) NOT NULL,
    driver_phone_number VARCHAR(255) NOT NULL,
    company_id          UUID,
    CONSTRAINT pk_bus PRIMARY KEY (id)
);

CREATE TABLE companies
(
    id              UUID         NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    company_name    VARCHAR(255) NOT NULL,
    company_address VARCHAR(255),
    email           VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(255) NOT NULL,
    status          VARCHAR(255) NOT NULL,
    registration    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_companies PRIMARY KEY (id)
);

CREATE TABLE routes
(
    id                  UUID             NOT NULL,
    updated_at          TIMESTAMP WITHOUT TIME ZONE,
    created_at          TIMESTAMP WITHOUT TIME ZONE,
    origin              VARCHAR(255)     NOT NULL,
    destination         VARCHAR(255)     NOT NULL,
    distance_in_km      DOUBLE PRECISION NOT NULL,
    duration_in_minutes INTEGER          NOT NULL,
    price               DOUBLE PRECISION NOT NULL,
    company_id          UUID,
    bus_id              UUID,
    CONSTRAINT pk_routes PRIMARY KEY (id)
);

CREATE TABLE schedule
(
    id              UUID    NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    departure_time  TIMESTAMP WITHOUT TIME ZONE,
    arrival_time    TIMESTAMP WITHOUT TIME ZONE,
    travel_date     date,
    available_seats INTEGER NOT NULL,
    is_active       BOOLEAN NOT NULL,
    company_id      UUID,
    bus_id          UUID,
    status          VARCHAR(255),
    route_id        UUID,
    CONSTRAINT pk_schedule PRIMARY KEY (id)
);

ALTER TABLE app_users
    ADD CONSTRAINT uc_app_users_email UNIQUE (email);

ALTER TABLE booking
    ADD CONSTRAINT uc_booking_seatnumber UNIQUE (seat_number);

ALTER TABLE booking
    ADD CONSTRAINT uc_booking_ticketcode UNIQUE (ticket_code);

ALTER TABLE bus
    ADD CONSTRAINT uc_bus_driverphonenumber UNIQUE (driver_phone_number);

ALTER TABLE bus
    ADD CONSTRAINT uc_bus_platenumber UNIQUE (plate_number);

ALTER TABLE companies
    ADD CONSTRAINT uc_companies_companyname UNIQUE (company_name);

ALTER TABLE companies
    ADD CONSTRAINT uc_companies_email UNIQUE (email);

ALTER TABLE companies
    ADD CONSTRAINT uc_companies_phonenumber UNIQUE (phone_number);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_BUS FOREIGN KEY (bus_id) REFERENCES bus (id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_ROUTE FOREIGN KEY (route_id) REFERENCES routes (id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_SCHEDULE FOREIGN KEY (schedule_id) REFERENCES schedule (id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_USER FOREIGN KEY (user_id) REFERENCES app_users (id);

ALTER TABLE bus
    ADD CONSTRAINT FK_BUS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);

ALTER TABLE routes
    ADD CONSTRAINT FK_ROUTES_ON_BUS FOREIGN KEY (bus_id) REFERENCES bus (id);

ALTER TABLE routes
    ADD CONSTRAINT FK_ROUTES_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);

ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_BUS FOREIGN KEY (bus_id) REFERENCES bus (id);

ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);

ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_ROUTE FOREIGN KEY (route_id) REFERENCES routes (id);