#Weather Service Challenge

###Requirements 

- API Rest using Spring Boot. 
- Java 11
- H2 Database
- The service must run on port 8081. 

###Data

Weather data is represented like this: 

```json
{
    "id" : 37892, 
    "date" : "2020-09-15",
    "location" : {
        "lat" : 32.7767,
        "lon" : 96.7970,
        "city" : "Dallas",
        "state" : "Texas"
    },
    "temperature" : [89.7, 84.3, 91.2, 93.1]  
}
```
###Required Endpoints 


####GET /weather

Gets all stored weather data in the system. Success code : HTTP 200.

####GET /weather/{weatherId}

Gets a weather by searching by id. Success code : HTTP 200. 
If the id does not exists : HTTP 404. 

####GET /weather?date={date}

Gets all weather data by searching by date. Success code : HTTP 200.
If there is no data for that date : HTTP 404

####POST /weather

Saves a new weather element. Success code : HTTP 201.
If there is one element with the same id already stored in the database : HTTP 400. 

####DELETE /weather/{weatherId}

Deletes one element by id. Success code: HTTP 200. 
If element does not exist: HTTP 404.

####DELETE /weather

Deletes all stored weather data in the system. Success code: HTTP 200. 

###ER Diagram



##Challenge: 

- Implement endpoints. 
- Implement unit tests.
- Insert mock data in H2 database when launching the application. 
- Use Java 8+ features (Lambdas, Streams, Functionals, Time API, Collections API improvements). 






