create table account(
    id BIGINT IDENTITY(1,1) PRIMARY KEY ,
    accountname nvarchar(100) not null,
    gmail nvarchar(255),
    password nvarchar(255) not null ,
    enabled BIT NOT NULL ,
    role nvarchar(50)
)