package com.yulin.config.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.ValueDescription

/**
 * 百度审核API配置文件
 */
@Serializable
data class ImageCheckBaidu(
    @ValueDescription("百度appid")
    @SerialName("appId")
    var appId: String = "",
    @ValueDescription("百度apikey")
    @SerialName("apiKey")
    var apiKey: String = "",
    @ValueDescription("百度secretKey")
    @SerialName("secretKey")
    var secretKey: String = "",
    @ValueDescription("是否启用疑似涩图的图片撤回")
    @SerialName("suspected")
    var suspected: Boolean = false
)
