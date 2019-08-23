<template>
	<view style="overflow: scroll;">
		<view class="msg-detail-top">
			<view class="msg-detail-top-belong">
				<image class="msg-detail-top-belong_head" :src="msg.image_url" ></image>
				<text class="msg-detail-top-belong_src"> {{ msg.source }} </text>
				<button class="msg-detail-top-belong_button" size="mini" :class="computedIsActive" @click="changeFollow"> {{ followText }} </button>
				<image class="msg-detail-top-belong_collect" :src="computedIsCollect" @click="changeCollect"></image>
			</view>
			<view class="msg-detail-top-content">
				<text class="msg-detail-top-content_text"> {{ msg.title }} </text>
			</view>
			<view class="msg-detail-top-circle">
				<image class="msg-detail-top-circle_img" :src="msg.circle_img"></image>
				<text class="msg-detail-top-circle_text"> {{ msg.circle_name }} </text>
				<button class="msg-detail-top-circle_join" size="mini" @click="changeJoin"> {{ joinText }} </button>
			</view>
			<view class="msg-detail-top-border"></view>
			<view class="msg-detail-top-modify">
				<view class="msg-detail-top-modify_module" @click="clickGood(msg)">
					<image class="msg-detail-top-modify_icon" src="/static/img/home/msg/good.png" ></image>
					<text class="msg-detail-top-modify_count">{{ msg.good_count }}</text>
				</view>
				<view class="msg-detail-top-modify_module" @click="clickComment(msg)">
					<image class="msg-detail-top-modify_icon" src="/static/img/home/msg/comment.png"></image>
					<text class="msg-detail-top-modify_count">{{msg.comments_count}}</text>
				</view>
				<view class="msg-detail-top-modify_module" @click="clickForward(msg)">
					<image class="msg-detail-top-modify_icon" src="/static/img/home/msg/forward.png"></image>
					<text class="msg-detail-top-modify_count">{{ msg.forward_count }}</text>
				</view>			
				<image src="/static/img/home/msg/more.png" style="position: absolute; right: 20upx; height: 40upx; width: 40upx;" mode=""></image>
			</view>
		</view>
		
		<view class="msg-detail-center">
			<view class="msg-detail-center-title">
				<text class="msg-detail-center-title_text" style="margin: 20upx; text-align: 25upx; position: relative;">相关推荐</text>
			</view>
			<view class="msg-detail-center-content">
				<!-- 滑动框 -->
			</view>
		</view>
		
		<view class="msg-detail-hots">
			<view class="msg-detail-hots-title">
				<text class="msg-detail-hots-title_text" style="margin: 20upx; text-align: 25upx; position: relative;">热门评论</text>
			</view>
		</view>
		
		<view class="msg-detail-comments">
			<view class="msg-detail-comments-title">
				<text class="msg-detail-comments-title_text" style="margin: 20upx; text-align: 25upx; position: relative;">所有评论</text>
			</view>
			<view class="msg-detail-comments-content">
				<dynamic-comment class="" :msg="msg" ></dynamic-comment>
			</view>
		</view>
	</view>
    
</template>

<script>
	const FAIL_CONTENT = '<p>获取信息失败</p>';
	import dynamicComment from '../../../components/dynamic-comment.vue';
	export default {
		data() {
			return {
				msg: {},
				content: '',
				isFollow: false,
				followText: '关注',
				isCollect: false,
				joinText: '加入',
				isJoin: false
			}
		},
		components: {
			dynamicComment
		},
		computed: {
			computedIsActive() {
				return this.isFollow ? 'followed' : ''
			},
			computedIsCollect() {
				if(this.isCollect){
					return '/static/img/home/msg-detail/collected.png'
				} else {
					return '/static/img/home/msg-detail/uncollect.png'
				}
			}
		},
		onShareAppMessage() {
			return {
				title: this.msg.title,
				path: '/pages/home/detail/detail?query=' + JSON.stringify(this.msg)
			}
		},
		onLoad(event) {
			// 目前在某些平台参数会被主动 decode，暂时这样处理。
			try {
				this.msg = JSON.parse(decodeURIComponent(event.query));
			} catch (error) {
				this.msg = JSON.parse(event.query);
			}

			this.getDetail();
			uni.setNavigationBarTitle({
				title: '动态详情'
			});
		},
		methods: {
			getDetail() {
				uni.request({
					url: 'https://unidemo.dcloud.net.cn/api/news/36kr/' + this.msg.post_id,
					success: (result) => {
						if (result.statusCode == 200) {
							this.content = result.data.content;
						} else {
							this.content = FAIL_CONTENT;
						}
					}
				});
			},
			changeFollow() {
				if (this.isFollow){
					this.followText = '关注';
					console.log('unfollow');
				} else {
					this.followText = '已关注';
					console.log('follow');
				}
				this.isFollow = !(this.isFollow);
				//isFollow改变需要传给后台
			},
			changeCollect() {
				if (this.isCollect) {
					//需要加一个弹框确定取消收藏
					console.log('cancel collect');
				} else {
					console.log('collect');
				}
                this.isCollect = !(this.isCollect);
			},
			changeJoin() {
				if (this.isJoin){
					console.log('go to see');
				} else {
					this.joinText = '去看看';
					this.isJoin = true;
				}
				
			},
			clickGood(detail) {
				console.log('click good');
			},
			clickComment(detail) {
				console.log('click comment');
			},
			clickForward(detail) {
				console.log('click forward');
			}
		}
	}
