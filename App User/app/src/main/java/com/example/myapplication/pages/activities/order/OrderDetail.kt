package com.example.myapplication.pages.activities.order

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.OrderProductDetail
import com.example.myapplication.pages.RatingActivity
import com.example.myapplication.pages.apdaters.OrderProductListAdapter
import com.example.myapplication.viewmodels.order.OrderProductViewModel



class OrderDetail : AppCompatActivity() {
    private lateinit var subTotalTV : TextView
    private lateinit var discountTV : TextView
    private lateinit var totalTV : TextView
    private lateinit var orderDetailListRV : RecyclerView
    private lateinit var orderProductViewModel: OrderProductViewModel
    private lateinit var orderProductListAdapter: OrderProductListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        orderProductListAdapter = OrderProductListAdapter(arrayListOf())
        initUI()

        val orderId = intent.getStringExtra("orderId").toString().toInt()
        println("Get String Extra: $orderId")
        val orderPromotion = intent.getStringExtra("orderPromotion")
        val orderTotalPrice = intent.getStringExtra("orderTotalPrice")
        discountTV.text = orderPromotion
        totalTV.text = orderTotalPrice
        setUpObserve(orderId)
    }

    private fun initUI(){
        subTotalTV = findViewById(R.id.subTotalValueTV)
        discountTV = findViewById(R.id.discountValueTV)
        totalTV = findViewById(R.id.totalValueTV)
        orderDetailListRV = findViewById(R.id.orderDetailRV)

        orderProductListAdapter.onRatingClick = { rating ->
            val intent = Intent(
                this,
                RatingActivity::class.java
            )
//            intent.putExtra("productId", productId)
            startActivity(intent)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserve(orderId: Int){
        orderProductViewModel = ViewModelProvider(this)[OrderProductViewModel::class.java]
        orderProductViewModel.getAllProductOfOrder(orderId)
        orderProductViewModel.orderProduct.observe(this){
            println(it)
            orderProductListAdapter.addOrderProduct(it as ArrayList<OrderProductDetail>)
            orderProductListAdapter.notifyDataSetChanged()
            orderDetailListRV.layoutManager = LinearLayoutManager(this)
            orderDetailListRV.adapter = orderProductListAdapter
        }
    }

}