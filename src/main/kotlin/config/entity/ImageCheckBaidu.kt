package com.yulin.config.entity


import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName

/**
 * 百度审核API配置文件
 */
@Serializable
data class ImageCheckBaidu(
    @ValueDescription("百度appid")
    @ValueName("appId")
    var appId: String = "",
    @ValueDescription("百度apikey")
    @ValueName("apiKey")
    var apiKey: String = "",
    @ValueDescription("百度secretKey")
    @ValueName("secretKey")
    var secretKey: String = "",
    @ValueDescription("是否启用疑似涩图的图片撤回")
    @ValueName("suspected")
    var suspected: Boolean = false
)
