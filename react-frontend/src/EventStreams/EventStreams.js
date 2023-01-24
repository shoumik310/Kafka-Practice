import React from 'react';
import { StreamList } from './StreamList';
import StreamToggleEntity from './StreamToggleEntity';
import { v4 } from 'uuid';

export default function EventStreams() {
	return (
		<div>
			<table>
				<tbody>
					{StreamList.map((stream) => {
						return (
							<tr key={v4()}>
								<td>
									<StreamToggleEntity stream={stream} />
								</td>
							</tr>
						);
					})}
				</tbody>
			</table>
		</div>
	);
}
