package com.sanjayprajapat.sampleapp.data.api

import com.sanjayprajapat.sampleapp.data.model.BaseApiModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {



    @POST(Apis.GET_ORDERS)
    suspend fun getOrders(@Body jsonObject: HashMap<String, String?>): Response<BaseApiModel>?

}