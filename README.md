# elastic-spring

Commands to launch the project :
> mvn clean package
\
\
> docker run -p 9200:9200 -e "discovery.type=single-node"   -e "xpack.security.enabled=false"
> docker.elastic.co/elasticsearch/elasticsearch:8.8.1

When elasticsearch is up :

> java -jar target/spring-elastic-0.0.1-SNAPSHOT.jar

You were supposed to be able to launch the containers with ```docker compose up --build``` but there is an unknown
connection error :(

[Access swagger at this address](http://localhost:8080/swagger-ui/index.html#/)

TODO :

- store albums in elastic search
- tests
- search albums (/albums) + search params
- view album details (/album/id)
