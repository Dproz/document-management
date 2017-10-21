To Run the infrastructure during development through IDE run the following command:

```
docker-compose -f src/main/docker/infra-local.yml up -d

```

To bring down the infrastructure during development through IDE run the following command:

```
docker-compose -f src/main/docker/infra-local.yml down

```


To run the app in docker container

```

mvnw install
docker-compose -f src/main/docker/app.yml up -d
