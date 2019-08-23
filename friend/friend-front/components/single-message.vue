<template>
	<view class="single-message" @click="bindClick">
		<view class="single-message-circle" @click="goCirecle(msg)" v-if="tp === 1" style="background: rgb(245, 245, 245);">
			<image class="single-message-circle_img" :src="msg.circle_img" ></image>
			<view style="display: flex; flex: 1; flex-direction: column; position: absolute; left: 120upx;">
				<text class="single-message-circle_name">{{ msg.circle_name }}</text>
				<text class="single-message-circle_usernumber" style="color: rgb(0, 255, 0);">1万名即友加入</text>	
			</view>
			<view class="single-message-circle-user">
				<image src="/static/img/home/head/man.png" class="single-message-circle-user_head" style="height: 50upx; width: 50upx; z-index: 3; position: absolute; right: 70upx; border: #ffffff;"></image>
				<image src="/static/img/home/head/woman.png" class="single-message-circle-user_head" style="height: 50upx; width: 50upx; z-index: 2; position: absolute; right: 110upx; border: #ffffff;"></image>
				<image src="/static/img/home/head/man.png" class="single-message-circle-user_head" style="height: 50upx; width: 50upx; z-index: 1; position: absolute; right: 150upx; border: #ffffff;"></image>
			    <image class="single-message-circle-user_righticon" src="/static/img/home/rightarrow.png" ></image>
			</view>
		</view>
		<!-- type == 2 begin -->
		<view class="single-message-circle" v-if="tp === 2">
			<image class="single-message-circle_img" :src="msg.circle_img" ></image>
			<view style="display: flex; flex: 1; flex-direction: column; position: absolute; left: 120upx;">
				<text class="single-message-circle_name">{{ msg.circle_name }}</text>
				<text class="single-message-circle_usernumber" style="color: rgb(200, 200, 200);">05/22</text>
			</view>
		</view>
		<!-- type == 2 End -->
		<view class="single-message-content" @click="goDetail(msg)">
			<text class="single-message-content_detail">{{ msg.detail }}</text>
            <!-- 			加一个view放图片 -->
		</view>
		<view class="single-message-belong" @click="goUser(msg)" v-if="tp === 1">
			<image class="single-message-belong_head" :src="msg.image_url" ></image>
			<view style="width: auto; height: 60upx; position: relative; left:70upx; margin-top: 25upx; white-space: nowrap; display: flex; flex: 1; flex-direction: row;">
				<text class="single-message-belong_name"> {{ msg.source }} </text>
				<text class="single-message-belong_send"> 发布 </text>	
			</view>
		</view>
		<view class="single-message-modify">
			<view class="single-message-modify-module" @click="clickGood(msg)">
				<image class="single-message-modify-module_icon" :src="computedIsClickGood" ></image>
				<text class="single-message-modify-module_count">{{ msg.good_count }}</text>
			</view>
			<view class="single-message-modify-module" @click="clickComment(msg)">
				<image class="single-message-modify-module_icon" src="/static/img/home/msg/comment.png"></image>
				<text class="single-message-modify-module_count">{{ msg.comments_count }}</text>
			</view>
			<view class="single-message-modify-module" @click="clickForward(msg)">
				<image class="single-message-modify-module_icon" src="/static/img/home/msg/forward.png"></image>
				<text class="single-message-modify-module_count">{{ msg.forward_count }}</text>
			</view>			
			<image src="/static/img/home/msg/more.png" style="position: absolute; right: 5upx; height: 40upx; width: 40upx;" mode=""></image>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'message-detail',
		// props: ['messageinfo'],
        props: {
            msg: {
                type: Object,
                default: function(e) {
                    return {}
                }
            },
			tp: {
				type: Number,
				default: function(e) {
					return {}
				}
			}
        },
		data() {
			return {
				isClickGood: false
			}
		},
		computed:{
			computedIsClickGood() {
				if(this.isClickGood){
					return '/static/img/home/msg/gooded.png'
				} else {
					return '/static/img/home/msg/good.png'
				}
			}
		},
		methods: {
		    close(e) {
		        this.$emit('close');
		    },			// 查看详情
			goDetail(detail) {
				console.log('go detail');
				uni.navigateTo({
					url: '/pages/home/detail/message-detail?query=' + encodeURIComponent(JSON.stringify(detail))
				});
			},
			goCirecle(detail) {
				console.log('go circle');
			},
			goUser(detail) {
				console.log('go user');
			},
			clickGood(detail) {
				this.isClickGood = !(this.isClickGood);
				console.log('click good');
			},
			clickComment(detail) {
				console.log('click comment');
			},
			clickForward(detail) {
				console.log('click forward');
			},
		    bindClick() {
		        this.$emit('click');
		    }
		}
	}
</script>

<style lang="scss" scoped>
	.single-message {
		background: rgb(255,255,255);
		border-bottom: solid 15upx rgb(230, 230, 230);
	}
	.single-message-circle {
		height: 100upx;
		width: 100%;
		white-space: nowrap;
		display: flex;
		flex: 1;
		flex-direction: row;
	}
	.single-message-circle_img {
		height: 70upx;
		width: 70upx;
		position:relative;
		margin: 15upx;
	}
	.single-message-circle_name {
		width: auto;
		position: relative;
		text-align: left;
		height: auto;
		font-size: 30upx;
		top: 15upx;
	}
	.single-message-circle_usernumber {
		width: auto;
		height: auto;
		position: relative;
		text-align: left;
		font-size: 10upx;
	}
	.single-message-circle-user {
		position: absolute;
		right: 10upx;
		height: 50upx;
		width:  200upx;
		margin-top: 25upx;	
		margin-right: 10upx;
	}
	.single-message-circle-user_righticon {
		height: 50upx;
		width:  50upx;
		position: absolute; 
		right: 10upx;
	}
	.single-message-content {
		width: 100%;
		height: auto;
		overflow: auto;
	}
	.single-message-content_detail {
		width: auto;
		height: auto;
		margin: 20upx;
	}
	.single-message-belong {
		width: auto;
		height: 90upx;
		margin-left: 15upx;
		margin-right: 15upx;
		display: flex;
		flex-direction: row;
		border-bottom: solid 3upx rgb(245, 245, 245);
	}
	.single-message-belong_head {
		height: 50upx;
		width: 50upx;
		position: absolute;
		margin-top: 20upx;
	}
	.single-message-belong_name {
		width: auto;
		display: inline-block;
		font-size: 20upx;
	}
	.single-message-belong_send {
		position: relative;
		left:10upx;
		display: inline-block;
		color: rgb(200, 200, 200); 
		font-size: 20upx; 
	}
	.single-message-modify {
		width: 100%;
		height: auto;
		white-space: nowrap;
		display: flex;
		flex: 1;
		flex-direction: row;
		padding: 15upx;
	}
	.single-message-modify-module {
		width: 200upx;
		height: auto;
		display:inline-block;
		display: flex;
		flex: 1;
		flex-direction: row;
	}
	.single-message-modify-module_icon {
		height: 30upx;
		width: 30upx;
	}
	.single-message-modify-module_count {
		height: 30upx;
		width: auto;
		text-emphasis-color: #000000;
	}
</style>
