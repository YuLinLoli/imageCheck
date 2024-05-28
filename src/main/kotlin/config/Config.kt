package com.yulin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {
    @ValueDescription("图片审核是否开启（默认false）")
    @ValueName("onOroff")
    var onOroff: Boolean by value(false)

    @ValueDescription("主人QQ")
    @ValueName("master")
    var master: Long by value(0L)

    @ValueDescription("图片违规是否撤回（默认false）")
    @ValueName("withdraw")
    var withdraw: Boolean by value(false)

    @ValueDescription("需要审核的图片的大小（单位KB）")
    @ValueName("checkImageSize")
    var checkImageSize: Int by value(100)

    @ValueDescription("是否储存违规图片")
    @ValueName("imageSave")
    var imageSave: Boolean by value(true)

    @ValueDescription("是否启用疑似涩图的图片保存")
    @ValueName("suspected")
    var suspected: Boolean = false

    @ValueDescription("图片储存路径")
    @ValueName("imageSaveLocal")
    var imageSaveLocal: String by value("./image")

    @ValueDescription("是否将图片发送给主人以及抄送人")
    @ValueName("sendImage")
    var sendImage: Boolean by value(true)

    @ValueDescription("帮助指令")
    @ValueName("helpOrder")
    var helpOrder: String by value("i帮助")

    @ValueDescription("抄送人")
    @ValueName("copyList")
    var copyList: MutableList<Long> by value()

    @ValueDescription("启用的群")
    @ValueName("groupList")
    var groupList: MutableList<Long> by value()

    @ValueDescription(
        "api选择\n" +
                "默认为1\n" +
                "1为百度Api\n" +
                "2为阿里Api\n" +
                "3为腾讯Api\n" +
                "0为不推荐的本地审核api"
    )
    @ValueName("apiSelect")
    var apiSelect: Int by value(1)

    @ValueDescription("审核失败是否启用本地审核api（默认false，不推荐）")
    @ValueName("checkFailed")
    var checkFailed: Boolean by value(false)
}