package com.kimuohs.basicstream.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.kimuohs.basicstream.models.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class KafkaUserProducerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	ProducerFactory<String, User> userProducerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	KafkaTemplate<String, User> userKafkaTemplate() {
		var kafkaTemplate = new KafkaTemplate<>(userProducerFactory());

//		kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
//			@Override
//			public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
//
//				log.info("ACK from ProducerListener message: {} offset:  {}", producerRecord.value(),
//						recordMetadata.offset());
//			}
//			
//			@Override
//			public void onError(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata,
//					Exception exception) {
//				// TODO Auto-generated method stub
//				ProducerListener.super.onError(producerRecord, recordMetadata, exception);
//			}
//		});
		return kafkaTemplate;
	}

}
