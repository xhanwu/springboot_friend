/**
 * Request 0.0.5
 * @Class uni-app request网络请求库
 * @Author lu-ch
 * @Date 2019-06-05
 * @Email webwork.s@qq.com
 * **/
import store from '../store'

export default class Request {
  config = {
    //此处设置服务器的baseUrl
    //局域网EasyMock测试账号：yangning.hong 密码Aa123456
	//baseUrl: 'http://10.92.34.48:7300/mock/5d26e44c19ad5d00200d5d10/example',
    baseUrl: 'http://10.92.34.48:88',
    header: {
      'Content-Type': 'application/x-www-form-urlencoded',
	  'Token': ''
    },
    method: 'GET',
    dataType: 'json',
    responseType: 'text',
    success () {
    },
    fail () {
    },
    complete () {
    }
  }

  static posUrl (url) { /* 判断url是否为绝对路径 */
    return /(http|https):\/\/([\w.]+\/?)\S*/.test(url)
  }

  interceptor = {
    request (f) {
      if (f) {
        Request.requestBeforeFun = f
      }
    },
    response (f) {
      if (f) {
        Request.requestComFun = f
      }
    }
  }

  static requestBeforeFun (config) {
    return config
  }

  static requestComFun (response) {
    return response
  }

  setConfig (f) {
    this.config = f(this.config)
  }

  request (options = {}) {
    options.baseUrl = options.baseUrl || this.config.baseUrl
    options.dataType = options.dataType || this.config.dataType
    options.url = Request.posUrl(options.url) ? options.url : (options.baseUrl + options.url)
    options.data = options.data || {}
    options.header = options.header || this.config.header
    options.method = options.method || this.config.method
	//设置Token added by yangning.hong
	options.header['Token'] = store.state.user.token
    return new Promise((resolve, reject) => {
      let next = true
      let _config = null
      options.complete = (response) => {
        let statusCode = response.statusCode
        response.config = _config
        response = Request.requestComFun(response)
        if (statusCode === 200) { // 成功
          resolve(response)
        } else {
          reject(response)
        }
      }
      let cancel = (t = 'handle cancel') => {
        let err = {
          errMsg: t,
          config: afC
        }
        reject(err)
        next = false
      }
      let afC = { ...this.config, ...options }
      _config = { ...afC, ...Request.requestBeforeFun(afC, cancel) }
      if (!next) return
      uni.request(_config)
    })
  }

  get (url, data, options = {}) {
    options.url = url
    options.data = data
    options.method = 'GET'
    return this.request(options)
  }

  post (url, data, options = {}) {
    options.url = url
    options.data = data
    options.method = 'POST'
    return this.request(options)
  }
	
	put (url, data, options = {}) {
	  options.url = url
	  options.data = data
	  options.method = 'PUT'
	  return this.request(options)
	}
}
