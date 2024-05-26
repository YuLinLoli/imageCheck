package com.yulin.util

import com.alibaba.fastjson2.JSON
import com.aliyun.green20220302.Client
import com.aliyun.green20220302.models.ImageModerationRequest
import com.aliyun.green20220302.models.ImageModerationResponse
import com.aliyun.teaopenapi.models.Config
import com.aliyun.teautil.models.RuntimeOptions
import com.yulin.config.ImageCheckApiConfig
import java.util.*


object AliImageCheckUtil {
    private val client = createClient()
    /**
     * 创建请求客户端
     *
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun createClient(): Client {
        val config = Config()
        config.setAccessKeyId(ImageCheckApiConfig.aliApi.accessKeyId)
        config.setAccessKeySecret(ImageCheckApiConfig.aliApi.accessKeySecret)
        // 设置http代理。
        //config.setHttpProxy("http://10.10.xx.xx:xxxx");
        // 设置https代理。
        //config.setHttpsProxy("https://10.10.xx.xx:xxxx");
        // 接入区域和地址请根据实际情况修改
        // 接入地址列表：https://help.aliyun.com/document_detail/467828.html?#section-uib-qkw-0c8
        config.setEndpoint(ImageCheckApiConfig.aliApi.endpoint)
        return Client(config)
    }

    /**
     * 发送审核请求
     */
    @Throws(Exception::class)
    fun aliImageCheckFun(imageUrl: String): ImageModerationResponse? {
        // 创建RuntimeObject实例并设置运行参数
        val runtime = RuntimeOptions()

        // 检测参数构造。
        val serviceParameters: MutableMap<String, String> = HashMap()
        //公网可访问的URL。
        serviceParameters["imageUrl"] = imageUrl
        //待检测数据唯一标识
        serviceParameters["dataId"] = UUID.randomUUID().toString()

        val request = ImageModerationRequest()
        // 图片检测service：内容安全控制台图片增强版规则配置的serviceCode，示例：baselineCheck
        // 支持service请参考：https://help.aliyun.com/document_detail/467826.html?0#p-23b-o19-gff
        request.setService("baselineCheck")
        request.setServiceParameters(JSON.toJSONString(serviceParameters))
        var response: ImageModerationResponse? = null
        try {
            response = client.imageModerationWithOptions(request, runtime)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}