# SmartQuark

- SmartQuark is a RESTful API that provides a way to manage persons and phone numbers.
- It uses Kotlin 2 and Quarkus 3.x.
- We are using PostgreSQL as the database, with Panache driver.
- We do use DAO files to interact with the database, while we store Entities in "model" folder.
- Almost all models have 'dtos' classes, which are used to transfer data between the API and the client as JSON.
- All the business logic is stored inside the service classes, as CDI beans.
- The API expose an Open API documentation, which is generated automatically by Quarkus.
- All REST controllers are inside the "controller" folder.
- There is one web controller, which is used to serve the Open API documentation and an Home Page.