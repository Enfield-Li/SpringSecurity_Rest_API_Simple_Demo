## A Project Helps To Setup Spring-Security With Spring-boot REST Api

#### Default settings:

1. Roles:

   Intern and Manager.

2. Permissions:

   Intern have access to `read` operation;
   Manager have access to both `read` and `write` operations;
   There's a public url named `access-by-all` where everyone can access.

#### See things in action:

1. Run the app;

2. Swagger UI automatically starts up, please check your default browser!

3. Login as "intern" or "manager" in swagger UI:

   {
   "username": "intern",
   "password": "intern"
   }
   or
   {
   "username": "manager",
   "password": "manager"
   }

4. Test different endpoint as you wish :)
