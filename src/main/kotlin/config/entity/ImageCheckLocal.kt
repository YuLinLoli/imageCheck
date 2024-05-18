package com.yulin.config.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.ValueDescription

@Serializable
data class ImageCheckLocal(
    @ValueDescription("本地地址")
    @SerialName("localAddress")
    var localAddress: String = "",
    @ValueDescription("本地地址")
    @SerialName("imagePron")
    var imagePron: Double = 0.45
)