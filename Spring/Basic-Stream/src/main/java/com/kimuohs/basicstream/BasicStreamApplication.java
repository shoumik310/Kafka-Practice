package com.kimuohs.basicstream;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import com.kimuohs.basicstream.producers.MyKafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@EnableKafka
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class BasicStreamApplication {

	private final MyKafkaProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(BasicStreamApplication.class, args);
	}

//	@Bean
	CommandLineRunner commandLineRunner() {
		String topic = "spring2";

		String uuid = UUID.randomUUID().toString();
		String message = "Hello World: " + uuid;

		log.info("Writing message with {} to {}", uuid, topic);
		producer.sendMessage("Hello World: " + uuid, topic);

		uuid = UUID.randomUUID().toString();
		message = "Hello World: " + uuid;

		log.info("Writing message with key: {} and value:{} to {}", uuid, message, topic);
		producer.sendMessage("Hello World: " + uuid, uuid, topic);
		return null;
	}
}

// https://reflectoring.io/spring-boot-kafka/
