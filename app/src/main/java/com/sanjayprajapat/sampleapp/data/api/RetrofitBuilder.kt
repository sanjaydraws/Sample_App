package com.sanjayprajapat.sampleapp.data.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sanjayprajapat.sampleapp.constants.AuthConstant
import com.sanjayprajapat.sampleapp.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitBuilder {
    private val gson: Gson = GsonBuilder().serializeNulls().create() // it will show request keys with null

    @Provides
    fun getRetrofit(@ApplicationContext context: Context,
                    sharedPreferencesHelper: SharedPreferencesHelper
    ): Retrofit {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(Apis.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

        //log http request & response with logging interceptor
        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val newRequest: Request = originalRequest.newBuilder()
                //                    .addHeader("Interceptor-Header", "xyz")
                .addHeader("Authorization", AuthConstant.AUTH_Token ?:"")// token
                .build()
            chain.proceed(newRequest)
        })

        return builder.client(httpClient.build()).build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }



    @Provides
    @Singleton
    fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}