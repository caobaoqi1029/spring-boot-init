package com.mcddhub.init.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * NetUtils
 *
 * @version 1.0.0
 * @author: caobaoqi1029
 * @date: 2024/10/1 12:22
 */
@Slf4j
public class NetUtils {
    /**
     * 获取客户端 IP 地址
     *
     * @param request request
     * @return ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                // 根据网卡取本机配置的 IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    if (log.isDebugEnabled()) {
                        log.debug(e.getMessage(), e);
                    }
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        // 多个代理的情况，第一个 IP 为客户端真实 IP,多个 IP 按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if (ip == null) {
            return "127.0.0.1";
        }
        return ip;
    }

}
