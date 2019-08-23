<template>
	<view class="s-page-wrapper is-100vh">
		<view class="avatar_area">
				<image src="../../static/img/login/head.png" mode="aspectFit" class="logoimg"></image>
		</view>
		<view class="registercontent">
			<view class="has-mglr-10 ">
				<view class=" has-mgtb-10 ">
					<input type="number" maxlength="11" placeholder="请输入手机号" @input="inputMobile" class="is-input1 " />
				</view>
				<view class="has-mgtb-10" style="height: 88upx">
					<input type="number" maxlength="6" placeholder="短信验证码" class="code_content " />
					<view class="code_img" @tap="getsmscode">{{smsbtn.text}}</view>
				</view>
				<view class=" has-radius has-mgtb-10">
					<input placeholder="设置登录密码" :password="true" @input="inputPwd" class="is-input1" />
				</view>
				<view class=" has-radius has-mgtb-10">
					<input placeholder="确认登录密码" :password="true" @input="inputPwdConfirm" class="is-input1" />
				</view>
				<view class=" registerbtn has-radius has-mgtb-20">
					<button @tap="doRegister">注 册</button>
				</view>
			</view>
		</view>
		<view class="is-20vh has-mgt-80">
			<navigator url="#" class=" has-radius is-center is-grey " hover-class="">
				<text>注册即表示同意</text><text class="is-blue">《用户协议》</text>
			</navigator>
		</view>
	</view>
</template>

<script>
	import {
		Register
	} from '../../api/api.js'
	
	export default {
		data() {
			return {
				mobile: '',
				userpwd: '',
				userpwd_cnf: '',
				smsbtn: {
					text: '获取验证码',
					status: false,
					codeTime: 60
				},
				timerId: null,
			};
		},
		methods: {
			getsmscode: function() {
				if (this.smsbtn.status == true) {
					console.log('message：', "别着急！短信已经发送了~");
					return false;
				}
				this.smsbtn.status = true; // 这段代码其实应该加在你request请求 短信发送成功后 
				this.timerId = setInterval(() => {
						var codeTime = this.smsbtn.codeTime;
						codeTime--;
						this.smsbtn.codeTime = codeTime;
						this.smsbtn.text = codeTime + "S";
						if (codeTime < 1) {
							clearInterval(this.timerId);
							this.smsbtn.text = "重新获取";
							this.smsbtn.codeTime = 60;
							this.smsbtn.status = false;
						}
					},
					1000);
				return false;
			},
			inputMobile(e) {
				this.mobile = e.target.value
			},
			inputPwd(e) {
				this.userpwd = e.target.value
			},
			inputPwdConfirm(e) {
				this.userpwd_cnf = e.target.value
			},
			doRegister() {
				if (this.mobile == '' || this.userpwd == '' || this.userpwd_cnf == ''){
					console.log("手机号，密码不能为空");
				}
				if (this.userpwd != this.userpwd_cnf){
					console.log("请重新确认密码");
				}
				let regInfo = {
					mobile: this.mobile,
					password: this.userpwd
				}
				Register(regInfo).then(res => {
					console.log("register success");
					let code = res.data.code;
					let info = res.data.info;
					if (code == 200) {
						uni.showToast({title: info + "\ncode" + code});
						uni.navigateTo({
							url: '/pages/login/login'
						});
					} else {
						uni.showToast({title: info + "\ncode" + code});
					}
				}).catch(err => {
					console.log("register fail");
				})
			}
		}
	}
</script>

<style>
	page {
		min-height: 100%;
		background-color: #FFFFFF;
	}

	.registercontent {
		width: 85%;
		margin: 0 auto;
	}

	.avatar_area {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-content: center;
		align-items: center;
	}

	.logoimg {
		width: 200upx;
		height: 200upx;
		border-radius: 50%;
	}

	.is-input1 {
		height: 88upx;
		width: 100%;
		line-height: 88upx;
		color: #353535;
		font-size: 32upx;
		box-sizing: border-box;
		appearance: none;
		border: 2upx solid #e5e5e5;
		box-shadow: none;
		border-radius: 44upx;
		outline: 0;
		display: block;
		padding-left: 30upx;
		margin: 0;
		font-family: inherit;
		background: #fff;
		resize: none;
	}

	.iconfont {
		position: absolute;
		font-size: 40upx;
		right: 12%;
		z-index: 999;
		width: 105upx;
		text-align: center;
		color: #353535;
		margin-top: -11%;
		background: #fff;
		border-top-right-radius: 44upx;
		border-bottom-right-radius: 44upx;
		height: 80upx;
		line-height: 80upx;
	}

	.code_content{
		height: 88upx;
		width: 70%;
		line-height: 88upx;
		color: #353535;
		font-size: 32upx;
		box-sizing: border-box;
		appearance: none;
		border: 2upx solid #e5e5e5;
		box-shadow: none;
		border-radius: 44upx;
		outline: 0;
		padding-left: 30upx;
		margin: 0;
		font-family: inherit;
		background: #fff;
		resize: none;
		float: left;
	}

	.code_img {
		float: right;
		font-size: 28upx;
		z-index: 999;
		width: 30%;
		text-align: center;
		color: #FFFFFF;
		background-color: #39b54a;
		border-radius: 44upx;
		height: 88upx;
		line-height: 88upx;
	}

	.registerbtn button {
		margin-top: 20upx;
		height: 88upx;
		width: 100%;
		line-height: 88upx;
		color: #ffffff;
		font-size: 32upx;
		border-radius: 44upx;
		outline: 0;
		display: block;
		margin: 0;
		font-family: inherit;
		background: #f35;
		opacity: 0.8;
	}

	button:after {
		border: 2upx solid #f2f2f2;
	}

	.has-mgtb-10 {
		margin-top: 20upx !important;
		margin-bottom: 20upx !important;
	}

	.has-mglr-10 {
		margin-left: 20upx !important;
		margin-right: 20upx !important;
	}

	.has-mgtb-20 {
		margin-top: 40upx !important;
		margin-bottom: 40upx !important;
	}

	.has-radius {
		border-radius: 4px;
	}

	/* 字体居中 */
	.is-center {
		text-align: center !important;
	}

	.is-grey {
		color: #888888 !important;
	}
</style>
