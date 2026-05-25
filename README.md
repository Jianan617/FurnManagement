## 1. Project Overview

This is a learning project, which is a frontend-backend separated furniture management system.

The frontend is developed with Vue and Element Plus. It uses Axios to send HTTP requests to the backend API.

The backend is developed with the SSM framework, including Spring, Spring MVC and MyBatis. MySQL is used as the database. Maven is used for dependency management. MyBatis Generator is used to generate entity classes, mapper interfaces and mapper XML files. PageHelper is used to implement pagination.

## 2. Technology Stack

### Frontend

- Vue
- Vue Router
- Vuex
- Element Plus
- Axios
- JavaScript
- npm

### Backend

- Spring
- Spring MVC
- MyBatis
- MyBatis Generator
- MyBatis PageHelper
- Maven
- Druid Connection Pool
- Jackson
- Tomcat

### Database

- MySQL

## 3. Project Structure

```text
my_ssm_project/
    MySSM/                  # Backend SSM project
        src/main/java/       # Java source code
        src/main/resources/  # Spring, MyBatis and JDBC configuration files
        src/main/webapp/     # Web configuration files
        mbg.xml              # MyBatis Generator configuration
        pom.xml              # Maven configuration
        
    myssm_vue/               # Frontend Vue project
        src/                 # Vue source code
        public/              # Static files
        package.json         # npm dependencies
        vue.config.js        # Vue development server and proxy configuration

    README.md
