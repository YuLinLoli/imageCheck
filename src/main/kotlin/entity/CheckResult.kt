package com.yulin.entity

import kotlinx.serialization.Serializable

@Serializable
data class CheckResult(
    var boolean: Boolean?,
    var msg: String
)
