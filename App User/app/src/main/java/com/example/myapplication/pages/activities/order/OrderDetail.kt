package com.example.myapplication.pages.activities.order

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.OrderProductDetail
<<<<<<< HEAD:App User/app/src/main/java/com/example/myapplication/pages/OrderDetail.kt
=======
import com.example.myapplication.pages.RatingActivity
>>>>>>> a2491c7d92aab341cb6f790148a90842d0496940:App User/app/src/main/java/com/example/myapplication/pages/activities/order/OrderDetail.kt
import com.example.myapplication.pages.apdaters.OrderProductListAdapter
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.order.OrderProductViewModel
import com.example.myapplication.viewmodels.promotion.PromotionViewModel


class OrderDetail : AppCompatActivity() {
    private lateinit var subTotalTV : TextView
    private lateinit var discountTV : TextView
    private lateinit var totalTV : TextView
    private lateinit var orderDetailListRV : RecyclerView
    private lateinit var orderProductViewModel: OrderProductViewModel
    private lateinit var orderProductListAdapter: OrderProductListAdapter
    private lateinit var imgToolbarBtnBack : ImageView
    private lateinit  var orderStatus:String;
    private var  subtotal:Double = 0.0
    private lateinit var statusOrder:TextView
    private lateinit var methodPaymentTV:TextView
    private lateinit var promotionViewModel:PromotionViewModel
    private lateinit var orderPromotion:String
    private lateinit var loading:ProgressBar
    private  var sum:Double=0.0
    private lateinit var scrollView: ScrollView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)


        orderProductListAdapter = OrderProductListAdapter(arrayListOf())
        initViewModel()
        initUI()
        val orderId = intent.getStringExtra("orderId").toString().toInt()
        println("Get String Extra: $orderId")
        orderPromotion = intent.getStringExtra("orderPromotion").toString()
        val orderTotalPrice = intent.getStringExtra("orderTotalPrice")
        orderStatus = intent.getStringExtra("orderStatus").toString()
        val methodPayment = intent.getStringExtra("paymentMethodID").toString()
        if(orderStatus=="0")
        {
            statusOrder.text="Đơn hàng đang chờ xác nhận"
        }
        else if(orderStatus=="-1")
        {
            statusOrder.text="Đơn hàng bị huỷ"
        }else if (orderStatus=="1")
        {
            statusOrder.text="Đơn hàng được xác nhận !"
        }
        else if (orderStatus=="2")
        {
            statusOrder.text="Đơn hàng giao thành công"
        }
        if(methodPayment == "3")
        {
            methodPaymentTV.text = "ZaloPay"
        }
        totalTV.text = orderTotalPrice
        setUpObserve(orderId)

    }
    private fun initViewModel(){
        promotionViewModel = ViewModelProvider(this)[PromotionViewModel::class.java]
    }

    private fun initUI(){
        scrollView = findViewById(R.id.viewAll)
        loading= findViewById(R.id.showLoading)
        methodPaymentTV = findViewById(R.id.methodPayment)
        statusOrder= findViewById(R.id.statusOrder)
        imgToolbarBtnBack= findViewById(R.id.imgToolbarBtnBack)
        subTotalTV = findViewById(R.id.subTotalValueTV)
        discountTV = findViewById(R.id.discountValueTV)
        totalTV = findViewById(R.id.totalValueTV)
        orderDetailListRV = findViewById(R.id.orderDetailRV)

        orderProductListAdapter.onRatingClick = { rating ->
            val intent = Intent(
                this,
                RatingActivity::class.java
            )
            intent.putExtra("productId", rating.getProductId().toString())
            startActivity(intent)

        }
        imgToolbarBtnBack.setOnClickListener {
            super.onBackPressed();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserve(orderId: Int){
        orderProductViewModel = ViewModelProvider(this)[OrderProductViewModel::class.java]
        orderProductViewModel.getAllProductOfOrder(orderId)
        orderProductViewModel.orderProduct.observe(this){
            println(it)

            if(it!=null)
            {
                orderProductListAdapter.addOrderProduct(it as ArrayList<OrderProductDetail>)
                orderProductListAdapter.notifyDataSetChanged()
                orderDetailListRV.layoutManager = LinearLayoutManager(this)
                orderDetailListRV.adapter = orderProductListAdapter
                loading.visibility = View.GONE
                scrollView.visibility = View.VISIBLE
                it.forEach {
                    sum+=it.getPrice();
                }
                subTotalTV.text = Utils.formatCurrency(sum) + " đ"
            }
            else{
                loading.visibility = View.VISIBLE
                scrollView.visibility = View.INVISIBLE

            }

        }

        promotionViewModel.getPromotion(orderPromotion.toInt())
        promotionViewModel.promotion.observe(this) {
            if (it != null) {
                discountTV.text = it.getDiscount().toString() + "%"
                loading.visibility = View.GONE

            } else {
                loading.visibility = View.VISIBLE
            }

        }
    }

}