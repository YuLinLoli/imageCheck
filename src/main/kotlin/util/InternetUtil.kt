package com.yulin.util

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.core5.http.io.entity.EntityUtils

object InternetUtil {

    fun post(url: String): JsonElement? {
        // 创建HttpPost实例
        val post = HttpPost(url)
        val execute = HttpClients.createDefault().execute(post)
        if (execute.code != 200) {
            return null
        }
        val entity = EntityUtils.toString(execute.entity)
        // 读取并输出响应内容
        return Json.parseToJsonElement(entity)
    }

}