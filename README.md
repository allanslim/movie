
To run Postgresql database
```
docker-compose -f ./movie/postgresql-docker-compose.yaml up -d

```

To run RabbitMQ
```
docker-compose -f ./movie/rabbitmq-docker-compose.yaml up -d

```


To run Movie API

```
mvn clean install
mvn spring-boot:run
```

To run the Docker container

```
mvn clean install dockerfile:build
docker run -p 8080:8080 -t springio/gs-spring-boot-docker
```

To Create a Movie:

```
curl -X POST \
  http://localhost:8080/movies \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
    "title": "Jumanji",
    "umid": "bdeef",
    "releaseYear": "1993",
    "runtime": "2 hours",
    "rating": "PG13"
}'
```

To get all movies:

```
curl -i -XGET "http://localhost:8080/movies"
```

To get a movie by Id:

```
curl -i -XGET "http://localhost:8080/movies/abc123"
```
