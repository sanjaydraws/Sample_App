package com.sanjayprajapat.sampleapp.data.db.entities

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
@Entity
data class OrderData (
    @SerializedName("order_id")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "order_id")val order_id:Int? = null,

    @SerializedName("phone")
    @Expose
    @ColumnInfo(name = "phone")val phone:String? = null,
    @SerializedName("sequence_no")
    @Expose
    @ColumnInfo(name = "sequence_no")val sequence_no:Int? = null,
    @SerializedName("order_type")
    @Expose
    @ColumnInfo(name = "order_type")val order_type:String? = null,

    @SerializedName("expected_date")
    @Expose
    @ColumnInfo(name = "expected_date")val Expected_Date :String? = null,


    )
