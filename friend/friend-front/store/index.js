import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters.js'
import user from './module/user.js'

Vue.use(Vuex)

const store = new Vuex.Store({
	modules: {
		user
	},
	getters
})

export default store
