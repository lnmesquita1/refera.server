
# Refera Server

This is an API to manage maintanence orders from Refera.

# Requiriments

- PostgreSQL Server 13.5
`https://www.enterprisedb.com/downloads/postgres-postgresql-downloads`
- GIT
- Spring Tools Suite (or Eclipse)

Install this programs which you can easily find and download from google.

# Import the project

- Clone this repository:
```bash
  git clone https://github.com/lnmesquita1/refera.server.git
```
- Open STS or Eclipse and click on File > Import > Maven > Existing Maven Projects
- Click next and then browse the repository which you have cloned
- Mark the checkbox pom.xml and click finish

# How to configure

- When you install PostgresSQL Server 13.5, it comes with pgAdmin; a graphical tool for managing and developing your databases. So open pgAdmin 4 and create a database 
- Create a database
- Go to STS or Eclipse and open `application.properties` file
- Change the configurations of the postgreSQL and your own database configurations in this section:

```bash
## PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/refera
spring.datasource.username=postgres
spring.datasource.password=123
```



## Run Locally

- Right click on the project > Run As > Spring Boot App


## Usage/Examples

You can test the API on postman or in other program you prefer.
I didn't implement any authorization for the endpoints, but I put all the dependencies for OAuth2 authorization flow. You can check in the end of this readme how to implement this authorization.

- This is the endpoint to create order: POST `localhost:8080/api/order`. Example of the body request:
```
{
    "name": "Cleiton",
    "celPhone": "123",
    "category": {
        "id": 5,
        "name": "teste 12/12"
    },
    "description": "Descrição teste",
    "agency": "Agencia",
    "company": "Teste",
    "deadline": "2021-12-10T08:00:00"
}
```
- This is the endpoint to list all orders: GET `localhost:8080/api/order/all`

- This is the endpoint to create category: POST `localhost:8080/api/category`. Example of the body request:
```
{
    "name": "Category name"
}
```
- This is the endpoint to list all categories: GET `localhost:8080/api/category/all`
