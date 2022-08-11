<template>
<div v-if="streamManager">
	<mission-video :stream-manager="streamManager" />
	<div>
		<p>{{ nickname }}</p>
	</div>
</div>
</template>

<script>
import MissionVideo from './MissionVideo';


export default {
	name: 'UserVideo',

	components: {
		MissionVideo,
	},

	props: {
		streamManager: Object,
		nickname: Object,
	},

	computed: {	
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>
