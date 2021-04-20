# Hotels and Flights APi with tests

## endpoints

### List of flights

when filtering by params all 4 params must be specified

- /api/v1/flights
- /api/v1/flights?origin=Puerto Iguazú&destination=Bogotá&dateFrom=10/02/2021&dateTo=20/02/2021

### Booking flights

the body should be provided as the following example, the flight number origin and destination should matrch with the flight obtained from the listing service

```json
{
    "userName" : "seba_gonzalez@unmail.com",
    "booking" : {
        "dateFrom" : "10/11/2021",
        "dateTo" : "20/11/2021",
        "destination": "Puerto Iguazú",
        "origin" : "Buenos Aires",
        "flightNumber" : "BAPI-1235",
        "peopleAmount" : 1,
        "seats" : 2,
        "seatType" : "DOUBLE",
        "people" : [
            {
                "dni" : "12345678",
                "name" : "Pepito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1982",
                "mail" : "pepitogomez@gmail.com"
            },
             {
                "dni" : "13345678",
                "name" : "Fulanito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1983",
                "mail" : "fulanitogomez@gmail.com"
            }
        ],
        "paymentMethod" : {
            "type" : "CREDIT",
            "number" : "1234-1234-1234-1234",
            "dues" : 1
        }
    }
}
```


### List of hotels

when filtering by params all 3 params should be provided at once as well

- /api/v1/hotels
- /api/v1/hotels?dateFrom=10/02/2021&dateTo=23/03/2021&destination=Puerto Iguazú

### Booking  a hotel

the body must be provided as follows:

```json
{
    "userName" : "seba_gonzalez@unmail.com",
    "booking" : {
        "dateFrom" : "10/11/2021",
        "dateTo" : "20/11/2021",
        "destination": "Puerto Iguazú",
        "hotelCode" : "CH-0003",
        "peopleAmount" : 1,
        "seats" : 2,
        "roomType" : "DOUBLE",
        "people" : [
            {
                "dni" : "12345678",
                "name" : "Pepito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1982",
                "mail" : "pepitogomez@gmail.com"
            },
             {
                "dni" : "13345678",
                "name" : "Fulanito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1983",
                "mail" : "fulanitogomez@gmail.com"
            }
        ],
        "paymentMethod" : {
            "type" : "CREDIT",
            "number" : "1234-1234-1234-1234",
            "dues" : 6
        }
    }
}
```


