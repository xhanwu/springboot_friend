<template>
	<view class="body_bg">
		<view class="page_login">
			<!-- 头部logo -->
			<view class="head">
				<image style="width: 70px;height: 70px;" :src="imgInfo.head" class="head_logo" />
			</view>
			<!-- 登录form -->
			<view class="login-card">
				<view class="login_form">
					<view class="input">
						<view class="img">
							<image style="width:27px;height: 27px;" :src="imgInfo.icon_user" />
						</view>
						<input type="number" maxlength="11" @input="inputMobile" placeholder="请输入手机号">
						<view class="img">
							<image @tap="delUser" class="img_del" :src="imgInfo.icon_del" />
						</view>
					</view>
					<view class="line" />
					<view class="input">
						<view class="img">
							<image style="width:27px;height: 27px;" :src="imgInfo.icon_pwd" />
						</view>
						<input :type="pwdType" :value="userpwd" @input="inputPwd" placeholder="请输入密码">
						<view class="img" @tap="switchPwd">
							<image v-if="pwdType=='password'" class="img_pwd_switch" :src="imgInfo.icon_pwd_invisible" />
							<image v-if="pwdType!='password'" class="img_pwd_switch" :src="imgInfo.icon_pwd_visible" />
						</view>
					</view>
				</view>
			</view>
			<!-- 登录提交 -->
			<button class="submit" type="primary" @tap="bindLogin">登录</button>
			<view class="opts">
				<text @tap="goReg" class="text">立即注册</text>
				<text @tap="findPwd" class="text">忘记密码？</text>
			</view>
			<view class="quick_login_line">
				<view class="line" />
				<text class="text">快速登录</text>
				<view class="line" />
			</view>
			<view class="quick_login_list">
				<image @tap="thirdLogin('qq')" class="item" :src="imgInfo.qq" />
				<image @tap="thirdLogin('wechat')" class="item" :src="imgInfo.wechat" />
				<image @tap="thirdLogin('weibo')" class="item" :src="imgInfo.weibo" />
			</view>
		</view>
	</view>
</template>

