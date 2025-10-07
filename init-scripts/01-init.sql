-- Script de inicialización para la base de datos MySQL
-- Este script se ejecuta automáticamente cuando se crea el contenedor de MySQL

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS `ws-api-test` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE `ws-api-test`;

-- Configurar zona horaria
SET time_zone = '+00:00';

-- Configuraciones adicionales para optimización
SET GLOBAL innodb_buffer_pool_size = 134217728; -- 128MB
SET GLOBAL max_connections = 100;

-- Log de inicialización
SELECT 'Base de datos para la prueba de la API de WhatsApp inicializada correctamente' AS message;
