package com.vorxsoft.wxtest;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

import java.util.List;

/**
 * @Author boundlesswu
 * @Description
 * @Date 2018-03-01 11:42
 **/
public class WxSendMsg {
  public static String getOpenId(WxMpService wxService,String nickname){
    String ret=null;
    WxMpUserList wxUserList=null;
    try {
      wxUserList = wxService.getUserService().userList(null);
      String lang = "zh_CN"; //语言
      WxMpUser user;
      List<String> openids = wxUserList.getOpenids();
      for (String openid : openids) {
        try {
          user = wxService.getUserService().userInfo(openid, lang);
          //System.out.println(user);
          if (user.getNickname().equals(nickname))
            ret = new String(openid);
        } catch (WxErrorException e) {
          e.printStackTrace();
        }
      }
    } catch (WxErrorException e) {
      e.printStackTrace();
    }
    return ret;
  }
  public static void main(String args[]) {
//    WxMpService wxMpService = WxMpServiceInstance.getInstance()
//            .getWxMpService();
//    try {
//      WxMpUserList wxUserList = wxMpService.getUserService().userList(null);
//    } catch (WxErrorException e) {
//      e.printStackTrace();
//    }
    WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
    config.setAppId("wxe39b786c0a3d592f"); // 设置微信公众号的appid
    config.setSecret("3d34a4d8729850fa96a95e75dfb5deb2"); // 设置微信公众号的app corpSecret
    config.setToken("公众号Token"); // 设置微信公众号的token
    config.setAesKey("公众号EncodingAESKey"); // 设置微信公众号的EncodingAESKey

    WxMpService wxService = new WxMpServiceImpl();
    wxService.setWxMpConfigStorage(config);
    WxMpUserList wxUserList = new WxMpUserList();
    try {
      wxUserList = wxService.getUserService().userList(null);
      System.out.println(wxUserList);
    } catch (WxErrorException e) {
      e.printStackTrace();
    }
// 用户的openid在下面地址获得
// https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=用户管理&form=获取关注者列表接口%20/user/get
    //String openid = "oNJyy1A5CJrt45X9IMyhDKRbZAaI";
//    String openid = wxUserList.getNextOpenid();
//    String lang = "zh_CN"; //语言
//    WxMpUser user;
//    try {
//      user = wxService.getUserService().userInfo(openid,lang);
//      System.out.println(user);
//    } catch (WxErrorException e) {
//      e.printStackTrace();
//    }
//    String openid = getOpenId(wxService,"无边");
//    WxMpKefuMessage message = WxMpKefuMessage.TEXT().toUser(openid).content("Hello World").build();
//    try {
//      wxService.getKefuService().sendKefuMessage(message);
//    } catch (WxErrorException e) {
//      e.printStackTrace();
//    }

//    String url = "http://e27cb240.ngrok.io";
//
//    wxService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
//    WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
//    try {
//      WxMpUser wxMpUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
//    } catch (WxErrorException e) {
//      e.printStackTrace();
//    }
    List<String> openids = wxUserList.getOpenids();
    for (String openid : openids) {
      WxMpKefuMessage message = WxMpKefuMessage.TEXT().toUser(openid).content("Hello World").build();
      try {
        wxService.getKefuService().sendKefuMessage(message);
      } catch (WxErrorException e) {
        e.printStackTrace();
      }
    }
  }
}
