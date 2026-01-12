# ktor-email-auth
explore ktor backend with GraphQL

### TODO
1. go over ktor tutorials, https://ktor.io/docs/server-create-a-new-project.html
2. add interactions with Postgresql
3. add GraphQL
4. add interactions with GraphQL
5. add unit tests
6. add integration tests

### Completed
1. basic email and password signup and login
2. add JWT authentication


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

### Quick Start (Recommended)

#### Development Setup (One-time)
1. **Copy environment template:**
   ```bash
   cp .env-sample .env.development
   ```

2. **Edit `.env.development`** and set:
   ```bash
   LOG_LEVEL=DEBUG
   PORT=8080
   ```

3. **Configure IntelliJ IDEA:**
   - Run → Edit Configurations
   - Find your "EngineMain" configuration
   - In "Environment variables" field, browse to your `.env.development` file
   - Click OK

4. **Run the application:**
   - Just click the green ▶️ Run button in IntelliJ
   - Or use: `./gradlew run`

#### Production Testing Setup
1. **Copy environment template:**
   ```bash
   cp .env-sample .env
   ```

2. **Edit `.env`** with your production values:
   ```bash
   LOG_LEVEL=INFO
   PORT=8080
   JWT_SECRET=your-production-secret
   # Add other production secrets...
   ```

3. **Run with production environment:**
   ```bash
   # Load .env and run
   export $(cat .env | xargs) && ./gradlew run
   ```

### Manual Commands

To build or run the project manually, use one of the following tasks:

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

## Environment Variables

The application uses the following environment variables:

| Variable    | Description                    | Default | Development | Production |
|-------------|--------------------------------|---------|-------------|------------|
| `LOG_LEVEL` | Logging level (DEBUG/INFO/WARN) | INFO    | DEBUG       | INFO       |
| `PORT`      | Server port                    | 8080    | 8080        | 8080       |

### Environment Files

- **`.env-sample`** - Template with all available environment variables (committed)
- **`.env.development`** - Your development environment variables (gitignored)
- **`.env`** - Your production/testing environment variables (gitignored)

### Setup Instructions

1. **For Development:**
   ```bash
   cp .env-sample .env.development
   # Edit .env.development with LOG_LEVEL=DEBUG
   # Configure IntelliJ to use this file (one-time setup)
   ```

2. **For Production Testing:**
   ```bash
   cp .env-sample .env
   # Edit .env with your production secrets
   # Run: export $(cat .env | xargs) && ./gradlew run
   ```

**Note:** All `.env*` files except `.env-sample` are gitignored to protect your secrets.
