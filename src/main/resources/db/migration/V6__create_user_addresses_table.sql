CREATE TABLE user_addresses (
                                id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                account_id BIGINT NOT NULL,
                                username NVARCHAR(255),
                                phone NVARCHAR(50),
                                address NVARCHAR(500),
                                defaultaddress BIT,

                                CONSTRAINT FK_user_addresses_account
                                    FOREIGN KEY (account_id)
                                        REFERENCES account(id)
);