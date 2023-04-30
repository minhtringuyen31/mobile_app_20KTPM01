package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.checkout.CreateOrder
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.pages.apdaters.CheckoutApdater
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.order.CheckoutViewModel
import com.example.myapplication.viewmodels.order.OrderViewModel
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONObject
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPayError
import vn.zalopay.sdk.ZaloPaySDK
import vn.zalopay.sdk.listeners.PayOrderListener
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Checkout.newInstance] factory method to
 * create an instance of this fragment.
 */
class Checkout : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var btnShowBottomSheet: TextView
    private val appModel: AppViewModel by activityViewModels()
    private lateinit var checkoutAdapter:CheckoutApdater
    private lateinit var itemCheckoutListView: ListView
    private lateinit var btnCheckout:TextView
    private lateinit var checkoutViewModel: CheckoutViewModel
    private lateinit var cancelCheckout:TextView
    private lateinit var back_checkout:ImageView
    private lateinit var addressCheckout:LinearLayout
    private lateinit var timeCheckout:TextView
    private lateinit var subTotal:TextView
    private lateinit var total:TextView
    private lateinit var addItem:TextView
    private lateinit var discount:TextView
    private var subtotal=0.0
    private lateinit var showAddress:TextView
    private lateinit var methodSelected:TextView
    private lateinit var view:View
    private  var cartItemCallAPI:ArrayList<CartItem> = ArrayList<CartItem>()
    private lateinit var orderViewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun handleGetTime(){
        SingleDateAndTimePickerDialog.Builder(context)
            .bottomSheet()
            .curved()
            .titleTextColor(Color.BLACK)
            .backgroundColor(Color.rgb(245,248,250))
            .title("Hãy chọn thời gian đặt hàng")
            .listener {
                val inputFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy")
                } else {
                    TODO("VERSION.SDK_INT < O")
                }
                try {
                    val localDateTime = LocalDateTime.parse(it.toString(), inputFormat)
                    val zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("GMT+07:00"))
                    val outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val outputDate = zonedDateTime.format(outputFormat)
                    checkoutViewModel.setTime(outputDate)
                } catch (e: DateTimeParseException) {
                    println("Error parsing date: $e")
                }
            }.display()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view=inflater.inflate(R.layout.fragment_checkout, container, false)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        // ZaloPay SDK Init
        ZaloPaySDK.init(554, Environment.SANDBOX)
        (activity as MainActivity).showToolbarAndNavigationBar(false)
        setUpViewModel()
        initUI(view)
        setupObserve()
        return view
    }
    @SuppressLint("InflateParams")
    fun initUI(view:View){
        showAddress = view.findViewById(R.id.showAddress)
        cancelCheckout = view.findViewById(R.id.cancelCheckout)
        addressCheckout= view.findViewById(R.id.add_Address)
        back_checkout = view.findViewById(R.id.back_checkout)
        timeCheckout= view.findViewById(R.id.timeShip)
        subTotal= view.findViewById(R.id.subTotalCheckout)
        total= view.findViewById(R.id.totalCheckout)
        addItem= view.findViewById(R.id.addItems)
        discount= view.findViewById(R.id.addDiscount)
        itemCheckoutListView = view.findViewById(R.id.listItemCheckout)
        checkoutAdapter = CheckoutApdater(arrayListOf(),this,view.context)
        itemCheckoutListView.adapter=checkoutAdapter
        btnShowBottomSheet = view.findViewById(R.id.method_payment)
        btnCheckout = view.findViewById(R.id.btnPlaceOrderCheckout)
        methodSelected= view.findViewById(R.id.methodSelected)
        btnShowBottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(view.context)
            val viewitem = layoutInflater.inflate(R.layout.payment_method_layout, null)
            val momo = viewitem.findViewById<LinearLayout>(R.id.momo)
            val paypal = viewitem.findViewById<LinearLayout>(R.id.paypal)
            momo.setOnClickListener {
                methodSelected.text= "Momo"
                dialog.dismiss()
            }
            paypal.setOnClickListener {
                methodSelected.text= "ZaloPay"
                dialog.dismiss()
            }
            dialog.setContentView(viewitem)
            dialog.show()
        }
    }
    private fun setUpViewModel(){
        checkoutViewModel =ViewModelProvider(this)[CheckoutViewModel::class.java]
        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]
    }
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    private fun setupObserve(){
        appModel.getCartItemViewModel().cartItems.observe(viewLifecycleOwner){
            val items=it
            items.forEach{
                subtotal+=it.getPrice()
            }
            cartItemCallAPI.apply {
                cartItemCallAPI.addAll(it)
            };
            checkoutViewModel.subTotal.value=subtotal
            subTotal.text= Utils.formatCurrency(checkoutViewModel.subTotal.value!!) + " đ"
            total.text =  Utils.formatCurrency( (checkoutViewModel.subTotal.value!!-(checkoutViewModel.subTotal.value!!*checkoutViewModel.getPercentVoucher()))) + " đ"
            checkoutAdapter.apply {
                addItems(items)
            }
        }
        if(checkoutViewModel.getAddress().isNotEmpty()){
            showAddress.visibility = View.VISIBLE
        }
        back_checkout.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        btnCheckout.setOnClickListener {
            val orderApi = CreateOrder()
            try {
                val data: JSONObject = orderApi.createOrder("1000")
                val code = data.getString("returncode")
                if (code == "1") {
                    val myActivity = activity as MainActivity
                    ZaloPaySDK.getInstance().payOrder(myActivity, data.getString("zptranstoken"), "demozpdk://app", object :
                            PayOrderListener {
                            override fun onPaymentSucceeded(
                                transactionId: String,
                                transToken: String,
                                appTransID: String
                            ) {


                                SweetAlertDialog(view.context, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Đặt hàng thành công")
                                    .setContentText("Quay trở lại trang Order để theo dõi đơn hàng")
                                    .setConfirmText("Đồng ý")
                                    .setConfirmClickListener { sDialog ->
                                        sDialog.dismissWithAnimation()
                                        (view.context as FragmentActivity).supportFragmentManager
                                            .beginTransaction()
                                            .replace(R.id.flFragment, Activities(),"Activities").addToBackStack(null)
                                            .commit()
                                    }.show()


                            }
                            override fun onPaymentCanceled(zpTransToken: String, appTransID: String) {
                                SweetAlertDialog(view.context, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Đặt hàng thất bại")
                                    .setContentText("Vui lòng kiểm tra lại")
                                    .setConfirmText("Đồng ý")
                                    .setConfirmClickListener { sDialog ->
                                        sDialog.dismissWithAnimation()
                                    }
                                    .show()
                            }
                            override fun onPaymentError(
                                zaloPayError: ZaloPayError,
                                zpTransToken: String,
                                appTransID: String
                            ) {
                                SweetAlertDialog(view.context, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Lỗi")
                                    .setContentText("Vui lòng kiểm tra lại")
                                    .setConfirmText("Đồng ý")
                                    .setConfirmClickListener { sDialog ->
                                        sDialog.dismissWithAnimation()
                                    }
                                    .show()
                            }
                        })
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        cancelCheckout.setOnClickListener {
            //call api and delete Order
//            (view.context as FragmentActivity).supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.flFragment, Order(),"Order").addToBackStack(null)
//                .commit()
            val address = checkoutViewModel.getAddress();
            val sharedPreferences: SharedPreferences =
                view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
            val userID = sharedPreferences.getString("userID", "")
            val newOrder =com.example.myapplication.modals.Order(userID!!.toInt(),checkoutViewModel.getTime(),address,Utils.getDigitInString(total.text.toString()),0,1,3);
            orderViewModel.createOrder(newOrder,cartItemCallAPI);

        }
        addressCheckout.setOnClickListener {
          val dialog = CustomDialog(view.context)
            dialog.setUpDialog()
        }

        timeCheckout.setOnClickListener{
            handleGetTime()
        }

        addItem.setOnClickListener {
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, Order(),"Order").addToBackStack(null)
                .commit()
        }
        discount.setOnClickListener {
                // Replace fragment or activity Promotio
        }
    }
    fun handleNewIntent(intent: Intent?) {
        // handle the new intent here
        println("Di vao day"+intent)
        ZaloPaySDK.getInstance().onResult(intent)
    }
   inner class CustomDialog(context: Context) {
        private var dialog: Dialog = Dialog(context)
        fun setUpDialog(){
            dialog.setContentView(R.layout.custom_dialog_input_address)
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.setCancelable(false)
            dialog.window!!.attributes.windowAnimations = R.style.CustomBottomSheetDialogTheme
            val okay_text = dialog.findViewById<TextView>(R.id.add_Address)
            val edtAddress = dialog.findViewById<EditText>(R.id.addressUser)
            okay_text.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
                checkoutViewModel.setAddress(edtAddress.text.toString())
                showAddress.text=edtAddress.text
            })
            dialog.show()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Checkout.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Checkout().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}