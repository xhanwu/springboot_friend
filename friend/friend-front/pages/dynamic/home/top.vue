<template>
	<view id="content">
		<image id="logo" v-bind:src="toplogoimg" @tap="jumpnew"></image>
		<text id="title">{{title}}</text>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: '动态',
				toplogoimg:'/static/img/dynamic/ic_navbar_add_friends.png'
			}
		},
		onLoad() {

		},
		methods: {
			jumpnew:function(){
				uni.navigateTo({
				     url:'/pages/dynamic/findfriend/find'
				});
			}

		}
	}
</script>

<style scoped>
	#content {
		height: 108px;
		text-align: left;
		margin-top: 0px;
		padding-top: 0px;
	}

	#logo {
		height: 40px;
		width: 40px;
		margin-top: 0px;
		vertical-align: middle;
		
        padding: 2px 0; /* 撑开高度 */
	}

	#title {
		font-size: 25px;
		height:40px;
		color: #8f8f94;
		
		display: inline-block;
        vertical-align: middle;
		
        padding: 2px 0; /* 撑开高度 */
		margin-left: 35%;
		margin-top: 0px;

	}
</style>
