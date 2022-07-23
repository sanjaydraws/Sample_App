package com.sanjayprajapat.sampleapp.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sanjayprajapat.sampleapp.constants.AuthConstant.MY_SHARED_PREF
import com.sanjayprajapat.sampleapp.data.db.entities.OrdersDao
import com.sanjayprajapat.sampleapp.data.db.entities.OrdersDatabase
import com.sanjayprajapat.sampleapp.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun customSharedPref(@ApplicationContext context: Context) : SharedPreferences {
        return   context.getSharedPreferences(
            MY_SHARED_PREF,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideSharedPrefHelper(gson: Gson, sharedPreferences: SharedPreferences) = SharedPreferencesHelper(gson, sharedPreferences)


    @Provides
    @Singleton
    fun provideOrdersDao(@ApplicationContext context: Context):OrdersDao =
        OrdersDatabase.getInstance(context).ordersDao
//    @Provides
//    @Singleton
//    fun provideGsonHelper(gson:Gson?) = GsonHelper(gson)

//    @Provides
//    @Singleton
//    fun provideFirebaseAuth(): FirebaseAuth =  FirebaseAuth.getInstance()

}