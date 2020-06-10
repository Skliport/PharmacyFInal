/* Programación de Bases de Datos - Período III - Proyecto de Período

Grupo III: Farmacia - Sistema de control de inventario y ventas

Estudiantes:
1. Flores Medina, Christian Neftali. (Moderador).
2. Magaña Urrutia, Juan Sebastian.
3. Rivas Pereira, Elisa Abigail.
4. Palma Flores, Bryan Mauricio.
5. Santamaria Calderón, Rene Francisco. */

/* - - - - - - - - - DATABASE - - - - - - - - -  */

CREATE DATABASE pharmacy;
GO

USE pharmacy;

/* - - - - - - - - - TABLES - - - - - - - - -  */

CREATE TABLE [user] (
    [user_id] INT NOT NULL IDENTITY(1,1) CONSTRAINT pk_user PRIMARY KEY,
    [username] VARCHAR(50) NOT NULL,
    [password] VARCHAR(300) NOT NULL,
    [first_name] VARCHAR(20) NOT NULL,
    [last_name] VARCHAR(20) NOT NULL,
    [admin_access] BIT NOT NULL,
    [vendor_access] BIT NOT NULL,
    [inventory_access] BIT NOT NULL,
    [order_access] BIT NOT NULL
);

CREATE TABLE [product] (
    [product_id] INT NOT NULL IDENTITY(1,1) CONSTRAINT pk_product PRIMARY KEY,
    [product_name] VARCHAR(50) NOT NULL,
    [product_brand] VARCHAR(20) NOT NULL,
    [unit_price] REAL NOT NULL,
    [units_in_stock] INT NOT NULL
);

CREATE TABLE [order] (
    [order_id] INT NOT NULL IDENTITY(1,1) CONSTRAINT pk_order PRIMARY KEY,
    [date] DATETIME NOT NULL,
    [active_order] BIT NOT NULL
);

CREATE TABLE [order_detail] (
    [order_detail_id] INT NOT NULL IDENTITY(1,1) CONSTRAINT pk_order_detail PRIMARY KEY,
    [order_id] INT NOT NULL,
    [product_id] INT NOT NULL,
    [unit_price] REAL NOT NULL,
    [quantity] INT NOT NULL,
    [discount] REAL NOT NULL
);

CREATE TABLE [temp_order_detail] (
    [product_id] INT NOT NULL,
    [unit_price] REAL NOT NULL,
    [quantity] INT NOT NULL,
    [discount] REAL NOT NULL
);

CREATE TABLE [audit]
(
    [username] VARCHAR(50),
    [date] DATETIME,
    [table] VARCHAR (50),
    [action] VARCHAR(50),
    [old_value]  VARCHAR(500),
    [new_value] VARCHAR(500)
);

/* - - - - - - - - - RELATIONSHIPS - - - - - - - - -  */

ALTER TABLE [order_detail] ADD 
CONSTRAINT fk_od_order FOREIGN KEY ([order_id]) REFERENCES [order] ([order_id]),
CONSTRAINT fk_od_product FOREIGN KEY ([product_id]) REFERENCES [product] ([product_id]);

/* - - - - - - - - - CREATE VIEWS - - - - - - - - -  */

/* 1. UsuarioAdminView 
Recupera una vista de los usuarios en la tabla [user] */

GO

CREATE VIEW UsuarioAdminView AS
SELECT [user_id], 
username, 
first_name, 
last_name, 
admin_access, 
vendor_access, 
inventory_access, 
order_access 
FROM [user];

/* 2. Orders
Recupera el historial general de ordenes de la tabla [order] */

GO

CREATE VIEW Orders AS
SELECT oo.order_id, oo.[date],
CAST(SUM(od.unit_price * od.quantity * (1-od.discount)) AS DECIMAL(10, 2)) AS total
FROM [order] oo JOIN [order_detail] od ON oo.order_id = od.order_id
WHERE oo.[active_order] = '1'
GROUP BY oo.order_id, oo.[date];


