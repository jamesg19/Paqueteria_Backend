USE `paqueteria` ;


-- Agregar Departamentos de Guatemala
INSERT INTO departamentos (id, nombre, map_id)
VALUES
    (1, 'Guatemala','GU'),
    (2, 'Baja Verapaz','BV'),
    (3, 'Alta Verapaz','AV'),
    (4, 'El Progreso','PR'),
    (5, 'Izabal','IZ'),
    (6, 'Zacapa','ZA'),
    (7, 'Chiquimula','CQ'),
    (8, 'Santa Rosa','SR'),
    (9, 'Jalapa','JA'),
    (10, 'Jutiapa','JU'),
    (11, 'Sacatepequez','SA'),
    (12, 'Chimaltenango','CM'),
    (13, 'Escuintla','ES'),
    (14, 'Suchitepequez','SU'),
    (15, 'Retalhuleu','RE'),
    (16, 'San Marcos','SM'),
    (17, 'Huehuetenango','HU'),
    (18, 'Quetzaltenango','QZ'),
    (19, 'Totonicapan','TO'),
    (20, 'Quiche','QC'),
    (21, 'Solola','SO'),
    (22, 'Peten','PE');


-- Agregar Sucursales en Quetzaltenango
INSERT INTO sucursales ( id, nombre, direccion,municipio, departamento_id)
VALUES
    (1, 'Sucursal Xela', 'Dirección Xela 1','Quetzaltenango', 18),
    (2, 'Sucursal Guatemala', 'Dirección Guatemala 1','Guatemala', 1),
    (3, 'Sucursal Peten', 'Dirección Peten 1','Flores', 22),
    (4, 'Sucursal Escuintla', 'Dirección Escuintla 1','Tiquisate', 13);


-- Agregar Roles
INSERT INTO roles (id, nombre_rol)
VALUES
    (1, 'Secretaria/o'),
    (2, 'Bodega'),
    (3, 'Conserje'),
    (4, 'Operaciones de Logística'),
    (5, 'Guardia'),
    (6, 'Piloto'),
    (7, 'Auxiliar de Piloto'),
    (8, 'Gerente');

-- Agregar Personal en Sucursal Quetzaltenango (ID: 1)
INSERT INTO personal (id, nombre, apellido, sucursal_id, rol_id)
VALUES
    (1, 'Juan', 'Pérez', 1, 1), -- Secretaria/o
    (2, 'María', 'González', 1, 2), -- Bodega
    (3, 'Carlos', 'Rodríguez', 1, 3), -- Conserje
    (4, 'Luisa', 'Sánchez', 1, 4), -- Operaciones de Logística
    (5, 'Pedro', 'Martínez', 1, 5), -- Guardia
    (6, 'Ana', 'López', 1, 6), -- Piloto
    (7, 'Roberto', 'Díaz', 1, 7), -- Auxiliar de Piloto
    (8, 'James', 'Gramajo', 1, 8); -- Gerente

-- Agregar Personal en Sucursal Guatemala (ID: 2)
INSERT INTO personal (id, nombre, apellido, sucursal_id, rol_id)
VALUES
    (9, 'Sofía', 'Hernández', 2, 1), -- Secretaria/o
    (10, 'Esteban', 'Ramírez', 2, 2), -- Bodega
    (11, 'Laura', 'Gómez', 2, 3), -- Conserje
    (12, 'Andrés', 'Paz', 2, 4), -- Operaciones de Logística
    (13, 'Raúl', 'Jiménez', 2, 5), -- Guardia
    (14, 'Isabel', 'Torres', 2, 6), -- Piloto
    (15, 'Antonio', 'Vargas', 2, 7), -- Auxiliar de Piloto
    (16, 'William', 'Umaña', 2, 8); -- Gerente

-- Agregar Personal en Sucursal Peten (ID: 3)
INSERT INTO personal (id, nombre, apellido, sucursal_id, rol_id)
VALUES
    (17, 'Laura', 'Pérez', 3, 1), -- Secretaria/o
    (18, 'Carmen', 'González', 3, 2), -- Bodega
    (19, 'Angela', 'Rodríguez', 3, 3), -- Conserje
    (20, 'Isabel', 'Sánchez', 3, 4), -- Operaciones de Logística
    (21, 'Luis', 'Martínez', 3, 5), -- Guardia
    (22, 'Alberto', 'López', 3, 6), -- Piloto
    (23, 'Cesar', 'Díaz', 3, 7), -- Auxiliar de Piloto
    (24, 'David', 'Martinez', 3, 8); -- Gerente

