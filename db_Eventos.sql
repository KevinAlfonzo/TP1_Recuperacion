DROP DATABASE db_Eventos;
CREATE DATABASE db_Eventos;

USE db_Eventos;
Go

-- tables
-- Table: Event
CREATE TABLE Event (
    id_event int IDENTITY(1,1) NOT NULL,
    Event_Name varchar(50) NOT NULL
        CONSTRAINT event_name_letters_only CHECK (Event_Name NOT LIKE '%[^a-zA-Z ]%'),
    Description varchar(100) NOT NULL,
    Date date NOT NULL,
    Certificate decimal(10,2) NOT NULL
		CONSTRAINT certificate_default DEFAULT (40.00),
    Status char(1) NOT NULL
        CONSTRAINT status_event CHECK (Status = 'A' OR Status = 'I'),
    CONSTRAINT Event_pk PRIMARY KEY (id_event)
);

-- Table: Ponentes
CREATE TABLE Ponentes (
    id_speaker int IDENTITY(1,1) NOT NULL,
    Names varchar(50) NOT NULL
        CONSTRAINT speaker_names_letters_only CHECK (Names NOT LIKE '%[^a-zA-Z ]%'),
    Last_Names varchar(50) NOT NULL
        CONSTRAINT speaker_last_names_letters_only CHECK (Last_Names NOT LIKE '%[^a-zA-Z ]%'),
    Email varchar(100) NOT NULL
        CONSTRAINT speaker_email_format CHECK (Email LIKE '%@%.%'),
        CONSTRAINT uq_email_speaker UNIQUE (Email),
    Cellphone char(9) NOT NULL
        CONSTRAINT speaker_cellphone_format CHECK (Cellphone LIKE '9[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    Type_Document char(3) NOT NULL
        CONSTRAINT speaker_type_document CHECK (Type_Document = 'DNI' OR Type_Document = 'CNE'),
    Number_Document varchar(12) NOT NULL
        CONSTRAINT speaker_document_digits CHECK (Number_Document NOT LIKE '%[^0-9]%'),
        CONSTRAINT speaker_document_length CHECK (
            (Type_Document = 'DNI' AND LEN(Number_Document) = 8) OR 
            (Type_Document = 'CNE' AND LEN(Number_Document) = 12)
        ),
        CONSTRAINT speaker_uq_number_document UNIQUE (Number_Document),
    Specialty varchar(50) NOT NULL,
    Status char(1) NOT NULL
        CONSTRAINT status_speaker CHECK (Status = 'A' OR Status = 'I'),
    CONSTRAINT Ponentes_pk PRIMARY KEY (id_speaker)
);

-- Table: Participants
CREATE TABLE Participants (
    id_participans int IDENTITY(1,1) NOT NULL,
    Names varchar(50) NOT NULL
        CONSTRAINT participans_names_letters_only CHECK (Names NOT LIKE '%[^a-zA-Z ]%'),
    Last_Names varchar(50) NOT NULL
        CONSTRAINT participans_last_names_letters_only CHECK (Last_Names NOT LIKE '%[^a-zA-Z ]%'),
    Email varchar(100) NOT NULL
        CONSTRAINT participans_email_format CHECK (Email LIKE '%@%._%'),
        CONSTRAINT uq_email_participants UNIQUE (Email),
    Cellphone char(9) NOT NULL
        CONSTRAINT participans_cellphone_format CHECK (Cellphone LIKE '9[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    Type_Document char(3) NOT NULL
        CONSTRAINT participans_type_document CHECK (Type_Document = 'DNI' OR Type_Document = 'CNE'),
    Number_Document varchar(12) NOT NULL
        CONSTRAINT participans_number_document_digits CHECK (Number_Document NOT LIKE '%[^0-9]%'),
        CONSTRAINT participans_number_document_length CHECK (
            (Type_Document = 'DNI' AND LEN(Number_Document) = 8) OR 
            (Type_Document = 'CNE' AND LEN(Number_Document) = 12)
        ),
        CONSTRAINT participans_uq_number_document UNIQUE (Number_Document),
    Registration_Date date NOT NULL ,
    Status char(1) NOT NULL
        CONSTRAINT status_participants CHECK (Status = 'A' OR Status = 'I'),
    CONSTRAINT Participants_pk PRIMARY KEY (id_participans)
);
select id_participants, Names, Last_Names, Email, Cellphone, Type_Document, Number_Document, Registration_Date, Status, User_Name, '*****' Password, status='A' from Participants
where User_Name = "" and Password = "";

SELECT @@IDENTITY id_participants
select * from Participants;

-- Table: Participation_Registration
CREATE TABLE Participation_Registration (
    id_registration int IDENTITY(1,1) NOT NULL,
    Certification bit NOT NULL,
		CONSTRAINT certification_values CHECK (Certification = 0 OR Certification = 1),
    Date_Registration date NOT NULL,
    Event_id_event int NOT NULL,
    Ponentes_id_speaker int NOT NULL,
    Participants_id_participans int NOT NULL,
    CONSTRAINT Participation_Registration_pk PRIMARY KEY (id_registration)
);


-- foreign keys
-- Reference: Participation_Registration_Event (table: Participation_Registration)
ALTER TABLE Participation_Registration ADD CONSTRAINT Participation_Registration_Event
    FOREIGN KEY (Event_id_event)
    REFERENCES Event (id_event);

-- Reference: Participation_Registration_Participants (table: Participation_Registration)
ALTER TABLE Participation_Registration ADD CONSTRAINT Participation_Registration_Participants
    FOREIGN KEY (Participants_id_participans)
    REFERENCES Participants (id_participans);

-- Reference: Participation_Registration_Ponentes (table: Participation_Registration)
ALTER TABLE Participation_Registration ADD CONSTRAINT Participation_Registration_Ponentes
    FOREIGN KEY (Ponentes_id_speaker)
    REFERENCES Ponentes (id_speaker);


INSERT INTO Event (Event_Name, Description, Date, Status)
VALUES 
('Ciberseguridad', 'Bloque dedicado a la Ciberseguridad', '2024-08-01', 'A'),
('Transformación Digital', 'Bloque dedicado a la Transformación Digital', '2024-08-02', 'A'),
('Desarrollo de Software Empresarial', 'Bloque dedicado al Desarrollo de Software Empresarial', '2024-08-03', 'A'),
('Inteligencia Artificial', 'Aplicaciones y usos de la IA', '2024-09-10', 'A'),
('Marketing Digital', 'Estrategias de marketing online', '2024-09-15', 'A'),
('Java Fundamentals', 'Desarrollo de software con Java', '2024-09-20', 'A'),
('Machine Learning', 'Técnicas y algoritmos de aprendizaje automático', '2024-09-25', 'A'),
('Blockchain', 'Tecnología de cadenas de bloques', '2024-10-01', 'A'),
('Big Data', 'Análisis y gestión de grandes volúmenes de datos', '2024-10-05', 'A'),
('Cloud Computing', 'Servicios y plataformas en la nube', '2024-10-10', 'A');

SELECT * FROM Event;

INSERT INTO Ponentes (Names, Last_Names, Email, Cellphone, Type_Document, Number_Document, Specialty, Status)
VALUES
('Carlos', 'Martinez', 'carlos.martinez@hotmail.com', '912345678', 'DNI', '12345678', 'Ciberseguridad', 'A'),
('Laura', 'Garcia', 'laura.garcia@yahoo.com', '923456789', 'DNI', '23456789', 'Transformación Digital', 'A'),
('Juan', 'Perez', 'juan.perez@gmail.com', '934567890', 'CNE', '123456789012', 'Desarrollo de Software', 'A'),
('Maria', 'Lopez', 'maria.lopez@hotmail.com', '945678901', 'DNI', '34567890', 'Inteligencia Artificial', 'A'),
('Luis', 'Torres', 'luis.torres@gmail.com', '956789012', 'DNI', '45678901', 'Marketing Digital', 'A'),
('Ana', 'Diaz', 'ana.diaz@hotmail.com', '967890123', 'CNE', '234567890123', 'Java Fundamentals', 'A'),
('Pedro', 'Rodriguez', 'pedro.rodriguez@yahoo.com', '978901234', 'DNI', '56789012', 'Machine Learning', 'A'),
('Sofia', 'Hernandez', 'sofia.hernandez@yahoo.com', '989012345', 'DNI', '67890123', 'Blockchain', 'A'),
('Andres', 'Gonzalez', 'andres.gonzalez@hotmail.com', '999123456', 'DNI', '78901234', 'Big Data', 'A'),
('Elena', 'Morales', 'elena.morales@gmail.com', '911234567', 'CNE', '345678901234', 'Cloud Computing', 'A');

SELECT * FROM Ponentes;

INSERT INTO Participants (Names, Last_Names, Email, Cellphone, Type_Document, Number_Document, Registration_Date, Status)
VALUES
('Jose', 'Ramirez', 'jose.ramirez@yahoo.com', '912345678', 'DNI', '12345678', '2024-07-01', 'A'),
('Clara', 'Gomez', 'clara.gomez@gmail.com', '923456789', 'DNI', '23456789', '2024-07-02', 'A'),
('Miguel', 'Sanchez', 'miguel.sanchez@hotmail.com', '934567890', 'CNE', '123456789012', '2024-07-03', 'A'),
('Lucia', 'Reyes', 'lucia.reyes@yahoo.com', '945678901', 'DNI', '34567890', '2024-07-04', 'A'),
('Roberto', 'Vargas', 'roberto.vargas@gmail.com', '956789012', 'DNI', '45678901', '2024-07-05', 'A'),
('Carmen', 'Nunez', 'carmen.nunez@hotmail.com', '967890123', 'CNE', '234567890123', '2024-07-06', 'A'),
('Diego', 'Fernandez', 'diego.fernandez@yahoo.com', '978901234', 'DNI', '56789012', '2024-07-07', 'A'),
('Jonas', 'Zubieta', 'j.zubieta@gmail.com', '989012345', 'DNI', '67890123', '2024-07-08', 'A'),
('Sergio', 'Alvarez', 'sergio.alvarez@yahoo.com', '999123456', 'DNI', '78901234', '2024-07-09', 'A'),
('Kevin', 'Silva', 'kevin.silva@vallegrande.edu.pe', '911234567', 'CNE', '345678901234', '2024-07-10', 'A');

SELECT * FROM Participants;

INSERT INTO Participation_Registration (Certification, Date_Registration, Event_id_event, Ponentes_id_speaker, Participants_id_participans)
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
    pr.id_registration, 
    CASE 
        WHEN pr.Certification = 1 THEN 'Sí' 
        ELSE 'No' 
    END AS Certificado, 
    pr.Date_Registration AS Fecha_Registro, 
    e.Event_Name AS Nombre_Evento,
    p.Names + ' ' + p.Last_Names AS Nombre_Ponente,
    pa.Names + ' ' + pa.Last_Names AS Nombre_Participante
FROM 
    Participation_Registration pr
INNER JOIN 
    Event e ON pr.Event_id_event = e.id_event
INNER JOIN 
    Ponentes p ON pr.Ponentes_id_speaker = p.id_speaker
INNER JOIN 
    Participants pa ON pr.Participants_id_participans = pa.id_participans;

SELECT * FROM View_Participation_Registration_Details;

CREATE VIEW View_Participants_By_Event AS
SELECT 
    pa.id_participans AS ID_Participante,
    pa.Names + ' ' + pa.Last_Names AS Nombre_Participante,
    pa.Email AS Correo,
    pa.Cellphone AS Teléfono,
    e.id_event AS ID_Evento,
    e.Event_Name AS Nombre_Evento
FROM 
    Participation_Registration pr
INNER JOIN 
    Participants pa ON pr.Participants_id_participans = pa.id_participans
INNER JOIN 
    Event e ON pr.Event_id_event = e.id_event;

CREATE PROCEDURE InsertParticipationRegistration
    @Certification bit,
    @Date_Registration date,
    @Event_id_event int,
    @Ponentes_id_speaker int,
    @Participants_id_participans int
AS
BEGIN
    INSERT INTO Participation_Registration (Certification, Date_Registration, Event_id_event, Ponentes_id_speaker, Participants_id_participans)
    VALUES (@Certification, @Date_Registration, @Event_id_event, @Ponentes_id_speaker, @Participants_id_participans);
END;


EXEC InsertParticipationRegistration 
    @Certification = 1,          -- o 0 dependiendo si adquirió la certificación
    @Date_Registration = '2024-10-16',
    @Event_id_event = 10,         -- ID del evento correspondiente
    @Ponentes_id_speaker = 10,    -- ID del ponente correspondiente
    @Participants_id_participans = 11;  -- ID del participante correspondiente


-- BACKUP DE LA DB 
-- 1. Conéctate a tu instancia de SQL Server.
-- 2. Haz clic derecho en la base de datos que deseas respaldar.
-- 3. Selecciona Tareas > Respaldar
-- 4. En la ventana que aparece, selecciona el tipo de backup y la ubicación donde deseas guardarlo.
-- 5. Haz clic en "ACEPTAR"