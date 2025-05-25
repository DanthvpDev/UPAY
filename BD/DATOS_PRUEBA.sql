USE MASTER
GO
USE PLANILLAS_DANIELH
GO

SELECT * FROM EMPLEADOS

INSERT INTO EMPLEADOS (ID, NOMBRE, APELLIDO_1, APELLIDO_2, CORREO, TELEFONO, FECHA_NACIMIENTO)
VALUES
('505670901', 'Elena', 'Mora', 'Fernández', 'elena.mora@example.com', '88112233', '1995-12-05'),
(
    '301410001',      -- ID (Cédula simulada: 301410001)
    'Ana',          -- Nombre
    'Morales',      -- Primer apellido
    'Rodríguez', -- Segundo apellido
    'ana.morales.p34k2@web.net',     -- Correo electrónico generado: ana.morales.p34k2@web.net
    '52962519',   -- Teléfono generado: 52962519
    '1994-07-28' -- Fecha de nacimiento generada: 1994-07-28
),
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

INSERT INTO CARRERA_PROFESIONAL (NOMBRE_CERT, NOMBRE_INSTITUCION, ANIO, EMPLEADO_ID, GRADO_ID)
VALUES
('Bachillerato en Educación', 'Universidad Nacional', 2016, '505670901', 3),
('Licenciatura en Educación', 'Universidad de Costa Rica', 2018, '505670901', 4),
('Maestría en Gestión Educativa', 'Universidad Estatal a Distancia', 2021, '505670901', 5),
(
    'Bachillerato en Administración de Empresas', -- Nombre del certificado/título
    'Universidad de Costa Rica', -- Nombre de la institución
    2018,             -- Año de obtención (dentro del rango válido)
    '301410001',      -- ID del empleado (Ana Morales)
    3                -- ID del Grado Académico (3 corresponde a 'BACH' - Bachillerato en los datos de prueba)
),
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

select * from PUESTOS

INSERT INTO NOMBRAMIENTOS (FECHAI_NOMBRAMIENTO, FECHAF_NOMBRAMIENTO, ESTADO, ES_SALARIOGLOBAL, EMPLEADO_ID, PUESTO_ID)
VALUES 
('2020-02-01', '2022-01-31', 'INA', 0, '505670901', 1), -- Asistente Administrativo
('2022-02-01', NULL, 'ACT', 0, '505670901', 4),
(GETDATE() + 1, NULL, 'ACT', 1, '123456789', 2),
(
    GETDATE() + 1,        -- Fecha de inicio del nombramiento (fecha actual)
    NULL,              -- Fecha fin del nombramiento (NULL si es indefinido)
    'ACT',            -- Estado del nombramiento (Activo)
    1,                -- Es salario global (1 si aplica, 0 si no) - Ajustar según la necesidad
    '301410001',      -- ID del empleado (Ana Morales)
    3                -- ID del puesto (Ingeniero de Software, Categoría 2) - Reemplazar si se usa otro puesto Cat 2
),
(GETDATE() + 5, NULL, 'ACT', 1, '302154789', 1),
(GETDATE() + 2, NULL, 'ACT', 0, '108965478', 2),
(GETDATE() + 1, NULL, 'ACT', 1, '101230456', 1)


INSERT INTO AJUSTES_SALARIALES 
(DESCRIPCION, FECHA_INICIO, FECHA_FIN, ES_DEDUCCION, ES_VALOR, USA_ANIOS, VALOR, CATEGORIA, ESTADO)
VALUES 
('PENSION MAGISTERIO', '2025-01-01', NULL, 1, 0, 0, 8, 0, 'ACT'),
('BANCO POPULAR', '2025-01-01', NULL, 1, 0, 0, 1, 0, 'ACT'),
('POLIZA DE VIDA', '2025-01-01', NULL, 1, 1, 0, 18450, 0, 'ACT'),
('COLEGIO PROFESIONAL CAT 2', '2025-01-01', NULL, 1, 1, 0, 5000, 2, 'ACT'),
('CCSS', '2025-01-01', NULL, 1, 0, 0, 4, 0, 'ACT'),
('Anualidad', '2025-01-01', NULL, 0, 0, 1, 1, 1, 'ACT'),
('Escalafón Cat 1', '2025-01-01', NULL, 0, 0, 0, 3, 1, 'ACT'),
('Escalafón Cat 2', '2025-01-01', NULL, 0, 0, 0, 1, 2, 'ACT'),
('Dedicación Exclusiva', '2025-01-01', NULL, 0, 0, 0, 30, 2, 'ACT'),
('Carrera Profesional', '2025-01-01', NULL, 0, 1, 0, 3273, 2, 'ACT')

INSERT INTO DETALLES_PLANILLA(PRIMER_PAGO, SEGUNDO_PAGO, SALARIO, SALARIO_BRUTO, SALARIO_NETO, DIAS_TRABAJADOS, RENTA_TOTAL, FECHA_PAGO1, FECHA_PAGO2, PLANILLA_ID, EMPLEADO_ID)
VALUES 
(1,1,0,1,0,20,20000, '20250412', '20250428', 1, '301410001')

INSERT INTO INCAPACIDADES (TIPO, FECHA_INICIO, FECHA_FIN, EMPLEADO_ID)
VALUES
('ENF', '2025-03-24', '2025-05-22', '101230456'),
('EMB', '2025-03-02', '2025-05-09', '108965478'),
('ACC', '2025-03-03', '2025-04-29', '123456789');
GO 

INSERT INTO PENSIONES(MONTO, FECHA_INICIO, FECHA_FIN, ESTADO, EMPLEADO_ID)
VALUES
(150000, '20250329', NULL, 'ACT', '108965478'),
(150000, '20240226', NULL, 'ACT', '123456789')


INSERT INTO TOPES_RENTA (monto_tope, monto_base, anio, porcentaje, ESTADO) VALUES
(922000, 0, 2025, 0, 'ACT'),
(1363000, 922001, 2025, 10, 'ACT'),
(2387000, 1363001, 2025, 15, 'ACT'),
(4773000, 2387001, 2025, 20, 'ACT'),
(9999999999, 4773001, 2025, 25, 'ACT');

INSERT INTO PERMISOS(DESCRIPCION, GOCE_SALARIO, FECHA_INICIO, FECHA_FIN, ESTADO, EMPLEADO_ID)
VALUES
('Permiso de estudios con goce de salario', 1, '2025-01-15', '2025-07-15', 'APR', '505670901'),
('Permiso por asuntos personales sin goce de salario', 0, '2025-02-10', '2026-02-10', 'APR', '301410001')

