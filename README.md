Develop microservice in Spring Boot that would serve a single Restful API 
 
GET on http://localhost:8083/pricing/v1/prices/{storeID}/{articleID}?page=1&pageSize=3
 
the response example:


    {
        "generated_date": "2023-11-22T16:44:50Z",
        "article": "1000102674",
        "store": "7001",
        “meta” : {
               “page” : 1,
               “size” : 3
         }
         “properties” : {
        	"uom": "EA",
        	“description”: “WH Halifax Passage Lever in Satin Nickel”,
        	“brand”: “Weiser”,
        	“model”: “9GLA1010”
        },
        "prices": [ 
{
            "type": "retail",
            "subtype": "regular",
            "currency": "CAD",
            "amount": 30.0,
            "valid_from": "2023-12-31T23:59:59Z",
            "valid_to": "9999-12-31T23:59:59Z"
},
{
            "type": "retail",
            "subtype": "discounted",
            "currency": "CAD",
            "amount": 27.0,
            "valid_from": "2023-12-21T23:59:59Z",
            "valid_to": "2025-12-31T23:59:58Z"
},
{
            "type": "retail",
            "subtype": "discounted",
            "currency": "CAD",
            "amount": 26.5,
            "valid_from": "2023-12-21T23:59:59Z",
            "valid_to": "2025-12-25T23:59:58Z",
}
       ]
    }
 




Please note that prices can have overlapping validity ranges. For application to check the prices and 
mark prices having overlapping validity ranges and different price values as “overlapped” : true. In the example above second and 3rd prices will be marked as “overlapped”
merge prices (two or more) having overlapping validity ranges and equal price values into a single price with largest combined validity range
create set of unit tests to validate a  and b above. 

In case and the requested price is not found respond with following 
{
    "type": "Not_Found",
    "title": "Unavailable prices",
    "status": 404,
    "detail": "No prices were found for a given request
}
Please use in-memory DB 
<dependency> 
<groupId>com.h2database</groupId> 
<artifactId>h2</artifactId> 
<scope>runtime</scope> 
</dependency>
Please populate required data into a DB tables on application start automatically
Please come up with the data model for this assignment (for you to decided what would it be)
For the application to use in-memory cache that could be implemented using Map interface. Serve prices from cache and if not found fall back to fetch prices from the DB
It should be a production ready code with comments , logs, swagger url should be available ( http://localhost:8083/{context-path}/swagger-ui.html and / or http://localhost:8083/v3/api-docs)

The acceptance criteria:
working project that could be executed on the local computer and serve the API call from Postman or any other http client 
use pom.xml – for maven 
comments, errors handling, clean project structure 
time for completion no more than 3 hr
please ZIP the project and put back on the shared drive (same place you got an assignment from)




