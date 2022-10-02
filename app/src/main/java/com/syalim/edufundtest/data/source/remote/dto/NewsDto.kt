package com.syalim.edufundtest.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)