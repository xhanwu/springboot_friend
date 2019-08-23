<template>
	<view>  
		<view class="avatar_view">
			<image v-if="imgSelected" class="avatar" :src="imgFile.path"></image>
			<image v-else class="avatar" :src="userInfo.avatar"></image>
		</view>
		<view class="uni-padding-wrap uni-common-mt" style="padding: 20upx;">
			<button type="primary" @tap="chooseImage()">请选择图片</button>
		</view>
		<view class="uni-padding-wrap uni-common-mt" style="padding: 20upx;">
			<button type="primary" @tap="saveAvatar()">确定保存</button>
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
				imgSelected: false,
				imgFile: {}
			};
		},
		computed: {
			...mapGetters(['hasLogin','userInfo'])
		},
		methods:{
			chooseImage(){
				let that = this;
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function (res) {
						console.log(JSON.stringify(res.tempFilePaths));
						that.imgSelected = true;
						// uni.previewImage({
						// 	urls: res.tempFilePaths
						// })
						that.imgFile = res.tempFiles[0];
						console.log("hyn " + res.tempFiles[0])
						console.log("hyn " +  that.imgSelected)
					}
				})
			},
			saveAvatar(){
				this.userInfo.avatar = this.imgFile;
			}
		}
	}
</script>

<style scoped>
.avatar_view{
	display: flex;
	justify-content: center;
	padding: 20upx;
}
.avatar {
	width: 200upx;
	height: 200upx;
	border-radius: 50%;
}
</style>
