-- ==========================================
-- CREACIÓN DE LA BASE DE DATOS
-- ==========================================
CREATE DATABASE ITIN_PC;
GO

USE ITIN_PC;
GO

-- ==========================================
-- TABLA: tipos_cliente
-- ==========================================
CREATE TABLE tipos_cliente (
    tipo_cliente_id INT IDENTITY(1,1) PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('VIP', 'NORMAL', 'DISTRIBUIDOR'))
);

-- ==========================================
-- TABLA: empleados
-- ==========================================
CREATE TABLE empleados (
    empleado_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    puesto VARCHAR(50),
    fecha_contratacion DATETIME DEFAULT GETDATE(),
    estado VARCHAR(10) DEFAULT 'ACTIVO'
);

-- ==========================================
-- TABLA: usuarios
-- ==========================================
CREATE TABLE usuarios (
    usuario_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    clave_acceso VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL CHECK (rol IN ('ADMIN', 'VENDEDOR', 'TECNICO')),
    empleado_id INT NOT NULL,
    FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id)
);

-- ==========================================
-- TABLA: auditoria_empleados
-- ==========================================
CREATE TABLE auditoria_empleados (
    auditoria_empleado_id INT IDENTITY(1,1) PRIMARY KEY,
    empleado_id INT NOT NULL,
    accion VARCHAR(50) NOT NULL,
    fecha DATETIME DEFAULT GETDATE(),
    usuario_id INT,
    FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)
);

-- ==========================================
-- TABLA: clientes
-- ==========================================
CREATE TABLE clientes (
    cliente_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50),
    correo VARCHAR(100),
    telefono VARCHAR(20),
    tipo_cliente_id INT,
    FOREIGN KEY (tipo_cliente_id) REFERENCES tipos_cliente(tipo_cliente_id)
);

-- ==========================================
-- TABLA: formas_pago
-- ==========================================
CREATE TABLE formas_pago (
    forma_pago_id INT IDENTITY(1,1) PRIMARY KEY,
    forma VARCHAR(50) NOT NULL UNIQUE
);

-- ==========================================
-- TABLA: ventas
-- ==========================================
CREATE TABLE ventas (
    venta_id INT IDENTITY(1,1) PRIMARY KEY,
    empleado_id INT NOT NULL,
    cliente_id INT NOT NULL,
    fecha DATETIME DEFAULT GETDATE(),
    forma_pago_id INT,
    FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id),
    FOREIGN KEY (forma_pago_id) REFERENCES formas_pago(forma_pago_id)
);

-- ==========================================
-- TABLA: marcas
-- ==========================================
CREATE TABLE marcas (
    marca_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- ==========================================
-- TABLA: categorias
-- ==========================================
CREATE TABLE categorias (
    categoria_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- ==========================================
-- TABLA: productos
-- ==========================================
CREATE TABLE productos (
    producto_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    marca_id INT NOT NULL,
    categoria_id INT NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2),
    FOREIGN KEY (marca_id) REFERENCES marcas(marca_id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(categoria_id)
);

-- ==========================================
-- TABLA: stock
-- ==========================================
CREATE TABLE stock (
    stock_id INT IDENTITY(1,1) PRIMARY KEY,
    cantidad INT NOT NULL,
    producto_id INT NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES productos(producto_id)
);

-- ==========================================
-- TABLA: detalles_venta
-- ==========================================
CREATE TABLE detalles_venta (
    detalle_venta_id INT IDENTITY(1,1) PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2),
    FOREIGN KEY (venta_id) REFERENCES ventas(venta_id),
    FOREIGN KEY (producto_id) REFERENCES productos(producto_id)
);



-- ==========================================
-- TABLA: estado_servicio
-- ==========================================
CREATE TABLE estado_servicio (
    estado_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre_estado VARCHAR(50) NOT NULL
);


-- ==========================================
-- TABLA: servicio_tecnico
-- ==========================================
CREATE TABLE servicio_tecnico (
    servicio_tecnico_id INT IDENTITY(1,1) PRIMARY KEY,
    producto_id INT NOT NULL,
    cliente_id INT NOT NULL,
    tipo_servicio VARCHAR(100),
    problemas_reportados TEXT,
    fecha_servicio DATETIME DEFAULT GETDATE(),
    empleado_id INT,
    estado_id INT NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES productos(producto_id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id),
    FOREIGN KEY (estado_id) REFERENCES estado_servicio(estado_id)
);
