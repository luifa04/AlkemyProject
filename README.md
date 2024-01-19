# ONG - SOMOS MAS

¡Bienvenido a ONG - SOMOS MAS!

## Descripción del Proyecto

**ONG - SOMOS MAS** es una iniciativa que tiene como objetivo contribuir de manera positiva a la sociedad. A través de este proyecto, buscamos facilitar la gestión y operación de organizaciones no gubernamentales, permitiendo un manejo eficiente de usuarios, roles y otras funcionalidades clave.

## Tecnologías Utilizadas

Este proyecto ha sido desarrollado utilizando un conjunto de tecnologías modernas:

- **Spring Boot**: Simplifica el desarrollo de aplicaciones Java con una configuración mínima.
- **MySQL Driver**: Conector JDBC para la base de datos MySQL.
- **Spring Data JPA**: Ofrece una capa de acceso a datos fácil de usar para la base de datos.
- **Spring Web**: Proporciona herramientas para desarrollar aplicaciones web.
- **Spring Security**: Garantiza la seguridad de la aplicación mediante la autenticación y el control de acceso.
- **Json Web Token (JWT)**: Utilizado para la autenticación segura y la transmisión de información.
- **Spring Validation**: Módulo de validación de Spring.
- **Spring Hateoas**: Permite incorporar enlaces de navegación relacionados con los recursos en las respuestas de la API.
- **Lombok**: Biblioteca que agiliza el desarrollo eliminando la necesidad de escribir ciertos métodos.
- **FreeMarker**: Motor de plantillas para la generación de contenido web.
- **Mail Sender**: Utilizado para enviar correos electrónicos.
- **Amazon Web Services (AWS)**: Plataforma de servicios en la nube.
- **JUnit - Mockito**: Herramientas para realizar pruebas unitarias y de integración.
- **Swagger**: Facilita la creación, documentación y uso de servicios web RESTful.

## Requerimientos

Asegúrate de tener las siguientes herramientas instaladas antes de comenzar con el desarrollo:

- **Java - 11.x.x**
- **Maven - 4.x.x**
- **MySQL - 8.x.x**

## Herramientas Recomendadas

Para una experiencia de desarrollo eficiente, se recomienda el uso de las siguientes herramientas:

- **IntelliJ IDEA Community Edition**: Entorno de desarrollo integrado para Java.
- **Jira**: Herramienta de gestión de proyectos y seguimiento de problemas.
- **Git**: Sistema de control de versiones distribuido.
- **Postman**: Plataforma para el desarrollo y la prueba de API.
- **Discord, WhatsApp, Slack**: Herramientas de comunicación para el equipo de desarrollo.

## Diagrama de Entidad-Relación

A continuación, se muestra el diagrama de entidad-relación que representa la estructura de la base de datos utilizada en este proyecto.

![Screenshot 2023-11-15 093046](https://github.com/luifa04/AlkemyProject/assets/88746660/36f59502-31fb-4082-84d0-4685fea19a77)


## Comenzando

Este proyecto cuenta con una API que incluye un sistema de semillas (seeders) diseñado para cargar distintos usuarios, tanto administradores como usuarios regulares. Esta carga solo se realiza si no hay usuarios previamente autenticados en la base de datos.

Además, la API está documentada utilizando Swagger. Puedes acceder a la documentación visitando la siguiente URL después de levantar el proyecto: [Swagger Documentation](http://localhost:8080/swagger-ui.html).

## Datos de Inicio de Sesión

### Usuarios Admin:
- mateolopez@gmail.com
- gabrielsuarez@gmail.com
- rodrigosanchez@gmail.com
- brunoalmeida@gmail.com
- juliandelcanto@gmail.com
- fernandaortiz@gmail.com
- lucianapomilio@gmail.com
- gabrielamichelini@gmail.com
- georginazenteno@gmail.com
- lucialopez@gmail.com

En todos los casos, la contraseña es: `useradmin`

### Usuarios User:
- julialedesma@gmail.com
- paulasanchez@gmail.com
- nataliaperez@gmail.com
- claragrivarello@gmail.com
- sabrinaizquierdoz@gmail.com
- martinriquelme@gmail.com
- horacioorellena@gmail.com
- juanpalermo@gmail.com
- gastongutierrez@gmail.com
- pabloginobili@gmail.com

En todos los casos, la contraseña es: `useruser`

## Despliegue

La aplicación está actualmente deployada en [Render](https://render.com/) y utiliza una base de datos alojada en [Clever Cloud](https://www.clever-cloud.com/).

La base de datos en Clever Cloud utiliza MySQL 8.x.x.

¡Visita [ONG-API en Render](https://ong-api-mscx.onrender.com/swagger-ui.html#) para explorar la aplicación en vivo!

## Instrucciones para Ejecutar Localmente

Sigue estos pasos para ejecutar el proyecto en tu máquina local:

1. Clona el repositorio a tu máquina local:

    ```bash
    git clone https://github.com/tu_usuario/tu_repositorio.git
    ```

2. Navega al directorio del proyecto:

    ```bash
    cd tu_repositorio
    ```

3. Modifica los archivos `application.properties` y `application.yml`, especialmente el URL, USERNAME y PASSWORD para tu entorno local.

4. Instala las dependencias:

    ```bash
    mvn install
    ```

5. Ejecuta la aplicación:

    ```bash
    mvn spring-boot:run
    ```

6. Abre tu navegador y visita [http://localhost:8080/swagger-ui.html#](http://localhost:8080/swagger-ui.html#).

¡Ahora deberías poder explorar la aplicación localmente!

