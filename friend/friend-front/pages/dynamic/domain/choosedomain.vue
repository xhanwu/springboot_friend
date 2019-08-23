<template>
	<view class="content">
		<view class="top">
			<input class="wenzi" type="text" maxlength="20" placeholder="输入圈子名字"/>
			<img class="sou1" src="static/sousuo_3.png" height="35" />
		</view>
		<view class="hr">
			<hr class="hr1"/>
		</view>
		<view class="nodomain">
			<button class="btn1">{{nondomainstring}}</button>
			<button class="btn2">{{choicestring}}</button>
		</view>
		<view class="hr">
			<hr class="hr1"/>
		</view>
		<view class="nodomain">
			<button class="btn1" @tap="createdom">{{createdomain}}</button>
		</view>
		<view class="hr">
			<hr class="hr1"/>
		</view>
		<view class="domain">
			<img class="domainicon" :src="domainuri"  />
			<button class="btn3">{{domainname}}</button>
			<button class="btn4ischked?btn4:btn4yes" @tap="check">{{choicestring}}</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: "发现朋友",
				tongxunlu:"通讯录好友",
				weibo:"微博好友",
				advicename:"好友推荐",
				nondomainstring:"不加入任何圈子",
				domainname:"职场那些事",
				choicestring:"选择",
				createdomain:"创建圈子",
				domainuri:'/static/icon-test_2.png',
			}
		},
		methods:{
			createdom:function(){
				uni.navigateTo({
					 url:'/pages/domain/input'
				});
			}
		}
	}
</script>

<style>
	.content {
		text-align: center;
		height: 400px;
	}
	.top{
		border:1px solid green;
		display: flex;
		height:45px;
		width:100%;
	}
	.sou1{
		display: flex;
		margin-left: 10%;
	}
	.wenzi{
		display: inline-block;
		color: #656b79;
		background-color: #f6f8fa;
		border-radius: 20px;
		border: 0;
		box-sizing: border-box;
		color: inherit;
		font-size: 18px;
		height: 43px;
		outline: 0;
		overflow: hidden;
		position: relative;
		text-align: center;
		margin-left: 15%;
		border:1px solid green;
	}
	.input-placeholder{
		height: 43px;
	}
	.hr1{
		height:4px;
		background-color: #eeeeee;
		width:100%;
	}
	.domain{
		display: flex;
		margin-top: 10px;
	}
	.domainicon{
		border-radius: 50%;
		height:55px;
		width:55px;
		
	}
	.btn2{
		display: inline-block;
		vertical-align:middle;
		width:25%;
		color: #656b79;
		background-color: #3366CC;
		-webkit-appearance: none;
		   -moz-appearance: none;
				appearance: none;
		border-radius: 4px;
		border: 0;
		box-sizing: border-box;
		color: inherit;
		font-size: 18px;
		height: 40px;
		outline: 0;
		overflow: hidden;
		position: relative;
		text-align: center;
	}
	.btn3{
		display: inline-block;
		color: #656b79;
		background-color: #f6f8fa;
		-webkit-appearance: none;
		   -moz-appearance: none;
				appearance: none;
		border-radius: 4px;
		border: 0;
		box-sizing: border-box;
		color: inherit;
		font-size: 18px;
		outline: 0;
		overflow: hidden;
		position: relative;
	}
	.btn4{
		display: inline-block;
		vertical-align:middle;
		width:25%;
		color: #656b79;
		background-color: #3366CC;
		-webkit-appearance: none;
		   -moz-appearance: none;
				appearance: none;
		border-radius: 4px;
		border: 0;
		box-sizing: border-box;
		color: inherit;
		font-size: 18px;
		height: 40px;
		outline: 0;
		overflow: hidden;
		position: relative;
		text-align: center;
	}
	.nodomain{
		display: flex;
	}
</style>
