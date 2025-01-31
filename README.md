# ktor-email-auth
explore ktor backend with GraphQL

### TODO
1. add JWT authentication
2. add interactions with Postgresql
3. add GraphQL
4. add interactions with GraphQL

### Completed
1. basic email and password signup and login


This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Ktor GitHub page](https://github.com/ktorio/ktor)
- The [Ktor Slack chat](https://app.slack.com/client/T09229ZC6/C0A974TJ9). You'll need to [request an invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up) to join.

## Features

Here's a list of features included in this project:

| Name                                                             | Description                                                    |
| ------------------------------------------------------------------|---------------------------------------------------------------- |
| [Request Validation](https://start.ktor.io/p/request-validation) | Adds validation for incoming requests                          |
| [Authentication](https://start.ktor.io/p/auth)                   | Provides extension point for handling the Authorization header |
| [Routing](https://start.ktor.io/p/routing)                       | Provides a structured routing DSL                              |
| [Authentication Basic](https://start.ktor.io/p/auth-basic)       | Handles 'Basic' username / password authentication scheme      |
| [Swagger](https://start.ktor.io/p/swagger)                       | Serves Swagger UI for your project                             |

## Building & Running

To build or run the project, use one of the following tasks:

| Task                          | Description                                                          |
| -------------------------------|---------------------------------------------------------------------- |
| `./gradlew test`              | Run the tests                                                        |
| `./gradlew build`             | Build everything                                                     |
| `buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `buildImage`                  | Build the docker image to use with the fat JAR                       |
| `publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `run`                         | Run the server                                                       |
| `runDocker`                   | Run using the local docker image                                     |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```
