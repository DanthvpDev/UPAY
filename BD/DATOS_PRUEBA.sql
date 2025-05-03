USE MASTER
GO
USE PLANILLAS_DANIELH
GO

INSERT INTO EMPLEADOS (ID, NOMBRE, APELLIDO_1, APELLIDO_2, CORREO, TELEFONO, FECHA_NACIMIENTO)
VALUES
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

INSERT INTO NOMBRAMIENTOS (FECHAI_NOMBRAMIENTO, FECHAF_NOMBRAMIENTO, ESTADO, ES_SALARIOGLOBAL, EMPLEADO_ID, PUESTO_ID)
VALUES 
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
('Anualidad', '2025-01-01', NULL, 0, 0, 1, 1, 1, 'ACT'),
('Escalafón Cat 1', '2025-01-01', NULL, 0, 0, 0, 3, 1, 'ACT'),
('Escalafón Cat 2', '2025-01-01', NULL, 0, 0, 0, 1, 2, 'ACT'),
('Dedicación Exclusiva', '2025-01-01', NULL, 0, 0, 0, 30, 2, 'ACT'),
('Carrera Profesional', '2025-01-01', NULL, 0, 1, 0, 3273, 2, 'ACT'),

select E.NOMBRE, NOMBRE_CERT, GA.CATEGORIA AS CATEGORIA_GRADO, P.CATEGORIA AS CATEGORIA_PUESTO, FECHAI_NOMBRAMIENTO, GRADO, ES_SALARIOGLOBAL, SALARIO_GLOBAL, E.ID
from CARRERA_PROFESIONAL CP
INNER JOIN EMPLEADOS E 
ON CP.EMPLEADO_ID = E.ID
INNER JOIN GRADOS_ACADEMICOS GA
ON CP.GRADO_ID = GA.ID
INNER JOIN NOMBRAMIENTOS N
ON E.ID = N.EMPLEADO_ID
INNER JOIN PUESTOS P 
ON N.PUESTO_ID = P.ID
WHERE E.NOMBRE = 'ANA'

INSERT INTO DETALLES_PLANILLA(PRIMER_PAGO, SEGUNDO_PAGO, SALARIO, SALARIO_BRUTO, SALARIO_NETO, DIAS_TRABAJADOS, RENTA_TOTAL, FECHA_PAGO1, FECHA_PAGO2, PLANILLA_ID, EMPLEADO_ID)
VALUES 
(1,1,0,1,0,20,20000, '20250412', '20250428', 1, '301410001')

INSERT INTO INCAPACIDADES (TIPO, FECHA_INICIO, FECHA_FIN, EMPLEADO_ID)
VALUES
('ENF', '2025-03-24', '2025-05-22', '101230456'),
('EMB', '2025-03-02', '2025-05-09', '108965478'),
('ACC', '2025-03-03', '2025-04-29', '123456789');
GO 


SELECT * FROM INCAPACIDADES
WHERE MONTH(GETDATE()) >= MONTH(FECHA_INICIO) AND MONTH(GETDATE()) <= MONTH(FECHA_FIN)