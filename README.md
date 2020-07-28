# Used car shop service
## Run
Just for building an application

``
./activator run
``

Application will be started at http://localhost:9000

## Run with h2-console 
If you want to run application with h2-browser, you should run:

``
./activator
``

then 

``
[used-car-shop-service] $ h2-browser
``
Default settings for h2:
 * JDBC URL: jdbc:h2:mem:play
 * username: sa
 * pass: "" -> literally empty
 
after it'll started, start service:

``
[used-car-shop-service] $ run
``

## Using 
All endpoints available at http://localhost:9000

## Base requests

* **Product create**
```
curl --location --request POST 'localhost:9000/product/create' \
--header 'Content-Type: application/json' \
--data-raw '{
        "brand": "BMW",
        "model": "X5",
        "yearOfProduction": 1988,
        "mileage": 1001,
        "price": 35000
}'
```
* **Model create**
```
curl --location --request POST 'localhost:9000/model/create' \
--header 'Content-Type: application/json' \
--data-raw '{
        "name": "X6",
        "yearOfProductionStart": 1900
}'
```
* **Brand create**
```
curl --location --request POST 'localhost:9000/brand/create' \
--header 'Content-Type: application/json' \
--data-raw '{
        "name": "Dodje11",
        "originCountry": "America"
    }'
```
## Swagger

Also available on this url:

http://localhost:9000/docs/swagger-ui/index.html?url=/assets/swagger.json

But without POST operations
