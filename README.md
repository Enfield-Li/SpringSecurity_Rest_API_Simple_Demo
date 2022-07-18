## A Project Helps To Setup Spring-Security With Spring-boot REST Api

### Take it into action:

1). `Run` the app;

2). `Swagger UI automatically starts up` at port 8000 on Windows system, please check your default browser! 
(Or open directly: http://localhost:8000/swagger-ui/index.html)

3). `Login` as "intern" or "manager" in swagger UI:

      {
         "username": "intern",
         "password": "intern"
      }
      or
      {
         "username": "manager",
         "password": "manager"
      }

4.  `Test` different endpoint as you wish 😃

### Some default set up:

1). There are two `Roles`:

      Intern and Manager.

2). Different `Permissions` for different roles:

      a. Intern have access to "read" operation;
      b. Manager have access to both "read" and "write" operations;
      c. There's a "public url" named access-by-all where everyone can access.
