use master;

DROP DATABASE IF EXISTS db_VentaDelivery;
CREATE DATABASE db_VentaDelivery;

use db_VentaDelivery;

-- tables
-- Table: PERSON
DROP TABLE IF EXISTS PERSON;
CREATE TABLE PERSON (
    id int identity(1,1),
    type_person char(1) 
		CONSTRAINT type_person CHECK(type_person ='C' OR type_person ='A'),
    names varchar(60)
		CONSTRAINT names_onlyletters_person CHECK (names NOT LIKE '%[^a-zA-Z ]%'),
    last_names varchar(80)
		CONSTRAINT last_names_onlyletters_person CHECK (last_names NOT LIKE '%[^a-zA-Z ]%'),
    type_document char(3)
		CONSTRAINT type_document_person CHECK(type_document ='DNI' OR type_document ='CNE'),
    number_document varchar(12) 
		CONSTRAINT number_document_onlynumbers_person CHECK (number_document NOT LIKE '%[^0-9]%'),
		CONSTRAINT number_document_digit_person CHECK (LEN(number_document)= 8 or LEN(number_document) = 12),
		CONSTRAINT uq_number_document_person UNIQUE (number_document),
    district char(20)
		CONSTRAINT district_df DEFAULT ('Nuevo Imperial'),
    address varchar(60),
    email varchar(50)
		CONSTRAINT email_client CHECK(email LIKE '%@%._%'),
		CONSTRAINT uq_email_person UNIQUE (email),
    cell_phone char(9)
		CONSTRAINT cellphone_person CHECK (cell_phone like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    user_name varchar(15)
		CONSTRAINT uq_username_person UNIQUE (user_name),
    password varchar(15),
    status char(1)
		CONSTRAINT status_df_client DEFAULT ('A'),
    CONSTRAINT PERSON_pk PRIMARY KEY  (id)
);

-- Table: CATEGORY
DROP TABLE IF EXISTS CATEGORY;
CREATE TABLE CATEGORY (
    id int identity(1,1),
    names varchar(60)
		CONSTRAINT names_onlyletters_category CHECK (names NOT LIKE '%[^a-zA-Z ]%'),
    CONSTRAINT CATEGORY_pk PRIMARY KEY  (id)
);


-- Table: PRODUCT
DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE PRODUCT (
    id int identity(1,1),
    names varchar(80)
		CONSTRAINT names_onlyletters_product CHECK (names NOT LIKE '%[^a-zA-Z ]%'),
    price decimal(8,2),
    brand varchar(80),
    stock int
		CONSTRAINT stock_onlynumbers_product CHECK (stock NOT LIKE '%[^0-9]%'),
    description varchar(80) ,
    status char(1)
		CONSTRAINT status_df DEFAULT ('A'),
    id_category int,
    CONSTRAINT PRODUCT_pk PRIMARY KEY  (id)
);

-- Table: SALE
DROP TABLE IF EXISTS SALE;
CREATE TABLE SALE (
    id int identity(1,1),
    date datetime DEFAULT GETDATE(),
    id_person int,
    payment_type char(1) 
		CONSTRAINT payment_type_sale CHECK(payment_type ='Y' OR payment_type ='E'),
    status char(1)
		CONSTRAINT status_df_sale DEFAULT ('A'),
    CONSTRAINT SALE_pk PRIMARY KEY  (id)
);

-- Table: SALE_DETAIL
CREATE TABLE SALE_DETAIL (
    id int identity(1,1),
    id_sale int,
    id_product int,
    amount int
		CONSTRAINT amount_onlynumbers CHECK (amount NOT LIKE '%[^0-9]%'),
	subtotal decimal(8,2)
    CONSTRAINT SALE_DETAIL_pk PRIMARY KEY  (id)
);

-- foreign keys
-- Reference: PRODUCTS_CATEGORY (table: PRODUCT)
ALTER TABLE PRODUCT ADD CONSTRAINT PRODUCTS_CATEGORY
    FOREIGN KEY (id_category)
    REFERENCES CATEGORY (id);

-- Reference: SALES_DETAILS_PRODUCT (table: SALE_DETAIL)
ALTER TABLE SALE_DETAIL ADD CONSTRAINT SALES_DETAILS_PRODUCT
    FOREIGN KEY (id_product)
    REFERENCES PRODUCT (id);

-- Reference: SALES_DETAILS_SALE (table: SALE_DETAIL)
ALTER TABLE SALE_DETAIL ADD CONSTRAINT SALES_DETAILS_SALE
    FOREIGN KEY (id_sale)
    REFERENCES SALE (id);

-- Reference: SALE_CLIENT (table: SALE)
ALTER TABLE SALE ADD CONSTRAINT SALE_CLIENT
    FOREIGN KEY (id_person)
    REFERENCES PERSON (id);
--Teniendo en cuenta que tanto la creaci n
--BASE DE DATOS ,
--TABLAS y las
-- RELACIONES entre las TABLAS que esten  previamente creada  
-------------------------------------------------- EMPEZAMOS A HACER LA INSERCCION DE DATOS!----------------------------------------------------------------------------------

---Ponemos en uso la Base de Datos---
use db_VentaDelivery;

---INSERTA DATOS EN LA TABLA MAESTRA PERSON---
INSERT INTO PERSON (type_person, names, last_names, type_document, number_document, address, email, cell_phone, user_name, password) VALUES
    ('C', 'Hernan', 'Chumpitaz', 'DNI', '70145327', 'Calle Comercio', 'h.chumpitaz@gmail.com', '941865400', 'hrenan', '123456'),
    ('C', 'Ana Carmen', 'L pez', 'DNI', '78540109', 'Av.Los naranjales', 'ana@gmail.com', '923456789', 'Anita', 'Anita123'),
    ('C', 'Pedro', 'Mart nez', 'DNI', '98765432', 'Av.Los Olivos 145', 'pedromartin@gmail.com', '987654321', 'Pedrito', 'Pedro123'),
    ('C', 'Laura', 'S nchez', 'DNI', '15437111', 'Calle Libertadores 148', 'laurasanchez@yahoo.com', '967890123', 'Laurita', 'Laurita123'),
    ('C', 'Javier', 'G mez', 'DNI', '72489586', 'Avenida Santa Cruz 471', 'javier@gmail.com', '911222333', 'Javierxd', 'Javierxd123'),
    ('C', 'Mar a', 'Fern ndez', 'DNI', '45789612', 'Calle Belen 789', 'maria@gmail.com', '944555666', 'Mariaxd', 'Mariaxd123'),
    ('C', 'Carlos', 'Rodr guez', 'DNI', '78562102', 'Avenida Rosales 521', 'carlos@gmail.com', '977888999', 'Carlitos', 'Carlitos123'),
    ('C', 'Sof a', 'P rez', 'DNI', '58741236', 'Calle Se o de Luren 345', 'sofia@gmail.com', '901112131', 'Sof axd', 'Sof axd123'),
    ('C', 'Luis', 'Hern ndez', 'DNI', '78412364', 'Avenida La Molina 796', 'luis@yahoo.com', '941516171', 'Luisxd', 'Luisxd123'),
    ('C', 'Marta', 'Gonz lez', 'DNI', '12478965', 'Calle Ayacucho 678', 'marta@gmail.com', '981920212', 'Martaxd', 'Martaxd123'),
    ('C', 'Diego', 'Silva', 'DNI', '48326583', 'Avenida Mariana 421', 'diego@yahoo.com', '922333444', 'Dieguito', 'Dieguito123'),
    ('A', 'Kevin', 'Alfonzo', 'DNI', '73828771', 'Jr. Manchego Mu oz 336', 'kevin.silva@vallegrande.edu.pe', '904535298', 'kevin_alfonzo', '2!p!vaILP');

select * from PERSON; --Se hace la consulta que recupera y muestra todas las filas y columnas de la tabla "PERSON"

UPDATE PERSON 
SET user_name = 'hernan', password = '123456'
WHERE id =1;

---INSERTA DATOS EN LA TABLA  CATEGORY---
INSERT INTO CATEGORY  --se utiliza para insertar datos en la tabla "CATEGORY".--

	(names) --Especifica la columna en las que se insertar n los datos--

VALUES --Esta palabra clave se utiliza para especificar los valores que se insertar n en las columnas correspondientes de la tabla "CATEGORY"--

	--Se inserta los datos por cada columna epecificada--
    ('Detergente'),
    ('Bebidas'),
    ('Abarrotes'),
	('Higiene'),
	('PrimerosAuxilios'),
	('Libreria'),
	('Mascotas'),
	('Regalos'),
	('Verdura'),
    ('Limpieza');

select * from CATEGORY;  --Se hace la consulta que recupera y muestra todas las filas y columnas de la tabla "CATEGORY"


---INSERTA DATOS EN LA TABLA MAESTRA PERSON---
INSERT INTO PRODUCT  --se utiliza para insertar datos en la tabla "PRODUCT".--

	(names, price, brand, stock, description, id_category)  --Especifica las columnas en las que se insertar n los datos--

VALUES --Esta palabra clave se utiliza para especificar los valores que se insertar n en las columnas correspondientes de la tabla "PRODUCT"--

	--Se inserta los datos por cada columna epecificada--
	('Avena', '1.50', 'San Antonio', '1', 'Es un producto fiable','3'), 
	('Leche', '5.00', 'Gloria', '1', 'Es un producto fiable','3'), 
	('Detergente', '7.00', 'Opal', '1', 'Es un producto fiable','10'), 
	('Poet', '1.50', 'Poett', '1', 'Es un producto fiable','10'),
	('Huevo', '0.8', 'Huevo', '1', 'Es un producto fiable','3'),
	('Cereal', '0.7', 'Angel', '1', 'Es un producto fiable','3'),
	('Coca Cola', '15.00', 'CocaCola', '1', 'Es un producto fiable','3'),
	('Zanahoria', '0.5', 'unica', '1', 'Es un producto fiable','9'),
	('Shampoo', '1.50', 'sedal', '1', 'Es un producto fiable','4'),
	('Crema de Peinar', '1.00', 'pantene', '1', 'Es un producto fiable','4');

select * from PRODUCT;  --Se hace la consulta que recupera y muestra todas las filas y columnas de la tabla "PRODUCT"

---INSERTA DATOS EN LA TABLA TRANSACIONAL SALE--

INSERT INTO SALE  --se utiliza para insertar datos en la tabla "SALE".--
	(id_person, payment_type)  --Especifica las columnas en las que se insertar n los datos--

VALUES  --Esta palabra clave se utiliza para especificar los valores que se insertar n en las columnas correspondientes de la tabla "SALE"--

	--Se inserta los datos por cada columna epecificada--
	(1, 'E'),
	(2, 'E'),
	(3, 'E'),
	(4, 'Y'),
	(5, 'Y'),
	(6, 'E'),
	(7, 'E'),
	(8, 'E'),
	(9, 'E'),
	(10, 'E');

select * from SALE; --Se hace la consulta que recupera y muestra todas las filas y columnas de la tabla "SALE"


---INSERTA DATOS EN LA TABLA TRANSACIONAL SALE_DETAIL--
INSERT INTO SALE_DETAIL --se utiliza para insertar datos en la tabla "SALE_DETAIL".--

	(id_sale, id_product, amount)  --Especifica las columnas en las que se insertar n los datos--
VALUES --Esta palabra clave se utiliza para especificar los valores que se insertar n en las columnas correspondientes de la tabla "SALE_DETAIL"--

	--Se inserta los datos por cada columna epecificada--
	 (1, 1, 4),
	 (2, 2, 5),
	 (3, 3, 7),
	 (4, 4, 1),
	 (5, 5, 8),
	 (6, 6, 9),
	 (7, 7, 5),
	 (8, 8, 1),
	 (9, 9, 1),
	 (10, 10, 4);
