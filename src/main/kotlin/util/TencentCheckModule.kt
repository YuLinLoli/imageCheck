package com.yulin.util

import com.qcloud.cos.COSClient
import com.qcloud.cos.ClientConfig
import com.qcloud.cos.auth.BasicCOSCredentials
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingRequest
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingResponse
import com.qcloud.cos.region.Region
import com.yulin.config.ImageCheckApiConfig

object TencentCheckModule {

    private val cred by lazy { BasicCOSCredentials(ImageCheckApiConfig.tencentApi.secretId, ImageCheckApiConfig.tencentApi.secretKey) }
    private val clientConfig by lazy {
        ClientConfig(Region(ImageCheckApiConfig.tencentApi.region))
            .apply {
                // 如果需要配置其他选项，例如超时、代理等，可以在这里添加
            }
    }
    private val client: COSClient by lazy { COSClient(cred, clientConfig) }
    fun tencentImageCheckUtil(imageUrl: String): ImageAuditingResponse? {
        val request = ImageAuditingRequest()
        request.bucketName = ""
        //2.2设置审核策略 不传则为默认策略（预设）
        //request.setBizType("");
        //2.3设置图片url
        request.detectUrl = imageUrl
        return client.imageAuditing(request)
    }
}