/* 3. Productos
Recupera una vista de productos con un stock superior a 0 [product] */

GO

CREATE VIEW Productos AS
SELECT * FROM [product] WHERE [units_in_stock] > 0;

/* 4. ProductosCompleto
Recupera una vista completa de todos los productos [product] */

GO

CREATE VIEW ProductosCompleto AS
SELECT * FROM [product];

/* 5. GeneralLog 
Recupera una vista general del log de la tabla [audit] */

GO

CREATE VIEW GeneralLog AS
SELECT * FROM [audit];

/* - - - - - - - - - STORED PROCEDURES - - - - - - - - -  */

/* 1. createUser
Crea un usuario-login e inserta los datos de usuario en la tabla [user] */

GO

CREATE PROCEDURE createUser(
    @username NVARCHAR(50),
    @login NVARCHAR(50),
    @password VARCHAR(300),
    @first_name VARCHAR(20),
    @last_name VARCHAR(20),
    @admin_access BIT,
    @vendor_access BIT,
    @inventory_access BIT,
    @order_access BIT,
    @db NVARCHAR(30))
AS
BEGIN
    BEGIN TRANSACTION

        DECLARE @login_statement nvarchar(4000);
        SET @login_statement = N'CREATE LOGIN ' + QUOTENAME(@login) + 
        ' WITH PASSWORD = ' + QUOTENAME(@password, '''') 
        + ', default_database = ' + QUOTENAME(@db);
        EXEC(@login_statement)

        DECLARE @alter_statement nvarchar(4000);
        SET @alter_statement = N'ALTER LOGIN ' + QUOTENAME(@login) + ' ENABLE';
        EXEC(@alter_statement)

        DECLARE @user_statement nvarchar(4000);
        SET @user_statement = N'CREATE USER ' + QUOTENAME(@username) + 
        ' FOR LOGIN ' + QUOTENAME(@login) + ' WITH DEFAULT_SCHEMA = [DBO]';
        EXEC(@user_statement)

        DECLARE @grant_exec nvarchar(4000);
        SET @grant_exec = N'GRANT EXECUTE TO ' + QUOTENAME(@username);
        EXEC(@grant_exec);

        DECLARE @grant_datareader nvarchar(4000);
        SET @grant_datareader = N'ALTER ROLE db_datareader ADD MEMBER ' + QUOTENAME(@username);
        EXEC(@grant_datareader);

        DECLARE @grant_datawriter nvarchar(4000);
        SET @grant_datawriter = N'ALTER ROLE db_datawriter ADD MEMBER ' + QUOTENAME(@username);
        EXEC(@grant_datawriter);

        INSERT INTO [user] (username, [password], first_name, last_name, 
        admin_access, vendor_access, inventory_access, order_access)
        VALUES (@username,  HASHBYTES('SHA2_512', @password), @first_name, @last_name, @admin_access, 
        @vendor_access, @inventory_access, @order_access);

    IF(@@ERROR > 0) 
    BEGIN
        Rollback Transaction
    END
    ELSE
    BEGIN
        Commit Transaction
    END
END;

/* 2. insertNewOrder
Crea una orden de venta e inserta los registros de la tabla temporal
[temp_order_detail] en la tabla [order_detail] final */

GO

CREATE PROCEDURE insertNewOrder
AS
BEGIN

    DECLARE @order_id INT, @product_id INT, @unit_price REAL,
    @quantity INT, @discount REAL

    DECLARE temp_order_detail_cursor CURSOR FOR 
    SELECT * FROM [temp_order_detail];

    BEGIN TRANSACTION

        INSERT INTO [order] ([date], [active_order]) VALUES (CURRENT_TIMESTAMP, '1');

        SET @order_id = (SELECT TOP 1 order_id FROM [order] ORDER BY [date] DESC);

        OPEN temp_order_detail_cursor;

        FETCH NEXT FROM temp_order_detail_cursor
        INTO @product_id, @unit_price, @quantity, @discount;

        WHILE @@FETCH_STATUS = 0  
        BEGIN
            INSERT INTO [order_detail] (order_id, product_id, unit_price, quantity, discount)
            VALUES (@order_id, @product_id, @unit_price, @quantity, @discount);

            FETCH NEXT FROM temp_order_detail_cursor
            INTO @product_id, @unit_price, @quantity, @discount;
        END;

        CLOSE temp_order_detail_cursor;
        DEALLOCATE temp_order_detail_cursor;
        DELETE FROM [temp_order_detail];

        IF(@@ERROR > 0) 
        BEGIN
            Rollback Transaction
        END
        ELSE
        BEGIN
            Commit Transaction
        END
END;

/* 3. insertIntoProduct
Inserta un producto en la tabla [product] */

GO

CREATE PROCEDURE insertIntoProduct( 
    @product_name AS VARCHAR(50),
    @product_brand AS VARCHAR(20),
    @unit_price AS REAL,
    @units_in_stock AS INT)
AS
BEGIN
    BEGIN TRANSACTION

    INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
    VALUES (@product_name, @product_brand, @unit_price, @units_in_stock);

    IF(@@ERROR > 0) 
    BEGIN
        Rollback Transaction
    END
    ELSE
    BEGIN
        Commit Transaction
    END
END;

/* 4. insertIntoTempOrderDetail
Inserta un detalle de producto temporal en la tabla [temp_order_detail] */

GO

CREATE PROCEDURE insertIntoTempOrderDetail( 
    @product_id INT, @unit_price REAL,
    @quantity INT, @discount REAL)
AS
BEGIN
    BEGIN TRANSACTION

    INSERT INTO [temp_order_detail] (product_id, unit_price, quantity, discount)
    VALUES (@product_id, @unit_price, @quantity, @discount);

    IF(@@ERROR > 0) 
    BEGIN
        Rollback Transaction
    END
    ELSE
    BEGIN
        Commit Transaction
    END
END;

/* 5. update_user
Actualizar un registro de la tabla [user] */

GO

CREATE PROCEDURE update_user (
    @user_id INT,
    @first_name VARCHAR(20) ,
    @last_name VARCHAR(20),
    @vendor_access BIT, 
    @inventory_access BIT,
    @order_access BIT)
AS
    UPDATE [user] SET [first_name] = @last_name,
                      [last_name]  = @last_name,
                      [vendor_access] = @vendor_access,
                      [inventory_access] = @vendor_access,
                      [order_access] = @order_access
                      WHERE [user_id] =  @user_id;

/* 6. update_product
Actualiza un registro de la tabla [product] */

GO

CREATE PROCEDURE update_product(
    @product_id INT,
    @product_name VARCHAR(50),
    @product_brand VARCHAR(20),
    @unit_price REAL,
    @units_in_stock INT)
AS  
    UPDATE [product] SET  [product_name] = @product_name,
                          [product_brand] = @product_brand,
                          [unit_price] = @unit_price,
                          [units_in_stock] = @units_in_stock
                          WHERE  [product_id] = @product_id;

/* 7. decreaseUnits_stock 
Reduce las unidades en stock para un producto [product] */

GO

CREATE PROCEDURE decreaseUnits_stock
    @uproduct_id int,
    @uquantity int
AS
BEGIN
    UPDATE product SET units_in_stock = units_in_stock - @uquantity
    WHERE product_id = @uproduct_id;
END

/* 8. increaseUnits_stock 
Incrementa las unidades en stock para un producto [product] */

GO

CREATE PROCEDURE increaseUnits_stock
    @uproduct_id int,
    @uquantity int
AS
BEGIN
    UPDATE product SET units_in_stock = units_in_stock + @uquantity
    WHERE product_id = @uproduct_id;
END

/* 9. deleteOrder 
Anula una orden, estableciendo su estado como inactivo */

GO

CREATE PROCEDURE deleteOrder 
    @dorder_id int
AS
BEGIN
    UPDATE [order] SET [active_order] = '0' 
    WHERE order_id = @dorder_id;
END

/* 10. insertUserAdmin
Insertar un primer usuario administrador en la tabla [user] */

GO

CREATE PROCEDURE insertUserAdmin(
    @username NVARCHAR(50),
    @password VARCHAR(300),
    @first_name VARCHAR(20),
    @last_name VARCHAR(20))
AS
BEGIN
    BEGIN TRANSACTION
        INSERT INTO [user] (username, [password], first_name, last_name, 
        admin_access, vendor_access, inventory_access, order_access)
        VALUES (@username,  HASHBYTES('SHA2_512', @password), 
        @first_name, @last_name, '1', '1', '1', '1');
    IF(@@ERROR > 0) 
    BEGIN
        Rollback Transaction
    END
    ELSE
    BEGIN
        Commit Transaction
    END
END;

/* - - - - - - - - - FUNCTIONS - - - - - - - - -  */

/* 1. getAuthenticatedUser 
Retorna el registro de la tabla [user] del usuario que inicia sesión */

GO

CREATE FUNCTION getAuthenticatedUser(
    @username VARCHAR(50), 
    @password VARCHAR(300))
RETURNS TABLE
AS
RETURN
    SELECT * FROM [user] 
    WHERE username = @username AND [password] = HASHBYTES('SHA2_512', @password);

/* 2. getOrderById 
Retorna un listado filtrado de la tabla [order] por un order_id definido */

GO

CREATE FUNCTION getOrderById(@order_id INT)
RETURNS TABLE
AS
RETURN
    SELECT oo.order_id, oo.[date],
	CAST(SUM(od.unit_price * od.quantity * (1-od.discount)) AS DECIMAL(10, 2)) AS total
	FROM [order] oo JOIN [order_detail] od ON oo.order_id = od.order_id
	WHERE oo.order_id = @order_id
	GROUP BY oo.order_id, oo.[date];

/* 3. getOrderDetailById
Retorna registros de la tabla [order_detail] asociados a una orden definida.
(Muestra los detalles de una orden) */

GO

CREATE FUNCTION getOrderDetailById(@order_id INT)
RETURNS TABLE
AS
RETURN
    SELECT 
    od.order_id,
    od.order_detail_id,  
    pr.product_name,
    pr.product_brand,
    od.unit_price,
    od.quantity,
    od.discount
    FROM [order_detail] od JOIN [product] pr
    ON od.product_id = pr.product_id
    WHERE order_id = @order_id;
    
/* 4. checkIfUsernameExists
Verifica si un username existe en la tabla [user]; retorna 1 si 
el usuario existe, y retorna 0 si el usuario no existe */

GO

CREATE FUNCTION checkIfUsernameExists(@username VARCHAR(50))
RETURNS TABLE
AS
RETURN
	SELECT COUNT(*) AS users_found FROM [user] WHERE username = @username;

/* - - - - - - - - - TRIGGERS - - - - - - - - -  */

/* 1. trigger_order_detail_insert */

GO

CREATE TRIGGER trigger_order_detail_insert
    ON order_detail
    AFTER insert AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_NDATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'order_detail';
    set @V_action = 'INSERT';
    SET @V_NDATA = (select CONCAT(order_detail_id,' ',order_id,' ',product_id,' ',unit_price,' ',quantity,' ',discount) from inserted);
    INSERT INTO [audit] (username,[date],[table],[action],new_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_NDATA);
END;

/* 2. trigger_order_detail_update */

GO

CREATE TRIGGER trigger_order_detail_update
    ON order_detail
    AFTER UPDATE AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_ADATA as VARCHAR(500);
    DECLARE @V_NDATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'order_detail';
    set @V_action = 'UPDATE';
    SET @V_ADATA = (select CONCAT(order_detail_id,' ',order_id,' ',product_id,' ',unit_price,' ',quantity,' ',discount) from deleted);
    SET @V_NDATA = (select CONCAT(order_detail_id,' ',order_id,' ',product_id,' ',unit_price,' ',quantity,' ',discount) from inserted);
    INSERT INTO [audit] (username,[date],[table],[action],old_value,new_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_ADATA,@V_NDATA);
END;

/* 3. trigger_order_detail_delete */

GO

CREATE TRIGGER trigger_order_detail_delete
    ON order_detail
    after DELETE AS
BEGIN
    DECLARE cursorEmployee CURSOR FOR SELECT CONCAT(order_detail_id,' ',order_id,' ',product_id,' ',unit_price,' ',quantity,' ',discount) FROM deleted;    
	OPEN cursorEmployee; 
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_ADATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'order_detail';
    set @V_action = 'DELETE';
    FETCH NEXT FROM cursorEmployee INTO @V_ADATA;
    WHILE @@FETCH_STATUS=0
    BEGIN
        INSERT INTO [audit] (username,[date],[table],[action],old_value) 
        values (@V_USERNAME,@V_date,@V_table,@V_action,@V_ADATA);
        FETCH NEXT FROM cursorEmployee INTO @V_ADATA;
    END;
    CLOSE cursorEmployee;
END;

/* 4. trigger_product_delete */

GO

CREATE TRIGGER trigger_product_delete
    ON Product
    AFTER DELETE AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_ADATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'product';
    set @V_action = 'DELETE';
    SET @V_ADATA = (select CONCAT(product_id,' ',product_name,' ',product_brand,' ',product_id,' ',unit_price,' ',units_in_stock) from deleted);
    INSERT INTO [audit] (username,[date],[table],[action],old_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_ADATA);
END;

/* 5. trigger_product_update */

GO

CREATE TRIGGER trigger_product_update
    ON Product
    AFTER UPDATE AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_ADATA as VARCHAR(500);
    DECLARE @V_NDATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'product';
    set @V_action = 'UPDATE';
    SET @V_ADATA = (select CONCAT(product_id,' ',product_name,' ',product_brand,' ',product_id,' ',unit_price,' ',units_in_stock) from deleted);
    SET @V_NDATA = (select CONCAT(product_id,' ',product_name,' ',product_brand,' ',product_id,' ',unit_price,' ',units_in_stock) from inserted);
    INSERT INTO [audit] (username,[date],[table],[action],old_value,new_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_ADATA,@V_NDATA);
END;
GO

/* 6. trigger_product_insert */

GO

CREATE TRIGGER trigger_product_insert
    ON Product
    AFTER insert AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_NDATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'product';
    set @V_action = 'INSERT';
    SET @V_NDATA = (select CONCAT(product_id,' ',product_name,' ',product_brand,' ',product_id,' ',unit_price,' ',units_in_stock) from inserted);
    INSERT INTO [audit] (username,[date],[table],[action],new_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_NDATA);
END;

/* 7. trigger_user_insert */

GO

CREATE TRIGGER trigger_user_insert
    ON [user]
    AFTER insert AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_NDATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'user';
    set @V_action = 'INSERT';
    SET @V_NDATA = (select CONCAT(user_id,' ',username,' ',[password],' ',first_name,' ',last_name,' ',admin_access,' ',vendor_access,' ',inventory_access) from inserted);
    INSERT INTO [audit] (username,[date],[table],[action],new_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_NDATA);
END;

/* 8. trigger_user_update */

GO

CREATE TRIGGER trigger_user_update
    ON [user]
    AFTER UPDATE AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_ADATA as VARCHAR(500);
    DECLARE @V_NDATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'user';
    set @V_action = 'UPDATE';
    SET @V_ADATA = (select CONCAT(user_id,' ',username,' ',[password],' ',first_name,' ',last_name,' ',admin_access,' ',vendor_access,' ',inventory_access) from deleted);
    SET @V_NDATA = (select CONCAT(user_id,' ',username,' ',[password],' ',first_name,' ',last_name,' ',admin_access,' ',vendor_access,' ',inventory_access) from inserted);
    INSERT INTO [audit] (username,[date],[table],[action],old_value,new_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_ADATA,@V_NDATA);
END;

/* 9. trigger_user_delete */

GO

CREATE TRIGGER trigger_user_delete
    ON [user]
    AFTER DELETE AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_ADATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'user';
    set @V_action = 'DELETE';
    SET @V_ADATA = (select CONCAT(user_id,' ',username,' ',[password],' ',first_name,' ',last_name,' ',admin_access,' ',vendor_access,' ',inventory_access) from deleted);
    INSERT INTO [audit] (username,[date],[table],[action],old_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_ADATA);
END;

/* 10. trigger_order_insert */

GO

CREATE TRIGGER trigger_order_insert
    ON [order]
    AFTER INSERT AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_NDATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'order';
    set @V_action = 'INSERT';
    SET @V_NDATA = (select CONCAT(order_id,' ',[date],' ',active_order) from inserted);
    INSERT INTO [audit] (username,[date],[table],[action],new_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_NDATA);
END;

/* 11. trigger_order_update */

GO

CREATE TRIGGER trigger_order_update
    ON [order]
    AFTER UPDATE AS
BEGIN
    DECLARE @V_USERNAME as VARCHAR(50);
    DECLARE @V_date as DATETIME;
    DECLARE @V_table as VARCHAR(50);
    DECLARE @V_action as VARCHAR(50);
    DECLARE @V_ADATA as VARCHAR(500);
    DECLARE @V_NDATA as VARCHAR(500);
    SET @V_USERNAME = SYSTEM_USER;
    SET @V_date = CURRENT_TIMESTAMP;
    set @V_table = 'order';
    set @V_action = 'UPDATE';
    SET @V_ADATA = (select CONCAT(order_id,' ',[date],' ',active_order) from deleted);
    SET @V_NDATA = (select CONCAT(order_id,' ',[date],' ',active_order) from INSERTED);
    INSERT INTO [audit] (username,[date],[table],[action],old_value,new_value) 
    values (@V_USERNAME,@V_date,@V_table,@V_action,@V_ADATA,@V_NDATA);
END;

/* - - - - - - - - - DATA INSERT - - - - - - - - -  */

GO

-- Table: [product]

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Paracetamol', 'Pharmalab', '2.50', '25');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Miconazol', 'Medaf', '5.65', '21');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Clorfenamina ', 'Sinofi', '8.00', '8');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Nimodipino', 'Sinofi', '7.20', '19');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Cinitaprida', 'Unipharm', '11', '32');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Neobol', 'Genfar', '3.75', '42');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Levetiracetam', 'Neoben', '8.90', '10');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Hidroxizina', 'Pharmalab', '12.00', '26');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Teofilina', 'Medikem', '14.50', '23');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Desmopresina', 'Unipharm', '8.00', '12');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Ondansetron', 'Genfar', '7.65', '54');

INSERT INTO [product] (product_name, product_brand, unit_price, units_in_stock)
VALUES ('Daunorubicina', 'Sinofi', '14.75', '11');

-- Table: [order]

INSERT INTO [order] ([date], [active_order]) VALUES (CURRENT_TIMESTAMP, '1');

INSERT INTO [order] ([date], [active_order]) VALUES (CURRENT_TIMESTAMP, '1');

-- Table: [order_detail]

INSERT INTO [order_detail] (order_id, product_id, unit_price, quantity, discount)
VALUES ('1', '1', '2.50', '3', '0.50');

INSERT INTO [order_detail] (order_id, product_id, unit_price, quantity, discount)
VALUES ('1', '2', '5.65', '1', '0.50');

INSERT INTO [order_detail] (order_id, product_id, unit_price, quantity, discount)
VALUES ('1', '3', '8.00', '1', '0.50');

INSERT INTO [order_detail] (order_id, product_id, unit_price, quantity, discount)
VALUES ('2', '6', '3.75', '1', '0.20');

INSERT INTO [order_detail] (order_id, product_id, unit_price, quantity, discount)
VALUES ('2', '7', '8.90', '2', '0.20');
