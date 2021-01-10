# Personal Notes Rest APIs
## Dependencies
- Spring Boot 2.4.1
- Java 1.8
- Mongodb
- Maven 

### How to run
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Eclipse
- File -> Import -> Existing Maven Project -> Navigate to the folder where you 
- Select the right project
- Choose the Spring Boot Application file (search for @SpringBootApplication annotation)
- Right Click on the file and Run as Java Application
# APIs
### Add a note
-  API
```
http://localhost:8082/api/v1/notes/create
```
- Sample request body

```
{ 	"userId":"Kalani",
	"content":"This is first note"
}
```
- Sample success response body
```
Successfully created: 5ffa30f0156f9264d8173860
```

### Get a note
#### Get all notes of a user
- API
```
http://localhost:8082/api/v1/notes/getAll/{userId}
```
- Sample success response body
```
[
    {
        "id": "5ffa207fe68c6e1f3365c6cd",
        "userId": "Kalani",
        "content": "This is first note",
        "achivedStatus": true
    },
    {
        "id": "5ffa25448c5db91005ce114b",
        "userId": "Kalani",
        "content": "This is first note",
        "achivedStatus": false
    }
 ]  
 ```

#### Get all archived notes of a user
- API
```
http://localhost:8082/api/v1/notes/getArchivedAll/{userId}
```
- Sample success response body
```
[
    {
        "id": "5ffa207fe68c6e1f3365c6cd",
        "userId": "Kalani",
        "content": "This is first note",
        "achivedStatus": true
    },
    {
        "id": "5ffa2b7843da7d121e11fbb5",
        "userId": "Kalani",
        "content": "This is second note",
        "achivedStatus": true
    }
]
```

#### Get all un-archived notes of a user
- API
```
http://localhost:8082/api/v1/notes/getUnArchivedAll/{userId}
```
-  Sample success response body
```
[
    {
        "id": "5ffa207fe68c6e1f3365c6cd",
        "userId": "Kalani",
        "content": "This is first note",
        "achivedStatus": false
    },
    {
        "id": "5ffa2b7843da7d121e11fbb5",
        "userId": "Kalani",
        "content": "This is second note",
        "achivedStatus": false
    }
 ]
```
 
### Update a note
- API
```
http://localhost:8082/api/v1/notes/update
```
- Sample request body
```
{ 
	"id":"5ffa2ae62a9b06459399ffd8",
	"userId":"Kalani",
	"content":"I'm updated!"
}
```
- Sample success response body
```
Succesfully Updated 5ffa2ae62a9b06459399ffd8
```
### Delete a note
- API
```
http://:8082/api/v1/notes/deletById
```
- Sample request body
```
{ 
	"id":"5ffa2ae62a9b06459399ffd8",
	"userId":"Kalani"
}
```
- Sample success response body
```
Succesfully Deleted 5ffa2ae62a9b06459399ffd8
```
### Archive a note
- API
```
http://localhost:8082/api/v1/notes/archiveNote
```
- Sample request body
```
{ 
	"id":"5ffa2b7843da7d121e11fbb5",
	"userId":"kalani"
}
```
- Sample success response body
```
Succesfully archived 5ffa2b7843da7d121e11fbb5
```
### Un-Archive a note
- API
```
http://localhost:8082/api/v1/notes/unArchiveNote
```
- Sample request body
```
{ 
	"id":"5ffa2b7843da7d121e11fbb5",
	"userId":"kalani"
}
```
- Sample success response body
```
Succesfully unarchived 5ffa2b7843da7d121e11fbb5
```
# Technologies
- Spring Boot:This platform is multi-user-environment since this app require high concurency.Java is multithreaded so it demand many threads and achieve high concurency and make application fast.Provide robust security(ex:Kerberos, token, OpenAM, basic auth, cookie).Easy set up.Best way to build microservices.Standalone just run applications.Embedded server.  
Alternative : NodeJs -Better option to develop highly scalable applications.
- Maven: simple dependency management.
Alternative:Gradle
- Mongodb:Provide high scalability(Horizontally scalable).flexible documents schemas and powerful querying.

# Improvements
- Add Unit testing using jUnit unit testing framework.
- Implement centralized exception handling.(Exception response class and single class for exception handling .Exception response class help to keep same error code format. )
- Add user authentication via OAuth2.







