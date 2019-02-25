# Vehicle Routing Problem
Programming [challenge](https://github.com/ifood/ifood-backend-logistic-test) for Backend Engineer at Ifood

## Build and test the application
```sh
./mvnw clean install
```

## Build the docker image
```
docker build --tag vehicle-routing-problem:latest .
```

## Run the app container 
```sh
docker run --rm -p "8080:8080" vehicle-routing-problem:latest
```

## Access the API Documentation
http://localhost:8080/swagger-ui.html
