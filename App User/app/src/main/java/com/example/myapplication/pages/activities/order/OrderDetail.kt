package com.example.myapplication.pages.activities.order

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.myapplication.Admin.controllers.OrderController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.checkout.Refund
import com.example.myapplication.modals.OrderProductDetail
import com.example.myapplication.pages.RatingActivity
import com.example.myapplication.pages.apdaters.OrderProductListAdapter
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.order.OrderProductViewModel
import com.example.myapplication.viewmodels.order.RefundViewModel
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
    private var subtotal:Double = 0.0
    private lateinit var statusOrder:TextView
    private lateinit var methodPaymentTV:TextView
    private lateinit var promotionViewModel:PromotionViewModel
    private lateinit var orderPromotion:String
    private lateinit var loading:ProgressBar
    private var sum:Double=0.0
    private lateinit var scrollView: ScrollView
    private lateinit var cancelOrderDetail:LinearLayout
    private lateinit var cancelOrderDetailTV:TextView
    private var orderId:Int=0
    private lateinit var reFundViewModelProvider:RefundViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        val policy = ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        orderProductListAdapter = OrderProductListAdapter(arrayListOf(),0,0)
        initViewModel()
        initUI()
        orderId = intent.getStringExtra("orderId").toString().toInt()
        orderPromotion = intent.getStringExtra("orderPromotion").toString()
        val orderTotalPrice = intent.getStringExtra("orderTotalPrice")
        orderStatus = intent.getStringExtra("orderStatus").toString()
        val methodPayment = intent.getStringExtra("paymentMethodID").toString()
        if(orderStatus=="0")
        {
            orderProductListAdapter = OrderProductListAdapter(arrayListOf(),0,0)
            statusOrder.text="Đơn hàng đang chờ xác nhận"
            cancelOrderDetail.visibility = View.VISIBLE

        }
        else if(orderStatus=="-1")
        {
            orderProductListAdapter = OrderProductListAdapter(arrayListOf(),-1,0)
            statusOrder.text="Đơn hàng bị huỷ"
            cancelOrderDetail.visibility = View.GONE
        }else if (orderStatus=="1")
        {
            orderProductListAdapter = OrderProductListAdapter(arrayListOf(),1,0)
            statusOrder.text="Đơn hàng được xác nhận !"
            cancelOrderDetail.visibility = View.GONE
        }
        else if (orderStatus=="2")
        {

            orderProductListAdapter = OrderProductListAdapter(arrayListOf(),2,1)
            orderProductListAdapter.onRatingClick = { rating ->
                val intent = Intent(
                    this,
                    RatingActivity::class.java
                )
                intent.putExtra("productId", rating.getProductId().toString())
                startActivity(intent)

            }
            statusOrder.text="Đơn hàng giao thành công"
            cancelOrderDetail.visibility = View.GONE
        }
        if(methodPayment == "3")
        {
            methodPaymentTV.text = "ZaloPay"
        }
        totalTV.text = Utils.formatCurrency(orderTotalPrice!!.toDouble())  +  " VND"
        setUpObserve(orderId)

    }
    private fun initViewModel(){
        reFundViewModelProvider = ViewModelProvider(this)[RefundViewModel::class.java]
        promotionViewModel = ViewModelProvider(this)[PromotionViewModel::class.java]
    }

    private fun initUI(){
        cancelOrderDetailTV = findViewById(R.id.cancelOrderDetailTV)
        cancelOrderDetail=findViewById(R.id.cancelOrderDetail)
        scrollView = findViewById(R.id.viewAll)
        loading= findViewById(R.id.showLoading)
        methodPaymentTV = findViewById(R.id.methodPayment)
        statusOrder= findViewById(R.id.statusOrder)
        imgToolbarBtnBack= findViewById(R.id.imgToolbarBtnBack)
        subTotalTV = findViewById(R.id.subTotalValueTV)
        discountTV = findViewById(R.id.discountValueTV)
        totalTV = findViewById(R.id.totalValueTV)
        orderDetailListRV = findViewById(R.id.orderDetailRV)
        orderProductListAdapter.onBuyAgainClick = {product ->

        }

        imgToolbarBtnBack.setOnClickListener {
            super.onBackPressed();
        }
        cancelOrderDetailTV.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Huỷ đơn hàng")
                .setContentText("Bạn có chắc sẽ huỷ đơn hàng ?")
                .setConfirmText("Đồng ý")
                .setConfirmClickListener { sDialog ->
                    val orderProvider = ViewModelProvider(this)[OrderController::class.java]
                    orderProvider.changeDenyStatus(orderId).observe(this) {
                    }
                    var total1=0.0

                    reFundViewModelProvider.getTokenByOrderID(orderId)
                    orderProvider.getOrder(orderId).observe(this){
                        val order=it
                        reFundViewModelProvider.getorder.observe(this){
                            if(it!=null)
                            {
                                val refundAPI = Refund()
                                refundAPI.refund(it.getToken(),order.getTotal()!!.toInt().toString())
                                sDialog.dismissWithAnimation()
                                if(refundAPI.order.value == true)
                                {

                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.putExtra("refund","true");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    finish()
                                }

                            }
                        }
                    }

                }.setCancelText("Huỷ")
                .setCancelClickListener {

                    it.dismissWithAnimation()
                }
                .show()


        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserve(orderId: Int){
        orderProductViewModel = ViewModelProvider(this)[OrderProductViewModel::class.java]
        orderProductViewModel.getAllProductOfOrder(orderId)
        orderProductViewModel.orderProduct.observe(this){


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