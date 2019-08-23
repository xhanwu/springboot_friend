<template>
	<view id="content" :style="'height:'+valueheight+'px;'">
		<hr id="hr1"></hr>
		<image id="logo" v-bind:src="logoimg" ></image>
		<text id="title" @tap="issuedynamic">{{title}}</text>
		<hr id="hr2"></hr>
		<view v-for="(item,index) in mydynainfolist" :key="index">
			<view class="contentview">
				<img id="img1" v-bind:src="logoimg" height="45"/>
				<view class="contentitems">
					<button class="btn3">{{item.name}}</button><br/>
					<button class="btn4">{{item.date}}</button>
					<button class="btn5">{{item.address}}</button></br>
				 </view>
				 
			</view>
			<view class="conentpage">
				<label>
					{{item.text}}
				</label><br/>
				
				<view class="">
					<view v-if="item.imageisarray">
						
						<view class="uni-uploader-body">
							<view class="uni-uploader__files">
								<block v-for="(imager,index) in item.image" :key="index">
									<view class="uni-uploader__file">
										<image class="uni-uploader__img" :src="imager" :data-src="imager" ></image>
									</view>
								</block>
							</view>
						</view>
						
						
					</view>
					<view v-else>
						<view class="uni-uploader-body">
							<view class="uni-uploader__files">
								<block >
									<view class="uni-uploader__file">
										<image class="uni-uploader__img" :src="item.image" :data-src="item.image" ></image>
									</view>
								</block>
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="contentcomments">
				<img id="img3" v-if="flagvalue(index)" v-bind:src="likeduri" v-on:click="isShowImg(index,flag)"/>
				<img id="img4" v-if="!flagvalue(index)" v-bind:src="likeuri" v-on:click="isShowImg(index,flag)"/>{{item.nice}}
				<img id="img5" v-bind:src="commenturi" @tap="jumpcomment(item.name,item.date,item.address,item.text,item.image)"/>{{commentnumber}}
				<img id="img6" v-bind:src="shareuri" height="24"/>
			</view>
		</view>
	</view>
</template>

