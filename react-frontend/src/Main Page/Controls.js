import React from 'react';

import EventCreators from '../Events/EventCreators';
import EventStreams from '../EventStreams/EventStreams';

export default function Controls() {
	return (
		<>
			<EventCreators />
			<EventStreams />
		</>
	);
}
