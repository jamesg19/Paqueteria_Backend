DROP SCHEMA IF EXISTS `paqueteria`;

CREATE SCHEMA `paqueteria`;
USE `paqueteria` ;

-- Tabla de departamentos
CREATE TABLE departamentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    map_id VARCHAR (50) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT = 1;

-- Tabla de Sucursales
CREATE TABLE sucursales (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    municipio VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    departamento_id INT NOT NULL,
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
)ENGINE=InnoDB AUTO_INCREMENT = 100;




-- Tabla de Roles
CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_rol VARCHAR(50) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT = 1;

-- Tabla de Personal de Sucursales
CREATE TABLE personal (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sucursal_id INT NOT NULL,
    rol_id INT NOT NULL,

    FOREIGN KEY (sucursal_id) REFERENCES sucursales(id),
    FOREIGN KEY (rol_id) REFERENCES roles(id)
)ENGINE=InnoDB AUTO_INCREMENT = 1;

-- Tabla de Tarifas por Envío
CREATE TABLE tarifa_envio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cantidad_envios INT NOT NULL,
    tarifa DECIMAL(10, 2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT = 1;

-- Tabla de Envíos
CREATE TABLE envios (
    envio_id INT PRIMARY KEY AUTO_INCREMENT,
    fecha_envio DATE NOT NULL,
    sucursal_origen_id INT NOT NULL,
    sucursal_destino_id INT NOT NULL,
    cantidad_paquetes INT NOT NULL,
    tarifa_id INT NOT NULL,
    fecha_entrega DATE NOT NULL,
    peso DECIMAL(5, 2) NOT NULL DEFAULT 1,
    FOREIGN KEY (sucursal_origen_id) REFERENCES sucursales(id),
    FOREIGN KEY (sucursal_destino_id) REFERENCES sucursales(id),
    FOREIGN KEY (tarifa_id) REFERENCES tarifa_envio(id)
)ENGINE=InnoDB AUTO_INCREMENT = 1;

-- Tabla de Vehículos
CREATE TABLE vehiculos (
    vehiculo_id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(100) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    placas VARCHAR(50) UNIQUE NOT NULL ,
    tonelaje DECIMAL(5, 2) NOT NULL,
    color VARCHAR(50)  ,
    sucursal INT NOT NULL, -- A la sucursal que pertenece el vehiculo
    FOREIGN KEY (sucursal) REFERENCES sucursales(id)
)ENGINE=InnoDB AUTO_INCREMENT = 1;

-- Tabla de Seguimiento de la ruta del envío
CREATE TABLE seguimiento_envios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    envio_id INT NOT NULL,
    sucursal_id INT,
    vehiculo_id INT,
    descripcion VARCHAR(255),

    FOREIGN KEY (envio_id) REFERENCES envios(envio_id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursales(id),
    FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(vehiculo_id)
) ENGINE=InnoDB AUTO_INCREMENT = 1;




-- Tabla de Distancias entre Tiendas
CREATE TABLE distancia_tiendas (
    sucursal_origen INT NOT NULL,
    sucursal_destino INT NOT NULL,
    distancia DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (sucursal_origen,sucursal_destino),
    FOREIGN KEY (sucursal_origen) REFERENCES sucursales(id),
    FOREIGN KEY (sucursal_destino) REFERENCES sucursales(id)
)ENGINE=InnoDB AUTO_INCREMENT = 1;



