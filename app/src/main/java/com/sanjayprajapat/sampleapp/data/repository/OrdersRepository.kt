package com.sanjayprajapat.sampleapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.sanjayprajapat.sampleapp.R
import com.sanjayprajapat.sampleapp.data.api.ApiService
import com.sanjayprajapat.sampleapp.data.model.AllOrdersData
import com.sanjayprajapat.sampleapp.data.model.OrderInfoData
import com.sanjayprajapat.sampleapp.data.model.Resource
import com.sanjayprajapat.sampleapp.utils.isConnectedToInternet
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OrdersRepository @Inject constructor( @ApplicationContext val context:Context,val  gson:Gson,
                                            val apiService: ApiService ) {



    val params: HashMap<String, String?> by lazy {
        HashMap<String, String?>()
    }
    suspend fun getAllOrders(restaurant_id: Int?, status: Int?, page: Int?):Resource<AllOrdersData>{
        if (isConnectedToInternet(context = context) != true) {
            return Resource.error(data = null, context?.getString(R.string.no_internet_connected))
        }
        try {
            params.also {
                it["restaurant_id"] = restaurant_id.toString()
                it["status"] = status.toString()
                it["page"] = page.toString()
            }
            val response = apiService.getOrders(params)
            if (response?.isSuccessful == false) {
                return Resource.error(
                    data = null,
                    context.getString(R.string.default_error_message)
                )
            }
            if (response?.code() == 200) {
                 val orderData  = gson.fromJson(response.body()?.data,AllOrdersData::class.java)
//                val posts  = gson.convertFromJson(response.body().toString(),AllPosts::class.java)
                return Resource.success(data = orderData)
            } else if (response?.code() == 400) {
                return Resource.error(message = response.message()?:context.getString(R.string.default_error_message))
            }
            return Resource.error( message = response?.message()?: context.getString(R.string.default_error_message))
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.error(data = null, context.getString(R.string.default_error_message))
        }
    }

}