<template>
	<view>
		<view class="list" v-for="(list,list_i) in settingList" :key="list_i">
			<view class="li" v-for="(li,li_i) in list" @tap="toPage(list_i,li_i)" v-bind:class="{'noborder':li_i==list.length-1}"  hover-class="hover" :key="li.name" >
				<view class="icon" v-show="li.icon != ''"><image :src="'../../static/img/mine/'+li.icon"></image></view>
				<view class="text">{{li.name}}</view>
				<image class="to" src="../../static/img/to.png"></image>
			</view>
		</view>
		<view v-if="hasLogin" class="btn_logout" @tap="bindLogout">
			<button type="warn">退出登录</button>
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
				settingList:[
					[
						{name:'推送通知设置',icon:''},
						{name:'浏览偏好设置',icon:''},
						{name:'隐私设置',icon:''},
					],
					[
						{name:'加入即刻',icon:''},
						{name:'用户协议',icon:''}
					],
					[
						{name:'清理缓存',icon:'circle.png'},
						{name:'把即刻推荐给朋友',icon:'favorite.png'},
						{name:'给即刻评分',icon:'favorite.png'},
					]
				],
			}
		},
		computed: {
			...mapGetters(['hasLogin','userInfo'])
		},
		methods: {
			...mapActions(['logout']),
			bindLogout() {
				console.log("Logout")
				this.logout()
				uni.navigateBack()
			},
		}
	}
</script>

<style lang="scss" scoped>
.list{
	width:100%;border-bottom:solid  #f1f1f1;
	background-color: #FFFFFF;
	margin-top: 40upx;
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

.btn_logout{
	margin-top: 40upx;
}
</style>
