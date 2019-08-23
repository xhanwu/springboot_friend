<template>
	<view>
		<view class="edit_box">
			<input v-model="content" @input="inputValue" class="is-input1" />
		</view>
		<view class="uni-padding-wrap uni-common-mt save_button">
			<button type="primary" @tap="saveContent()">确定保存</button>
		</view>
	</view>
</template>

<script>
	import {
	    mapGetters,
	} from 'vuex'
	export default {
		data(){
			return {
				name: '',
				content: '',
				hit: ''
			};
		},
		computed: {
			...mapGetters(['hasLogin','userInfo'])
		},
		methods: {
			inputValue(e) {
				console.log(e.target.value);
			},
			saveContent(){
				console.log("save " + this.content);
			}
		},
		onLoad(options) {
			console.log("onload " + options.name + " content " + options.content + " hit " + options.hit);
			this.name = options.name;
			this.content = options.content;
			this.hit = options.hit;
			uni.setNavigationBarTitle({
				title: this.name + "编辑"
			});
		},
	}
</script>

<style scoped>
	.save_button {
		width: 100%;
	}
	
	.edit_box{
		font-size: 2em;
		border: 1px solid #000000;
		border-radius: 4px;
		margin: 20upx;
		background-color: #FFFFFF;
		max-height: 45px;
		display: flex;
		padding: 3px;
	}
</style>
