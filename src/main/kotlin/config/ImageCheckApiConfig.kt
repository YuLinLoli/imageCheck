package com.yulin.config

import com.yulin.config.entity.ImageCheckBaidu
import com.yulin.config.entity.ImageCheckLocal
import kotlinx.serialization.SerialName
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object ImageCheckApiConfig : AutoSavePluginConfig("imageCheckApiConfig") {
    @ValueDescription(
        "百度appid\n" +
                "appID\n" +
                "apiKey\n" +
                "secretKey\n" +
                "是否启用疑似涩图图片撤回"
    )
    @SerialName("baiduApi")
    var baiduApi: ImageCheckBaidu by value()

    @ValueDescription(
        "本地审核api\n" +
                "本地审核api地址\n" +
                "本地审核阈值"
    )
    @SerialName("localApi")
    var localApi: ImageCheckLocal by value()
}