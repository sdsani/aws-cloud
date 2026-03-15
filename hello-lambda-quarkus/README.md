# AWS Lambda – Quarkus Function
This repository contains an AWS Lambda function built using **Quarkus**.
The project is designed for fast startup, low memory usage, and cloud-native deployment.

---

# Overview

* Runtime: Java (Quarkus)
* Deployment Target: AWS Lambda
* Build Tool: Gradle
* Infrastructure: AWS IAM, Lambda, S3, EventBridge
* Packaging: Uber JAR / Native Image (optional)

---

# Useful Links

## AWS Docs

| Description           | Link                                |
|-----------------------|-------------------------------------|
| Lambda Documentation  | https://docs.aws.amazon.com/lambda/ |
| IAM Documentation     | https://docs.aws.amazon.com/iam/    |
| CLI Documentation     | https://docs.aws.amazon.com/cli/    |

### Quarkus
| Description              | Link                                                                        |
|--------------------------|-----------------------------------------------------------------------------|
| Quarkus Documentation    | https://quarkus.io/guides/                                                  |
|                          | https://code.quarkus.io/                                                    |
| Quarkus AWS Lambda Guide | https://quarkus.io/guides/aws-lambda                                        |
| Other                    | https://docs.aws.amazon.com/lambda/latest/dg/with-s3-example.html           |
| Guide                    | https://docs.quarkiverse.io/quarkus-amazon-services/dev/amazon-lambda.html  |

---

# Project Structure

```
src/
 ├── main/java/
 │    └── com.shahkaar.lambda/GreetingLambda.java
 ├── main/resources/
 │    └── application.properties
 └── test/
```

Key files:

| File                     | Description                                 |
|--------------------------|---------------------------------------------|
| `GreetingLambda.java`    | Entry point for Lambda                      |
| `application.properties` | Quarkus configuration                       |
| `build.gradle`           | Gradle dependencies and build configuration |

---
# Infrastructure Components
## [IAM Configuration](docs/iam-role-setup.md)
## [S3](docs/s3-setup.md)
## [Event Bridge](docs/event-bridge.md)

---

# Admin Commands

### Build Project

```bash
./gradle build
```

### Build Native Image (optional)

```bash
take long since it is using a docker image
sudo ./gradlew build -Dquarkus.native.enabled=true
```

### Deploy
```bash
./build/manage.sh
./build/manage.sh create
./build/manage.sh delete
```

# Quarkus Configuration

`application.properties`

Example configuration:

```
quarkus.lambda.handler=<handler-class>

quarkus.log.level=INFO
quarkus.http.port=8080
```

Optional performance tuning:

```
quarkus.native.enable-http-url-handler=true
quarkus.native.enable-https-url-handler=true
```

---

# Environment Variables

Configure environment variables in Lambda:

| Variable    | Description      |
| ----------- | ---------------- |
| `ENV`       | Environment name |
| `LOG_LEVEL` | Logging level    |

---

# Troubleshooting

Common issues:

**Cold start latency**

* Use native build
* Reduce dependencies

**Permission errors**

* Verify IAM role policies

**Handler not found**

* Ensure handler matches:

```
io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest
```

---

# Maintainers

Owner: `Shah din sani`
