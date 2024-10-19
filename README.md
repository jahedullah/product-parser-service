## Running the Product Parser Application

To get started with the Product Parser application, follow the steps below to set up the necessary environment.

### Prerequisites

1. **Docker**: Ensure you have the latest version of Docker installed on your machine.
2. **Java**: Use Java 21 for compatibility.

### Setting Up MySQL Database

You have two options to set up the MySQL database for the application:

#### Option 1: Pull MySQL Image Manually

1. Pull the MySQL image:
   ```bash
   docker pull mysql:8.3
2. **Create a new database** named `productParserDb`. The application is configured to look for this database.

   If you choose to set up the database manually, please ensure to set the root password to `12345678`.

#### Option 2: Use Docker Compose

1. Navigate to the root directory of the project where the `docker-compose.yml` file is located.

2. Run the following command:
   ```bash
   docker-compose up
This command will automatically provision the MySQL database.

### Running the Application

Once the database is up and running, you can start the Product Parser application.

### Accessing the API

After the application has started, you can access the exposed APIs via Swagger at the following URL:  
[http://localhost:8080/productParser/swagger-ui/index.html](http://localhost:8080/productParser/swagger-ui/index.html)

### Voila!

You’re all set to start using the Product Parser application! If you encounter any issues, please refer to the documentation or reach out for support.
