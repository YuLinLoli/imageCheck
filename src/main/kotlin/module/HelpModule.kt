package com.yulin.module

import com.yulin.config.Config
import net.mamoe.mirai.event.events.MessageEvent

object HelpModule {
    suspend fun help(event: MessageEvent) {
        if (event.message.contentToString() == Config.helpOrder && event.sender.id == Config.master) {
            event.subject.sendMessage("[帮助]\n" +
                    "指令-切换图片审核=0|1|2|3\n" +
                    "(0为不推荐的本地审核api，1为百度审核，2为阿里审核，3为腾讯审核)\n" +
                    "指令-关闭|开启本群图片审核(开启或者关闭本群的图片审核)\n" +
                    "指令-关闭|开启图片审核(开启或者关闭本插件（帮助不会关闭）)\n" +
                    "指令-添加抄送人=(示例：指令-添加抄送人=12345678)\n" +
                    "指令-添加抄送人(可艾特，可以艾特多人)")
        }
    }
}