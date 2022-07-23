package com.sanjayprajapat.sampleapp.ui.Orders

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjayprajapat.sampleapp.data.db.entities.OrderData
import com.sanjayprajapat.sampleapp.data.db.entities.OrdersDao
import com.sanjayprajapat.sampleapp.data.db.entities.OrdersDatabase
import com.sanjayprajapat.sampleapp.data.model.AllOrdersData
import com.sanjayprajapat.sampleapp.data.model.OrderInfoData
import com.sanjayprajapat.sampleapp.data.model.Resource
import com.sanjayprajapat.sampleapp.data.repository.OrdersRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrdersViewModel @Inject constructor(@ApplicationContext context:Context, private val ordersRepository: OrdersRepository,
                                         ):ViewModel() {

    private val _allOrdersDataResponse:MutableLiveData<Resource<AllOrdersData>> =MutableLiveData()
    val allOrdersDataResponse:LiveData<Resource<AllOrdersData>> = _allOrdersDataResponse


    private val  ordersDao by lazy  {
        OrdersDatabase.getInstance(context).ordersDao
    }

    fun getAllOrders(restaurant_id: Int?, status: Int?, page: Int?){
        viewModelScope.launch {
            _allOrdersDataResponse.postValue(Resource.loading())
            val ordersInfoData = ordersRepository.getAllOrders(restaurant_id, status, page
            )
            _allOrdersDataResponse.postValue(ordersInfoData)
        }
    }

    fun insertOrderData (orderData:OrderData?){
        viewModelScope.launch {
            ordersDao.insertOrders(orderData)
        }
    }
    fun deleteOrdersData(){
        viewModelScope.launch {
            ordersDao.deleteAllOrders()
        }
    }
    private val _getAllOrders : MutableLiveData<List<OrderData>> = MutableLiveData()
    val allOrdersResponse : LiveData<List<OrderData>> = MutableLiveData()

    fun getAllOrders(){
        viewModelScope.launch {
            _getAllOrders.postValue(ordersDao.getAllOrders())
        }
    }
}