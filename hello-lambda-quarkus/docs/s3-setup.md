# S3 Setup for Lambda and EventBridge

| Description             | Link                                                                                               |
|-------------------------|----------------------------------------------------------------------------------------------------|
| EventBridge Permissions | https://docs.aws.amazon.com/AmazonS3/latest/userguide/ev-permissions.html                          |
| Enabling EventBridge    | https://docs.aws.amazon.com/AmazonS3/latest/userguide/enable-event-notifications-eventbridge.html  |


## Overview
This document describes how to create an Amazon S3 bucket and configure it for use with AWS Lambda and EventBridge events.

---

# 1. Create S3 Bucket

## Using AWS Console

1. Go to **AWS Console**
2. Navigate to **S3**
3. Click **Create bucket**
4. Configure:
    - **Bucket name:** `sds-second`
    - **Region:** same region as Lambda
5. Keep defaults unless your use case requires changes
6. Click **Create bucket**

---

## Using AWS CLI

```bash
aws s3api create-bucket \
  --bucket sds-second \
  --region us-east-1
```

## Enable EventBridge Notifications on the S3 Bucket

1. Open the **AWS Management Console**
2. Navigate to **S3**
3. Select the target bucket
4. Go to **Properties**
5. Scroll to **Event notifications**
6. Enable:

```
Send notifications to Amazon EventBridge for all events in this bucket
```

This allows S3 to publish events to the account’s default EventBridge bus.  

## Necessary (IAM) permissions for the bucket
When using native S3 -> EventBridge integration, no additional permissions are required.  
