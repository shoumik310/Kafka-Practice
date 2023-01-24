package com.kimuohs.basicstream.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kimuohs.basicstream.models.User;
import com.kimuohs.basicstream.producers.MyKafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/produce")
public class ProducerController {

	private final MyKafkaProducer producer;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	String hello() {
		return "Hello I am Running";
	}

	@PostMapping
	String sendMessage(@RequestParam(required = false) String key, @RequestParam(required = true) String value,
			@RequestParam(required = true) String topic) {
		if (key == null) {
			producer.sendMessage(value, topic);
			log.info("Message Created with value: " + value + " to topic: " + topic);
			return "Message Created with value: " + value + " to topic: " + topic;
		}

		producer.sendMessage(value, key, topic);
		log.info("Message Created with key: " + key + " and value: " + value + " to topic: " + topic);
		return "Message Created with key: " + key + " and value: " + value + " to topic: " + topic;

	}

	@PostMapping("/user")
	String sendUser(@Validated @RequestBody User user, @RequestParam(required = false) String key) {

		String topic = "spring3";
		if (key == null) {
			producer.sendUser(user, topic);
			log.info("User Message Created with value: " + user + " to topic: " + topic);
			return "User Message Created with value: " + user + " to topic: " + topic;
		}

		producer.sendUser(key, user, topic);
		log.info("User Message Created with key: " + key + " and value: " + user + " to topic: " + topic);
		return "User Message Created with key: " + key + " and value: " + user + " to topic: " + topic;
	}
}
