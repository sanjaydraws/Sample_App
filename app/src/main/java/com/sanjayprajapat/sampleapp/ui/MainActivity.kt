package com.sanjayprajapat.sampleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.sanjayprajapat.sampleapp.R
import com.sanjayprajapat.sampleapp.adapters.OrdersAdapter
import com.sanjayprajapat.sampleapp.data.db.entities.OrdersDao
import com.sanjayprajapat.sampleapp.data.db.entities.OrdersDatabase
import com.sanjayprajapat.sampleapp.data.model.Status
import com.sanjayprajapat.sampleapp.databinding.ActivityMainBinding
import com.sanjayprajapat.sampleapp.ui.Orders.OrdersViewModel
import com.sanjayprajapat.sampleapp.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private var binding:ActivityMainBinding ? = null
    private val viewModel by viewModels<OrdersViewModel>()


    private val ordersDao by lazy {
        OrdersDatabase.getInstance(this).ordersDao
    }
    private val mOrderAdapter by lazy {
        OrdersAdapter(arrayListOf()) {
            // to store on database
//            CoroutineScope(Dispatchers.IO).launch {
//                ordersDao.insertOrders(it)
//            }
            viewModel.insertOrderData(it)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        binding?.apply {
            setContentView(root)
            lifecycleOwner = this@MainActivity
            executePendingBindings()
        }


        init()
    }

    override fun initArguments() {
        binding?.rcOrdersList?.adapter = mOrderAdapter

        viewModel.getAllOrders(restaurant_id = 1,status = 1,page = 1)
        viewModel.getAllOrders()
    }

    override fun initViews() {
    }

    override fun setupListener() {
        binding?.fabDeleteOrders?.setOnClickListener {
//            viewModel.getAllOrders(restaurant_id = 1,status = 1,page = 1)
//            CoroutineScope(Dispatchers.IO).launch {
//                ordersDao.deleteAllOrders()
//            }
            viewModel.deleteOrdersData()
        }
    }

    override fun loadData() {
//        CoroutineScope(Dispatchers.IO).launch {
//            // toget
//            Log.d("All_Orders_Data", "loadData: ${ordersDao.getAllOrders()}")
//        }
        viewModel.allOrdersResponse.observe(this, Observer {
            it?:return@Observer
             Log.d("All_Orders_Data", "loadData: $it")
        })
        viewModel.allOrdersDataResponse.observe(this, Observer {
            it?:return@Observer
            when(it.status){
                Status.SUCCESS ->{
                    binding?.progressbar?.isVisible = false
//                    showMsg(it.message)
//                    showMsg(it.data.toString())
                    mOrderAdapter.updateData(it.data?.orderInfo?.orders)
                }
                Status.LOADING->{
                    binding?.progressbar?.isVisible = true
                }
                Status.ERROR->{
                    binding?.progressbar?.isVisible = false
                    showMsg(it.message)
                }
            }
        })

    }
}