</script>

<style lang="scss" scoped>
    page {
		background: rgb(230, 230, 230);
		height: 100%;
	}
	.msg-detail-top {
		background: rgb(255, 255, 255);
	}
	//belong部分
	.msg-detail-top-belong {
		height: 100upx;
		width: 100%;
		background: white;
		white-space: nowrap;
		display: flex;
		flex: 1;
		flex-direction: row;
	}
	.msg-detail-top-belong_head {
		height: 70upx;
		width: 70upx;
		position:relative;
		margin: 15upx;
	}
	.msg-detail-top-belong_src {
		width: auto;
		display: inline-block;
		font-size: 30upx;
		margin-top: 15upx;
	}
	.msg-detail-top-belong_button {
		height: 60upx;
		width: 130upx;
		position: absolute;
		top: 20upx;
		right: 100upx;
		font-size: 28upx;
		text-align: center;
		color: white;
		padding: 0;
		background-color: cornflowerblue;
		border-radius: 50upx;
	}
	.followed {
		background-color: grey;
	}
	.msg-detail-top-belong_collect {
		height: 60upx;
		width: 60upx;
		position: absolute;
		top: 20upx;
		right: 20upx;
	}
	//content部分
	.msg-detail-top-content {
		width: 100%;
		height: auto;
		overflow: auto;
	}
	.msg-detail-top-content_text {
		width: auto;
		height: auto;
		float: left;
		margin: 20upx;
	}
	//circle部分
	.msg-detail-top-circle {
		height: 100upx;
		width: calc(100%-40upx);
		margin: 20upx;
		background: white;
		white-space: nowrap;
		display: flex;
		flex: 1;
		flex-direction: row;
		background: rgb(245, 245, 245);
	}
	.msg-detail-top-circle_img {
		height: 70upx;
		width: 70upx;
		position:relative;
		margin: 15upx;	
	}
	.msg-detail-top-circle_text {
		width: auto;
		display: inline-block;
		font-size: 30upx;
		margin-top: 15upx;
	}
	.msg-detail-top-circle_join {
		height: 60upx;
		width: 130upx;
		position:relative;
		top: 20upx;
        margin-right: 10upx;
		font-size: 28upx;
		text-align: center;
		color: cornflowerblue;
		padding: 0;
		background-color: white;
		border-radius: 50upx;	
		border: solid 1upx cornflowerblue;
	}
	.msg-detail-top-border {
		margin-left: 20upx;
		margin-right: 20upx;
		width: calc(100%-40upx);
		border: solid 2upx rgb(245, 245, 245);
	}
	//评论部分
	.msg-detail-top-modify {
		width: 100%;
		height: auto;
		white-space: nowrap;
		display: flex;
		flex: 1;
		flex-direction: row;
		padding: 15upx;
	}
	.msg-detail-top-modify_module {
		width: 200upx;
		height: auto;
		display:inline-block;
		display: flex;
		flex: 1;
		flex-direction: row;
	}
	.msg-detail-top-modify_icon {
		height: 30upx;
		width: 30upx;
	}
	.msg-detail-top-modify_count {
		height: 30upx;
		width: auto;
		text-emphasis-color: #000000;
	}
	//推荐部分
	.msg-detail-center {
		height: 200upx;
		width: 100%;
		border: solid 2upx rgb(245, 245, 245);
		background: rgb(255, 255, 255);
	}
	//热门评论
	.msg-detail-hots {
		margin-top: 10upx; 
		height: 300upx;
		background: rgb(255, 255, 255);
	}
	//所有评论
	.msg-detail-comments {
		margin-top: 10upx; 
		height: 500upx;
		background: rgb(255, 255, 255);
	}
</style>