<script>
	import {
		mapState,
		mapActions
	} from 'vuex'
	export default {
		data() {
			return {
				mobile: '',
				userpwd: '',
				pwdType: 'password',
				imgInfo: {
					head: '/static/img/login/head.png',
					icon_user: '/static/img/login/icon_user.png',
					icon_del: '/static/img/login/icon_del.png',
					icon_pwd: '/static/img/login/icon_pwd.png',
					icon_pwd_visible: '/static/img/login/icon_pwd_visible.png',
					icon_pwd_invisible: '/static/img/login/icon_pwd_invisible.png',
					qq: '/static/img/common/qq.png',
					wechat: '/static/img/common/wechat.png',
					weibo: '/static/img/common/weibo.png'
				}
			}
		},
		methods: {
			...mapActions(['login']),
			inputMobile(e) {
				this.mobile = e.target.value
			},
			inputPwd(e) {
				this.userpwd = e.target.value
			},
			delUser() {
				this.mobile = ''
			},
			switchPwd() {
				this.pwdType = this.pwdType === 'text' ? 'password' : 'text'
			},
			bindLogin() {
				console.log('mobile:' + this.mobile + ',pwd:' + this.userpwd)
				if (this.mobile == "" || this.userpwd == ""){
					uni.showToast({title: "手机号或密码不能为空"});
					return;
				}
				let loginInfo = {
					mobile: this.mobile,
					password: this.userpwd,
				}
				this.login(loginInfo).then(res =>{
					console.log('登录成功 code:' + res.statusCode);
					uni.reLaunch({
						url: '/pages/home/home'
					})
				}).catch(err => {
					console.log('登录失败' + err);
					uni.showToast({title: "登录失败 code" + err.data.code});
				});
			},
			findPwd() {
				uni.navigateTo({
					url: '/pages/login/find-pwd'
				})
			},
			goReg() {
				uni.navigateTo({
					url: '/pages/login/register'
				})
			},
			thirdLogin(type) {
				console.log(type)
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		background-color: white;
	}

	;
	$logo-padding: 10px;
	$form-border-color: rgba(214, 214, 214, 1);
	$text-color: #B6B6B6;

	.body_bg {
		position: relative;
		align-items: center;
		min-height: 100vh;
		background-color: #FFE411;
		overflow: hidden;
		z-index: 1;

		&:before,
		&:after {
			content: "";
			position: absolute;
			left: 50%;
			min-width: 300vw;
			min-height: 300vw;
			background-color: white;
			animation-name: rotate;
			animation-iteration-count: infinite;
			animation-timing-function: linear;
		}

		&:before {
			bottom: 15vh;
			border-radius: 45%;
			animation-duration: 10s;
		}

		&:after {
			bottom: 12vh;
			opacity: .5;
			border-radius: 47%;
			animation-duration: 10s;
		}
	}

	@keyframes rotate {
		0% {
			transform: translate(-50%, 0) rotateZ(0deg);
		}

		50% {
			transform: translate(-50%, -2%) rotateZ(180deg);
		}

		100% {
			transform: translate(-50%, 0%) rotateZ(360deg);
		}
	}

	.head {
		display: flex;
		align-items: center;
		justify-content: center;
		padding-top: $logo-padding;
		padding-bottom: $logo-padding;

		.head_bg {
			border: 1px solid #fbecf1;
			border-radius: 50px;
			width: 100px;
			height: 100px;
			display: flex;
			align-items: center;
			justify-content: center;

			.head_inner_bg {
				border-radius: 40px;
				width: 80px;
				height: 80px;
				display: flex;
				background-color: #fc2c5d;
				align-items: flex-end;
				justify-content: center;
				overflow: hidden;
			}
		}
	}

	.page_login {
		padding: 10px;
		height: 400upx;
		position: relative;
		z-index: 2;
	}

	.login-card {
		border-radius: 25upx;
		padding: 10upx 25upx;
		box-shadow: 0 6upx 20upx rgba(0, 0, 0, 0.12);
		position: relative;
		margin-top: 10upx;
	}

	.login_form {
		display: flex;
		margin: 20px;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		border: 1px solid $form-border-color;
		border-radius: 10px;

		.line {
			width: 100%;
			height: 1px;
			background-color: $form-border-color;
		}

		.input {
			width: 100%;
			max-height: 45px;
			display: flex;
			padding: 3px;
			flex-direction: row;
			align-items: center;
			justify-content: center;

			.img {
				min-width: 40px;
				min-height: 40px;
				margin: 5px;
				display: flex;
				align-items: center;
				justify-content: center;
			}

			.img_del {
				width: 21px;
				height: 21px;
			}

			.img_pwd_switch {
				width: 30px;
				height: 30px;
			}

			input {
				outline: none;
				height: 30px;
				width: 100%;

				&:focus {
					outline: none;
				}
			}
		}
	}

	.submit {
		margin-top: 15px;
		margin-bottom: 15px;
		height: 84upx;
		line-height: 84upx;
		border-radius: 25upx;
		font-size: 32upx;
		background: linear-gradient(left, #FF978D, #FFBB69);
	}

	.opts {
		margin-top: 5px;
		margin-left: 5px;
		margin-right: 5px;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-between;

		.text {
			font-size: 13px;
			color: $text-color;
		}
	}

	.quick_login_line {
		margin-top: 40px;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;

		.line {
			height: 1px;
			width: 38%;
			background-color: rgba(211, 211, 213, 1);
		}

		.text {
			font-size: 13px;
			color: rgba(200, 200, 200, 1);
			margin: 2px;
		}
	}

	.quick_login_list {
		margin-top: 5px;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;

		.item {
			width: 50px;
			height: 50px;
			margin: 20px;
		}
	}
</style>
