package com.kimuohs.basicstream.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TopicConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
//	@Bean
//	KafkaAdmin admin() {
//	    Map<String, Object> configs = new HashMap<>();
//	    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//	    return new KafkaAdmin(configs);
//	}

	@Bean
	NewTopic topic1() {
		log.info("Spring 1 Created");
	    return TopicBuilder.name("spring1")
	            .partitions(10)
	            .replicas(3)
	            .build();
	}
	
	@Bean
	NewTopic topic2() {
		log.info("Spring 2 Created");
	    return TopicBuilder.name("spring2")
	            .partitions(2)
	            .replicas(2)
	            .build();
	}
	
	@Bean
	NewTopic topic3() {
		log.info("Spring 3 Created");
	    return TopicBuilder.name("spring3")
	            .partitions(3)
	            .replicas(3)
	            .build();
	}
}
