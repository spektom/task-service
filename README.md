task-service
============

This is the example of how easy is creating a REST service in Java nowadays.

Running
--------

As a pre-requisite you should have MariaDB installed, and database created as follows:

```sql
CREATE DATABASE taskdb;
GRANT ALL ON taskdb.* TO 'user'@'localhost' IDENTIFIED BY 'pass';
```

To compile, run:

```bash
mvn clean package
```

To start the REST service, run:

```bash
java -jar target/task-service-0.0.1-SNAPSHOT.jar
```


Testing the REST service
-------------------------

##### Create a new task #####

```bash
~$ curl -X POST -H "Content-Type: application/json" -d '{"text":"Implement simplest REST Java application"}' http://localhost:8080/tasks
```

##### See the task contents #####

```bash
~$ curl  http://localhost:8080/tasks/1
```
```json
{
  "text" : "Implement simplest REST Java application",
  "created" : 1395665199000,
  "completed" : null,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/tasks/1"
    }
  }
}
```

##### Create another task #####

```bash
~$ curl -X POST -H "Content-Type: application/json" -d '{"text":"Go home"}' http://localhost:8080/tasks
```

##### Find all tasks #####

```bash
~$ curl  http://localhost:8080/tasks
```
```json
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/tasks{?page,size,sort}",
      "templated" : true
    },
    "search" : {
      "href" : "http://localhost:8080/tasks/search"
    }
  },
  "_embedded" : {
    "tasks" : [ {
      "text" : "Implement simplest REST Java application",
      "created" : 1395665199000,
      "completed" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/tasks/1"
        }
      }
    }, {
      "text" : "Go home",
      "created" : 1395665359000,
      "completed" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/tasks/2"
        }
      }
    } ]
  },
  "page" : {
    "size" : 20,
    "totalElements" : 2,
    "totalPages" : 1,
    "number" : 0
  }
}
```

(See how easy will be implemeting pagination over this REST service!)

##### Mark the first task as complete #####

```bash
~$ curl -X PATCH -H "Content-Type: application/json" -d "{\"completed\":$(($(date +%s)*1000))}" http://localhost:8080/tasks/1
```

##### Find incomplete tasks #####

```bash
~$ curl  http://localhost:8080/tasks/search/findByCompletedIsNull
```
```json
{
  "_embedded" : {
    "tasks" : [ {
      "text" : "Go home",
      "created" : 1395665359000,
      "completed" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/tasks/2"
        }
      }
    } ]
  }
}
```
