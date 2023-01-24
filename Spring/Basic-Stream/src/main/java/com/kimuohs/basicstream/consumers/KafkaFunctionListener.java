package com.kimuohs.basicstream.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.kimuohs.basicstream.models.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaFunctionListener {

	@KafkaListener(topics = "spring2", groupId = "spring-group-1")
	void listener(ConsumerRecord<String, String> record) {
		log.info("Received {}: {}", record.key(), record.value());
	}

	@KafkaListener(topics = { "spring1", "spring2" }, groupId = "spring-group-2")
	void multiTopicListener(@Payload ConsumerRecord<String, String> record) {
		log.info("Multi Topic Listener [{}]: {}", record.topic(), record.value());
	}

	@KafkaListener(groupId = "spring-group-3", topicPartitions = @TopicPartition(topic = "spring1", partitionOffsets = {
			@PartitionOffset(partition = "0", initialOffset = "0") }))
	void listenToPartitionWithOffset(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
			@Header(KafkaHeaders.OFFSET) int offset) {
		log.info("Received message [{}] from partition-{} with offset-{}", message, partition, offset);
	}

	@KafkaListener(topics = "spring2", groupId = "spring-reply")
	@SendTo("spring1")
	String listenAndReply(ConsumerRecord<String, String> record) {
		log.info("Listen and Reply {}: {}", record.key(), record.value());
		return "Returned " + record.value();
	}

	@KafkaListener(topics = "spring3", groupId = "spring-user", containerFactory = "userKafkaListenerContainerFactory")
	void userListener(User user) {
		log.info("User Listener: [{}]", user);
	}

}
