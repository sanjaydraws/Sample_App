package com.sanjayprajapat.sampleapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sanjayprajapat.sampleapp.data.db.entities.OrderData


@Keep
data class OrderInfoData (
    @SerializedName("orders")
    @Expose
    val orders:List<OrderData>?,
        )

