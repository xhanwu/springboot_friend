<template>
	<view class="ccl" @click="showmore">
		<image class="circle-icon " :src="circlename.img"></image>        
        <view class="circle-text">
        	<text>{{ circlename.typename }}</text>
        </view>
	</view>
</template>

<script>
	export default {
		name: 'circle-button',
		props: {
			circlename: {
				type: Object,
				default: function(e) {
				    return {}
				}
			}
		},
		methods: {
			showmore() {
				console.log(this.circlename.typename);
				if(this.circlename.typename == '更多'){
					uni.navigateTo({url:'/pages/home/circles/all-circles'});
				}
				
			}
		}
	}
</script>

<style lang="scss" scoped>
	.ccl {
		width: 100%;
		height: 100%;
	}
	.circle-icon {
		width: 120upx;
		height: 120upx;
	}
	.circle-text {
		font-size: 12px;
		text-align: center;
		width: 120upx;
	}
</style>
