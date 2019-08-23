<template>
	<view class="content">
		<view class="my-circles">
			<view class="all-circles" @click="showMyCircles">
				<text class="show-all">我的圈子</text>
				<image class="right-icon" :src="imginfo.rightarrow" ></image>
			</view>

			<scroll-view class="scroll-screen" scroll-x="true" >
				<view class="scroll-circles" v-for="(item, index) in circletype" :key="index">
                     <circle-button class="circle-choice" :circlename="item" ></circle-button>
				</view>	   
			</scroll-view>
		</view>
        <view class="find-more">
			<scroll-view id="tab-bar" class="uni-swiper-tab" scroll-x :scroll-left="scrollLeft">
				<text v-for="(tab, index) in tabBars" :key="tab.ref" :class="['swiper-tab-list',tabIndex==index ? 'active' : '']"
				 :id="tab.ref" :data-current="index" @click="tapTab(index)">{{tab.name}}</text>
			</scroll-view>
			<scroll-view class="list" v-for="(tabItem, idx) in newsList" :key="idx" v-if="tabIndex === idx" scroll-y
			 @scrolltolower="loadMore(idx)">
				<block v-for="(newsItem, newsIndex) in tabItem.data" :key="newsIndex">
					<single-message class="message-one" :msg="newsItem" :tp="1"></single-message>
				</block>
				<view class="uni-tab-bar-loading">
					<view class="loading-more"> {{ loadingText }} </view>
				</view>
			</scroll-view>
        </view>
	</view>
</template>

