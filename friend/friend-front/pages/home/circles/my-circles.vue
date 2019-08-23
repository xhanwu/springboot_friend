<template>
	<view style="margin-top: 10upx; background-color: #FFFFFF;" >
		<view class="my-circles-stick">
			<view class="my-circles-stick_top">
				<text style="position: relative; left: 10upx;">置顶到首页({{}}/6)</text>
			</view>
			<view class="my-circles-stick_content">
				<text style="position: relative; margin: 50upx; text-align: center;  width: 100%;">可以将关心的圈子置顶到首页哦</text>
			</view>
		</view>
		<view class="my-circles-all">
			<text>我的圈子</text>
		</view>
	</view>
</template>

<script>
	export default {
		data(){
			return{
				
			}
		},
		onLoad(event) {
			uni.setNavigationBarTitle({
				title: '我的圈子'
			});
		},
	}
</script>

<style>
	page {
		background: rgb(230, 230, 230);
	}
	.my-circles-stick {
		height: 200upx;
		width: 100%;
	}
	.my-circles-stick_top {
		height: 50upx;
		width: 100%;
	}
	.my-circles-stick_content {
		height: 150upx;
		width: 100%;
		display: flex;
	    justify-content: center;
		align-content: center;
	}
</style>
