package com.sanjayprajapat.sampleapp.data.model

import androidx.annotation.Keep
import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class BaseApiModel {
    @SerializedName("status")
    @Expose
    var status = 0

    @SerializedName("message")
    @Expose
    var message = ""


    @SerializedName("data")
    @Expose
    var data: JsonElement? = null

//    @SerializedName("token")
//    @Expose
//    var loginToken: String = ""
//

    override fun toString(): String {
        return "{status : " + status + ", message : " + message + ", data:" + data.toString() + "}"
    }
}