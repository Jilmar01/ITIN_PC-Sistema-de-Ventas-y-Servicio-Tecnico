USE ITIN_PC;

-- tipos_cliente
INSERT INTO tipos_cliente (tipo) VALUES 
('VIP'), ('NORMAL'), ('DISTRIBUIDOR');

-- formas_pago
INSERT INTO formas_pago (forma) VALUES 
('Efectivo'), ('Tarjeta de crédito'), ('Transferencia bancaria');

-- marcas
INSERT INTO marcas (nombre) VALUES 
('HP'), ('Dell'), ('Lenovo'), ('Asus'), ('Acer'), ('MSI'), ('Logitech'), ('Samsung'), ('Apple'), ('Sony'), ('Kingston'), 
('Corsair'), ('TP-Link');

-- categorias
INSERT INTO categorias (nombre) VALUES 
('Laptops'), ('Periféricos'), ('Redes'), ('Almacenamiento'), ('Monitores'), ('Componentes');

-- estado_servicio
INSERT INTO estado_servicio (nombre_estado) VALUES 
('Pendiente'), ('En proceso'), ('Finalizado');

-- empleados
INSERT INTO empleados (nombre, apellido, puesto, fecha_contratacion) VALUES
('Universidad', 'ESPE', 'Administrador del Sistema', '2024-01-01 10:20:18'),
('Jilmar', 'Gonzalez', 'Administrador del Sistema', '2024-02-01 13:30:45'),
('Jhonny', 'Romero', 'Vendedor de tecnologia', '2024-03-01 09:15:30'),
('Ana', 'Paredes', 'Vendedora de hardware', '2024-04-01 11:45:00'),
('Said', 'Bosques', 'Tecnico de soporte', '2024-05-01 08:25:10'),
('Marco', 'Vega', 'Tecnico especialista', '2024-06-01 14:00:00');

-- usuarios
INSERT INTO usuarios (empleado_id, nombre_usuario, clave_acceso, rol) VALUES
(1, 'ESPE', 'ESPE123', 'ADMIN'),
(2, 'admin_jilmar', 'admin123', 'ADMIN'),
(3, 'vendedor_jhonny', 'vendedor123', 'VENDEDOR'),
(4, 'vendedora_ana', 'ana456', 'VENDEDOR'),
(5, 'tecnico_said', 'tecnico123', 'TECNICO'),
(6, 'tecnico_marco', 'marco456', 'TECNICO');

-- clientes
INSERT INTO clientes (nombre, apellido, correo, telefono, tipo_cliente_id) VALUES
('Carlos', 'Ruiz', 'cruiz@email.com', '0987654321', 2),
('Andrea', 'Martinez', 'amartinez@email.com', '0987654322', 1),
('Luis', 'Gomez', 'lgomez@email.com', '0987654323', 2),
('Maria', 'Lopez', 'mlopez@email.com', '0987654324', 1),
('Pedro', 'Sanchez', 'psanchez@email.com', '0987654325', 2),
('Lucia', 'Perez', 'lperez@email.com', '0987654326', 1),
('Jorge', 'Vera', 'jvera@email.com', '0987654327', 2),
('Ana', 'Torres', 'atorres@email.com', '0987654328', 1),
('Sofia', 'Mora', 'smora@email.com', '0987654329', 2),
('Diego', 'Castro', 'dcastro@email.com', '0987654330', 1);

-- productos
INSERT INTO productos (nombre, marca_id, categoria_id, descripcion, precio) VALUES
('Laptop Asus Tuff', 4, 1, 'Intel Corei5, 12 Ram', 899.99),
('Mouser Logitech', 7, 2, 'Mouser Inalambrico', 29.99),
('Disco Duro Kingston', 11, 4, 'Disco Duro de 1TB', 59.99),
('Monitor Samsung Curvo', 8, 5, 'Monitor Curvo de 27"', 299.45),
('Tarjeta Grafica MSI', 6, 6, 'Tarjeta Grafica RTX 3060', 399.99),
('Laptop Dell Inspiron', 2, 1, 'Intel Corei7, 16 Ram', 1099.99),
('Teclado Mecánico Corsair', 12, 2, 'Teclado Mecánico Retroiluminado', 89.99),
('Laptop HP Pavilion', 1, 1, 'Intel Core i7, 16GB RAM, 512GB SSD', 1200.00),
('Laptop Lenovo ThinkPad', 3, 1, 'Intel Core i5, 8GB RAM, 256GB SSD', 950.10),
('Monitor Dell UltraSharp', 2, 5, 'Monitor 24" Full HD', 320.10),
('Teclado Logitech K380', 7, 2, 'Teclado Bluetooth compacto', 45.00),
('Mouse HP X200', 1, 2, 'Mouse inalámbrico', 25.00),
('Disco Duro Externo Seagate', 11, 4, '2TB USB 3.0', 85.30),
('Router Asus RT-AC68U', 4, 3, 'Router WiFi AC1900', 130.00),
('Memoria RAM Corsair Vengeance', 12, 6, '16GB DDR4 3200MHz', 75.30),
('Auriculares Apple AirPods', 9, 2, 'Auriculares inalámbricos', 180.00),
('Monitor Acer Nitro', 5, 5, 'Monitor 27" 165Hz', 350.05),
('Teclado Mecánico MSI Vigor', 6, 2, 'Teclado RGB para gaming', 99.00),
('Laptop Acer Aspire', 5, 1, 'Intel Core i3, 8GB RAM, 1TB HDD', 700.10),
('Disco SSD Kingston A400', 11, 4, 'SSD 480GB SATA', 60.10),
('Mouse Gamer Corsair Harpoon', 12, 2, 'Mouse óptico RGB', 49.00),
('Switch TP-Link 8 Puertos', 3, 3, 'Switch Gigabit Ethernet', 55.75),
('Monitor Samsung Odyssey', 8, 5, 'Monitor Curvo 32"', 420.00),
('Auriculares Sony MDR-ZX110', 10, 2, 'Auriculares con cable', 25.00),
('Laptop Apple MacBook Air', 9, 1, 'Apple M1, 8GB RAM, 256GB SSD', 1400.25),
('Teclado Apple Magic Keyboard', 9, 2, 'Teclado inalámbrico', 120.00),
('Tarjeta Gráfica Asus Dual', 4, 6, 'NVIDIA RTX 4060', 520.25),
('Router TP-Link Archer AX50', 13, 3, 'Router WiFi 6', 150.99),
('Disco Duro WD My Passport', 11, 4, '2TB USB 3.0', 90.00),
('Monitor LG UltraGear', 5, 5, 'Monitor Gaming 27" QHD', 400.99);

