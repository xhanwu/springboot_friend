<template>
	<view>
		<view class="header" v-bind:class="{'status':isH5Plus}">
			<view class="setting" @click="navTo('/pages/mine/setting')">
				<image src="../../static/img/mine/settings.png"></image>
			</view>
		</view>
		<view class="user-dashboard" v-if="hasLogin" @click="navTo('/pages/mine/user_details')">
			<view class="userbox">
				<image class="portrait" :src="userInfo.avatar"></image>
				<view class="username">
					{{userInfo.nickname}}
				</view>
				<image class="to" src="../../static/img/common/to.png"></image>
			</view>
		</view> 
		<view class="login-dashboard" v-if="!hasLogin">
			<view class="box">
				<view class="label" v-for="(row,index) in loginTypeList" :key="row.name" hover-class="hover"  @tap="toLoginType(index)">
					<view class="icon">
						<image :src="'../../static/img/common/'+row.icon"></image>
					</view>
					{{row.name}}
				</view>
			</view>
		</view>
		<view class="list" v-for="(list,list_i) in menuList" :key="list_i">
			<view class="li" v-for="(li,li_i) in list" @tap="toPage(list_i,li_i)" v-bind:class="{'noborder':li_i==list.length-1}"  hover-class="hover" :key="li.name" >
				<view class="icon"><image :src="'../../static/img/mine/'+li.icon"></image></view>
				<view class="text">{{li.name}}</view>
				<image class="to" src="../../static/img/common/to.png"></image>
			</view>
		</view>
	</view>
</template>
<script>
	import {
	    mapGetters,
	    mapActions
	} from 'vuex'
	export default {
		data() {
			return {
				//#ifdef APP-PLUS
				isH5Plus:true,
				//#endif
				//#ifndef APP-PLUS
				isH5Plus:false,
				//#endif
				modalName: null,
				// hasLogin: false,
				// userinfo:{},
				loginTypeList:[
					//name-标题 icon-图标
					{name:'微信登录',icon:'wechat.png'},
					{name:'QQ登录',icon:'qq.png'},
					{name:'更多登录方式',icon:'more.png'}
				],
				menuList:[
					[
						{name:'我的圈子',icon:'circle.png'},
						{name:'我的收藏',icon:'favorite.png'},
						{name:'我的通知',icon:'message.png'},
						{name:'帮助与反馈',icon:'help.png'},
					],
					[
						{name:'免流量',icon:'circle.png'},
						{name:'即刻合伙人',icon:'favorite.png'},
					]
				],
			};
		},
		computed: {
			...mapGetters(['hasLogin','userInfo'])
		},
		onLoad() {
			//加载
			this.init();
		},
		onShow() {
			var ll = this.hasLogin;
			console.log("hyn haslogin " + ll + this.userInfo.nickname)
		},
		methods: {
			init() {		
			},
			//用户点击登录类型
			toLoginType(index){
				uni.showToast({title: this.loginTypeList[index].name});
				if(index == 2){
					this.showLoginView();
				}
			},
			//用户点击列表项
			toPage(list_i,li_i){
				uni.showToast({title: this.menuList[list_i][li_i].name});
			},
			/**
			 * 统一跳转接口,拦截未登录路由
			 * navigator标签现在默认没有转场动画，所以用view
			 */
			navTo(url){
				// if(!this.hasLogin){
				// 	url = '/pages/public/login';
				// }
				uni.navigateTo({  
					url
				})  
			},
			showLoginView() {
				uni.navigateTo({
					url: "/pages/login/login"
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
.header{
	&.status{padding-top:var(--status-bar-height);}
	background-color:$uni-header-bg-color;width:100%;height:10vw;padding:0 4%;display:flex;align-items:center;
	position: relative;
	.setting{
		flex-shrink:0;width:6vw;height:6vw;
		position: absolute;
		top: 1.5vw;
		right: 4vw;
		image{width:100%;height:100%}
	}
}
.hover{background-color:#eee}
.login-dashboard{
	background-color:$uni-header-bg-color;width:100%;height:11vw;padding:0 4%;margin-bottom:calc(11vw + 40upx);display:flex;align-items:flex-start;border-radius:0 0 100% 100%;margin-top: 0px;
	.box{
		width:100%;padding:0 1%;height:22vw;background-color:#fefefe;border-radius:24upx;box-shadow:0 0 20upx rgba(0,0,0,0.15);margin-bottom:40upx;display:flex;align-items:center;justify-content:center;
		.label{
			display:flex;align-items:center;justify-content:center;flex-flow:wrap;width:100%;height:100%;color:#666666;font-size:26upx;
			flex-direction: column;
			.icon{
				position:relative;width:10vw;height:12vw;margin:0 1vw;
				image{width:10vw;height:10vw;z-index: 9;}
			}
		}
	}
}

.user-dashboard{
	background-color:$uni-header-bg-color;width:100%;height:11vw;padding:0 4%;margin-bottom:calc(11vw + 40upx);display:flex;align-items:flex-start;border-radius:0 0 100% 100%;margin-top: 0px;
	.userbox{
		width:100%;padding:0 1%;height:22vw;background-color:#fefefe;border-radius:24upx;box-shadow:0 0 20upx rgba(0,0,0,0.15);margin-bottom:40upx;display:flex;align-items:center;justify-content:left;
		.portrait{
			width: 130upx;
			height: 130upx;
			border:5upx solid #fff;
			border-radius: 50%;
			margin-left: 30upx;
		}
		.username{
			margin-left: 30upx;
			word-wrap: break-word;
			font-size: 22px;
			max-width: 70%;
		}
		.to{flex-shrink:0;width:40upx;height:40upx}
	}
}

.list{
	width:100%;border-bottom:solid 26upx #f1f1f1;
	background-color: #FFFFFF;
	.li{
		width:100%;height:100upx;padding:0 4%;border-bottom:solid 1upx #e7e7e7;display:flex;align-items:center;
	&.noborder{border-bottom:0}
		.icon{
			flex-shrink:0;width:50upx;height:50upx;
			image{width:50upx;height:50upx}
		}
		.text{padding-left:20upx;width:100%;color:#666}
		.to{flex-shrink:0;width:40upx;height:40upx}
	}
}
</style>
