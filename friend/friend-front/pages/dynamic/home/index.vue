<template>
	<view class="indexcontent">
		<ttop></ttop>		
		<cctent></cctent>
	</view>
</template>

<script>
	import top from '@/pages/dynamic/home/top'
    import content from '@/pages/dynamic/home/content'
	
	
	export default {
		data() {
			return {
			}
		},
		onLoad() {
            
		},
		methods: {
			    
		},
		mounted:function(){
		},
		beforeDestroy:function() {
		},
		components:{
			'ttop':top,
			'cctent':content
		}
	}
</script>

<style>
	.indexcontent {
		text-align: center;
	}

	.logo {
		height: 100upx;
		width: 100upx;
		margin-top: 50upx;
	}

	.title {
		font-size: 15upx;
		color: #8f8f94;
		margin: 10px;
	}
</style>
