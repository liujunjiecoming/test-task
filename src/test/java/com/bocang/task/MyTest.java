package com.bocang.task;

import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.aliyun.iotx.api.client.IoTApiClientBuilderParams;
import com.aliyun.iotx.api.client.IoTApiRequest;
import com.aliyun.iotx.api.client.SyncApiClient;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 上午10:42 20-10-31
 */
public class MyTest {

    // 测试
//    private static String appKey = "27925316";
//    private static String appSecret = "29145cfe65d566c6643cbd2c7b9413df";

    // 测试
//    private static String appKey = "24977769";
//    private static String appSecret = "474851cb10c62282c8c45d0afa201cb5";
    // 云端
    private static String appKey = "24977173";
    private static String appSecret = "fd74ad6eaa7200f920378a0fb1f9b936";

    private static String cloudToken = "";

    private static SyncApiClient syncClient;

    private static IoTApiRequest request;

    static {
        IoTApiClientBuilderParams ioTApiClientBuilderParams =
                new IoTApiClientBuilderParams();
        ioTApiClientBuilderParams.setAppKey(appKey);
        ioTApiClientBuilderParams.setAppSecret(appSecret);
        syncClient = new SyncApiClient(ioTApiClientBuilderParams);

        request = new IoTApiRequest();
        //设置API的版本
        //调用/cloud/token接口时，不需要传CloudToken，请参见获取云端资源Token接口的代码示例
        //调用其他接口（除获取云端资源Token接口外），都需要先调用获取云端资源Token接口，获取到token后，再传入ApiVer和CloudToken，才可以正常调用

        //设置接口的参数
        request.putParam("grantType", "project");
        request.putParam("res", "a124RTQMN66MUNTz"); //授权的资源ID。grantType为project时，res的值为project的ID。
    }

    /**
     * 查询项目下产品列表
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test6() throws UnsupportedEncodingException {
//        request.setVersion("1.0");
        request.setApiVer("1.1.2");

        //分页参数
        request.putParam("pageNo", 1);
        request.putParam("pageSize", 10);

        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/thing/productList/get", request, true);

        System.out.println("response: " + new String(response.getBody(), "UTF-8"));
    }

    /**
     * 查询单个产品
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test8() throws UnsupportedEncodingException {
        request.setApiVer("1.1.2");

        //分页参数
        request.putParam("productKey", "a1miIDJ0Jwj");
        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/thing/product/get", request, true);
        System.out.println("response: " + new String(response.getBody(), "UTF-8"));
    }

    /**
     * 获取cloudToken
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test1() throws UnsupportedEncodingException {
        request.setApiVer("1.0.0");
        //请求参数域名、path、request
        //host地址  中国站：api.link.aliyun.com     新加坡：ap-southeast-1.api-iot.aliyuncs.com     美国（弗吉尼亚）：us-east-1.api-iot.aliyuncs.com     德国（法兰克福）：eu-central-1.api-iot.aliyuncs.com
        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/token", request, true);

        System.out.println("response code = " + response.getCode() + " response = " + new String(response.getBody(), "UTF-8"));

    }

    /**
     * 刷新cloudToken
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test1Refresh() throws UnsupportedEncodingException {
        request.setApiVer("1.0.0");
        request.putParam("cloudToken", cloudToken);
        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/token/refresh", request, true);

        System.out.println("response code = " + response.getCode() + " response = " + new String(response.getBody(), "UTF-8"));
    }


    /**
     * 获取物的属性
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test2() throws UnsupportedEncodingException {
        request.setApiVer("1.0.2");
        request.setCloudToken(cloudToken);

        // request.putParam("iotId", "LTNTlXJRpJCHWWI17mmr");
        request.putParam("productKey", "a19Sm2EkuhH");
        request.putParam("deviceName", "LTNTlXJRpJCHWWI17mmr");


        //请求参数域名、path、request
        //host地址  中国站：api.link.aliyun.com     新加坡：ap-southeast-1.api-iot.aliyuncs.com     美国（弗吉尼亚）：us-east-1.api-iot.aliyuncs.com     德国（法兰克福）：eu-central-1.api-iot.aliyuncs.com
        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/thing/properties/get", request, true);

        System.out.println("response = " + new String(response.getBody(), "UTF-8"));
    }

    /**
     * 获取物的模板
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test3() throws UnsupportedEncodingException {
        request.setApiVer("1.0.2");
//        request.setCloudToken(cloudToken);

        //request.putParam("iotId", "LTNTlXJRpJCHWWI17mmr");
        request.putParam("productKey", "a19Sm2EkuhH");
        request.putParam("deviceName", "LTNTlXJRpJCHWWI17mmr");

        //请求参数域名、path、request
        //host地址  中国站：api.link.aliyun.com     新加坡：ap-southeast-1.api-iot.aliyuncs.com     美国（弗吉尼亚）：us-east-1.api-iot.aliyuncs.com     德国（法兰克福）：eu-central-1.api-iot.aliyuncs.com
        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/thing/tsl/get", request, true);

        System.out.println("response = " + new String(response.getBody(), "UTF-8"));
    }

    /**
     * 触发物的属性
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test4() throws UnsupportedEncodingException {
        request.setApiVer("1.0.2");
        request.setCloudToken(cloudToken);

        request.putParam("productKey", "a19Sm2EkuhH");
        request.putParam("deviceName", "LTNTlXJRpJCHWWI17mmr");
        request.putParam("identifier", "PowerSwitch_1");
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("PowerSwitch_1", false);
        request.putParam("args", map);


        //请求参数域名、path、request
        //host地址  中国站：api.link.aliyun.com     新加坡：ap-southeast-1.api-iot.aliyuncs.com     美国（弗吉尼亚）：us-east-1.api-iot.aliyuncs.com     德国（法兰克福）：eu-central-1.api-iot.aliyuncs.com
        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/thing/service/invoke", request, true);


        System.out.println("response = " + new String(response.getBody(), "UTF-8"));
    }

    @Test
    public void test5() throws UnsupportedEncodingException {
        request.setApiVer("1.0.4");
        request.setCloudToken(cloudToken);

        //分页参数
        request.putParam("offset", 0);
        request.putParam("count", 5);

        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/account/queryIdentityByPage", request, true);

        System.out.println(new String(response.getBody(), "UTF-8"));
    }



    /**
     * 获取设备的基本信息
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test7() throws UnsupportedEncodingException {
        request.setApiVer("1.0.2");
        // fd5ba3529b094099a27bd1cff4e6a575
        // request.setCloudToken(cloudToken);
//        request.setCloudToken("asb");

//        request.putParam("iotId", "4Tfpf9ct9Tq4RarWtfz9000100");
        request.putParam("productKey", "a1wj0xvdMuX");
        request.putParam("deviceName", "siluchangjingkaiguan_012");

        ApiResponse response = syncClient.postBody("api.link.aliyun.com", "/cloud/thing/info/get", request, true);

        System.out.println("response: " + new String(response.getBody(), "UTF-8"));
    }


    @Test
    public void test9() {
        String topic = "/juhao/a1nHAsu1H5R/pSdXUsm98VLGnshYozD8/thing/sub/register";

        String[] split = topic.split("/");
        System.out.println(split[4] + "+" + split[5]);
    }

    @Test
    public void getProductKey() {
        System.out.println(getRandomString(11));
    }


    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("HHGGetBUDZuU1bDSQuGB000100".length());
    }


}