package com.yulin.module

import com.yulin.ImageCheck.logger
import com.yulin.config.Config
import com.yulin.entity.ApiEnum.entries
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.At

object OrderModule {

    suspend fun orderModule(event: MessageEvent) {
        if (event.sender.id != Config.master) {
            return
        }
        val message = event.message.contentToString()
        when{
            message.startsWith("指令-添加抄送人") -> {
                try {
                    if (message.contains("抄送人=")){
                        Config.copyList.add(message.split("=")[1].toLong())
                        return
                    }
                }catch (e: Exception){
                    logger.error("请输入正确的指令！例如：指令-添加抄送人=123456789")
                    e.printStackTrace()
                    return
                }
                for (at in event.message) {
                    if (at is At){
                        Config.copyList.add(at.target)
                    }
                }
                return
            }
            message.startsWith("指令-审核") -> {
                try {
                    Config.apiSelect = message.split("=")[1].toInt()
                    event.subject.sendMessage("已成功切换图片审核为${entries.firstOrNull { it.code == Config.apiSelect }?.apiName}")
                    return
                }catch (e:Exception) {
                    logger.error("请输入正确的指令！例如：指令-审核=1")
                    e.printStackTrace()
                    return
                }
            }
            message.startsWith("指令-") -> {
                if (event.sender.id != event.subject.id){
                    return
                }
                if (message.contains("开启本群图片审核")) {
                    Config.groupList.add(event.subject.id)
                    return
                }
                if (message.contains("关闭本群图片审核")) {
                    Config.groupList.remove(event.subject.id)
                    return
                }
                if (message.contains("关闭图片审核")) {
                    Config.onOroff = false
                    return
                }
                if (message.contains("开启图片审核")) {
                    Config.onOroff = true
                    return
                }
                return
            }
        }
    }
}