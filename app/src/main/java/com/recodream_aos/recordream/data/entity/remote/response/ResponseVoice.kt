package com.recodream_aos.recordream.data.entity.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseVoice(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean,
) {
    @Serializable
    data class Data(
        val _id: String,
        val recorder: String,
        val url: String,
    )
}
