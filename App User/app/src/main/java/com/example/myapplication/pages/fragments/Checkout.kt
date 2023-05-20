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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.checkout.CreateOrder
import com.example.myapplication.modals.CartItem
import com.example.myapplication.pages.activities.promotion.ListPromotion
import com.example.myapplication.pages.apdaters.CheckoutApdater
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.order.CheckoutViewModel
import com.example.myapplication.viewmodels.order.OrderViewModel
import com.example.myapplication.viewmodels.order.RefundViewModel
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.socket.client.Socket
import org.json.JSONObject
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPayError
import vn.zalopay.sdk.ZaloPaySDK
import vn.zalopay.sdk.listeners.PayOrderListener
import java.lang.reflect.Type
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
    private lateinit var checkoutAdapter: CheckoutApdater
    private lateinit var itemCheckoutListView: ListView
    private lateinit var btnCheckout:TextView

    private val checkoutViewModel:CheckoutViewModel by activityViewModels()
    private lateinit var cancelCheckout:TextView
    private lateinit var back_checkout:ImageView
    private lateinit var addressCheckout:LinearLayout
    private lateinit var timeCheckout:TextView
    private lateinit var subTotal:TextView
    private lateinit var total:TextView
    private lateinit var addItem:TextView
    private lateinit var discount:LinearLayout
    private var subtotal=0.0
    private lateinit var showAddress:TextView
    private lateinit var methodSelected:TextView
    private lateinit var view:View
    private  var cartItemCallAPI:ArrayList<CartItem> = ArrayList<CartItem>()
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var adddressAdapter:AddressApdapter
    private var selectedAddressBoolean:Boolean=true
    private lateinit var phoneUser:TextView
    private lateinit var updatePhone:TextView
    private lateinit var sharedPreferencesAdress:SharedPreferences
    private lateinit var sharedPreferencesTime:SharedPreferences
    private lateinit var sharedPreferencesPhone:SharedPreferences
    private lateinit var sharedPreferencesUser:SharedPreferences
    private lateinit var reFundViewModel:RefundViewModel
    private  var gson=Gson()
    private  val type: Type = object : TypeToken<ArrayList<String>>() {}.type
    private var carts: String? = null
    private lateinit var dataItem :ArrayList<String>
    private lateinit var toggleAddress:TextView
    private lateinit var mSocket: Socket



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    fun updateVoucher(percent1:String,idPromotion:String){
        var getandShowPercent = view.findViewById<TextView>(R.id.getandShowPercent);
        getandShowPercent.text = " - "+(percent1.toDouble()/100).toString() + " % "
        checkoutViewModel.setPercentVoucher(percent1.toDouble()/100)
        checkoutViewModel.setpromontionID(idPromotion)
        total.text =  Utils.formatCurrency( (checkoutViewModel.subTotal.value!!-(checkoutViewModel.subTotal.value!!*checkoutViewModel.getPercentVoucher()))) + " đ"
    }

    @SuppressLint("ResourceAsColor", "SimpleDateFormat")
    private fun handleGetTime(){
        SingleDateAndTimePickerDialog.Builder(context)
            .bottomSheet()
            .curved()
            .titleTextColor(Color.BLACK)
            .backgroundColor(Color.rgb(240,248,250))
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
                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    // Split date and time components
                    val _date = outputDate.substring(0, 10) // "2302-05-06"
                    val _time = outputDate.substring(10) // "14:55:00"
                    val regex = Regex("(\\d{4}-\\d{2}-\\d{2})(\\d{2}:\\d{2}:\\d{2})")
                    val matchResult = regex.find(outputDate)
                    val date = matchResult?.groupValues?.get(1) // "2302-05-06"
                    val currentDate = sdf.format(Date())
                    if(currentDate.contains(_date)) {
                        timeCheckout.text = "Hôm nay | $_time";
                        sharedPreferencesTime = view.context.getSharedPreferences("timeShip", AppCompatActivity.MODE_PRIVATE)

                    }
                    else {
                        timeCheckout.text=outputDate

                    }
                    sharedPreferencesTime.edit().putString("time", timeCheckout.text.toString()).apply()

                } catch (e: DateTimeParseException) {
                    println("Error parsing date: $e")
                }
            }.display()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        println(checkoutViewModel)



        view=inflater.inflate(R.layout.fragment_checkout, container, false)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        // ZaloPay SDK Init
        ZaloPaySDK.init(554, Environment.SANDBOX)
        (activity as MainActivity).showToolbarAndNavigationBar(false)


        sharedPreferencesAdress = view.context.getSharedPreferences("address", AppCompatActivity.MODE_PRIVATE)
        carts= sharedPreferencesAdress.getString("nameAddress", "").toString()

        if (carts == "") {
            dataItem = arrayListOf()

        } else {
            dataItem = gson.fromJson(carts, type);
        }
        setUpViewModel()
        initUI(view)
        setupObserve()
        println(checkoutViewModel)

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
        updatePhone=view.findViewById(R.id.updatePhone)
        itemCheckoutListView = view.findViewById(R.id.listItemCheckout)
        checkoutAdapter = CheckoutApdater(arrayListOf(),this,view.context)
        itemCheckoutListView.adapter=checkoutAdapter
        btnShowBottomSheet = view.findViewById(R.id.method_payment)
        btnCheckout = view.findViewById(R.id.btnPlaceOrderCheckout)
        methodSelected= view.findViewById(R.id.methodSelected)
        toggleAddress = view.findViewById(R.id.toggleAddress)
        phoneUser = view.findViewById(R.id.phoneUser)
        sharedPreferencesTime = view.context.getSharedPreferences("timeShip", AppCompatActivity.MODE_PRIVATE)
        val time=sharedPreferencesTime.getString("time","Current time")

        if(time!="Current time") {
            timeCheckout.text = time;

        }
        else{
            timeCheckout.text = "Hôm nay| Trong 15 phút";
        }
        val sharedPreferences_address = view.context.getSharedPreferences("address", AppCompatActivity.MODE_PRIVATE)
        val address=sharedPreferences_address.getString("addressSelected","Address")
        if(address!="Address") {
            toggleAddress.text = address
        }
        else
        {
            toggleAddress.text = "Address"
        }

    }



    private fun setUpViewModel(){
//        checkoutViewModel =ViewModelProvider(this)[CheckoutViewModel::class.java]
        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]


    }
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    private fun setupObserve(){

        btnShowBottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(view.context)
            val viewitem = layoutInflater.inflate(R.layout.payment_method_layout, null)
            val momo = viewitem.findViewById<LinearLayout>(R.id.momo)
            val paypal = viewitem.findViewById<LinearLayout>(R.id.paypal)
            momo.setOnClickListener {
                methodSelected.text= "ZaloPay"
                checkoutViewModel.setpromontionID("3")
                dialog.dismiss()
            }
            paypal.setOnClickListener {
                methodSelected.text= "Paypal"
                checkoutViewModel.setpromontionID("1")
                dialog.dismiss()
            }
            dialog.setContentView(viewitem)
            dialog.show()
        }
        appModel.getCartItemViewModel().cartItems.observe(viewLifecycleOwner){
            val items=it
            items.forEach{
                subtotal+=it.getPrice()
            }
            cartItemCallAPI.apply {
                cartItemCallAPI.addAll(it)
            }
            checkoutViewModel.subTotal.value=subtotal
            subTotal.text= Utils.formatCurrency(checkoutViewModel.subTotal.value!!) + " đ"

            println(checkoutViewModel.getPercentVoucher())
            total.text =  Utils.formatCurrency( (checkoutViewModel.subTotal.value!!-(checkoutViewModel.subTotal.value!!*checkoutViewModel.getPercentVoucher()))) + " đ"
            checkoutAdapter.apply {
                addItems(items)
            }
        }
        if(checkoutViewModel.getAddress().isNotEmpty()){
            showAddress.visibility = View.VISIBLE
        }
        back_checkout.setOnClickListener {
//            fragmentManager?.popBackStack()
            //Quay ve các activity Trước do
            val activity = activity
            // Kiểm tra xem đối tượng Activity có khác null không
            if (activity != null) {
                // Sử dụng phương thức onBackPressed() của Activity để quay lại Activity trước đó
                activity.onBackPressed()
            }
            checkoutViewModel.setPercentVoucher(0.0)

        }
            sharedPreferencesPhone = view.context.getSharedPreferences("phone", AppCompatActivity.MODE_PRIVATE)
        val phoneShare= sharedPreferencesPhone.getString("phoneUser","Số điện thoại")
        if(phoneShare!="Số điện thoại")
        {
            phoneUser.text=phoneShare
        }
        else{
            phoneUser.setOnClickListener {
                val dialog = CustomDialog(view.context)
                dialog.setUpDialogPhone()
                println(checkoutViewModel)
            }
        }
        updatePhone.setOnClickListener {
            val dialog = CustomDialog(view.context)
            dialog.setUpDialogPhone()
            println(checkoutViewModel)
        }

        btnCheckout.setOnClickListener {
            if(checkoutViewModel.getAddress()!="None")
            {
                val orderApi = CreateOrder()
                try {
                    val temp=Utils.getDigitInString(total.text.toString())
                    val data: JSONObject = orderApi.createOrder(temp.toInt().toString())
                    println(data)
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
                                        val address = checkoutViewModel.getAddress()
                                        val id = view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
                                        val userID = id.getString("userID", null)
                                        if(userID!=null){
                                            val newOrder =com.example.myapplication.modals.Order(userID.toInt(),checkoutViewModel.getTime(),address,Utils.getDigitInString(total.text.toString()),0,checkoutViewModel.getPromotionID(),3)
                                            println("new"+newOrder)
                                            orderViewModel.createOrder(newOrder,cartItemCallAPI, transactionId)
                                            val sharedPreferences = view.context.getSharedPreferences("cart", AppCompatActivity.MODE_PRIVATE)
                                            sharedPreferences.edit().remove("productID").apply()
                                            val sharedPreferences_address = view.context.getSharedPreferences("address", AppCompatActivity.MODE_PRIVATE)
                                            sharedPreferences_address.edit().remove("addressSelected").apply()
                                            val sharedPreferencesTime = view.context.getSharedPreferences("timeShip", AppCompatActivity.MODE_PRIVATE)
                                            sharedPreferencesTime.edit().remove("time").apply()
                                            val sharedPreferences_phone = view.context.getSharedPreferences("phone", AppCompatActivity.MODE_PRIVATE)
                                            sharedPreferences_phone.edit().remove("phoneUser").apply()
                                            appModel.removeAllCart(userID.toInt())
                                            (view.context as FragmentActivity).supportFragmentManager
                                                .beginTransaction()
                                                .replace(R.id.flFragment, Activities(),"Activities").addToBackStack(null)
                                                .commit()
                                        }

                                        sDialog.dismissWithAnimation()
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
            else
            {
                SweetAlertDialog(view.context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Hãy chọn địa chỉ giao hàng ")
                    .setContentText("Vui lòng xác nhận lại địa chỉ giao hàng và số điện thoại")
                    .setConfirmText("Đồng ý")
                    .setConfirmClickListener { sDialog ->
                        sDialog.dismissWithAnimation()
                    }
                    .show()
            }

        }

        cancelCheckout.setOnClickListener {
            //call api and delete Order
//            (view.context as FragmentActivity).supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.flFragment, Order(),"Order").addToBackStack(null)
//                .commit()
//
//            val refundAPI = Refund()
//            val data: JSONObject = refundAPI.refund("1000")
//            println(data)
//            val address = checkoutViewModel.getAddress()
////
//            val id = view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
//            val userID = id.getString("userID", "")
//            SocketHandler.setSocket()
//            SocketHandler.establishConnection()
//            mSocket = SocketHandler.getSocket()
//            cartItemCallAPI = ArrayList()
//            val newOrder =com.example.myapplication.modals.Order(38,"2023-05-13 16:05:00","test",123.0,0,2,3)
//            val result=orderViewModel.createOrder(newOrder,cartItemCallAPI,"")


        }
        addressCheckout.setOnClickListener {
          val dialog = CustomDialog(view.context)
            dialog.setUpDialog()
            println(checkoutViewModel)

        }

        timeCheckout.setOnClickListener{
            handleGetTime()
            println(checkoutViewModel)
        }

        addItem.setOnClickListener {
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, Order(),"Order").addToBackStack(null)
                .commit()
        }


        discount.setOnClickListener {
            val intent=Intent(
                view.context,
                ListPromotion::class.java
            )
            intent.putExtra("source","checkout")
            checkoutViewModel.setPercentVoucher(0.0)
            startActivity(intent)

        }
    }
    fun handleNewIntent(intent: Intent?) {
        ZaloPaySDK.getInstance().onResult(intent)
    }
   inner class CustomDialog(context: Context) {
        private var dialog: Dialog = Dialog(context)
       private lateinit var addressListView:ListView
       private lateinit var closeButton:ImageView
       private lateinit var selectedAddress:TextView
       private lateinit var closeButtonPhone:ImageView
       private lateinit var selectedPhone:TextView
        @SuppressLint("SuspiciousIndentation")
        fun setUpDialog(){

            dialog.setContentView(R.layout.custom_dialog_input_address)
            addressListView =dialog.findViewById(R.id.listAddress)
            closeButton = dialog.findViewById(R.id.closeDialogAddress)
            adddressAdapter = AddressApdapter(arrayListOf(),dialog.context)
            selectedAddress= dialog.findViewById(R.id.selectAddress)

                dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.setCancelable(true)
            dialog.window!!.attributes.windowAnimations = R.style.CustomBottomSheetDialogTheme
            closeButton.setOnClickListener {
                dialog.dismiss()
            }

            adddressAdapter.addAddress(dataItem)
            addressListView.adapter=adddressAdapter
            val addAddres = dialog.findViewById<TextView>(R.id.add_Address)
            val edtAddress = dialog.findViewById<EditText>(R.id.addressUser)
            addAddres.setOnClickListener(View.OnClickListener {
                dataItem.add(edtAddress.text.toString())
                adddressAdapter.addAddress(dataItem)
                sharedPreferencesAdress.edit().putString("nameAddress", gson.toJson(dataItem)).apply()
                edtAddress.text.clear()
            })
            selectedAddress.setOnClickListener {
                    toggleAddress.text = checkoutViewModel.getAddress()
                sharedPreferencesAdress.edit().putString("addressSelected",toggleAddress.text.toString()).apply()
                    dialog.dismiss()
            }
            addressListView.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id -> // Handle item click here
                    val checkBoxAddress = adapterView.getItemAtPosition(position) as String
                    checkoutViewModel.setAddress(checkBoxAddress)
                    adapterView[position].findViewById<CheckBox>(R.id.checkboxAddress).isChecked=selectedAddressBoolean
                    selectedAddressBoolean = !selectedAddressBoolean
                }
            dialog.show()
        }

       fun setUpDialogPhone(){
           dialog.setContentView(R.layout.custom_dialog_phone)
           closeButtonPhone = dialog.findViewById(R.id.closeDialogPhone)
           selectedPhone= dialog.findViewById(R.id.selectPhone)
           dialog.window!!.setLayout(
               ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.WRAP_CONTENT
           )
           dialog.setCancelable(true)
           dialog.window!!.attributes.windowAnimations = R.style.CustomBottomSheetDialogTheme
           closeButtonPhone.setOnClickListener {
               dialog.dismiss()
           }
           val sharedPreferences = dialog.context.getSharedPreferences("phone", AppCompatActivity.MODE_PRIVATE)
           val edtPhone = dialog.findViewById<EditText>(R.id.addphone)
           selectedPhone.setOnClickListener(View.OnClickListener {
               sharedPreferences.edit().putString("phoneUser",edtPhone.text.toString()).apply()
                phoneUser.text=edtPhone.text.toString()
               edtPhone.text.clear()
               dialog.dismiss()
           })
           dialog.show()
       }
    }

    inner class AddressApdapter(private val address: ArrayList<String>, mContext: Context) :
        ArrayAdapter<Any?>(mContext, R.layout.item_address, address as ArrayList<*>) {
        private  var selectedPostion=-1
        private inner class ViewHolder {
            lateinit var checkBox: CheckBox
            lateinit var txtName: TextView
            lateinit var iconDelete: ImageView

        }
        override fun getCount(): Int {
            return address.size
        }
        override fun getItem(position: Int): String {
            return address[position]
        }
        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            var convertView = convertView
            val viewHolder: ViewHolder
            val result: View
            if (convertView == null) {
                viewHolder = ViewHolder()
                convertView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)
                viewHolder.txtName =
                    convertView.findViewById(R.id.nameAddress)
                viewHolder.checkBox =
                    convertView.findViewById(R.id.checkboxAddress)
                viewHolder.iconDelete = convertView.findViewById(R.id.iconDeleteAddres)
                result = convertView
                convertView.tag = viewHolder
            } else {
                viewHolder = convertView.tag as ViewHolder
                result = convertView
            }
            val item: String = getItem(position)
            viewHolder.txtName.text = item
            viewHolder.checkBox.isChecked = false
            viewHolder.iconDelete.setOnClickListener {

                 dataItem = dataItem.filter {
                    it!=item
                } as ArrayList
                adddressAdapter.addAddress(dataItem)
                sharedPreferencesAdress.edit().putString("nameAddress", gson.toJson(dataItem)).apply()
            }


            return result
        }
        fun addAddress(addressParam: ArrayList<String>) {
            this.address.apply {
                clear()
                addAll(addressParam)
                notifyDataSetChanged()
            }
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