package com.kimuohs.basicstream.consumers;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@KafkaListener(id = "class-listener", topics = { "spring2", "spring3" })
public class KafkaClassListener {

	@KafkaHandler
	void listen(String message) {
		log.info("Kafka Listener[String]: {}", message);
	}

	@KafkaHandler(isDefault = true)
	void listenDefault(Object object) {
		log.info("Kafka Listener[Object]: {}", object.toString());
	}
}
