package com.gfg.walletService.config;

import com.gfg.CommonConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public DefaultKafkaConsumerFactory<String, String> consumerConfig(){
        Map<String,Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.KAFKA_BOOTSTRAP_SERVER);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<String,String>(map);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> concurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> listner = new ConcurrentKafkaListenerContainerFactory<>();
        listner.setConsumerFactory(consumerConfig());
        return listner;
    }
}
