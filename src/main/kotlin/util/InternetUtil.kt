package com.yulin.util

import com.yulin.ImageCheck.logger
import com.yulin.entity.LocalBody
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*

import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


object InternetUtil {

//    fun post(url: String): JsonElement? {
//        // 创建HttpPost实例
//        val post = HttpPost(url)
//        val execute = HttpClients.createDefault().execute(post)
//        if (execute.code != 200) {
//            return null
//        }
//        val entity = EntityUtils.toString(execute.entity)
//        // 读取并输出响应内容
//        return Json.parseToJsonElement(entity)
//    }

    suspend fun get(url: String): HttpResponse? {
        val httpClient = HttpClient()
            httpClient.use {
                val response = it.get(url)
                if (response.status.isSuccess()) {
                    return response
                }
            }

            return null
        }

    /**
     * 获取本地审核结果
     */
    suspend fun getLocalBody(url: String): LocalBody? {
            val httpClient = HttpClient {
                install(ContentNegotiation) {
                    json()
                }
            }
            httpClient.use {
                val get = it.get(url)
                if (get.status.isSuccess()) return get.body<LocalBody>()
                return null
            }
    }



    suspend fun post(url: String): HttpResponse? {
        HttpClient().use {
            val response = it.post(url)
            if (response.status.isSuccess()) {
                logger.info(response.toString())
                return response
            }
        }
        return null
    }

}