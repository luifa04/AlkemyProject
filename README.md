# ONG - SOMOS MAS

Welcome to ONG - SOMOS MAS!

## Project Description

**ONG - SOMOS MAS** is an initiative aimed at making a positive contribution to society. Through this project, we aim to simplify the management and operation of non-governmental organizations, enabling efficient handling of users, roles, and other key functionalities.

## Technologies Used

This project has been developed using a set of modern technologies:

- **Spring Boot**: Simplifies Java application development with minimal configuration.
- **MySQL Driver**: JDBC connector for MySQL database.
- **Spring Data JPA**: Provides an easy-to-use data access layer for the database.
- **Spring Web**: Offers tools for developing web applications.
- **Spring Security**: Ensures application security through authentication and access control.
- **Json Web Token (JWT)**: Used for secure authentication and information transmission.
- **Spring Validation**: Spring validation module.
- **Spring Hateoas**: Allows incorporating resource-related navigation links in API responses.
- **Lombok**: Library that streamlines development by eliminating the need to write certain methods.
- **FreeMarker**: Template engine for web content generation.
- **Mail Sender**: Used for sending emails.
- **Amazon Web Services (AWS)**: Cloud services platform.
- **JUnit - Mockito**: Tools for performing unit and integration tests.
- **Swagger**: Facilitates the creation, documentation, and use of RESTful web services.

## Requirements

Make sure to have the following tools installed before starting development:

- **Java - 11.x.x**
- **Maven - 4.x.x**
- **MySQL - 8.x.x**

## Tools Used

- **IntelliJ IDEA Community Edition**: Integrated development environment for Java.
- **Jira**: Project management and issue tracking tool.
- **Git**: Distributed version control system.
- **Postman**: Platform for API development and testing.
- **Discord, WhatsApp, Slack**: Communication tools for the development team.

## Entity-Relationship Diagram

Below is the entity-relationship diagram representing the database structure used in this project.

![Screenshot 2023-11-15 093046](https://github.com/luifa04/AlkemyProject/assets/88746660/36f59502-31fb-4082-84d0-4685fea19a77)

## Getting Started

This project features an API that includes a seed system designed to load various users, both administrators and regular users. This loading only occurs if there are no previously authenticated users in the database.

Additionally, the API is documented using Swagger. You can access the documentation by visiting the following URL after launching the project: [Swagger Documentation](http://localhost:8080/swagger-ui.html).

## Login Information

### Admin Users:
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

In all cases, the password is: `useradmin`

### Regular Users:
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

In all cases, the password is: `useruser`

## Deployment

The application is currently deployed on [Render](https://render.com/) and uses a database hosted on [Clever Cloud](https://www.clever-cloud.com/).

The Clever Cloud database uses MySQL 8.x.x.

Visit [ONG-API on Render](https://ong-api-mscx.onrender.com/swagger-ui.html#) to explore the live application!

## Instructions for Local Execution

Follow these steps to run the project on your local machine:

1. Clone the repository to your local machine:

    ```bash
    git clone https://github.com/your_username/your_repository.git
    ```

2. Navigate to the project directory:

    ```bash
    cd your_repository
    ```

3. Modify the `application.properties` and `application.yml` files, especially the URL, USERNAME, and PASSWORD for your local environment.

4. Install dependencies:

    ```bash
    mvn install
    ```

5. Run the application:

    ```bash
    mvn spring-boot:run
    ```

6. Open your browser and visit [http://localhost:8080/swagger-ui.html#](http://localhost:8080/swagger-ui.html#).



## Additional Resources

- **Project Presentation**: Explore a detailed presentation of the project by visiting [this link](https://docs.google.com/presentation/d/1N_aaGmopll2zEWi6PfpSH1Oa0QjKHO6LrJSuGfwc3Go/edit?usp=sharing).

- **Problem Statement**: Understand the challenges addressed by the project by accessing the document at [this link](https://drive.google.com/file/d/1wHiD5_Oify5CDV3YQZfn_Vmjy1NBK5CQ/view?usp=sharing).

...

Now you should be able to explore the application locally!

