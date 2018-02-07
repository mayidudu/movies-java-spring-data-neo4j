package com.jn.rb.data.process.vertx;

/**
 * Created by marvin on 2018/1/22.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jn.rb.data.process.entity.Product;
import com.jn.rb.data.process.services.EsAdvanceService;
import io.vertx.core.AbstractVerticle;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class FacebookVerticle extends AbstractVerticle {
    private final Environment env;
    private final EsAdvanceService esAdvanceService;

    public FacebookVerticle(final ApplicationContext context){
        env=context.getEnvironment();
        esAdvanceService = (EsAdvanceService) context.getBean("esAdvanceService");
    }


    @Override
    public void start() throws Exception {
        System.out.println("start mainverticle!");
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", env.getProperty("kafka.bootstrap.servers"));
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "GroupA");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");

        // use consumer for interacting with Apache Kafka
        KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, config);


// subscribe to several topics
//        Set<String> topics = new HashSet<>();
//        topics.add("ma-test-topic");
//        topics.add("topic2");
//        topics.add("topic3");
//        consumer.subscribe(topics);

        // or just subscribe to a single topic
        consumer.subscribe("facebook",ar -> {
            if (ar.succeeded()) {
                System.out.println("subscribed");
            } else {
                System.out.println("Could not subscribe " + ar.cause().getMessage());
            }
        });
        consumer.handler(record -> {
            System.out.println("============================");
            System.out.println("Processing key=" + record.key() + ",value=" + record.value() +
                ",partition=" + record.partition() + ",offset=" + record.offset());
            Product product= new Product();
            try{
                JSONObject jsonObject= JSON.parseObject(record.value());
                product.setDescription(jsonObject.getString("spam")+":"+jsonObject.getInteger("parrot"));

            }catch(Exception e){
                System.out.println("is not jsonstring");
            }

        });
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
    }
}
