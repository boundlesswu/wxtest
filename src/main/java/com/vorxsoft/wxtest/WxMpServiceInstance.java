package com.vorxsoft.wxtest;
import java.io.InputStream;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * @Author boundlesswu
 * @Description
 * @Date 2018-03-01 10:38
 **/

public class WxMpServiceInstance {
  private WxMpService wxMpService;
  private WxMpConfigStorage wxMpConfigStorage;
  private WxMpMessageRouter wxMpMessageRouter;

  private static WxMpServiceInstance instance = null;

  public static WxMpServiceInstance getInstance() {
    if (instance == null) {
      try {
        instance = new WxMpServiceInstance();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return instance;
  }

  private WxMpServiceInstance() throws Exception {
    wxMpService = new WxMpServiceImpl();
    // 读取配置文件
    InputStream inputStream = WxMpServiceInstance.class
            .getResourceAsStream("weixin.xml");

    wxMpConfigStorage = WxMpXMLInMemoryConfigStorage.fromXml(inputStream);
    wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
    wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
  }

  public WxMpService getWxMpService() {
    return wxMpService;
  }

  public WxMpConfigStorage getWxMpConfigStorage() {
    return wxMpConfigStorage;
  }

  public WxMpMessageRouter getWxMpMessageRouter() {
    return wxMpMessageRouter;
  }

}
