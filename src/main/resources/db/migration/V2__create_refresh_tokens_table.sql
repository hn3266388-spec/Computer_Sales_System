CREATE TABLE refresh_tokens (
                                id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                token NVARCHAR(500) NOT NULL UNIQUE,
                                account_id BIGINT NOT NULL,
                                expiry_date DATETIMEOFFSET(7) NOT NULL,
                                revoked BIT NOT NULL DEFAULT 0,

                                CONSTRAINT fk_refresh_token_account
                                    FOREIGN KEY (account_id)
                                        REFERENCES account(id)
);