<script>
	
	import Bus from '../middle/bus.js';
	import CommentBus from '../middle/commentbus.js';
	var name="lichun.xiu";
	var mydynainfolistvalue = new Array(10);
	
	export default {
		data() {
			return {
				title: '发布动态...',
				logoimg:"/static/img/dynamic/avatar_full.jpg",
				userimg:"/static/img/dynamic/jiyou2.jpg",
				imgurl:"static/FqnVLqxL-fBiUdbbl0grAbmCqpc1.jpg",
				commentnumber:45,
				clicknice1:12,
				clicknice2:23,
				clicknice3:3,
				clicknice4:9,
				clicknice5:51,
				flag:false,
				flag1:false,
				flag2:false,
				flag3:false,
				flag4:false,
				flag5:false,
				flag6:false,
				flag7:false,
				flag8:false,
				flag9:false,
				flag10:false,
				likeuri:'/static/img/dynamic/ic_comment_like.png',
				likeduri:'/static/img/dynamic/ic_comment_like_selected.png',
				commenturi:'/static/img/dynamic/ic_common_notification_chat.png',
				shareuri:'/static/img/dynamic/ic_navbar_share.png',
				// news: []
				
				nameinfo:[],
				dateinfo:[],
				addressinfo:[],
				textinfo:[],
				imginfo:[],
				niceinfo:[],
				mydynainfo1:"",
				mydynainfo2:"",
				mydynainfo3:"",
				mydynainfo4:"",
				mydynainfo5:"",
				mydynainfo6:"",
				mydynainfo7:"",
				mydynainfo8:"",
				mydynainfo9:"",
				mydynainfo10:"",
				
				mydynainfonext:"",
				mydynainfolist:[],
			}
		},
		mounted:function(){
			uni.request({
				url:'http://10.92.34.48:7300/mock/5d4b8bb019ad5d00200d5d34/',
				method: 'GET',
				data: {},
				success: res => {
					var index = 0;
					while(index<5){
						switch(index){
							case 0:
							    this.nameinfo[index] = res.data.name1.cname;
								break;
							case 1:
							    this.nameinfo[index] = res.data.name2.cname;
								break;
							case 2:
								this.nameinfo[index] = res.data.name3.cname;
								break;
							case 3:
								this.nameinfo[index] = res.data.name4.cname;
								break;
							case 4:
								this.nameinfo[index] = res.data.name5.cname;
								break;
							default:
							    this.nameinfo[index] = "default name";
					}
						index++;
					}
					index = 0;
					while(index<5){
						switch(index){
							case 0:
							    this.dateinfo[index] = res.data.date1.datetime;
								break;
							case 1:
							    this.dateinfo[index] = res.data.date2.datetime;
								break;
							case 2:
								this.dateinfo[index] = res.data.date3.datetime;
								break;
							case 3:
								this.dateinfo[index] = res.data.date4.datetime;
								break;
							case 4:
								this.dateinfo[index] = res.data.date5.datetime;
								break;
							default:
							    this.dateinfo[index] = "default date";
					}
						index++;
					}
				
				    index = 0;
				    while(index<5){
				    	switch(index){
				    		case 0:
				    		    this.addressinfo[index] = res.data.address1.county;
				    			break;
				    		case 1:
				    		    this.addressinfo[index] = res.data.address2.county;
				    			break;
				    		case 2:
				    			this.addressinfo[index] = res.data.address3.county;
				    			break;
				    		case 3:
				    			this.addressinfo[index] = res.data.address4.county;
				    			break;
				    		case 4:
				    			this.addressinfo[index] = res.data.address5.county;
				    			break;
				    		default:
				    		    this.addressinfo[index] = "default date";
				    }
				    	index++;
				    }
					
					index = 0;
					while(index<5){
						switch(index){
							case 0:
							    this.textinfo[index] = res.data.text1.cparagraph;
								break;
							case 1:
							    this.textinfo[index] = res.data.text2.cparagraph;
								break;
							case 2:
								this.textinfo[index] = res.data.text3.cparagraph;
								break;
							case 3:
								this.textinfo[index] = res.data.text4.cparagraph;
								break;
							case 4:
								this.textinfo[index] = res.data.text5.cparagraph;
								break;
							default:
							    this.textinfo[index] = "default date";
					}
						index++;
					}
					
					index = 0;
					while(index<5){
						switch(index){
							case 0:
							    this.imginfo[index] = res.data.image1.image;
								break;
							case 1:
							    this.imginfo[index] = res.data.image2.image;
								break;
							case 2:
								this.imginfo[index] = res.data.image3.image;
								break;
							case 3:
								this.imginfo[index] = res.data.image4.image;
								break;
							case 4:
								this.imginfo[index] = res.data.image5.image;
								break;
							default:
							    this.imginfo[index] = "default date";
					} 
						 index++;
					} 
					
					index = 0;
					while(index<5){
						switch(index){
							case 0:
							    this.niceinfo[index] = res.data.base1.natural;
								break;
							case 1:
							    this.niceinfo[index] = res.data.base2.natural;
								break;
							case 2:
								this.niceinfo[index] = res.data.base3.natural;
								break;
							case 3:
								this.niceinfo[index] = res.data.base4.natural;
								break;
							case 4:
								this.niceinfo[index] = res.data.base5.natural;
								break;
							default:
							    this.niceinfo[index] = "default date";
					} 
						 index++;
					} 
					
					index=0;
					while(index<5){
						switch(index){
								case 0:
								    this.mydynainfo1 = new Object();
								    this.mydynainfo1.name=this.nameinfo[index];
								    console.log(index+"---"+this.mydynainfo1.name);
								    this.mydynainfo1.date=this.dateinfo[index];
								    console.log(index+"---"+this.mydynainfo1.date);
								    this.mydynainfo1.address=this.addressinfo[index];
								    console.log(index+"---"+this.mydynainfo1.address);
								    this.mydynainfo1.text=this.textinfo[index];
								    console.log(index+"---"+this.mydynainfo1.text);
									this.mydynainfo1.image=this.imginfo[index];
									console.log(index+"---"+this.mydynainfo1.image);
									this.mydynainfo1.nice=this.niceinfo[index];
									console.log(index+"---"+this.mydynainfo1.nice);
									this.mydynainfolist.push(this.mydynainfo1);
									break;
								case 1:
								    this.mydynainfo2 = new Object();
								    this.mydynainfo2.name=this.nameinfo[index];
								    this.mydynainfo2.date=this.dateinfo[index];
								    this.mydynainfo2.address=this.addressinfo[index];
								    this.mydynainfo2.text=this.textinfo[index];
									this.mydynainfo2.image=this.imginfo[index];
									this.mydynainfo2.nice=this.niceinfo[index];
									this.mydynainfolist.push(this.mydynainfo2);
									break;
								case 2:
									this.mydynainfo3 = new Object();
									this.mydynainfo3.name=this.nameinfo[index];
									this.mydynainfo3.date=this.dateinfo[index];
									this.mydynainfo3.address=this.addressinfo[index];
									this.mydynainfo3.text=this.textinfo[index];
									this.mydynainfo3.image=this.imginfo[index];
									this.mydynainfo3.nice=this.niceinfo[index];
									this.mydynainfolist.push(this.mydynainfo3);
									break;
								case 3:
									this.mydynainfo4 = new Object();
									this.mydynainfo4.name=this.nameinfo[index];
									this.mydynainfo4.date=this.dateinfo[index];
									this.mydynainfo4.address=this.addressinfo[index];
									this.mydynainfo4.text=this.textinfo[index];
									this.mydynainfo4.image=this.imginfo[index];
									this.mydynainfo4.nice=this.niceinfo[index];
									this.mydynainfolist.push(this.mydynainfo4);
									break;
								case 4:
									this.mydynainfo5 = new Object();
									this.mydynainfo5.name=this.nameinfo[index];
									this.mydynainfo5.date=this.dateinfo[index];
									this.mydynainfo5.address=this.addressinfo[index];
									this.mydynainfo5.text=this.textinfo[index];
									this.mydynainfo5.image=this.imginfo[index];
									this.mydynainfo5.nice=this.niceinfo[index];
									this.mydynainfolist.push(this.mydynainfo5);
									break;
								default:
								    console.log("error");;
						} 
						index=index+1;
					};
				},
				fail: () => {
				},
				complete: () => {
				}
			});
			
			Bus.$on('add_dynamic',(content01,address,images)=>{//监听first组件的add_dynamic事件
			  var tempmydynainfo = new Object();
			  tempmydynainfo.name="tom";
			  console.log("---"+tempmydynainfo.name);
			  tempmydynainfo.date=this.getnow();
			  console.log("---"+tempmydynainfo.date);
			  tempmydynainfo.address=address;
			  console.log("---"+tempmydynainfo.address);
			  tempmydynainfo.text=content01;
			  console.log("---"+tempmydynainfo.text);
			  let localimages = [];
			  let index;
			  let imagecount = images.length;
			  tempmydynainfo.imageisarray = false;
			  if(imagecount>1){
				  tempmydynainfo.imageisarray = true;
				  for(index in images){
					  localimages.push(images[index]);
				  }
				  tempmydynainfo.image = localimages;
			  }else if(imagecount==1){
				  tempmydynainfo.image= images[0];
			  }else{
				  tempmydynainfo.image= "/static/img/dynamic/avatar_full.jpg";
			  }
			  console.log("---"+tempmydynainfo.image);
			  tempmydynainfo.nice="0";
			  console.log("---"+tempmydynainfo.nice);
			  
			  switch(this.mydynainfolist.length+1){
				  
				  case 6:
				      this.mydynainfo6 =new Object();
					  this.mydynainfo6 = tempmydynainfo;
					  this.mydynainfolist.unshift(this.mydynainfo6);
				  break;
				  case 7:
				      this.mydynainfo7 =new Object();
					  this.mydynainfo7 = tempmydynainfo;
					  this.mydynainfolist.unshift(this.mydynainfo7);
				  break;
				  case 8:
				      this.mydynainfo8 =new Object();
					  this.mydynainfo8 = tempmydynainfo;
					  this.mydynainfolist.unshift(this.mydynainfo8);
				  break;
				  case 9:
				      this.mydynainfo9 =new Object();
					  this.mydynainfo9 = tempmydynainfo;
					  this.mydynainfolist.unshift(this.mydynainfo9);
				  break;
				  case 10:
				      this.mydynainfo10 =new Object();
					  this.mydynainfo10 = tempmydynainfo;
					  this.mydynainfolist.unshift(this.mydynainfo10);
				  break;
				  default:
				      this.mydynainfolist.unshift(tempmydynainfo);
			  }
			  
			  uni.switchTab({
			       url:'/pages/dynamic/home/index'
			  });
			});
		},
		updated:function(){
			//有效
		},
		onLoaded:function(){
			//无效
		},
		onLoad() {
		  alert(456);
		},
		beforeDestroy:function() {
			// 销毁监听事件
			//Bus.$off('add_dynami');
		},
		methods: {
			isShowImg:function(place,flag){
				switch(place+1){
					case 1:
					    this.flag1=!this.flag1;
						if(this.flag1){
							this.mydynainfo1.nice++;
						}else{
							this.mydynainfo1.nice--;
						}
					break;
					case 2:
					    this.flag2=!this.flag2;
						if(this.flag2){
							this.mydynainfo2.nice++;
						}else{
							this.mydynainfo2.nice--;
						}
					break;
					case 3:
					    this.flag3=!this.flag3;
						if(this.flag3){
							this.mydynainfo3.nice++;
						}else{
							this.mydynainfo3.nice--;
						}
					break;
					case 4:
					    this.flag4=!this.flag4;
						if(this.flag4){
							this.mydynainfo4.nice++;
						}else{
							this.mydynainfo4.nice--;
						}
					break;
					case 5:
					    this.flag5=!this.flag5;
						if(this.flag5){
							this.mydynainfo5.nice++;
						}else{
							this.mydynainfo5.nice--;
						}
					break;
					case 6:
					    this.flag6=!this.flag6;
						if(this.flag6){
							this.mydynainfo6.nice++;
						}else{
							this.mydynainfo6.nice--;
						}
					break;
					case 7:
					    this.flag7=!this.flag7;
						if(this.flag7){
							this.mydynainfo7.nice++;
						}else{
							this.mydynainfo7.nice--;
						}
					break;
					case 8:
					    this.flag8=!this.flag8;
						if(this.flag8){
							this.mydynainfo8.nice++;
						}else{
							this.mydynainfo8.nice--;
						}
					break;
					case 9:
					    this.flag9=!this.flag9;
						if(this.flag9){
							this.mydynainfo9.nice++;
						}else{
							this.mydynainfo9.nice--;
						}
					break;
					case 10:
					    this.flag10=!this.flag10;
						if(this.flag10){
							this.mydynainfo10.nice++;
						}else{
							this.mydynainfo10.nice--;
						}
					break;
				}
			},
			issuedynamic:function(){
				uni.switchTab({
				     url:'/pages/dynamic/issue/dynamic'
				});
			},
			jumpcomment:function(name,date,address,text,image){
				console.log("--------------------------------name:"+name);
				this.mydynainfonext = new Object();
				this.mydynainfonext.name=name;
				this.mydynainfonext.date=date;
				this.mydynainfonext.address=address;
				this.mydynainfonext.text=text;
				this.mydynainfonext.image=image;
				
				setTimeout(()=>{                                        
						CommentBus.$emit('add_comment',name,date,address,text,image);
				},200);
				
				uni.navigateTo({
				     url:'/pages/dynamic/comment/addcomment'
				});
			},
			flagvalue:function(val){
				switch(val+1){
					case 1:
					    return this.flag1;
					case 2:
						return this.flag2;
					case 3:
						return this.flag3;
					case 4:
						return this.flag4;
					case 5:
						return this.flag5;
					case 6:
						return this.flag6;
					case 7:
						return this.flag7;
					case 8:
						return this.flag8;
					case 9:
						return this.flag9;
					case 10:
						return this.flag10;
			}
			},
			getnow:function(){
				var myDate = new Date();
				myDate.getYear();      //获取当前年份(2位)
				myDate.getFullYear(); //获取完整的年份(4位,1970-????)
				myDate.getMonth();      //获取当前月份(0-11,0代表1月)
				myDate.getDate();      //获取当前日(1-31)
				myDate.getDay();        //获取当前星期X(0-6,0代表星期天)
				myDate.getTime();      //获取当前时间(从1970.1.1开始的毫秒数)
				myDate.getHours();      //获取当前小时数(0-23)
				myDate.getMinutes();    //获取当前分钟数(0-59)
				myDate.getSeconds();    //获取当前秒数(0-59)
				myDate.getMilliseconds(); //获取当前毫秒数(0-999)
				myDate.toLocaleDateString();    //获取当前日期
				var mytime=myDate.toLocaleTimeString();    //获取当前时间
				//alert(mytime);
				//alert(myDate.toLocaleString());
				return myDate.toLocaleString();      //获取日期与时间----如果涉及到时分秒，直接使用即可。
			},
		},
		props:[
			
		],
		computed: {
			// 计算属性的 getter
			valueheight:function(){
				return 300+this.mydynainfolist.length*270;
			}
			
		}
  }
		
