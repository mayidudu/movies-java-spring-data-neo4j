package com.jn.rb.data.process.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marvin on 2018/1/27.
 * used to opera twitter message, save to mysql
 */
public class TwitterVerticle extends AbstractVerticle{


    private final Environment env;

    public TwitterVerticle(final ApplicationContext context){
        env=context.getEnvironment();
    }

    @Override
    public void start() throws Exception {
        System.out.println("start twitterverticle!");
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", env.getProperty("kafka.bootstrap.servers"));
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "GroupA");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");

        // use consumer for interacting with Apache Kafka
        KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, config);

        // or just subscribe to a single topic
        consumer.subscribe("ma-test-topic",ar -> {
            if (ar.succeeded()) {
                System.out.println("subscribed");
            } else {
                System.out.println("Could not subscribe " + ar.cause().getMessage());
            }
        });
        consumer.handler(record -> {
            System.out.println("Processing key=" + record.key() + ",value=" + record.value() +
                    ",partition=" + record.partition() + ",offset=" + record.offset());
        });
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
    }
}
