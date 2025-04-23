USE MASTER
GO
USE PLANILLAS_DANIELH
GO

INSERT INTO EMPLEADOS (ID, NOMBRE, APELLIDO_1, APELLIDO_2, CORREO, TELEFONO, FECHA_NACIMIENTO)
VALUES
('123456789', 'Juan',   'Pérez',      'Gómez', 'juanerez@example.com',   '22223333', '1980-05-12'),
('101230456', 'Andrea', 'Vargas', 'Chaves', 'andrea.vargas@gmail.com', '88887777', '1990-05-15'),
('204589123', 'Luis', 'Castro', NULL, 'luis.castro@gmail.com', '88881234', '1985-10-02'),
('302154789', 'María', 'Solano', 'Jiménez', 'maria.solano@gmail.com', '89993344', '1993-03-21'),
('108965478', 'Carlos', 'González', 'Rojas', 'carlos.gonzalez@gmail.com', '88776655', 
'1978-11-12');

INSERT INTO GRADOS_ACADEMICOS (GRADO, PUNTOS, CATEGORIA, APLICA_PUNTOS)
VALUES
('CURS', 0, 1, 0),
('CERT', 1, 1, 1),
('BACH', 2, 2, 1),
('LICI', 3, 2, 1),
('MAES', 4, 2, 1)

select * from GRADOS_ACADEMICOS

INSERT INTO CARRERA_PROFESIONAL (NOMBRE_CERT, NOMBRE_INSTITUCION, ANIO, EMPLEADO_ID, GRADO_ID)
VALUES
('Certificación en Excel Avanzado', 'INA', 2021, '101230456', 1),
('Diplomado en Redes Informáticas', 'Universidad Fidélitas', 2018, '204589123', 2),
('Bachillerato en Administración', 'ULACIT', 2017, '302154789', 3),
('Licenciatura en Derecho', 'Universidad de Costa Rica', 2019, '108965478', 4),
('Técnico en Redes', 'INA', 2015, '302154789', 1),
('Bachillerato en Educación', 'UCR', 2012, '204589123', 2),
('Licenciatura en Derecho', 'UNA', 2017, '302154789', 3),
('Curso de Excel Avanzado', 'TecDigital', 2023, '108965478', 4)


INSERT INTO PUESTOS (NOMBRE, SALARIO_BASE, SALARIO_GLOBAL, CATEGORIA, ESTADO)
VALUES 
('Asistente Administrativo', 450000.00, 520000.00, 1, 'ACT'),
('Técnico de Soporte', 500000.00, 580000.00, 1, 'ACT'),
('Ingeniero de Software', 850000.00, 960000.00, 2, 'INA'),
('Coordinador de Proyectos', 950000.00, 1050000.00, 2, 'ELM');

INSERT INTO NOMBRAMIENTOS (FECHAI_NOMBRAMIENTO, FECHAF_NOMBRAMIENTO, ESTADO, ES_SALARIOGLOBAL, EMPLEADO_ID, PUESTO_ID)
VALUES 
(GETDATE() + 5, NULL, 'ACT', 1, '302154789', 1),
(GETDATE() + 2, NULL, 'ACT', 0, '108965478', 2),
(GETDATE() + 1, NULL, 'ACT', 1, '101230456', 1)

INSERT INTO AJUSTES_SALARIALES 
(DESCRIPRCION, FECHA_INICIO, FECHA_FIN, ES_DEDUCCION, ES_VALOR, USA_ANIOS, VALOR, CATEGORIA, ESTADO)
VALUES 
('Escalafón Cat 1', '2025-01-01', NULL, 0, 0, 0, 3, 1, 'ACT'),
('Escalafón Cat 2', '2025-01-01', NULL, 0, 0, 0, 1, 2, 'ACT'),
('Dedicación Exclusiva', '2025-01-01', NULL, 0, 0, 0, 55, NULL, 'ACT'),
('Carrera Profesional', '2025-01-01', NULL, 0, 1, 0, 3273, NULL, 'ACT')

select * from AJUSTES_SALARIALES

DELETE FROM AJUSTES_SALARIALES
DBCC CHECKIDENT('AJUSTES_SALARIALES', RESEED, 0)