</script>

<style scoped>
	#content {
		height: 1600px;
		text-align: left;
		margin-top: 10px;
		padding-top: 0px;
		background: #fff repeat-x 0 0;
	}

	#logo {
		width:55px;
		height:55px;
		margin-top: 0px;
		vertical-align: middle;
		
        padding: 2px 0; /* 撑开高度 */
		border-radius:50% ;
	}

	#title {
		font-size: 15px;
		height:40px;
		color: #8f8f94;
		
		display: inline-block;
        vertical-align: middle;
		
		
        padding: 0px 0; /* 撑开高度 */
		margin-top: 30px;
		margin-left:5px;
	}
	
	#hr1{
		height:4px;
		background-color: #eeeeee;
		width:100%;
	}
	#hr2{
		height:9px;
		background-color: #eeeeee;
		width:100%;
	}
	#img1{
		display: inline-block;
		border-radius:50% ;
	}
	.contentitems .btn3{
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
		margin-top: 0px;
		padding: 0 0;
		font-size: 10px;
		outline: 0;
		overflow: hidden;
		text-align: center
	}
	.contentitems .btn4{
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
		margin-top: 0px;
		padding: 0 0;
		font-size: 10px;
		outline: 0;
		overflow: hidden;
		text-align: center
	}
	.contentitems .btn5{
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
		margin-top: 0px;
		padding: 0 0;
		font-size: 10px;
	    outline: 0;
	    overflow: hidden;
	    text-align: center;
		margin-left: 10px;
	}
	.contentitems{
	    display:inline-block;
		font-size: 0;
		margin-left: 10px;
	}
	.contentview{
		display:inline-flex;
		font-size: 0;
	}
	.conentpage{
		font-size: 10px;
		margin-left: 6%;
	}
	#img3{
		border-radius:0;
		margin-left: 10%;
	}
	#img4{
		border-radius:0;
		margin-left: 10%;
	}
	#img5{
		border-radius:0;
		margin-left: 15%;
	}
	#img6{
		border-radius:0;
		margin-left: 15%;
	}
	.contentcomments{
		font-size: 10px;
	}
	.uni-media-list-body111{
		height: 30px;
		font-size:20px;
		}
	.uni-media-list-text-top{
		height: 29px;
		font-size:19px;
	}
	h2{
		height: 40px;
	}
	.uni-uploader__img{
		width:80%
	}
</style>
