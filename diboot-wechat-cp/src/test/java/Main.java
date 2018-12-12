import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpAgent;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;

/**
 * @author Mazc@dibo.ltd
 * @version 2018/9/3
 *
 */
public class Main {
    public static void main(String[] args) {
        String corpId = "";
        WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
        config.setCorpId(corpId);

        int agentId = 7;
        config.setCorpSecret("");

        config.setAgentId(agentId);
        // 实例化
        WxCpServiceImpl wxCpMsgService = new WxCpServiceImpl();
        wxCpMsgService.setWxCpConfigStorage(config);
        try{
            WxCpAgent agent =  wxCpMsgService.getAgentService().get(agentId);
            agent.setRedirectDomain("cs.elementwin.com");
            agent.setHomeUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+corpId+"&redirect_uri=http://cs.elementwin.com/business/wechat/cp/init/"+agentId+"&response_type=code&scope=snsapi_base&state=ewwx#wechat_redirect");
            String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/set";
            wxCpMsgService.post(url, agent.toJson());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
