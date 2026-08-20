CREATE TABLE inventory (
                           id BIGINT IDENTITY(1,1) PRIMARY KEY,
                           product_id BIGINT UNIQUE,
                           soluong INT NOT NULL DEFAULT 0,
                           selled INT NOT NULL DEFAULT 0,

    -- Tạo ràng buộc khóa ngoại (Foreign Key) liên kết với bảng products
                           CONSTRAINT FK_Inventory_Product FOREIGN KEY (product_id)
                               REFERENCES products(id)
                               ON DELETE CASCADE
);