CREATE TABLE brands (
                        id BIGINT IDENTITY(1,1) PRIMARY KEY,
                        name_brands NVARCHAR(255),
                        slug NVARCHAR(255),
                        logo NVARCHAR(255)
);