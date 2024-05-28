package com.yulin.config.entity

import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName

data class ImageCheckTencent(
    @ValueDescription("腾讯secretId")
    @ValueName("secretId")
    var secretId: String = "",
    @ValueDescription("腾讯secretKey")
    @ValueName("secretKey")
    var secretKey: String = "",
    @ValueDescription("腾讯地区的服务器")
    @ValueName("region")
    var region: String = "",
    @ValueDescription("图片审核阈值(0采用腾讯默认阈值)")
    @ValueName("imageScore")
    var imageScore: Int = 0
)
