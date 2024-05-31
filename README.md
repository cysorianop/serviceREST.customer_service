---

# Customer Service REST API

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.6.3-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

## Descripción

Customer Service REST API es una aplicación desarrollada con Spring Boot que permite consultar la información básica de un cliente. Este servicio utiliza datos mockeados para responder a las consultas.

## Características

- Consulta de información básica de clientes por tipo y número de documento.
- Manejo de errores y códigos HTTP (400, 404, 500, 200).
- Logging integrado para el seguimiento de solicitudes y errores.
- Tests unitarios para asegurar la calidad del código.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 2.7.0
- Maven 3.6.3
- Logback para logging
- JUnit 5 para pruebas unitarias

## Instalación y Ejecución

### Prerrequisitos

- Java 17
- Maven 3.6.3 o superior

### Paso a Paso

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/tu-usuario/customer-service.git
   cd customer-service
   ```

2. **Construir el proyecto:**

   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación:**

   ```bash
   java -jar target/customer-service-0.0.1-SNAPSHOT.jar
   ```

La aplicación estará disponible en `http://localhost:8090`.

## Uso de la API

### Endpoints

#### Obtener información del cliente

- **URL:** `/api/customers/{type}/{number}`
- **Método:** `GET`
- **Parámetros:**
  - `type`: Tipo de documento (C para cédula de ciudadanía, P para pasaporte).
  - `number`: Número de documento.

- **Ejemplo de petición:**

  ```bash
  curl -X GET "http://localhost:8090/api/customers/C/23445322"
  ```

- **Ejemplo de respuesta exitosa (200):**

  ```json
  {
    "firstName": "Juan",
    "middleName": "Carlos",
    "lastName": "Perez",
    "secondLastName": "Gomez",
    "phone": "123456789",
    "address": "Calle 123 #45-67",
    "city": "Bogotá"
  }
  ```

- **Ejemplo de respuesta con error (404):**

  ```json
  {
    "message": "Customer not found"
  }
  ```

### Pruebas Unitarias

Para ejecutar las pruebas unitarias:

```bash
mvn test
```

## Configuración de Logs

Los logs están configurados para escribirse en la consola y en un archivo ubicado en `logs/customer-service.log`.

### Ejemplo de Configuración (`logback-spring.xml`)

```xml
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>
    
    <logger name="com.serviceREST.customer_service" level="DEBUG"/>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/customer-service.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/customer-service-%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} - %msg%n</pattern>
        </encoder>
    </appender>
</configuration>
```

## Contribuir

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios necesarios y realiza commits (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube los cambios a tu repositorio (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---