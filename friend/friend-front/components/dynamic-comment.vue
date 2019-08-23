<template>
	<view class="">
       <view class="dynamic-comment-left">
             <image style="height: 80upx; width: 80upx; margin: 10upx;" src="/static/img/home/head/woman.png" mode=""></image>
       </view>		
	   <view class="dunamic-comment-right">
	   	    <view class="dynamic-comment-right-user">
	   	    	<text>{{msg.source}}</text>
	   	    </view>
			<view class="dynamic-comment-right-content">
				<text>test</text>
			</view>
			<view class="dynamic-comment-right-modify">
				<text>time</text>
				
			</view>
	   </view>
	</view>
</template>

<script>
	export default {
		name: 'dynamic-comment',
		        props: {
		    msg: {
		        type: Object,
		        default: function(e) {
		            return {}
		        }
		    }
		}
	}
</script>

<style>
	.dynamic-comment-left{
		position: absolute;
		left: 0;
		width: 20%;
		
	}
	.dunamic-comment-right{
		position: absolute;
        right: 0;
		width: 80%;
	}
	.dynamic-comment-right-user{
		
	}
	.dynamic-comment-right-content{
		
	}
	.dynamic-comment-right-modify{
		
	}
</style>
