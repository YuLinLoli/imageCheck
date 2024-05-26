package com.yulin.module

import com.yulin.ImageCheck.logger
import com.yulin.config.Config
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.isContextIdenticalWith

object OrderModule {

    fun orderModule(event: MessageEvent) {
        if (event.sender.id != Config.master) {
            return
        }
        val message = event.message.contentToString()
        when{
            message.startsWith("指令-审核") -> {
                try {
                    Config.apiSelect = message.split("=")[1].toInt()
                    return
                }catch (e:Exception) {
                    logger.error("请输入正确的指令！例如：指令-审核=1")
                    e.printStackTrace()
                    return
                }
            }
            message.startsWith("指令-i") -> {
                if (event.sender.id != event.subject.id){
                    return
                }
                if (message.contains("ion")) {
                    Config.groupList.add(event.subject.id)
                }
                if (message.contains("ioff")) {
                    Config.groupList.remove(event.subject.id)
                }
                return
            }

        }
    }
}