<template>
<div v-if="streamManager">
	<ov-video :stream-manager="streamManager"
	class="mx-2 my-2 w3-container border border-secondary w3-col m6"
    id="video-container"/>
	<div>
		<p>{{ clientData }}</p>
	</div>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
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
