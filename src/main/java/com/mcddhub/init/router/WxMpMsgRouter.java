package com.mcddhub.init.router;

import com.mcddhub.init.constant.WxMpConstant;
import com.mcddhub.init.handler.EventHandler;
import com.mcddhub.init.handler.MessageHandler;
import com.mcddhub.init.handler.SubscribeHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * WxMpMsgRouter
 *
 * @version 1.0.0
 * @author: caobaoqi1029
 * @date: 2024/10/1 12:51
 */
@Configuration
public class WxMpMsgRouter {

    @Resource
    private WxMpService wxMpService;

    @Resource
    private EventHandler eventHandler;

    @Resource
    private MessageHandler messageHandler;

    @Resource
    private SubscribeHandler subscribeHandler;

    @Bean
    public WxMpMessageRouter getWxMsgRouter() {
        WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        // 消息
        router.rule()
            .async(false)
            .msgType(WxConsts.XmlMsgType.TEXT)
            .handler(messageHandler)
            .end();
        // 关注
        router.rule()
            .async(false)
            .msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SUBSCRIBE)
            .handler(subscribeHandler)
            .end();
        // 点击按钮
        router.rule()
            .async(false)
            .msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.CLICK)
            .eventKey(WxMpConstant.CLICK_MENU_KEY)
            .handler(eventHandler)
            .end();
        return router;
    }
}
