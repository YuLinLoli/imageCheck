package com.yulin.module

import com.yulin.config.Config
import net.mamoe.mirai.event.events.MessageEvent

object HelpModule {
    suspend fun help(event: MessageEvent) {
        if (event.message.contentToString() == Config.helpOrder && event.sender.id == Config.master) {
            event.subject.sendMessage("[帮助]\n" +
                    "指令-切换图片审核=0|1|2\n" +
                    "(0为不推荐的本地审核api，1为百度审核，2位阿里审核)\n" +
                    "指令-ion|ioff(开启或者关闭本群的图片审核)")
        }
    }
}