-- stock_productos
INSERT INTO stock (producto_id, cantidad) VALUES
(1, 25),
(2, 29),
(3, 23),
(4, 15),
(5, 22),
(6, 28),
(7, 12),
(8, 31),
(9, 21),
(10, 27),
(11, 19),
(12, 24),
(13, 18),
(14, 20),
(15, 16),
(16, 30),
(17, 14),
(18, 26),
(19, 17),
(20, 13),
(21, 29),
(22, 22),
(23, 25),
(24, 15),
(25, 28),
(26, 19),
(27, 21),
(28, 27),
(29, 23),
(30, 12);

-- ventas
INSERT INTO ventas (cliente_id, empleado_id, fecha, forma_pago_id) VALUES
(7, 3, '2024-08-12 10:55:28', 2),
(8, 4, '2025-01-04 09:52:52', 1),
(9, 4, '2024-09-03 17:39:56', 1),
(6, 3, '2025-05-04 04:04:57', 2),
(6, 4, '2024-09-01 21:45:56', 2),
(10, 3, '2024-09-24 17:54:23', 3),
(5, 4, '2025-01-14 16:13:54', 2),
(2, 4, '2024-11-28 17:52:27', 1),
(2, 4, '2025-03-06 09:00:13', 2),
(1, 3, '2025-01-13 07:46:24', 1);

-- detalle_venta -- agregar atributo precio_total
INSERT INTO detalles_venta (producto_id, cantidad, venta_id, precio_unitario) VALUES
(3, 4, 1, 59.99),
(30, 1, 1, 4000.00),
(8, 1, 1, 1200.00),
(20, 2, 1, 60.00),
(2, 3, 2, 29.99),
(1, 2, 2, 899.99),
(5, 1, 2, 399.99),
(6, 1, 3, 1099.99),
(7, 1, 3, 89.99),
(9, 1, 3, 950.10),
(10, 1, 3, 320.10),
(11, 1, 4, 45.00),
(12, 1, 4, 25.00),
(13, 1, 4, 85.30),
(14, 1, 5, 130.00),
(16, 1, 6, 180.00),
(17, 1, 6, 350.05),
(4, 1, 6, 299.45),
(18, 1, 7, 99.00),
(19, 1, 7, 700.10),
(15, 1, 7, 75.30),
(21, 1, 8, 49.00),
(22, 1, 8, 55.75),
(23, 1, 9, 420.00),
(24, 2, 10, 25.00);

-- servicios_tecnicos
INSERT INTO servicio_tecnico (producto_id, cliente_id, tipo_servicio, problemas_reportados, fecha_servicio, empleado_id, estado_id) VALUES
(6, 3, 'Reparación', 'Problema con la pantalla', '2024-08-15 10:00:00', 5, 1),
(9, 1, 'Mantenimiento', 'Limpieza interna y actualización de software', '2024-09-10 14:30:00', 6, 2),
(3, 5, 'Reparación', 'Disco duro no reconocido', '2024-10-05 09:15:00', 5, 1),
(12, 2, 'Reemplazo', 'Mouse con problemas de conexión', '2024-11-20 11:45:00', 6, 3),
(17, 4, 'Reparación', 'Monitor parpadea intermitentemente', '2024-12-01 16:20:00', 5, 2),
(1, 6, 'Mantenimiento', 'Revisión general y limpieza', '2025-01-15 13:00:00', 6, 1),
(14, 7, 'Reparación', 'Router no emite señal WiFi', '2025-02-10 10:30:00', 5, 3),
(8, 8, 'Reemplazo', 'Teclado con teclas atascadas', '2025-03-05 15:45:00', 6, 2),
(20, 9, 'Reparación', 'SSD no detectado por el sistema', '2025-04-12 09:00:00', 5, 1),
(23, 10, 'Mantenimiento', 'Ajuste de configuración y limpieza de pantalla', '2025-05-20 14:15:00', 6, 2);
