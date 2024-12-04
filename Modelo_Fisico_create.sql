CREATE DATABASE db_Eventos;

USE db_Eventos;
Go

-- tables
-- Table: Event
CREATE TABLE Evento (
    id int IDENTITY(1,1) NOT NULL,
    event_name varchar(50)  NOT NULL
		CONSTRAINT event_name_letters_only CHECK (event_name NOT LIKE '%[^a-zA-Z ]%'),
    description varchar(100)  NOT NULL,
    date date  NOT NULL,
    certificate decimal(10,2)  NOT NULL
		CONSTRAINT certificate_default DEFAULT (40.00),
    status char(1)  NOT NULL
		CONSTRAINT status_event CHECK (status = 'A' OR status = 'I'),
    CONSTRAINT Event_pk PRIMARY KEY  (id)
);

-- Table: Participants
CREATE TABLE Participantes (
    id int IDENTITY(1,1) NOT NULL,
    names varchar(50)  NOT NULL
		CONSTRAINT participans_names_letters_only CHECK (names NOT LIKE '%[^a-zA-Z ]%'),
    last_names varchar(50)  NOT NULL,
		CONSTRAINT participans_last_names_letters_only CHECK (last_names NOT LIKE '%[^a-zA-Z ]%'),
    email varchar(100)  NOT NULL
		CONSTRAINT participans_email_format CHECK (email LIKE '%@%._%'),
        CONSTRAINT uq_email_participants UNIQUE (email),
    cellphone char(9)  NOT NULL
		CONSTRAINT participans_cellphone_format CHECK (cellphone LIKE '9[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    type_document char(3)  NOT NULL
		CONSTRAINT participans_type_document CHECK (type_document = 'DNI' OR type_document = 'CNE'),
    number_document varchar(12)  NOT NULL
		CONSTRAINT participans_number_document_digits CHECK (number_document NOT LIKE '%[^0-9]%'),
        CONSTRAINT participans_number_document_length CHECK (
            (type_document = 'DNI' AND LEN(number_document) = 8) OR 
            (type_document = 'CNE' AND LEN(number_document) = 12)
        ),
        CONSTRAINT participans_uq_number_document UNIQUE (number_document),
    registration_date datetime  NOT NULL,
	user_name varchar(30),
	password varchar(30),
    status char(1)  NOT NULL
		CONSTRAINT status_participants CHECK (status = 'A' OR status = 'I'),
    CONSTRAINT Participants_pk PRIMARY KEY  (id)
);

-- Table: Participation_Registration
CREATE TABLE Participation_Registration (
    id int IDENTITY(1,1) NOT NULL,
    date_registration datetime  NOT NULL,
    certification bit  NOT NULL,
		CONSTRAINT certification_values CHECK (Certification = 0 OR Certification = 1),
    event_id int  NOT NULL,
    ponentes_id int  NOT NULL,
    participants_id int  NOT NULL,
    CONSTRAINT Participation_Registration_pk PRIMARY KEY  (id)
);

-- Table: Ponentes
CREATE TABLE Ponentes (
    id int IDENTITY(1,1) NOT NULL,
    names varchar(50)  NOT NULL
		CONSTRAINT speaker_names_letters_only CHECK (names NOT LIKE '%[^a-zA-Z ]%'),
    last_names varchar(50)  NOT NULL
		CONSTRAINT speaker_last_names_letters_only CHECK (last_names NOT LIKE '%[^a-zA-Z ]%'),
    email varchar(100)  NOT NULL
		CONSTRAINT speaker_email_format CHECK (email LIKE '%@%.%'),
        CONSTRAINT uq_email_speaker UNIQUE (email),
    cellphone char(9)  NOT NULL
	    CONSTRAINT speaker_cellphone_format CHECK (cellphone LIKE '9[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    type_document char(3)  NOT NULL
		CONSTRAINT speaker_type_document CHECK (type_document = 'DNI' OR type_document = 'CNE'),
    number_document varchar(12)  NOT NULL
		CONSTRAINT speaker_document_digits CHECK (number_document NOT LIKE '%[^0-9]%'),
        CONSTRAINT speaker_document_length CHECK (
            (type_document = 'DNI' AND LEN(number_document) = 8) OR 
            (type_document = 'CNE' AND LEN(number_document) = 12)
        ),
		CONSTRAINT speaker_uq_number_document UNIQUE (number_document),
    specialty varchar(50)  NOT NULL,
    status char(1)  NOT NULL
		CONSTRAINT status_speaker CHECK (status = 'A' OR status = 'I'),
    CONSTRAINT Ponentes_pk PRIMARY KEY  (id)
);

-- foreign keys
-- Reference: Participation_Registration_Event (table: Participation_Registration)
ALTER TABLE Participation_Registration ADD CONSTRAINT Participation_Registration_Event
    FOREIGN KEY (event_id)
    REFERENCES Evento (id);

-- Reference: Participation_Registration_Participants (table: Participation_Registration)
ALTER TABLE Participation_Registration ADD CONSTRAINT Participation_Registration_Participants
    FOREIGN KEY (participants_id)
    REFERENCES Participantes (id);

-- Reference: Participation_Registration_Ponentes (table: Participation_Registration)
ALTER TABLE Participation_Registration ADD CONSTRAINT Participation_Registration_Ponentes
    FOREIGN KEY (ponentes_id)
    REFERENCES Ponentes (id);


INSERT INTO Evento (event_name, description, date, certificate, status)
VALUES 
('Ciberseguridad', 'Bloque dedicado a la Ciberseguridad', '2024-08-01', 40.00, 'A'),
('Transformacion Digital', 'Bloque dedicado a la Transformación Digital', '2024-08-02', 40.00, 'A'),
('Desarrollo de Software Empresarial', 'Bloque dedicado al Desarrollo de Software Empresarial', '2024-08-03', 40.00, 'A'),
('Inteligencia Artificial', 'Aplicaciones y usos de la IA', '2024-09-10', 40.00, 'A'),
('Marketing Digital', 'Estrategias de marketing online', '2024-09-15', 40.00, 'A'),
('Java Fundamentals', 'Desarrollo de software con Java', '2024-09-20', 40.00, 'A'),
('Machine Learning', 'Técnicas y algoritmos de aprendizaje automático', '2024-09-25', 40.00, 'A'),
('Blockchain', 'Tecnología de cadenas de bloques', '2024-10-01', 40.00, 'A'),
('Big Data', 'Análisis y gestión de grandes volúmenes de datos', '2024-10-05', 40.00, 'A'),
('Cloud Computing', 'Servicios y plataformas en la nube', '2024-10-10', 40.00, 'A');

SELECT * FROM Evento;

INSERT INTO Ponentes (names, last_names, email, cellphone, type_document, number_document, specialty, status)
VALUES
('Carlos', 'Martinez', 'carlos.martinez@hotmail.com', '912345678', 'DNI', '12345678', 'Ciberseguridad', 'A'),
('Laura', 'Garcia', 'laura.garcia@yahoo.com', '923456789', 'DNI', '23456789', 'Transformacion Digital', 'A'),
('Juan', 'Perez', 'juan.perez@gmail.com', '934567890', 'CNE', '123456789012', 'Desarrollo de Software', 'A'),
('Maria', 'Lopez', 'maria.lopez@hotmail.com', '945678901', 'DNI', '34567890', 'Inteligencia Artificial', 'A'),
('Luis', 'Torres', 'luis.torres@gmail.com', '956789012', 'DNI', '45678901', 'Marketing Digital', 'A'),
('Ana', 'Diaz', 'ana.diaz@hotmail.com', '967890123', 'CNE', '234567890123', 'Java Fundamentals', 'A'),
('Pedro', 'Rodriguez', 'pedro.rodriguez@yahoo.com', '978901234', 'DNI', '56789012', 'Machine Learning', 'A'),
('Sofia', 'Hernandez', 'sofia.hernandez@yahoo.com', '989012345', 'DNI', '67890123', 'Blockchain', 'A'),
('Andres', 'Gonzalez', 'andres.gonzalez@hotmail.com', '999123456', 'DNI', '78901234', 'Big Data', 'A'),
('Elena', 'Morales', 'elena.morales@gmail.com', '911234567', 'CNE', '345678901234', 'Cloud Computing', 'A');

SELECT * FROM Ponentes;

INSERT INTO Participantes (names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, password, status)
VALUES
('Jose', 'Ramirez', 'jose.ramirez@yahoo.com', '912345678', 'DNI', '12345678', '2024-07-01', 'jramirez', 'password123', 'A'),
('Clara', 'Gomez', 'clara.gomez@gmail.com', '923456789', 'DNI', '23456789', '2024-07-02', 'cgomez', 'securePass456', 'A'),
('Miguel', 'Sanchez', 'miguel.sanchez@hotmail.com', '934567890', 'CNE', '123456789012', '2024-07-03', 'msanchez', 'miguel987', 'A'),
('Lucia', 'Reyes', 'lucia.reyes@yahoo.com', '945678901', 'DNI', '34567890', '2024-07-04', 'lreyes', 'luciaPass01', 'A'),
('Roberto', 'Vargas', 'roberto.vargas@gmail.com', '956789012', 'DNI', '45678901', '2024-07-05', 'rvargas', 'robertoV1', 'A'),
('Carmen', 'Nunez', 'carmen.nunez@hotmail.com', '967890123', 'CNE', '234567890123', '2024-07-06', 'cnunez', 'nunezC2024', 'A'),
('Diego', 'Fernandez', 'diego.fernandez@yahoo.com', '978901234', 'DNI', '56789012', '2024-07-07', 'dfernandez', 'fernandezD!', 'A'),
('Jonas', 'Zubieta', 'j.zubieta@gmail.com', '989012345', 'DNI', '67890123', '2024-07-08', 'jzubieta', 'jonas123$', 'A'),
('Sergio', 'Alvarez', 'sergio.alvarez@yahoo.com', '999123456', 'DNI', '78901234', '2024-07-09', 'salvarez', 'sergioA456', 'A'),
('Kevin', 'Silva', 'kevin.silva@vallegrande.edu.pe', '911234567', 'CNE', '345678901234', '2024-07-10', 'ksilva', '2!p!vaILP', 'A');

SELECT * FROM Participantes;

INSERT INTO Participation_Registration (certification, date_registration, event_id, ponentes_id, participants_id)
VALUES
(1, '2024-07-01', 1, 1, 1),
(0, '2024-07-02', 2, 2, 2),
(1, '2024-07-03', 3, 3, 3),
(1, '2024-07-04', 4, 4, 4),
(0, '2024-07-05', 5, 5, 5),
(1, '2024-07-06', 6, 6, 6),
(0, '2024-07-07', 7, 7, 7),
(1, '2024-07-08', 8, 8, 8),
(0, '2024-07-09', 9, 9, 9),
(1, '2024-07-10', 10, 10, 10);

SELECT * FROM Participation_Registration;

DROP VIEW View_Participation_Registration_Details;
CREATE VIEW View_Participation_Registration_Details AS
SELECT 
    pr.id, 
    CASE 
        WHEN pr.certification = 1 THEN 'Sí' 
        ELSE 'No' 
    END AS Certificado, 
    pr.date_registration AS Fecha_Registro, 
    e.event_name AS Nombre_Evento,
    p.names + ' ' + p.last_names AS Nombre_Ponente,
    pa.names + ' ' + pa.last_names AS Nombre_Participante
FROM 
    Participation_Registration pr
INNER JOIN 
    Evento e ON pr.event_id = e.id
INNER JOIN 
    Ponentes p ON pr.ponentes_id = p.id
INNER JOIN 
    Participantes pa ON pr.participants_id = pa.id;

SELECT * FROM View_Participation_Registration_Details;

select id, names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, password, status from Participantes where user_name ='ksilva' and password = '2!p!vaILP' and status = 'A';

-- End of file.

