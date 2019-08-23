<template>
	<view class="content">
		<view class="top">
			<img class="user" :src="useruri" /> 
			<view>
				<view class="v1">
					<button class="btn1">{{userinfo}}</button><br/>
				</view>
				<view>
					<button class="btn2">{{date}}&nbsp;&nbsp;&nbsp;&nbsp;{{address}}</button><br/>
				</view>
			</view>
		</view>
		<button class="btn5">{{text}}</button>
		<hr class="hr1"/>
		<view class="comment">
			<img class="imguser" src="static/img/dynamic/avatar_full.jpg"/> 
			<button class="btn6">爱樱花的虾米</button>
			<button class="btn4">{{goodnumber}}</button>
			<img class="img1" v-if="flag" src="static/img/dynamic/ic_comment_like.png" @tap="isShowImg"/>
			<img class="img2" v-if="!flag" src="static/img/dynamic/ic_comment_like_selected.png" @tap="isShowImg"/>
		</view>
		<button class="btn5">{{funcomment}}</button>
		
	</view>
</template>

<script>
	
	import CommentBus from '../middle/commentbus.js';

	export default {
		//console.log("cba");
		data() {
			return {
				userinfo: "小朋友不爱吃糖",
				date:"2019-04-02",
				address:"惠州仲恺高新区",
				useruri:'/static/img/dynamic/jiyou2.jpg',
				flag:true,
				text:"北京时间8月9日，据《体育画报》报道，美国篮协主席杰里-克朗吉洛在接受采访时透露，卡梅罗-安东尼曾主动申请加入美国男篮参加今年的世界杯，但是遭到了美国篮协的婉拒。",
				goodnumber:0,
				funcomment:"早上看到这条新闻真的心疼，当年的纽约之王怎么会变成这样。真的没有其他办法了么。 我觉得瓜打不上NBA一定不是篮球本身实力的问题，瓜的名气已经足够大，技术虽然下滑但是依然比大部分NBA替补的能力强，正常来讲打个替补真的没问题。 希望瓜哥一切顺利，我爱melo，你永远都是我的信仰。",
			}
		},
		methods:{
			  isShowImg:function(){
				 this.flag=!this.flag;
				 if(this.flag){
				 	this.goodnumber=this.goodnumber-1;
				 }else{
				 	this.goodnumber=this.goodnumber+1;
				 }
			  }
		},
	    mounted:function(){
			//alert("mounted");
		},
		created:function(){
			//alert("created");
		},
		beforeCreate:function(){
			//alert("beforeCreate");
			CommentBus.$on('add_comment',(name,date,address,text,image)=>{//监听first组件的txt事件
			   console.log("++++++++++++++++++++++++++++++++++++name:"+name);
			   this.userinfo=name;
			   console.log("+++++date:"+date);
			   this.date=date;
			   console.log("-----address:"+address);
			   this.address=address;
			   console.log("-----text:"+text);
			   this.text=text;
			   console.log("-----image:"+image);
			   this.useruri=image;
			});
		},
		beforeDestroy:function() {
			// 销毁监听事件
			//alert("comment destory");
		    CommentBus.$off('add_comment')
		}
	}
</script>

<style>
	.content {
		text-align: center;
		height: 400px;
	}
	.top{
		border:1px solid red;
		font-size: 10px;
		display:inline-flex;
		width:100%;
	}
	.user{
		height:60px;
		width:60px;
		border-radius: 50%;
		padding: 10px 10px;
	}
	.btn1{
		/* border:1px solid red; */
		display:inline-table;
		font-size: 10px;
		text-align: left;
		width:210px;
		margin-top: 10px;
		margin-left: 0px;
	}
	.v1{
		margin: 0px;
	}
	.btn2{
		font-size:10px;
		width:210px;
		text-align: left;
		display: inline-block;
	}
	.btn6{
		font-size:10px;
		text-align: left;
		display: inline-block;
		padding: 20px;
	}
	.btn3{
		font-size: 10px;
		width:210px;
		text-align: left;
		margin-top: 0px;
		display: inline-block;
	}
	.btn5{
		font-size: 15px;
		width:80%;
		text-align: left;
		display: inline-block;
	}
	.btn4{
		font-size: 15px;
		display: inline-block;
		padding: 10px 30px 0;
		/* border:1px solid red; */
	}
	.hr1{
		height:4px;
		background-color: #eeeeee;
		width:100%;
	}
	.comment{
		display:inline-flex;
		border:1px solid green;
		width:100%;
		height:80px;
	}
	.imguser{
		height:45px;
		width:45px;
		border-radius: 50%;
		padding: 10px 10px;
	}
	.img1{
		border-radius:0;
		padding: 20px 30px;
		height:20px;
		width:20px;
	}
	.img2{
		border-radius:0;
		padding: 20px 30px;
		height:20px;
		width:20px;
	}
</style>
