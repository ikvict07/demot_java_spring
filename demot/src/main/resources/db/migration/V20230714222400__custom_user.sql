CREATE TABLE custom_user (

                             id BIGSERIAL PRIMARY KEY,

                             login TEXT NOT NULL,

                             password TEXT NOT NULL);

CREATE INDEX custom_user_login_idx ON custom_user (login);
