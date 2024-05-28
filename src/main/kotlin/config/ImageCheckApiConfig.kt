package com.yulin.config

import com.yulin.config.entity.ImageCheckAlibaba
import com.yulin.config.entity.ImageCheckBaidu
import com.yulin.config.entity.ImageCheckLocal
import com.yulin.config.entity.ImageCheckTencent
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName
import net.mamoe.mirai.console.data.value

object ImageCheckApiConfig : AutoSavePluginConfig("imageCheckApiConfig") {
    @ValueDescription(
        "百度审核api\n" +
                "百度appid\n" +
                "appID\n" +
                "apiKey\n" +
                "secretKey\n" +
                "是否启用疑似涩图图片撤回"
    )
    @ValueName("baiduApi")
    var baiduApi: ImageCheckBaidu by value()

    @ValueDescription(
        "本地审核api\n" +
                "本地审核api地址\n" +
                "本地审核阈值"
    )
    @ValueName("localApi")
    var localApi: ImageCheckLocal by value()

    @ValueDescription(
        "阿里审核api\n" +
                "RAM用户AccessKey ID\n" +
                "RAM用户AccessKey Secret\n" +
                "接入区域的服务器网址\n" +
                "审核规则(与阿里云的规则配置内的规则相匹配，只支持一个)\n" +
                "图片审核阈值0-100(保留两位小数，越大越严格)"
    )
    @ValueName("aliApi")
    var aliApi: ImageCheckAlibaba by value()
    @ValueDescription("腾讯审核api\n" +
            "腾讯secretId\n" +
            "腾讯secretKey\n" +
            "腾讯地区的服务器(示例：<BucketName-APPID>.cos.<Region>.myqcloud.com(image1-1234567890.cos.ap-beijing.myqcloud.com))\n" +
            "图片审核阈值(0采用腾讯默认阈值)")
    @ValueName("tencentApi")
    var tencentApi: ImageCheckTencent by value()
}