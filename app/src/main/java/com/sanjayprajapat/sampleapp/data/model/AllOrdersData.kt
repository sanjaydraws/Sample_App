package com.sanjayprajapat.sampleapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sanjayprajapat.sampleapp.data.db.entities.OrderData

@Keep
class AllOrdersData {
    @SerializedName("orderInfo")
    @Expose
    var orderInfo:OrderInfoData?= null
}


