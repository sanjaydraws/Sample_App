package com.sanjayprajapat.sampleapp.data.db.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrders(orderData: OrderData?)

    @Query("DELETE FROM OrderData")
    fun deleteAllOrders()

    @Query("SELECT * FROM OrderData")
    suspend fun getAllOrders():List<OrderData>
}