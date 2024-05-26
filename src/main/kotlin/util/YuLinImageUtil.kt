package com.yulin.util;

import com.yulin.config.Config
import com.yulin.util.InternetUtil.get
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.MessageSource.Key.recall
import java.io.ByteArrayOutputStream

object YuLinImageUtil {

    /**
     * 获取图片可访问的URL
     * image: Mirai Data image
     * return: String URL
     */
    suspend fun getImageUrl(image: Image): String {
        val queryUrl = image.queryUrl()
        if (image.queryUrl().contains("new")) {
            return "https://gchat.qpic.cn/gchatpic_new/114514/1919-810-" + queryUrl.split("-").last()
        }
        return queryUrl
    }

    /**
     * 如果图片违规就尝试调用此方法撤回
     * event: GroupMessageEvent
     * image: Mirai Data image
     */
    suspend fun imageWithdraw(event: GroupMessageEvent, imageUrl: String) {
        if (!Config.withdraw) {
            return
        }
        if (event.sender.permission < event.group.botPermission) {
            event.message.recall()
            event.subject.sendMessage("图片违规，已撤回！")
            event.subject.sendMessage(imageUrl)
        } else {
            event.subject.sendMessage("图片违规，但是不能撤回比自己权限高的人发的图片！")
            event.subject.sendMessage(imageUrl)
        }
        return
    }

    /**
     * 获取图片ByteArrayOutputStream，注意需要关闭资源！
     * url: String图片url
     */
    suspend fun imageToBytesArray(url: String): ByteArrayOutputStream? {
        val response = get(url) ?: return null
        if (response.status.isSuccess()) {
            // 创建ByteArrayOutputStream来存储图片内容
            val byteArrayOutputStream = ByteArrayOutputStream()
            // 将响应体的内容读取到ByteArrayOutputStream中
            val readBytes = response.readBytes()
            withContext(Dispatchers.IO) {
                byteArrayOutputStream.write(readBytes)
            }
            return byteArrayOutputStream

        }
        return null
    }

}
