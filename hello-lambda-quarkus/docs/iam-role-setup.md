# IAM Role Creation

## Overview

This document describes how to create an IAM role with a **trust policy** and attach a **permissions policy**.  

Two possible options here below. For this example we are using first option.  
- Use same role for both EventBridge and Lambda
- Use separate roles for EventBridge and Lambda  

## Create Trust Policy

The **trust policy** defines which service or principal can assume the role.

Example: Allow a service to assume the role. Two principles below are because we are using same role for both EventBridge and Lambda   

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": [
          "lambda.amazonaws.com",
          "events.amazonaws.com"
        ]
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
```

---

## Create IAM Role

Using AWS CLI:

```bash
aws iam create-role \
  --role-name shahkaar-lambda \
  --assume-role-policy-document file://trust-policy.json
```

---

## Create Permissions Policy

Define what the role **is allowed to do**.

Example:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:PutLogEvents"
      ],
      "Resource": "arn:aws:logs:*:*:*"
    }
  ]
}
```

---

## Create Policy

```bash
aws iam create-policy \
  --policy-name shahkaar-lambda  \
  --policy-document file://permissions-policy.json
```

---

## Attach Policy to Role

```bash
aws iam attach-role-policy \
  --role-name my-role \
  --policy-arn arn:aws:iam::ACCOUNT_ID:policy/my-policy
```
In addition to above policy, also attach AWSLambdaRole policy to this role to address following issue.
```bash
User: arn:aws:sts::303767825709:assumed-role/shahkaar-lambda/54fa5bd702a43604a30ca6de1bdd8943 is not authorized to perform: 
lambda:InvokeFunction on resource: arn:aws:lambda:us-east-1:303767825709:function:HelloLambdaQuarkus 
because no identity-based policy allows the lambda:InvokeFunction action (Service: AWSLambdaInternal; 
Status Code: 403; Error Code: AccessDeniedException; Request ID: 9c664376-32ec-4989-bbe1-0a227565e672; Proxy: null)
```        

---

## Verify Role

```bash
aws iam get-role \
  --role-name shahkaar-lambda
```

List attached policies:

```bash
aws iam list-attached-role-policies \
  --role-name shahkaar-lambda
```
