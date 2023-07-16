# Dmartready-s-Stock-Management-Backend
DMart's robust and efficient backend solution for managing stock levels. The application handles a variety of operations such as adding new stock, updating existing stock levels, tracking stock movements, and providing real-time visibility into current stock levels at various store locations.

### Micro Services Architecture

![Dmar-Stock-Management-Microservices-Architecture](https://github.com/princekr0722/Dmartready-s-Stock-Management-Backend/assets/112754559/636eb1f2-673b-4a7d-87f3-38c6b8e074ea)

### Features
* All crud opperations Stock items, Users, Stores to the system.
* Admin and Manager Side
* Real life scenario based logic between Admin, Manager, Stores and Products
* Real time updates of stocks of products
* Shifting of products from one location to another
* Safe and Secure application to let application to be only accessible only authorized clients.
* Automated, scalable, and efficient backend system
* Handle high volume of transactions
* Ensure data consistency and integrity
  

### API Documenetation
I have used the Swagger for the API Documenetation 
[NOTE: Base url may very upon on which server it is running on]

- User service: http://localhost:8083/swagger-ui/index.html#/
- Store service: http://localhost:8081/swagger-ui/index.html#/
- Product service: http://localhost:8082/swagger-ui/index.html#/


### Tech Stack
* Java: Due to its robustness, efficiency, and widespread industry adoption. 
* Spring Boot: For developing standalone, production-grade Spring-based applications that can be just run.
* SQL: For storing information such as stock details, store locations, user details, and stock movements.
* Web socket: For achieving real-time product stock updates and could be implemented for more use cases.
* Spring Security: To authenticate and authorize user on each api call for any micro service to making sure it from expected user.
* Eureka service registry: For service registration, discovery, load balancing and Fault Tolerance (fault tolerance is yet to implement).
* 

### Techniques and Architectures
* Micro Services Architecture: For Scalability and Flexibility as it is required in a production ready application for better maintainability.
* ORM optimization: For achieving optimized API calls to handle large volumn of customers and efficient api calls.


### Set Up Steps
* Download the application from git and open in your IDE
* Create 4 databases in My SQL with names:
    - dmartready_store_service
    - dmartready_product_service
    - dmartready_user_service
* Recommended order to run all the applications is:
    1. Service Registry
    2. User service, Store Service, Product service
    3. API Gateway
* Create user account
  - Better to create user with role *ROLE_ADMIN*, because it has the access of all the APIS
  - User creation required password in header: `"ApiAccessPassword" : SecreatCreateUserPassword@dmart`
* Now login with the  registered username and password
  - On login you will get JwtToken which must be stored with extra care and safty.
  - Use this token for any API call.
  - Token's validation is only 8hrs then after it will be invalidated.
* Now you are ready to go...