const kafka = require('kafka-node');
const Producer = kafka.Producer;
const client = new kafka.KafkaClient();
const producer = new Producer(client);

producer.on('ready', () => {
	console.log('Producer is ready');
});

producer.on('error', (err) => {
	console.error('Error from producer ', err);
});

export const sendMessage = (topic, message) => {
	const payload = [{ topic, messages: message }];
	producer.send(payload, (err, data) => {
		if (err) {
			console.error(`Error sending message to topic ${topic}: `, err);
		} else {
			console.log(`Successfully sent message to topic ${topic}: `, data);
		}
	});
};
