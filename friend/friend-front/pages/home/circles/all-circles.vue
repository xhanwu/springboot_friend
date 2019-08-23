<template>
	<view style="overflow: hidden;">
		<view class="content" >
			<view class="nav" :style="'height:' +getScreenHeight+'px' ">
				<view class="nav-left" >
					<scroll-view scroll-y :style="'height:'+height+'px'">
						<view 
							class="nav-left-item" 
							:class="[index==categoryActive?'active':'']" 
							id = "nav-left-item_id" 
							v-for="(item,index) in categoryList" :key="index" 
							@click="categoryClickMain(item,index)"
							:style="'height:' +getScreenHeight/15+'px' ">
							{{item.NAME}}
						</view>
					</scroll-view>
				</view>
				<view class="nav-right">
					<scroll-view scroll-y :scroll-top="scrollTop" @scroll="scroll" :style="'height:'+height+'px'" scroll-with-animation >
						<view :id="index==0?'first':''" class="nav-right-item" v-for="(item,index) in subCategoryList" :key="index">
							<view>{{item.NAME}}</view>
						</view>
					</scroll-view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				categoryList:[],
				subCategoryList: [],
				index: 0,
				height:0,
				categoryActive:0,
				scrollTop:0,
				scrollHeight:0,
				allCircles:[
					{ type: '热门推荐' },
					{ type: '搞笑沙雕' },
					{ type: '吃喝玩乐' },
					{ type: '拍拍生活' },
					{ type: '兴趣爱好' },
					{ type: '情绪' },
					{ type: '运动' },
					{ type: '时尚' },
					{ type: '音乐' },
					{ type: '书影' },
					{ type: '娱乐' },
					{ type: '爱豆' },
					{ type: '游戏' },
					{ type: '二次元' },
					{ type: '古风' }
				]
			}
		},
		computed: {
			getScreenHeight(){
				// return document.body.clientHeight;
				var ScreenHeight = uni.getSystemInfoSync().windowHeight;
				return ScreenHeight;
			}
		},
		methods:{
			scroll(e){
				this.scrollHeight = e.detail.scrollHeight;
			},
			categoryClickMain(categroy, index) {
				this.categoryActive = index;
				this.subCategoryList = categroy.subCategoryList;
				this.scrollTop = -this.scrollHeight*index;
			},

		},
		onLoad:function(){
			for(var i=0;i < this.allCircles.length;i++){
				var subList = [];
					for(var j=0;j<30;j++){
						subList.push({"NAME": this.allCircles[i].type + ": 圈子"+j})
					}
				this.categoryList.push({"NAME": this.allCircles[i].type, "subCategoryList":subList});
			}
			this.subCategoryList = this.categoryList[0].subCategoryList;
			// this.getList();
			uni.getSystemInfo({
				success: res => {
					this.height = res.screenHeight;
				}
			})
		},
		// mounted: function () {
		// 	var orderHight = document.body.clientHeight
		// 	console.log(orderHight)
		// 	document.getElementById('nav-left-item_id').style.height = orderHight/15 + 'px'
	 //    }
	}
</script>

<style scoped>
    
	.main {
		height: 100%;
	}
	.nav {
		display: flex;
		width: 100%;
		height: 100%;
		overflow: hidden;
	}
	.nav-left {
		width: 30%;
		display: flex;
		white-space:pre-wrap;

	}
	.nav-left-item {
		border-right: solid 1px #E0E0E0;
		border-bottom: solid 1px #E0E0E0;
		font-size: 30upx;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.nav-right {
		width: 70%;
/* 		padding-top: 22upx; */
	}
	.nav-right-item {
		height: 150upx;
		text-align: center;
		font-size: 28upx;
	}
	.active {
		color: #F24544;
	}
	.content {
/* 		padding-top: var(--status-bar-height); */
		width: 100%;
	}
</style>