-- Agregar Personal en Sucursal Escuintlad (ID: 4)
INSERT INTO personal (id, nombre, apellido, sucursal_id, rol_id)
VALUES
    (25, 'Enma', 'Hernández', 4, 1), -- Secretaria/o
    (26, 'Esteban', 'Ramírez', 4, 2), -- Bodega
    (27, 'Sandra', 'Gómez', 4, 3), -- Conserje
    (28, 'Andrea', 'Paz', 4, 4), -- Operaciones de Logística
    (29, 'Sergio', 'Jiménez', 4, 5), -- Guardia
    (30, 'Leonel', 'Torres', 4, 6), -- Piloto
    (31, 'Anders', 'Vargas', 4, 7), -- Auxiliar de Piloto
    (32, 'Ronaldo', 'Méndez', 4, 8); -- Gerente


-- Agregar Tarifas por Envío
INSERT INTO tarifa_envio (id, cantidad_envios, tarifa)
VALUES
    (1, 1, 10.00),
    (2, 5, 8.00),
    (3, 10, 7.00),
    (4, 20, 6.00),
    (5, 50, 5.00),
    (6, 100, 4.00);



-- Envios de Xela
INSERT INTO envios (fecha_envio, sucursal_origen_id, sucursal_destino_id, cantidad_paquetes, tarifa_id, fecha_entrega)
VALUES
    ('2023-09-26', 1, 2, 3, 1, '2023-09-30'), --
    ('2023-09-27', 1, 2, 5, 1, '2023-10-01'), --
    ('2023-09-28', 1, 3, 8, 2, '2023-10-02'),
    ('2023-09-29', 1, 4, 3, 1, '2023-10-03'),
    ('2023-09-30', 1, 3, 3, 1, '2023-10-04');

-- Envios de Guate
INSERT INTO envios (fecha_envio, sucursal_origen_id, sucursal_destino_id, cantidad_paquetes, tarifa_id, fecha_entrega)
VALUES
    ('2023-09-26', 2, 1, 5, 1, '2023-09-30'),
    ('2023-09-27', 2, 1, 8, 2, '2023-10-01'),
    ('2023-09-28', 2, 3, 3, 1, '2023-10-02'),
    ('2023-09-29', 2, 4, 4, 1, '2023-10-03'),
    ('2023-09-30', 2, 4, 5, 1, '2023-10-04');


-- Envios de Peten
INSERT INTO envios (fecha_envio, sucursal_origen_id, sucursal_destino_id, cantidad_paquetes, tarifa_id, fecha_entrega)
VALUES
    ('2023-09-26', 3, 1, 1, 1, '2023-09-30'), --
    ('2023-09-27', 3, 1, 1, 1, '2023-10-01'), --
    ('2023-09-28', 3, 4, 2, 1, '2023-10-02'),
    ('2023-09-29', 3, 4, 1, 1, '2023-10-03'),
    ('2023-09-30', 3, 2, 5, 1, '2023-10-04');

-- Envios de Escuintla
INSERT INTO envios (fecha_envio, sucursal_origen_id, sucursal_destino_id, cantidad_paquetes, tarifa_id, fecha_entrega)
VALUES
    ('2023-09-26', 4, 1, 2, 1, '2023-09-30'), --
    ('2023-09-27', 4, 2, 2, 1, '2023-10-01'), --
    ('2023-09-28', 4, 2, 4, 1, '2023-10-02'),
    ('2023-09-29', 4, 3, 5, 1, '2023-10-03'),
    ('2023-09-30', 4, 3, 1, 1, '2023-10-04');


-- Insertar vehículos para sucursal Quetzaltenango
INSERT INTO vehiculos (nombre, placas, color,tipo, tonelaje, sucursal)
VALUES
    ('Camion 1','C-0123GMX','Rojo','Camión', 5.0, 1),
    ('Furgoneta 1','C-0124GMX','Blanco','Furgoneta', 2.5, 1),
    ('Camion 2','C-0125GMX','Amarillo','Camión Grande', 10.0, 1);

-- Insertar vehículos para sucursal  Guatemala
INSERT INTO vehiculos (nombre, placas, color,tipo, tonelaje, sucursal)
VALUES
    ('Camion 1','C-0485JVB','Blanco','Camión', 6.0, 2),
    ('Furgoneta liviana','C-0123JWT','Azul','Furgoneta', 3.0, 2),
    ('Camion 2','C-0123JSU','Blanco','Camión Grande', 12.0, 2);

-- Insertar vehículos para sucursal  Peten
INSERT INTO vehiculos (nombre, placas, color,tipo, tonelaje, sucursal)
VALUES
    ('Camion 1','C-0485GHH','Rojo','Camión', 4.0, 3),
    ('Furgoneta 1','C-09455GPQ','Amarilla','Furgoneta', 2.0, 3),
    ('Furgoneta 2','C-0642GJF','Blanco','Furgoneta', 1.5, 3);


-- Insertar vehículos para sucursal  Escuintla
INSERT INTO vehiculos (nombre, placas, color,tipo, tonelaje, sucursal)
VALUES
    ('Camion','C-0785CFG','Negro','Camión', 5.3, 4),
    ('Pick up 1','P-0453FFG','Azul','PickUp', 2.0, 4),
    ('Fugoneta','C-0953FAG','Amarillo','Furgoneta', 1.5, 4);

