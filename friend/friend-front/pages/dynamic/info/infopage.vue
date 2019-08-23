<template>
    <view class="content">
        <view class="uni-list">
            <view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(item,index) in news" :key="index" @tap="opennews" :data-postid="item.post_id">
			   <view class="uni-media-list">
                    <!-- <image class="uni-media-list-logo" :src="item.author_avatar"></image> -->
                    <view class="uni-media-list-body111">
                        <view class="uni-media-list-text-top">{{index}} : {{item}}</view>
                    </view>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                news: []
            };
        },
        onLoad:function(){
            uni.request({
                //url: 'https://unidemo.dcloud.net.cn/api/news',
				url: 'https://www.easy-mock.com/mock/5d36768f0bd750762a136fdc/proinfo',
                method: 'GET',
                data: {},
                success: res => {
                    this.news = res.data.address;
					console.log(res.data.address);
                },
                fail: () => {},
                complete: () => {}
            });
        },
        methods:{
            opennews(e){
                uni.navigateTo({
                    url: '../news/news?postid='+e.currentTarget.dataset.postid
                });
            }
        }
    }
</script>

<style>
    /* .uni-media-list-body{height: auto;}
    .uni-media-list-text-top{line-height: 30px;} */
	.uni-media-list-body111{
		height: 30px;
		font-size:20px;
		}
	.uni-media-list-text-top{
		height: 29px;
		font-size:19px;
	}
	
</style>