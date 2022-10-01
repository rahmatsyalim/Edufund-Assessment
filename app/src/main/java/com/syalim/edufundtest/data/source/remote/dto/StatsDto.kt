package com.syalim.edufundtest.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class StatsDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("numbers")
    val numbers: StatsNumbersDto,
    @SerializedName("regions")
    val regions: List<StatsRegionalDto>,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("type")
    val type: String
)

data class StatsNumbersDto(
    @SerializedName("fatal")
    val fatal: Int,
    @SerializedName("infected")
    val infected: Int,
    @SerializedName("recovered")
    val recovered: Int
)

data class StatsRegionalDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("numbers")
    val numbers: StatsNumbersDto,
    @SerializedName("type")
    val type: String
)