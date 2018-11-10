/***
 * 用户名密码登录请求接口配置
 */
const TOKEN_API = '/token/apply';

/***
 * 企业微信OAuth授权获取用户身份凭证接口
 * 使用时需要将MSG换为您需要进行授权应用的应用编码
 * 该回调地址可在浏览器访问 /token/wechat/cp/buildOAuthUrl/MSG?url=xxxx 得出，该处MSG为应用编码，视实际情况而定，url参数内容为授权成功后的回调地址
 */
// const WECHAT_TOKEN_API = '/token/wechat/cp/apply/MSG';

/***
 * 公众号OAuth授权获取用户身份凭证接口
 * 该回调地址可在浏览器访问 /token/wechat/mp/buildOAuthUrl?url=xxx 得出，url参数内容为授权成功后的回调地址
 */
const WECHAT_TOKEN_API = '/token/wechat/mp/apply';

/***
 * 公众号下获取jssdk配置参数的接口
 */
const GET_WEIXIN_JSSDK_API = '/wechat/mp/getJsSdkConfig';

/***
 * 企业微信下获取jssdk配置参数的接口，MSG为应用编码，视实际情况而定
 */
// const GET_WEIXIN_JSSDK_API = '/wechat/cp/getJsSdkConfig/MSG';

/***
 * JSSDK API列表，企业微信详见 http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BE%AE%E4%BF%A1JS-SDK%E6%8E%A5%E5%8F%A3
 * 公众号详见  https://mp.weixin.qq.com/wiki?action=doc&id=mp1421141115
 */
const WEIXIN_JSSDK_API_LIST = ['uploadImage', 'chooseImage'];

export default {
  authType: 'login',
  AUTH_TYPE: {
    wechat: 'wechat',
    login: 'login',
  },
  authHeaderKey: 'authtoken',
  authStorageKey: 'token',
  authStorageTime: 'token_time',
  AUTH_REFRESH_INTERVAL: 1800000, // token刷新间隔 1800000
  requestTimeout: 10000,
  TOKEN_API,
  WECHAT_TOKEN_API,
  GET_WEIXIN_JSSDK_API,
  WEIXIN_JSSDK_API_LIST,
  noneAuthList: [],
};
