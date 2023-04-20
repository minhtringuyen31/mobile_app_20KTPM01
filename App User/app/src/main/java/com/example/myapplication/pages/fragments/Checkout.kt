package com.example.myapplication.pages.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.pages.apdaters.CheckoutApdater
import com.example.myapplication.viewmodels.AppViewModel
import com.example.myapplication.viewmodels.ProductCartViewModel
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

        initUI(view);
        setupObserve()
        return view;
    }
    fun initUI(view:View){

        itemCheckoutListView = view.findViewById(R.id.listItemCheckout)
        checkoutAdapter = CheckoutApdater(arrayListOf(),this,view.context)
        itemCheckoutListView.adapter=checkoutAdapter
        btnShowBottomSheet = view.findViewById(R.id.method_payment);

        // adding on click listener for our button.
        btnShowBottomSheet.setOnClickListener {

            // on below line we are creating a new bottom sheet dialog.
            val dialog = BottomSheetDialog(view.context)

            // on below line we are inflating a layout file which we have created.
            val viewitem = layoutInflater.inflate(R.layout.payment_method_layout, null)

            // on below line we are setting
            // content view to our view.
            dialog.setContentView(viewitem)

            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }
    }
    fun setUpViewModel(){

    }
    fun setupObserve(){
        appModel.getCartItemViewModel().cartItems.observe(viewLifecycleOwner){
            val items=it
            checkoutAdapter.apply {
                addItems(items)
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