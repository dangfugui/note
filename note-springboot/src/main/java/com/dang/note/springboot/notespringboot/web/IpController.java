package com.dang.note.springboot.notespringboot.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dang.note.springboot.notespringboot.core.ApiResult;

@RestController
@RequestMapping("/ip")
public class IpController {
    private static Logger log = LoggerFactory.getLogger(IpController.class);

    private static String address = "";

    static {
        try {
            address = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取本机ip 失败", e);
        }
    }

    @RequestMapping(value = "/getIp")
    public ApiResult getIp() {
        return ApiResult.success(address);
    }
}
