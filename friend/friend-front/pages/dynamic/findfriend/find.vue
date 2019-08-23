<template>
	<view class="addfriend">
		<view class="play">
			<view class="find">{{title}}</view>
			<img class="sou1" src="/static/img/dynamic/sousuo_3.png" @tap="developing"/>
		</view>
		<view class="share">
			<img class="tongxunlu" src="/static/img/dynamic/tongxunlu_12.png"  />
			<button class="btn1" @tap="developing">{{tongxunlu}}</button>
			<button class="btn2">|</button>
			<img class="weibo" src="static//img/dynamic/icon_weibo.png"/>
			<button class="btn3" @tap="developing">{{weibo}}</button>
		</view>
		<view class="note">
			<text class="advise">{{advicename}}</text>
		</view>
		<view class="recommend" v-if="isshow">
		    <img class="iconone" :src="jiyouuri" height="55" width="55" />
			<view class="info">
				<view class="intro">
				    <button class="name">{{namestring}}</button>
					<button class="set">{{setstring}}</button>
				</view>
				<button class="delete" @tap="deleteitem">X</button>
			    <view v-if="isfocus">
				      <button class="nofocus" @tap="focusone">{{nofocusstring}}</button>
				</view>
				<view v-else>
					   <button class="focus" @tap="focusone">{{yesfocusstring}}</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: "发现朋友",
				tongxunlu:"微信好友",
				weibo:"微博好友",
				advicename:"好友推荐",
				namestring:"刘包子的蒸屉",
				setstring:"“大学生的日常”优秀作者",
				nofocusstring:"+关注",
				yesfocusstring:"已关注",
				jiyouuri:'/static/img/dynamic/jiyou2.jpg',
				isshow:true,
				isfocus:false,
			}
		},
		methods: {
			deleteitem:function(){
				// alert("delete");
				this.isshow=false;
			},
			focusone:function(){
				this.isfocus=!this.isfocus;
			},
			developing:function(){
				alert("正在开发");
			},
		}
		
	}
</script>

<style>
	.addfriend{
		min-height: 400px;
	}
	.play{
		font-size: 26px;
		text-align: left;
		/* border:1px solid green; */
	}
	.find{
		display: inline-block;
		margin-left: 30%;
		padding: 4px 0; /* 撑开高度 */
		/* border:1px solid green; */
		font-size:15px;
		vertical-align: middle;
	}
	.sou1{
		display: inline-block;
		margin-left:25%;
		height:40px;
		padding: 2px 0; /* 撑开高度 */
		/* border:1px solid green; */
		vertical-align: middle;
	}
	.share{
		display:inline-flex;
		/* border:1px solid green; */
		width:100%;
	}
	.tongxunlu{
		height:50px;
		width:50px;
		margin-left: 5%;
	}
	.weibo{
		height:50px;
		width:50px;
		margin-left: 5%;
	}
	.note{
		/* border:1px solid green; */
		background: #eeeeee;
		width:100%;
		height: 40px;
		line-height: 40px;
		padding: 0 0;
		
		font-size: 26px;
		text-align: left;
	}
	.advise{
		font-size: 26px;
		/* border:1px solid green; */
		margin-top: 0px;
		padding: 0 20px;
	}
	.recommend{
		/* border:1px solid green; */
		display:flex;
		margin-top: 10px;
	}
	.iconone{
		border-radius: 50%;
	}
	.info{
		display:inline-flex;
		/* border:1px solid green; */
		width:90%;
		height:80px;
	}
	.intro{
		height: 80px;
	}
	.delete{
		text-align: middle;
		padding: 20px 10px;
		background: #4488CC;
		width:50px;
		font-size:18px;
	}
	.focus{
		text-align: center;
		padding: 25px 0px;
		font-size:10px;
		width:55px;
		border:1px solid grey;
		color:blue;
		height:78px;
	}
	.nofocus{
		text-align: center;
		padding: 25px 0px;
		font-size:10px;
		width:55px;
		border:1px solid yellow;
		color: cyan;
		height:78px;
	}
	.name{
		display: block;
		font-size: 15px;
	}
	.set{
		display: block;
		font-size: 15px;
	}
</style>
