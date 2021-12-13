
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
- Go to STS or Eclipse and open `application.properties` file
- Change the configurations of the postgreSQL and put your own database configurations in this section:

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

This API is also running in heroku. If you are not running the project locally, for these examples below, you can change the prefix `localhost:8080/` to `https://refera-server.herokuapp.com/`

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

## OAuth2 Authorization

This could be a simple implementation of OAuth2 authorization. As I said above, I already set all the dependencies on file pom.xml to work with OAuth2.
First of all you have to replace the content of this file `WebSecurityConfig.java` to:
```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.anyRequest().authenticated()
        	.and()
        	.httpBasic()
        	.and()
        	.csrf().disable();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
    		.withUser("admin").password(passwordEncoder().encode("123")).roles("ADMIN")
    		.and()
    		.withUser("fulano").password(passwordEncoder().encode("123")).roles("USER");
    }
    
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
}
```
In the configure method we are saying that to access the resources, all users have to be authenticated. I also have created in memory authentication above. So we have two users: admin and fulano. 

And we have to configure the client authorization in the `application.properties` file:
```
security.oauth2.client.scope=password
security.oauth2.client.client-id=clientteste
security.oauth2.client.client-secret=123

```

If the user "admin" wants to access some resource, he needs to get the token from this endpoint: `localhost:8080/oauth/token`, with these parameters:

Encode to base64 the client credentials with this format: `client-id:client-secret` and then put the result in the Header like this:

`Authorization` : `Basic Y2xpZW50dGVzdGU6MTIz`

In the Body you have to put the user credentials like this:

`grant_type` : `password`

`username` : `admin`

`password` : `123`

You have to send the request as POST, and it will return the result with the access token.
This access token you have to put in the header of all protected endpoints like this:

`Authorization` : `Bearer fe2e45e6-119f-469a-963a-a75a28fd93be`

