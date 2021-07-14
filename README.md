#TO RUN
Run the command: `mvn clean package && mvn spring-boot:run`

For simplicity on a proof of concept, this project was build on an in-memory database H2.  Each time the application is 
fired, it will recreate the sample data.  (And also you will lose any data you add yourself)

Sample data can be found at: `src/main/resources/data.sql`

The sample data (with real ids) can be found at http://localhost:8081/h2-console/


#API Documentation:  
Root URL 
`http://localhost:8081`

###Post a message
`POST /message`

```json
{
  "sender": "c",
  "recipient": "Adam",
  "text":  "Hello World!"
}
```

###Get a single message
`GET /message/{id}`

###Get Message for recipient from a given sender
`GET /messages/{recipient}/sender/{sender}?limit=10`

####If the limit is not present it will default to 30 days, the limit can be anything under 100 items
####anything over 100 items will be truncated down to 100

####As we do not have auth/users for this application, we will need to search by the username.


###get all messages 
`GET /messages?limit=10`
####If the limit is not present it will default to 30 days, the limit can be anything under 100 items
####anything over 100 items will be truncated down to 100



##Other Notes
There are some basic tests for the service and repository layers, as that is where the majority of the logic
lives.  

Some steps I would take, to take this from proof of concept, to production would be to add a messaging/queue system 
for all new posts, and update the database to either a relational db, such as postgres or even a nosql db if we 
aren't concerned with having a large relation between various tables.

