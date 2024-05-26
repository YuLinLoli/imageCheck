package com.yulin.config.entity

import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.ValueName

/**
 * 阿里审核api配置文件
 */
@Serializable
data class ImageCheckAlibaba(
    @ValueName("accessKeyId")
    var accessKeyId: String = "",
    @ValueName("accessKeySecret")
    var accessKeySecret: String = "",
    @ValueName("endpoint")
    var endpoint: String = "https://green-cip.cn-beijing.aliyuncs.com",
    @ValueName("checkRule")
    var checkRule: String = "baselineCheck",
    @ValueName("checkScore")
    var checkScore: Float = 80.00f


)
