package com.syalim.edufundtest.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class HospitalDto(
    @SerializedName("address")
    val address: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("province")
    val province: String,
    @SerializedName("region")
    val region: String
)