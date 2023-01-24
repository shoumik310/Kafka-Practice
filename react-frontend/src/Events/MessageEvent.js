import React from 'react';
// import { sendMessage } from '../Kafka/kafkaproducer';

function publishEvent(e) {
	e.preventDefault();
	console.log('Hello');
	// sendMessage('demo', 'a message!');
}

export default function MessageEvent() {
	return (
		<div>
			<form onSubmit={publishEvent}>
				<table>
					<tbody>
						<tr>
							<td>
								<label>Field 1</label>
								<input type='text' name='field1' />
							</td>
						</tr>
						<tr>
							<td>
								<label>Field 2</label>
								<input type='text' name='field2' />
							</td>
							<td>
								<input type='submit' />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	);
}
