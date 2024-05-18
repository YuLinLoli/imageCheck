package com.yulin.config

import kotlinx.serialization.SerialName
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {
    @ValueDescription("主人QQ")
    @SerialName("master")
    var master: Long = 0L

    @ValueDescription("图片违规是否撤回（默认false）")
    @SerialName("withdraw")
    var withdraw: Boolean = false

    @ValueDescription("需要审核的图片的大小（单位KB）")
    @SerialName("checkImageSize")
    var checkImageSize: Int = 100

    @ValueDescription("是否储存违规图片")
    @SerialName("imageSave")
    var imageSave: Boolean = true

    @ValueDescription("图片储存路径")
    @SerialName("imageSaveLocal")
    var imageSaveLocal: String = "./image"

    @ValueDescription("是否将图片发送给主人以及抄送人")
    @SerialName("sendImage")
    var sendImage: Boolean = true

    @ValueDescription("帮助指令")
    @SerialName("helpOrder")
    var helpOrder: String = "图片审核帮助"

    @ValueDescription("抄送人")
    @ValueName("copyList")
    var copyList: MutableList<Long> by value()

    @ValueDescription("启用的群")
    @ValueName("groupList")
    var groupList: MutableList<Long> by value()

    @ValueDescription(
        "api选择\n" +
                "默认为0\n" +
                "0为百度Api"
    )
    @SerialName("apiSelect")
    var apiSelect: Int = 0

    @ValueDescription("审核失败是否启用本地审核api（默认false）")
    @SerialName("checkFailed")
    var checkFailed: Boolean = false
}