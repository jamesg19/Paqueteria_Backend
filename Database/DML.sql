DROP SCHEMA IF EXISTS `paqueteria`;

CREATE SCHEMA `paqueteria`;
USE `paqueteria` ;

-- Tabla de Sucursales
CREATE TABLE Sucursales (
    id INT PRIMARY KEY,
    nombre VARCHAR(255),
    direccion VARCHAR(255),
    departamento VARCHAR(50)
);

-- Tabla de Roles
CREATE TABLE Roles (
    id INT PRIMARY KEY,
    nombre_rol VARCHAR(50)
);

-- Tabla de Personal de Sucursales
CREATE TABLE personal (
    id INT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    sucursal_id INT,
    rol_id INT,
    FOREIGN KEY (sucursal_id) REFERENCES Sucursales(id),
    FOREIGN KEY (rol_id) REFERENCES Roles(id)
);

-- Tabla de Tarifas por Envío
CREATE TABLE tarifa_envio (
    id INT PRIMARY KEY,
    cantidad_envios INT,
    tarifa DECIMAL(10, 2)
);

-- Tabla de Envíos
CREATE TABLE envios (
    envio_id INT PRIMARY KEY,
    fecha_envio DATE,
    sucurlar_origen_id INT,
    sucurlar_destino_id INT,
    cantidad_paquetes INT,
    tarifa_id INT,
    FOREIGN KEY (sucurlar_origen_id) REFERENCES Sucursales(id),
    FOREIGN KEY (sucurlar_destino_id) REFERENCES Sucursales(id),
    FOREIGN KEY (tarifa_id) REFERENCES tarifa_envio(id)
);

-- Tabla de Vehículos
CREATE TABLE vehiculos (
    vehiculo_id INT PRIMARY KEY,
    tipo VARCHAR(100),
    tonelaje DECIMAL(5, 2),
    sucursal INT, -- A la sucursal que pertenece el vehiculo
    FOREIGN KEY (sucursal) REFERENCES Sucursales(id)
);
