package com.sanjayprajapat.sampleapp.data.db.entities




import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import dagger.hilt.android.qualifiers.ApplicationContext


@Database(
    entities = [
        OrderData::class,
    ],
    version = 2
)
abstract class OrdersDatabase : RoomDatabase() {

    abstract val ordersDao: OrdersDao

    companion object {
        @Volatile
        private var INSTANCE: OrdersDatabase? = null

        fun getInstance(context: Context): OrdersDatabase {
            synchronized(this) {
                // whatever executed in this block , it's only executed by single thread
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    OrdersDatabase::class.java,
                    "school_db"
                ).fallbackToDestructiveMigration()
                    .build().also {
                    INSTANCE = it
                }
            }
        }
    }
}