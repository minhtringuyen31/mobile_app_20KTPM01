package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.pages.apdaters.CheckoutApdater
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.CheckoutViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

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
    private lateinit var btnCheckout:TextView;
    private lateinit var checkoutViewModel:CheckoutViewModel
    private lateinit var cancelCheckout:TextView;
    private lateinit var back_checkout:ImageView
    private lateinit var addressCheckout:LinearLayout;
    private lateinit var timeCheckout:TextView;
    private lateinit var subTotal:TextView
    private lateinit var total:TextView;
    private lateinit var addItem:TextView
    private lateinit var discount:TextView;
    private var subtotal=0.0
    private lateinit var showAddress:TextView;
    private lateinit var methodSelected:TextView;





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_checkout, container, false)
        // Inflate the layout for this fragment
        (activity as MainActivity).showToolbarAndNavigationBar(false)
        setUpViewModel();
        initUI(view);
        setupObserve()

        return view;
    }
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
        btnShowBottomSheet = view.findViewById(R.id.method_payment);
        btnCheckout = view.findViewById(R.id.btnPlaceOrderCheckout)
        methodSelected= view.findViewById(R.id.methodSelected)

        // adding on click listener for our button.
        btnShowBottomSheet.setOnClickListener {

            // on below line we are creating a new bottom sheet dialog.
            val dialog = BottomSheetDialog(view.context)

            // on below line we are inflating a layout file which we have created.
            val viewitem = layoutInflater.inflate(R.layout.payment_method_layout, null)
            val momo = viewitem.findViewById<LinearLayout>(R.id.momo);
            val paypal = viewitem.findViewById<LinearLayout>(R.id.paypal);
            momo.setOnClickListener {
                methodSelected.text= "Momo"
                dialog.dismiss()
            }
            paypal.setOnClickListener {
                methodSelected.text= "Paypal"
                dialog.dismiss()
            }
            // on below line we are setting
            // content view to our view.
            dialog.setContentView(viewitem)


            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }
    }
    private fun setUpViewModel(){
        checkoutViewModel =ViewModelProvider(this)[CheckoutViewModel::class.java]

    }

    @SuppressLint("SetTextI18n")
    private fun setupObserve(){

        appModel.getCartItemViewModel().cartItems.observe(viewLifecycleOwner){
            val items=it

            items.forEach{
                subtotal+=it.getPrice();
            }

            checkoutViewModel.subTotal.value=subtotal;
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
            //call api and wait admin confirm
        }

        cancelCheckout.setOnClickListener {
            //call api and delete Order
        }
        addressCheckout.setOnClickListener {
          var dialog = view?.let { it1 -> CustomDialog(it1.context) }
            dialog?.setUpDialog();
        }

        timeCheckout.setOnClickListener{

        }
        checkoutViewModel.subTotal.observe(viewLifecycleOwner){

        }
        println(checkoutViewModel.getAddress());

        addItem.setOnClickListener {
            (view?.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, Order()).addToBackStack(null)
                .commit()
        }
        discount.setOnClickListener {
                // Replace fragment or activity Promotio
        }
    }
   inner class CustomDialog(context: Context) {
        var dialog: Dialog = Dialog(context);
        fun setUpDialog(){
            dialog.setContentView(R.layout.custom_dialog_input_address)
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.setCancelable(false)
            dialog.window!!.attributes.windowAnimations = R.style.CustomBottomSheetDialogTheme

            var okay_text = dialog.findViewById<TextView>(R.id.add_Address)

            var edtAddress = dialog.findViewById<EditText>(R.id.addressUser)
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