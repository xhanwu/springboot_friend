const getters = {
	userInfo: state => state.user.userInfo,
	hasLogin: state => state.user.hasLogin,
	token : state => state.user.token,
}

export default getters