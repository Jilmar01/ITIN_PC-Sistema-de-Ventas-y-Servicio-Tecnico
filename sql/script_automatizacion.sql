-- EJECUTAR CADA UNO POR INDIVIDUAL

-- ==========================================
-- TRIGGER: actualizar_stock    
-- ==========================================
CREATE TRIGGER trg_actualizar_stock
ON detalles_venta
AFTER INSERT
AS
BEGIN
    -- Evita continuar si la cantidad resultante sería negativa
    IF EXISTS (
        SELECT 1
        FROM stock s
        JOIN inserted i ON s.producto_id = i.producto_id
        WHERE s.cantidad < i.cantidad
    )
    BEGIN
        RAISERROR('No hay suficiente stock para completar la venta.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- Actualiza el stock normalmente
    UPDATE s
    SET s.cantidad = s.cantidad - i.cantidad
    FROM stock s
    INNER JOIN inserted i ON s.producto_id = i.producto_id;
END;

-- ==========================================
-- PROCEDIMIENTO: DarDeBajaEmpleado    
-- ==========================================
CREATE PROCEDURE DarDeBajaEmpleado
    @EmpleadoId INT,
    @UsuarioId INT,
    @AccionAuditoria VARCHAR(20) -- Motivo o acción: 'RENUNCIA', 'DESPEDIDO', etc.
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Paso 1: Actualizar estado del empleado a 'INACTIVO'
        UPDATE empleados
        SET estado = 'INACTIVO'
        WHERE empleado_id = @EmpleadoId;

        -- Paso 2: Registrar la acción en la tabla de auditoría
        INSERT INTO auditoria_empleados (empleado_id, accion, fecha, usuario_id)
        VALUES (@EmpleadoId, @AccionAuditoria, GETDATE(), @UsuarioId);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END;
