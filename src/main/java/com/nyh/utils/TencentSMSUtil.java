package com.nyh.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;


public class TencentSMSUtil {

    // 现在由于只有注册过的项目才能使用短信功能，这里做了简化
    public static boolean send(String phoneNumber,String code) {
        System.out.println("已向" + phoneNumber + "发送取件码：" + code);
        return true;
    }

    /**
     * 腾讯云短信验证码发送
     * @param phoneNumber
     * @param code
     * @return
     */
    public static boolean send1(String phoneNumber,String code) {
        boolean flag = false;
        try {
            /* 必要步骤：
             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
             * 这里采用的是从环境变量读取的方式，需要在环境变量中先设置这两个值。
             * 你也可以直接在代码中写死密钥对，但是小心不要将代码复制、上传或者分享给他人，
             * 以免泄露密钥对危及你的财产安全。
             * CAM密匙查询: https://console.cloud.tencent.com/cam/capi*/
            Credential cred = new Credential("AKIDNXKsABvPcYDdoiLjrjKejX8SWJp43lCM", "uYv1qQjlW0D9oCJ9j25rMax83h4AzXop");
            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            /* SDK默认使用POST方法。
             * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
            httpProfile.setReqMethod("POST");
            /* SDK有默认的超时时间，非必要请不要进行调整
             * 如有需要请在代码中查阅以获取最新的默认值 */
            httpProfile.setConnTimeout(60);
            /* SDK会自动指定域名。通常是不需要特地指定域名的，但是如果你访问的是金融区的服务
             * 则必须手动指定域名，例如sms的上海金融区域名： sms.ap-shanghai-fsi.tencentcloudapi.com */
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK默认用TC3-HMAC-SHA256进行签名
             * 非必要请不要修改这个字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            /* 实例化要请求产品(以sms为例)的client对象
             * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量 */
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 你可以直接查询SDK源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用IDE进行开发，可以方便的跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();
            /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
            String sdkAppId = "1400558104";
            req.setSmsSdkAppid(sdkAppId);
            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
            String signName = "it小牛";
            req.setSign(signName);
            /* 模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看 */
            String templateId = "1075067";
            req.setTemplateID(templateId);
            /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
             * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */
            String[] phoneNumberSet = {"+86"+phoneNumber};
            req.setPhoneNumberSet(phoneNumberSet);
            /* 模板参数: 若无模板参数，则设置为空 */
            String[] templateParamSet = {code};
            req.setTemplateParamSet(templateParamSet);
            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);

            // 判断短信是否发送成功
            String result = res.getSendStatusSet()[0].getCode();
            if(result.equals("Ok")){
                flag=true;
            }
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));
            // 也可以取出单个值，你可以通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
            System.out.println(res.getSendStatusSet()[0].getCode());
            System.out.println(flag);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean loginSMS(String phoneNumber,String code) {
        boolean flag = false;
        try {
            Credential cred = new Credential("AKIDNXKsABvPcYDdoiLjrjKejX8SWJp43lCM", "uYv1qQjlW0D9oCJ9j25rMax83h4AzXop");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            String sdkAppId = "1400558104";
            req.setSmsSdkAppid(sdkAppId);
            String signName = "it小牛";
            req.setSign(signName);
            String templateId = "1075106";
            req.setTemplateID(templateId);
            String[] phoneNumberSet = {"+86"+phoneNumber};
            req.setPhoneNumberSet(phoneNumberSet);
            String[] templateParamSet = {code,"5"};
            req.setTemplateParamSet(templateParamSet);
            SendSmsResponse res = client.SendSms(req);

            // 判断短信是否发送成功
            String result = res.getSendStatusSet()[0].getCode();
            if(result.equals("Ok")){
                flag=true;
            }
            System.out.println(SendSmsResponse.toJsonString(res));
            System.out.println(res.getSendStatusSet()[0].getCode());
            System.out.println(flag);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return flag;
    }

}



