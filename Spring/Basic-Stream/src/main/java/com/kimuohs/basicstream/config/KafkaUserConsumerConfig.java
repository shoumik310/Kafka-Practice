package com.kimuohs.basicstream.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kimuohs.basicstream.models.User;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaUserConsumerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Bean
	ConsumerFactory<String, User> userConsumerFactory(){
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "spring-user");
	    
		return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(), new JsonDeserializer<>(User.class));
	}
	
	@Bean 
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, User>> userKafkaListenerContainerFactory(){
		var factory = new ConcurrentKafkaListenerContainerFactory<String,User>();
		factory.setConsumerFactory(userConsumerFactory());
		return factory;
	}
}
