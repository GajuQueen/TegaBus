ALTER TABLE routes
    DROP CONSTRAINT fk_routes_on_bus;

ALTER TABLE app_users
    ADD last_name VARCHAR(255);

ALTER TABLE bus
    ADD route_id UUID;

ALTER TABLE app_users
    ADD CONSTRAINT uc_app_users_password UNIQUE (password);

ALTER TABLE bus
    ADD CONSTRAINT FK_BUS_ON_ROUTE FOREIGN KEY (route_id) REFERENCES routes (id);

ALTER TABLE routes
    DROP COLUMN bus_id;

ALTER TABLE app_users
    ALTER COLUMN password SET NOT NULL;

ALTER TABLE bus
    DROP COLUMN status;

ALTER TABLE bus
    ADD status VARCHAR(255) NOT NULL;