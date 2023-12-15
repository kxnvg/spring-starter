ALTER TABLE users
    ADD COLUMN password VARCHAR(128) DEFAULT '{noop}123';

ALTER TABLE users_aud
    ADD COLUMN password VARCHAR(128);