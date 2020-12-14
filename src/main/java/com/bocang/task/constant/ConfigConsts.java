package com.bocang.task.constant;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author LJJ
 * @version 1.0
 * @Description 自定义系统常量
 * @date 2020/8/25 下午5:58
 */
public class ConfigConsts {

    /**
     * 语言 - 中文
     */
    public static final String LANG_ZH = "zh";

    /**
     * 语言 - 英文
     */
    public static final String LANG_EN = "en";

    /**
     * 缓存系统设置信息
     */
    public static final String REDIS_SYSTEM_SETTING = "system_setting";


    public final static String AMAZON_RESPONSE_SUCCESS = "success";

    /**
     * token
     */
    public static final String HEADER_TOKEN = "x-auth-token";

    /**
     * 此变量用来表示通用的权限所需的用户名和角色
     */
    public static final String GENERAL_ROLE_USER = "general";

    /**
     * 更新数据时记得更新缓存
     * redis 缓存账户信息 key
     */
    public static final String REDIS_USER_INFO = "user_info";

    /**
     * 更新数据时记得更新缓存
     * redis 缓存账户权限 key
     */
    public static final String REDIS_SHIRO = "user_shiro";

    /**
     * 缓存系统所有注册用户手机
     */
    public static final String REDIS_PHONE_SET = "phone_check";

    /**
     * 缓存系统所有注册用户邮箱
     */
    public static final String REDIS_EMAIL_SET = "email_check";

    /**
     * 系统代码中文内容
     */
    public static Map<Integer, String> ERROR_MSG = Maps.newHashMap();
    /**
     * 系统代码英文内容
     */
    public static Map<Integer, String> ERROR_MSG_EN = Maps.newHashMap();

    /**
     * 户需要登录检测的url列表
     */
    public static final String SYSTEM_SETTING_NOLOGINREQUIRED = "system.web.noLoginRequired";

    /**
     * 注册邮箱验证秘钥
     */
    public static final String SYSTEM_SETTING_SECRETKEY = "system.web.secretKey";

    /**
     * token失效时间
     */
    public static final String SYSTEM_SETTING_TOKENTIMEOUT = "system.web.tokenTimeOut";


    public static String getErrorMsg(Integer code) {
        return ERROR_MSG.containsKey(code) ? ERROR_MSG.get(code) : "";
    }

    /**
     * 验证码类型 - 手机
     */
    public static final Integer VERIFYCODE_TYPE_PHONE = 1;

    /**
     * 验证码类型 - 邮箱
     */
    public static final Integer VERIFYCODE_TYPE_EMAIL = 2;

    /**
     * 阿里云短信服务 access_key_id
     */
    public static final String SEND_MAIL_ACCESS_KEY_ID = "access.key.id";

    /**
     * 阿里云短信服务 access_key_secret
     */
    public static final String SEND_MAIL_ACCESS_KEY_SECRET = "access.key.secret";

    /**
     * 阿里云短信服务 domain
     */
    public static final String SEND_MAIL_DOMAIN = "send.mail.domain";

    /**
     * 阿里云短信服务 version
     */
    public static final String SEND_MAIL_VERSION = "send.mail.version";

    /**
     * 阿里云短信服务 action
     */
    public static final String SEND_MAIL_ACTION = "send.mail.action";

    /**
     * 阿里云短信服务 RegionId
     */
    public static final String SEND_MAIL_REGIONID = "send.mail.regionid";

    /**
     * 阿里云短信服务 TemplateCode
     */
    public static final String SEND_MAIL_TEMPLATE_CODE = "send.mail.templateCode";

    /**
     * 阿里云短信服务 SignName
     */
    public static final String SEND_MAIL_SIGN_NAME = "send.mail.signName";

    /**
     * 缓存设备中对应的topic信息
     */
    public static final String DEVICE_TOPIC = "device:topic";

}
