package com.blogsiteapp.admin.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.blogsiteapp.admin.model.BlogDetails;


@Configuration
public class KafkaProducerConfiguration {
	
	@Value("${kafka.server.url}")
	private String kafkaServerUrl;

    @Bean
    public ProducerFactory<String, BlogDetails> producerFactoryCourse() {
        return new DefaultKafkaProducerFactory<String, BlogDetails>(getKafkaConfig());
    }

    @Bean
    public KafkaTemplate<String, BlogDetails> kafkaTemplateCourse() {
        return new KafkaTemplate<>(producerFactoryCourse());
    }
    
    private Map<String, Object> getKafkaConfig(){
    	Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerUrl);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }

}
