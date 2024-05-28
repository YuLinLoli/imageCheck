package com.yulin.entity

enum class ApiEnum(val code: Int, val apiName: String) {
    BAIDU(1, "百度"),
    ALI(2, "阿里"),
    TENCENT(3, "腾讯"),
    LOCAL(0 , "本地");
}