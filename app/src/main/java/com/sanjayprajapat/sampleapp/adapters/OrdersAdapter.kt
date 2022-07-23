package com.sanjayprajapat.sampleapp.adapters

import com.sanjayprajapat.sampleapp.data.db.entities.OrderData




import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjayprajapat.sampleapp.databinding.ItemOrdersLayoutBinding
import com.sanjayprajapat.sampleapp.utils.isPrimeNumber


class OrdersAdapter(
    var orderDataList: List<OrderData>?,
    val onOrderItemClick: ((orderData: OrderData?) -> Unit)  ): RecyclerView.Adapter<OrdersAdapter.OrdersVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.OrdersVH {
        val binding = ItemOrdersLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersVH(binding)
    }
    fun updateData(orderDataList: List<OrderData>? ){
        this.orderDataList = orderDataList
        notifyDataSetChanged()
    }

//    companion object : DiffUtil.ItemCallback<Directories>() {
//        override fun areItemsTheSame(oldItem: Directories, newItem: Directories): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: Directories, newItem: Directories): Boolean {
//            return oldItem.id == newItem.id && oldItem.dirName == newItem.dirName
//        }
//    }


    override fun getItemCount(): Int {
        return orderDataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: OrdersAdapter.OrdersVH, position: Int) {
        orderDataList?.get(position)?.let { holder.loadData(it) }
    }
    inner  class OrdersVH(val binding :ItemOrdersLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun loadData(orderData: OrderData){
            binding.txtOrderType.text = "Order type ${orderData.order_type}"

            binding.expectedDate .text = "Order Expected Date ${orderData.Expected_Date}"
            binding.orderId .text = "Order ID ${orderData.order_id}"
            binding.txtSequenceNumber.text = "Order Sequence ${orderData.sequence_no} is ${isPrimeNumber(orderData.sequence_no)}"

            binding?.root.setOnClickListener {
                onOrderItemClick.invoke(orderData)
            }
        }
    }
}