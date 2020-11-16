package com.jitlantis.backend.API.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SmsUtils {

    private static final String accessKeyId ="";

    private static final String accessSecret ="";

    public static void AddSmsTemplate() {
        DefaultProfile profile = DefaultProfile.getProfile("ca-Toronto", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysDomain("");
        request.setSysVersion("2017-05-25");
        request.setSysAction("AddSmsTemplate");
        request.putQueryParameter("RegionId", "ca-Toronto");
        request.putQueryParameter("TemplateType", "23");
        request.putQueryParameter("TemplateName", "23");
        request.putQueryParameter("TemplateContent", "66");
        request.putQueryParameter("Remark", "12");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void sendCellCode() {
        DefaultProfile profile = DefaultProfile.getProfile("ca-Toronto", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "");
        request.putQueryParameter("PhoneNumbers", "");
        request.putQueryParameter("SignName", "");
        request.putQueryParameter("TemplateCode", "");
        request.putQueryParameter("SignName", "Jitlantis System");
        request.putQueryParameter("TemplateCode", "");
        String code="6666";
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendCellCode();
    }
}

