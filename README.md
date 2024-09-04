# elastic-spring

To launch the project :
```docker compose up --build```

[Access swagger at this address](http://localhost:8080/swagger-ui/index.html#/)

On swagger, use this for the pageable object (the default object fails) :
``{
  "page": 0,
  "size": 10,
  "sort": [
    ""
  ]
}``
