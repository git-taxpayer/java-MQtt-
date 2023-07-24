//package com.example.subjson.ServiceImpl;
//
//import me.chanjar.weixin.common.error.WxErrorException;
//import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class WeChatService {
//
//    @Value("${wechat.appId}")
//    private String appId;
//
//    @Value("${wechat.appSecret}")
//    private String appSecret;
//
//    public void sendTextMessage(String openid, String content) {
//        WxMpService wxMpService = new WxMpServiceImpl();
//        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
//        config.setAppId(appId);
//        config.setSecret(appSecret);
//        wxMpService.setWxMpConfigStorage(config);
//        try {
//            System.out.println();
//        } catch (WxErrorException e) {
//            // 处理异常
//            System.out.println();
//        }
//    }
//}
