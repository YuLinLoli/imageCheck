package com.yulin.module

import com.alibaba.fastjson.JSON.parseObject
import com.baidu.aip.contentcensor.AipContentCensor
import com.baidu.aip.contentcensor.EImgType
import com.yulin.ImageCheck.logger
import com.yulin.config.Config
import com.yulin.config.ImageCheckApiConfig
import com.yulin.entity.CheckResult
import com.yulin.util.CacheUtil
import com.yulin.util.InternetUtil
import com.yulin.util.YuLinImageUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonElement
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.Image
import org.json.JSONObject
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

object ImageCheckModule {

    /**
     * 图片审核主方法
     */
    suspend fun imageCheck(event: GroupMessageEvent) {
        if (!Config.groupList.contains(event.group.id)) {
            return
        }
        for (message in event.message) {
            if (message is Image) {
                val imageUrl = YuLinImageUtil.getImageUrl(message)
                val imageToBytesArray = YuLinImageUtil.imageToBytesArray(imageUrl) ?: continue
                val mimeType = CacheUtil.getMimeType(imageToBytesArray)
                if (mimeType == "gif" || imageToBytesArray.size() / 1000 < Config.checkImageSize) {
                    withContext(Dispatchers.IO) {
                        imageToBytesArray.close()
                    }
                    continue
                }
                var b: CheckResult
                try {
                    b = when (Config.apiSelect) {
                        0 -> baiduImageCheck(imageUrl)
                        1 -> localImageCheck(imageUrl)
                        else -> {
                            return
                        }
                    }
                } catch (e: Exception) {
                    event.bot.getFriend(Config.master)?.sendMessage("出现错误！意料之外的错误！请带上日志去github反馈issues\n" +
                            "https://github.com/YuLinLoli/imageCheck/issues")
                    event.bot.getFriend(Config.master)?.sendMessage(message)
                    withContext(Dispatchers.IO) {
                        imageToBytesArray.close()
                    }
                    e.printStackTrace()
                    return
                }
                if (b.boolean == null) {
                    event.bot.getFriend(Config.master)?.sendMessage("出现错误！(动态图容易出现错误，可以忽略)错误信息：${b.msg}\n错误图片：")
                    event.bot.getFriend(Config.master)?.sendMessage(message)
                    return
                }
                if (!b.boolean!! && Config.imageSave) {
                    imageToBytesArray.use {
                        it.write((Math.random() * 100).toInt() + 1)
                        val uuid = UUID.randomUUID().toString().replace("-", "")
                        val saveToLocal = CacheUtil.saveToLocal(it, "$uuid.png")
                        logger.info("文件储存到：$saveToLocal")
                    }
                }
                if (Config.sendImage) {
                    event.bot.getFriend(Config.master)
                        ?.sendMessage(message + "\r" + imageUrl + "\rGroup:${event.group.name + "(" + event.group.id + ")"}")
                    if (Config.copyList.size != 0) {
                        for (l in Config.copyList) {
                            event.bot.getFriend(Config.master)
                                ?.sendMessage(message + "\r" + imageUrl + "\rGroup:${event.group.name + "(" + event.group.id + ")"}")
                        }
                    }
                }
            }
        }
    }

    /**
     * 百度图片审核
     * 检测图片是否合规，如果不合规返回 false
     */
    private fun baiduImageCheck(url: String): CheckResult {
        var jsonObject = JSONObject()
        try {
            val imageCheck = ImageCheckApiConfig.baiduApi
            val client = AipContentCensor(imageCheck.appId, imageCheck.apiKey, imageCheck.secretKey)
            jsonObject = client.imageCensorUserDefined(url, EImgType.URL, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            val string = jsonObject.getString("conclusion")
            if (string == "合规") {
                return CheckResult(true, "合规")
            }
            if (string == "疑似") {
                return CheckResult(!ImageCheckApiConfig.baiduApi.suspected, "疑似")
            }
            return CheckResult(false, "是涩图！")
        } catch (e: Exception) {
            if (Config.checkFailed) {
                return localImageCheck(url)
            }
            return CheckResult(null, jsonObject.getString("error_msg"))

        } finally {
            logger.info(jsonObject.toString())
        }
    }

    /**
     * 本地图片审核
     */
    private fun localImageCheck(url: String): CheckResult {
        val imageUrl = ImageCheckApiConfig.localApi.localAddress + url
        var data: JsonElement? = InternetUtil.post(imageUrl)
        for (i in 1..3) {
            if (data == null) {
                logger.info("data is null , 重试${i}次")
                data = InternetUtil.post(imageUrl)
            } else {
                break
            }
        }
        val pron =
            BigDecimal(parseObject(data.toString())["porn"].toString().toDouble()).setScale(2, RoundingMode.HALF_UP)

        return CheckResult(pron compareTo BigDecimal(ImageCheckApiConfig.localApi.imagePron) < 0, "data is null")
    }

}