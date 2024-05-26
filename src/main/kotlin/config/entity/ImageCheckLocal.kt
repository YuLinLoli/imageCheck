package com.yulin.config.entity


import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName

@Serializable
data class ImageCheckLocal(
    @ValueDescription("本地地址")
    @ValueName("localAddress")
    var localAddress: String = "",
    @ValueDescription("本地地址")
    @ValueName("imagePron")
    var imagePron: Double = 0.45
)