package com.kimuohs.basicstream.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kimuohs.basicstream.models.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyKafkaProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final KafkaTemplate<String, User> userKafkaTemplate;

	public void sendMessage(String message, String topic) {
		kafkaTemplate.send(topic, message);
	}

	public void sendMessage(String message, String key, String topic) {
		kafkaTemplate.send(topic, key, message);
	}

	public void sendMessageWithCallback(String message, String topic) {
		kafkaTemplate.send(topic, message).whenComplete((result, ex) -> {
			if (ex != null) {
				log.warn("Unable to deliver message [{}]. {}", message, ex.getMessage());
			} else {
				log.info("Message [{}] delivered with offset {}", message, result.getRecordMetadata().offset());
			}
		});
	}

	public void sendUser(User user, String topic) {
		userKafkaTemplate.send(topic, user);
	}

	public void sendUser(String key, User user, String topic) {
		userKafkaTemplate.send(topic, key, user);
	}
}
