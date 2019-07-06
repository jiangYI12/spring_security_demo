package com.springsecurity.spring_security_demo.Util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.rmi.ServerException;

public class sendSms {
    public static void sendMsg(String phone,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI2pHwBJ458ssK", "enMRuies7TkAoC6CkIkpIwUSrvZgn7");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        request.putQueryParameter("PhoneNumber", phone);
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", "13202361544");
        request.putQueryParameter("TemplateCode", "SMS_169635103");
        request.putQueryParameter("TemplateParam", "{code:'" + code + "'}");
        request.putQueryParameter("SignName", "博客网");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
