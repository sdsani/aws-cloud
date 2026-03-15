package com.shahkaar.lambda;

import com.amazonaws.services.lambda.runtime.Context;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import org.jboss.logging.Logger;
import org.wildfly.common.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

//public class GreetingLambda implements RequestStreamHandler {
//public class GreetingLambda implements RequestHandler<Map<String, Object>, String> {
public class GreetingLambda implements RequestHandler<ScheduledEvent, String> {

    //private static final Logger LOG = Logger.getLogger(GreetingLambda.class);

    // https://github.com/quarkusio/quarkus/issues/7670

//    @Override
//    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
//        LOG.info("===============================================");
//        LOG.info(new String(input.readAllBytes(), StandardCharsets.UTF_8));
//    }

//    @Override
//    public String handleRequest(Map<String, Object> event, Context context) {
//        // Extract S3 details from the EventBridge detail field
//        Assert.assertNotNull(event);
//        Map<String, Object> detail = (Map<String, Object>) event.get("detail");
//        Map<String, Object> bucket = (Map<String, Object>) detail.get("bucket");
//        String bucketName = (String) bucket.get("name");
//        Map<String, Object> object = (Map<String, Object>) detail.get("object");
//        String objectKey = (String) object.get("key");
//
//        context.getLogger().log("Processing S3 event for bucket: " + bucketName + " and key: " + objectKey);
//        // Use AWS SDK for Java 2.x to retrieve the object if needed
//        // s3Client.getObject(b -> b.bucket(bucketName).key(objectKey));
//
//        return "Success";
//    }

    @Override
    public String handleRequest(ScheduledEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("================================================================");
        logger.log(event.toString());
        logger.log("Bucket Name: " + readFromLinkedHashMap(event.getDetail().get("bucket"), "name"));
        logger.log("Object Name: " + readFromLinkedHashMap(event.getDetail().get("object"), "key"));
        logger.log("Reason: " + event.getDetail().get("reason").toString());
        logger.log("================================================================");
        return "Success";
    }

    private String readFromLinkedHashMap(Object lhm, String key) {

        if (lhm instanceof Map<?, ?> map) {
            Object value = map.get(key);
            return value != null ? value.toString() : null;
        }
        return null;
    }
}