<script>
	import circleButton from '../../components/circle-button.vue';
	import singleMessage from '../../components/single-message.vue';
	import uniMediaList from '../../components/uni-media-list/uni-media-list.vue';
	import uniLoadMore from '../../components/uni-load-more/uni-load-more.vue';
	import {
		friendlyDate
	} from './common/util.js';
	import {GetDynamic} from '../../api/api.js';
	export default {
		data() {
			return {
				title: 'Hello',
				circletype: [
					{ typename: '更多', img: '/static/img/home/more.png' },
					{ typename: '读书', img: '/static/img/home/circles/book.png' },
					{ typename: '运动', img: '/static/img/home/circles/sports.png'  },
					{ typename: '游戏', img: '/static/img/home/circles/game.png'  },
					{ typename: '程序员', img: '/static/img/home/circles/codeman.png'  },
					{ typename: '情感', img: '/static/img/home/circles/feel.png'  },
					{ typename: '电影', img: '/static/img/home/circles/movies.png'  }
				],
				imginfo: {
					rightarrow: '/static/img/home/rightarrow.png'
				},
				loadingText: {
					contentdown: '上拉加载更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				},
				scrollLeft: 0,
				refreshing: false,
				refreshText: '下拉可以刷新',
				newsList: [],
				tabIndex: 0,
				tabBars: [{
					name: '推荐',
					id: 0,
					ref: 'new'
				}, {
					name: '附近',
					id: 23,
					ref: 'company'
				}, ]
			}
		},
		components: {
			circleButton,
			singleMessage,
			uniMediaList,
			uniLoadMore
		},
		computed: {
			scrollViewHeight() {
				return 'height:' + (uni.getSystemInfoSync().windowHeight) + 'px';
			}
		},
		onLoad: function() {
			// 初始化列表信息
			this.tabBars.forEach((tabBar) => {
				this.newsList.push({
					data: [],
					requestParams: {
						columnId: tabBar.id,
						minId: 0,
						pageSize: 10,
						column: 'id,detail,image_url,source,good_count,comments_count,forward_count,circle_name,circle_img'
					},
					loadingText: '加载中...'
				});
			});
			this.getList();
		},
		methods: {
			getList(action = 1) {
				let activeTab = this.newsList[this.tabIndex];
				activeTab.requestParams.time = new Date().getTime() + '';
				if (action === 1) {
					activeTab.requestParams.minId = 0;
				}
				GetDynamic(activeTab.requestParams).then(res => {
						console.log(res.statusCode);
						console.log(res.data.minId);
						if (res.statusCode == 200) {
							const data = res.data.dynamic.map((dynamic) => {
								return {
									id: dynamic.id,
									//datetime: friendlyDate(new Date(news.published_at.replace(/\-/g, '/')).getTime()),
									detail: dynamic.detail,
									image_url: dynamic.userhead,
									source: dynamic.username,
									good_count: dynamic.good_num,
									comments_count: dynamic.count_num,
									forward_count: dynamic.forward_num,
									circle_name: dynamic.circle,
									circle_img: dynamic.circlepicture
								};
							});
							if (action === 1) {
								activeTab.data = data;
								this.refreshing = false;
							} else {
								data.forEach((dynamic) => {
									activeTab.data.push(dynamic);
								});
							}
							activeTab.requestParams.minId = data[data.length - 1].id;
						}
				}).catch(err => {
					console.log("get dynamic error");
					console.log(err.errMsg);
				});
				// this.loadingText = '加载中...';
				// uni.request({
				// 	url: 'http://10.92.34.48:7300/mock/5d26e44c19ad5d00200d5d10/example/dynamic_recommend',
				// 	data: activeTab.requestParams,
				// 	success: (result) => {
				// 		console.log(result.statusCode);
				// 		if (result.statusCode == 200) {
				// 			const data = result.data.dynamic.map((dynamic) => {
				// 				return {
				// 					id: dynamic.id,
				// 					//datetime: friendlyDate(new Date(news.published_at.replace(/\-/g, '/')).getTime()),
				// 					detail: dynamic.detail,
				// 					image_url: dynamic.userhead,
				// 					source: dynamic.username,
				// 					good_count: dynamic.good_num,
				// 					comments_count: dynamic.count_num,
				// 					forward_count: dynamic.forward_num,
				// 					circle_name: dynamic.circle,
				// 					circle_img: dynamic.circlepicture
				// 				};
				// 			});
				// 			if (action === 1) {
				// 				activeTab.data = data;
				// 				this.refreshing = false;
				// 			} else {
				// 				data.forEach((dynamic) => {
				// 					activeTab.data.push(dynamic);
				// 				});
				// 			}
				// 			activeTab.requestParams.minId = data[data.length - 1].id;
				// 		}
				// 	}
				// });
			},
			dislike(tabIndex, newsIndex) {
				uni.showModal({
					content: '不感兴趣？',
					success: (res) => {
						if (res.confirm) {
							this.newsList[tabIndex].data.splice(newsIndex, 1);
						}
					}
				})
			},
			showMyCircles() {
				console.log('click my circles');
                uni.navigateTo({url:'/pages/home/circles/my-circles'});
			},
			loadMore() {
				console.log('load more');
				this.getList(2);
			},
			async changeTab(event) {
				let index = event.detail.current;
				if (this.isClickChange) {
					this.tabIndex = index;
					this.isClickChange = false;
					return;
				}
				let tabBar = await this.getElSize('tab-bar');
				let tabBarScrollLeft = tabBar.scrollLeft;
				let width = 0;
			
				for (let i = 0; i < index; i++) {
					let result = await this.getElSize(this.tabBars[i].ref);
					width += result.width;
				}
				let winWidth = uni.getSystemInfoSync().windowWidth,
					nowElement = await this.getElSize(this.tabBars[index].ref),
					nowWidth = nowElement.width;
				if (width + nowWidth - tabBarScrollLeft > winWidth) {
					this.scrollLeft = width + nowWidth - winWidth;
				}
				if (width < tabBarScrollLeft) {
					this.scrollLeft = width;
				}
				this.isClickChange = false;
				this.tabIndex = index;
			
				// 首次切换后加载数据
				const activeTab = this.newsList[this.tabIndex];
				if (activeTab.data.length === 0) {
					this.getList();
				}
			},
			getNodeSize(node) {
				return new Promise((resolve, reject) => {
					dom.getComponentRect(node, (result) => {
						resolve(result.size);
					});
				});
			},
			onRefresh(event) {
				this.refreshText = '正在刷新...';
				this.refreshing = true;
				this.getList();
			},
			getElSize(id) { //得到元素的size
				return new Promise((res, rej) => {
					uni.createSelectorQuery().select('#' + id).fields({
						size: true,
						scrollOffset: true
					}, (data) => {
						res(data);
					}).exec();
				});
			},
			async tapTab(index) { 
				if (this.tabIndex === index) {
					return false;
				} else {
					console.log('ssss')
					this.tabIndex = index;
					// 首次切换后加载数据
					const activeTab = this.newsList[this.tabIndex];
					if (activeTab.data.length === 0) {
						this.getList();
					}
				}
			},
		},
		onNavigationBarSearchInputChanged() {
		    // plus.nativeUI.alert('TBD');
		},
		onNavigationBarSearchInputClicked() {
			console.log('click searchinput');
			uni.navigateTo({url:'/pages/home/search/search-input'});
		},
		onNavigationBarSearchInputConfirmed() {
			//搜索按钮点击事件
		}
	}
</script>

<style>
	page {
		background: rgb(230, 230, 230);
		height: 100%;
		font-size: 11px;
		line-height: 1.8;
	}
	.content {
		height: 100%;
	}
/* 	我的圈子部分 */
    .my-circles {
		height: auto;
		width: 100%;
		background: #FFFFFF;
	}
	.all-circles {
		height: 70upx;
		width: 100%;
	}	
	.show-all {
		text-align: left;
        padding: 5upx;		
		font-size: 35upx;
	}
	.right-icon {
		height: 50upx;
		width:  50upx;
		position: fixed;
		right: 0;
		margin: 5upx;
	}
	.scroll-circles {
		height: 160upx;
		width: 150upx;
		display:inline-block
	}
	.scroll-screen {
        height: auto;
		width: 100%;
/* 		margin-top: 5upx; */
		white-space: nowrap;
	}
	.circle-choice {
        position: relative;
        left: 20upx;
		height: 160upx;
		width: 120upx;
	}

/* 	推荐部分 */
	.find-more {
		margin-top: 10upx;
		width: 100%;
		height: 100%;
		display: flex;
		flex: 1;
		flex-direction: column;
		overflow: hidden;
	}
	.message-one {
		width: 100%;
/* 		margin-bottom: 15upx; */
	}
	.uni-swiper-tab {
		width: 100%;
		white-space: nowrap;
		height: auto;
		background: #FFFFFF;
	}

	.swiper-tab-list {
		font-size: 30upx;
		height: auto;
		width: 50px;
		display: inline-block;
		position: relative;
		text-align: center;
		color: #555;
		z-index: 999;
	}
	.active {
		width: 50px;
		border-bottom: 3px solid #eeee00;	
		color: #000000;
	}
	.uni-tab-bar-loading {
		text-align: center;
		padding: 20upx;
		font-size: 14px;
		color: #CCCCCC;
	}
    .list {
		z-index: 1;
		width: 750upx;
		height: calc(100% - 100upx);
	}
</style>
