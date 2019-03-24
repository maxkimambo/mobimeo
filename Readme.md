## Mobimeo challenge 

This repository contains source code for the Verspaetungs api. 

A docker container is provided for simplicity 

```
docker run --rm -p 8081:8081 maxkimambo/mobimeo
```

To compile and run locally use the following: 

```
mvn package 

then run with 

java -jar target/mobimeo-0.0.1-SNAPSHOT.jar

```


The container exposes the api via the following endpoints. 

The endpoint that returns a vehicle at a given stop at a given time 

local:8081/api/vehicles/x/y/hh:mm:ss

The endpoint that returns the next vehicle for the stop 

localhost:8081/api/vehicles/next/x/y/hh:mm:ss 

The time needs to be supplied by the client otherwise the api will need to use internal system clock 
which is not always feasible. 



#### Design considerations 

Current design follows the MVC pattern. 
The data is handled in the repository, the service layer holds the necessary
business logic. 

Domain package holds the domain classes used to model this problem.


The unit tests have also been provided and can be run the following command

```
mvn test 

```


#### Possible improvements 

Look up of Cartesian coordinates used in the current solution currently are implemented with 
complexity of O(n) time this does not scale well if we have a lot of coordinates that need 
to be looked up. 

To solve this issue the coordinates could be divided into Voronoi Sites initial 
population of the Voronoi sites could be implemented using Fortune's algorithm 
which will improve the complexity down to  O(n log n) time. 



