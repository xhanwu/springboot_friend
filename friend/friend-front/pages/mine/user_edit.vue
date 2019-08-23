<template>
	<view>
		<view class="bg-img bg-mask flex align-center cover">
			<view class="padding-xl text-white">
				<image :src="userInfo.avatar" class="avatar" @tap="navTo('image_upload')"></image>
			</view>
			<view style="width: 100%;">
				<button class="btn_change_cover">
					<text class="lg text-white cuIcon-camera" style="margin-right: 10upx;"></text>
					更换封面
				</button>
			</view>
		</view>
		
		<view class="list" v-for="(list,list_i) in menuList" :key="list_i">
			<view class="li" v-for="(li,li_i) in list" @tap="goToInfoEdit(list_i,li_i)" v-bind:class="{'noborder':li_i==list.length-1}"  hover-class="hover" :key="li.name" >
				<text class="lable">{{li.name}}</text>
				<view class="content">
					<text class="text">{{li.content}}</text>
					<text class="hit">{{li.hit}}</text>
				</view>
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
				menuList:[
					[
						{name:'昵称',content:'', hit:'4-24个字符，30天内限修改1次'},
						{name:'签名',content:'介绍一下你自己', hit:''},
					],
					[
						{name:'性别',content:'', hit:''},
						{name:'生日',content:'', hit:'为你保密，只显示星座'},
						{name:'所在地',content:'', hit:''},
					],
					[
						{name:'学校',content:'', hit:'公开'},
						{name:'行业',content:'', hit:'为你推荐最关注的内容'},
					]
				],
			};
		},
		computed: {
			...mapGetters(['hasLogin','userInfo'])
		},
		methods: {
			navTo(url){
				uni.navigateTo({  
					url
				})  
			},
			goToInfoEdit(list_i,li_i){
				console.log("hyn list_i " + list_i);
				console.log("hyn li_i " + li_i);
				let menu = this.menuList[list_i][li_i];
				console.log("menu " + menu.name + " " + menu.content + " " + menu.hit);
				uni.navigateTo({
					url: "info_edit?name=" + menu.name + "&content=" + menu.content + "&hit=" + menu.hit
				})
			}
		},
		onLoad() {
			this.menuList[0][0].content = this.userInfo.nickname;
			this.menuList[1][0].content = this.userInfo.sex == 1 ? "男" : this.userInfo.sex == 2 ? "女" : "未知";
			this.menuList[1][1].content = this.userInfo.birthday + ' ' + this.userInfo.constellation;
			this.menuList[1][2].content = this.userInfo.pos_province + '-' + this.userInfo.pos_city;
			this.menuList[2][0].content = this.userInfo.school + '-' + this.userInfo.major + '-' + this.userInfo.enrollment_year;
			this.menuList[2][1].content = this.userInfo.industry;
		},
	}
</script>

<style lang="scss" scoped>
.cover{
	background-image: url('../../static/img/mine/cover_background.jpg');
	height: 15vh;
}

.avatar {
	width: 140upx;
	height: 140upx;
	border-radius: 50%;
	float: left;
	margin-left: 35vw;
}

.list{
	width:100%;border-bottom:solid 26upx #f1f1f1;
	background-color: #FFFFFF;
	.li{
		width:100%;height:100upx;padding:0 4%;border-bottom:solid 1upx #e7e7e7;display:flex;align-items:center;
	&.noborder{border-bottom:0}
		.lable{width:100upx;}
		.content{display: flex; flex-direction: column}
		.text{padding-left:20upx;width:100%;}
		.hit{padding-left:20upx;width:100%;color:darkgray}
	}
}

.btn_change_cover{
	float: right; 
	margin-right:20upx;
	margin-top: 150upx;
	//背景虚化
	background: hsla(0, 0%, 100%, .25) border-box;
	border-radius: .3em;
	font-size: small;
	color: white;
}
</style>
