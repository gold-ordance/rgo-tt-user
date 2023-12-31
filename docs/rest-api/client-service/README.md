### RestTaskService

Methods:
* [GET ALL](#get-all-the-clients)
* [GET BY ENTITY ID](#get-client-by-entityid)
* [SAVE](#save-client)

#### Get all the clients

**Request**:
```http request
GET http://localhost:8081/clients
```

**Responses:**

* **OK (200):**

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "SUCCESS"
    },
    "clients": [
        {
            "entityId": <number>,
            "email": <string>,
            "createdDate": <date>,
            "lastModifiedDate": <date>
        }
    ]
}
```

* **Too Many Requests (429):**
  * Too many requests.

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "TOO_MANY_REQUESTS"
    }
}
```

* **Internal error (500):**
    * Internal unexpected error.

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "ERROR",
        "message": <string>
    }
}
```

#### Get client by entityId

**Request**:
```http request
GET http://localhost:8081/clients/{entityId}
```

**Query params**:
```
entityId:
  type: number
  valid-value: [1, 2^63 - 1]
```

**Responses:**

* **OK (200):**

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "SUCCESS"
    },
    "client": {
        "entityId": <number>,
        "email": <string>,
        "createdDate": <date>,
        "lastModifiedDate": <date>
    }
}
```

* **Bad request (400):**
    * The {entityId} is negative.

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "INVALID_RQ",
        "message": "The entityId is negative."
    }
}
```

* **Not found (404):**
    * Not found by {entityId}.

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "NOT_FOUND"
    }
}
```

* **Too Many Requests (429):**
  * Too many requests.

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "TOO_MANY_REQUESTS"
    }
}
```

* **Internal error (500):**
    * Internal unexpected error.

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "ERROR",
        "message": <string>
    }
}
```

#### Save client

**Request**:
```http request
POST http://localhost:8081/clients
```

**Body**:
```
{
    "email": <string>,
    "password": <string>
}
```

**Responses:**

* **CREATED (201):**

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "STORED"
    },
    "client": {
        "entityId": <number>,
        "email": <string>,
        "createdDate": <string,
        "lastModifiedDate": <string>
    }
}
```

* **Bad request (400):**
    * The {email} is [null, empty].
    * The {password} is [null, empty].

Headers:
```
content-type: application/json; charset=utf-8
```

Bodies:
```
{
    "status": {
        "statusCode": "INVALID_RQ",
        "message": "The email is null."
    }
}
```
```
{
    "status": {
        "statusCode": "INVALID_RQ",
        "message": "The email is empty."
    }
}
```
```
{
    "status": {
        "statusCode": "INVALID_RQ",
        "message": "The password is null."
    }
}
```
```
{
    "status": {
        "statusCode": "INVALID_RQ",
        "message": "The password is empty."
    }
}
```

* **Too Many Requests (429):**
  * Too many requests.

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "TOO_MANY_REQUESTS"
    }
}
```

* **Internal error (500):**
    * Internal unexpected error.

Headers:
```
content-type: application/json; charset=utf-8
```

Body:
```
{
    "status": {
        "statusCode": "ERROR",
        "message": <string>
    }
}
```