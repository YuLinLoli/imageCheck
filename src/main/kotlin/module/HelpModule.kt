package com.yulin.module

import com.yulin.config.Config
import net.mamoe.mirai.event.events.MessageEvent

object HelpModule {
    suspend fun help(event: MessageEvent) {
        if (event.message.contentToString() == Config.helpOrder) {
            event.subject.sendMessage("[帮助]\n" +
                    "")
        }
    }
}