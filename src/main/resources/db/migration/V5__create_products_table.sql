CREATE TABLE products (
                          id BIGINT IDENTITY(1,1) PRIMARY KEY,

                          brands_id BIGINT,
                          categories_id BIGINT,

                          name_product NVARCHAR(255),
                          slug NVARCHAR(255),
                          price DECIMAL(18,2),
                          specs NVARCHAR(MAX),
                          image NVARCHAR(255),
                          description NVARCHAR(MAX),

                          CONSTRAINT FK_products_brands
                              FOREIGN KEY (brands_id)
                                  REFERENCES brands(id),

                          CONSTRAINT FK_products_categories
                              FOREIGN KEY (categories_id)
                                  REFERENCES categories(id)
);