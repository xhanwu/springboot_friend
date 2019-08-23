<template>
	<view>
		<view class="bg-img bg-mask flex align-center cover">
			<view class="padding-xl text-white">
				<image :src="userInfo.avatar" class="avatar"></image>
				<view class="padding-xs text-xxl text-bold" style="text-align: center;">
					{{userInfo.nickname}}
				</view>
			</view>
			<view style="width: 100%;">
				<button class="cu-btn round lines-white" style="float: right; margin-right:40upx;" @tap="navTo('/pages/mine/user_edit')">
					<text class="lg text-gray cuIcon-edit" style="margin-right: 10upx;"></text>
					编辑
				</button>
			</view>
		</view>
		<view class="profile_bar">
			<text class="icon_tag cuIcon-male" v-if="userInfo.sex == 1"></text>
			<text class="icon_tag cuIcon-female" v-else-if="userInfo.sex == 2"></text>
			<text class="profile_tag" v-for="(profile, i) in profile_tag_list" :key="i">
				{{profile.tag}}
			</text>
		</view>
		
		<view class="info_bar">
			<view class="grid margin-bottom text-center col-4">
				<view class="label" v-for="(info, info_i) in info_list" :key="info_i">
					{{info.label}}
					<view class="number">{{info.number}}</view>
				</view>
			</view>
		</view>

		<view class="scroll_content">
			<scroll-view scroll-x class="bg-white nav text-center">
				<view class="cu-item"  style="margin-left: 40upx; margin-right: 40upx;" :class="index==TabCur?'text-blue cur':''" v-for="(item,index) in 2" :key="index" @tap="tabSelect" :data-id="index">
					{{TabTitle[index]}}
				</view>
			</scroll-view>
			<scroll-view scroll-y v-if="TabCur == 1">
				<view class="profile_title">
					<image class="icon" src="../../static/img/mine/profile.png"></image>
					<view class="title">基本信息</view>
				</view>
				<view class="profile_detail">
					<view class="label">性别</view>
					<view class="content">{{userInfo.sex == 1 ? "男" : userInfo.sex == 2 ? "女" : "未知"}}</view>
				</view>
				<view class="profile_detail" v-if="userInfo.constellation != ''">
					<view class="label">星座</view>
					<view class="content"> {{userInfo.constellation}}</view>
				</view>
				<view class="profile_detail"  v-if="userInfo.pos_province != ''">
					<view class="label">所在地</view>
					<view class="content"> {{userInfo.pos_province + '-' + userInfo.pos_city}}</view>
				</view>
				
				<view class="profile_title" v-if="userInfo.school != ''">
					<image class="icon" src="../../static/img/mine/school.png"></image>
					<view class="title">学校</view>
				</view>
				<view class="profile_detail"  v-if="userInfo.school != ''">
					<view class="content_no_label">{{userInfo.school}}|{{userInfo.major}}</view>
				</view>
				
				<view class="profile_title" v-if="userInfo.industry != ''">
					<image class="icon" src="../../static/img/mine/industry.png"></image>
					<view class="title">行业</view>
				</view>
				<view class="profile_detail"  v-if="userInfo.industry != ''">
					<view class="content_no_label">{{userInfo.industry}}</view>
				</view>
			</scroll-view>
			<scroll-view scroll-y v-else>
				<view>动态</view>
			</scroll-view>
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
				TabCur: 0,
				scrollLeft: 0,
				TabTitle: ["动态", "档案"],
				info_list: [{label: "创建的圈子", number: 0},
					   {label: "加入的圈子", number: 1},
					   {label: "关注我的人", number: 2},
					   {label: "我关注的人", number: 3}],
				profile_tag_list: []
			};
		},
		computed: {
			...mapGetters(['hasLogin','userInfo'])
		},
		onLoad() {
			let list = [
				{tag: this.userInfo.pos_city},
				{tag: this.userInfo.industry},
				{tag: this.userInfo.school},
				{tag: this.userInfo.constellation}
			];
			
			for(var i in list){
				this.profile_tag_list.push(list[i]);
				console.log(this.profile_tag_list[i].tag);
			}
			
		},
		methods: {
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
			},
			navTo(url){
				uni.navigateTo({  
					url
				})  
			}
		}
	}
</script>

<style lang="scss" scoped>
.cover{
	background-image: url('../../static/img/mine/cover_background.jpg');
	height: 50vh;
}
.scroll_content{
	background-color: white;
	height: 95vh;
}

.avatar {
	width: 200upx;
	height: 200upx;
	border-radius: 50%;
}
.info_bar{
	position: relative;
	margin-top: -102upx;
	color: white;
	.lable{
		display:flex;align-items:center;justify-content:center;flex-flow:wrap;width:100%;height:100%;	
	}
	.number{
		display:flex;align-items:center;justify-content:center;flex-flow:wrap;width:100%;height:100%;
	}
}

.profile_bar{
	position: absolute;
	margin-top: -240upx;
	.profile_tag{
		padding: 12upx;
		margin-right: 10upx;
		//背景虚化
		background: hsla(0, 0%, 100%, .25) border-box;
		border-radius: 4em;
		font-size: xx-small;
		color: white;
	}
	.icon_tag{
		margin-left: 60upx; 
		margin-right: 10upx;
		padding-top: 12upx;
		padding-bottom: 12upx;
		padding-left: 20upx; 
		padding-right: 20upx;
		//背景虚化
		background: hsla(0, 0%, 100%, .25) border-box;
		border-radius: 4em;
		font-size: xx-small;
		color: white;
	}
}

.profile_title{
	background-color: #EFF3F6;
	height: 80upx;
	display: flex;
	flex-direction: row;
	.icon{
		width: 50upx;
		height: 50upx;
		margin-top: 16upx;
		margin-left: 50upx;
	}
	.title{
		height: auto;
		align-self: center;
		margin-left: 20upx;
		font-weight: bold;
	}
}
.profile_detail{
	display: flex;
	flex-direction: row;
	.label{
		background-color: white;
		height: 80upx;
		width: 90upx;
		margin-left: 118upx;
		padding-top: 16upx;
	}
	.content{
		background-color: white;
		height: 80upx;
		margin-left: 50upx;
		padding-top: 16upx;
		color: #8A8A8A;
	}
	.content_no_label{
		background-color: white;
		height: 80upx;
		margin-left: 118upx;
		padding-top: 16upx;
		color: #8A8A8A;
	}
}
</style>
