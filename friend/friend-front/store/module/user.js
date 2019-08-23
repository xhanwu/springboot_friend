import {
	Login,
	Logout
} from '../../api/api.js'

const user = {

	state: {
		hasLogin: false,
		token: "",
		userInfo: {
			nickname: "",
			uid: -1,
			sex: "0",//0:unknown;1:male;2:female
			birthday: "0000-00-00",
			constellation: "",//星座
			qq: "",
			login: "",//登录次数
			reg_ip: "",//登录ip地址
			last_login_ip: "",//最后一次登录的ip地址
			signature: "",//个性签名
			pos_province: "",//所在省份
			pos_city: "", //所在城市
			pos_district: "",//所在乡镇
			pos_community: "",
			session_id: "",
			fans: "",
			avatar: "",
			school: "", //学校
			major: "",//专业
			enrollment_year: "0000",//入学年份
			industry: "",//行业
		},
	},
	mutations: {
		LOGIN(state, res) {
			state.hasLogin = true;//设置登录状态
			state.token = res.data.jwtToken;//设置Token
			console.log("set token " + state.token);
			state.userInfo = {
				//本地调试使用，等后台搭建好后会做修改
				nickname : res != null ? res.data.data.nickname : "Unknown",
				avatar: "/static/img/login/head.png",
				sex: 1,
				birthday: "1991-02-07",
				constellation: "水瓶座",//星座
				pos_province: "广东",//所在省份
				pos_city: "惠州", //所在城市
				school: "广东工业大学", //学校
				major: "电子科学与技术",//专业
				enrollment_year: "2010",//入学年份
				industry: "互联网技术",//行业
			};
			state.userInfo['uid'] = res != null ? res.data.data.uid : -1;//设置uid
		},
		LOGOUT(state) {
			state.hasLogin = false;
			state.userInfo = {
				nickName : ""
			};
		}
	},
	actions: {
		async login(context, loginInfo) {
			console.log("hyn loginInfo " + loginInfo);
			return await new Promise ((resolve, reject) => {
				Login(loginInfo).then(res => {
					let code = res.data.code;
					let info = res.data.info;
					console.log("hyn request success");
					console.log(res.data);
					console.log("hyn code " + code + " info " + info);
					if(code == 200){
						context.commit('LOGIN', res);
						resolve(res);
					} else {
						console.log("hyn login fail " + code + " res " + res.data);
						reject(res);
					}
				}).catch(err => {
					console.log("hyn Login error");
					//console.log(err.errMsg);
					reject(err);
				});
			})
		},
		async logout(context) {
			return await new Promise((resolve, reject) => {
				Logout().then(res => {
					console.log('hyn Logout success')
					context.commit('LOGOUT');
					resolve(res);
				}).catch(err => {
					console.log('hyn Logout error')
					context.commit('LOGOUT');
					reject(err);
				});
			})
		}
	},
}

export default user
