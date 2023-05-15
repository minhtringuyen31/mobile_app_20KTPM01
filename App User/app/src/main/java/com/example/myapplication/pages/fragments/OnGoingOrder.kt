package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Order
import com.example.myapplication.pages.apdaters.OrderListAdapter
import com.example.myapplication.pages.activities.order.OrderDetail
import com.example.myapplication.viewmodels.AppViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OnGoingOrder.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnGoingOrder : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val appModel: AppViewModel by activityViewModels()
    private lateinit var onGoingOrderListAdapter: OrderListAdapter
    private lateinit var onGoingOrderListRecyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager



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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_going_order, container, false)

        val sharedPreferences: SharedPreferences =
            view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userID", null)
        if(userId!=null){
            setUpObserve(userId.toString().toInt());
        }
        initUI(view)

        println("On Going")
        return view
    }

    //0-


    private fun initUI(view: View){

        onGoingOrderListRecyclerView = view.findViewById(R.id.onGoingOderListRV)
        onGoingOrderListAdapter = OrderListAdapter(arrayListOf())
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        onGoingOrderListRecyclerView.layoutManager = layoutManager
        onGoingOrderListRecyclerView.adapter = onGoingOrderListAdapter;
        onGoingOrderListAdapter.onItemClick = {order ->
            val intent  =Intent(
                requireContext(),
                OrderDetail::class.java
            )
            intent.putExtra("orderId", order.getId().toString())
            println("Order ID " + order.getId())
            intent.putExtra("orderPromotion", order.getPromotionId().toString())
            intent.putExtra("orderTotalPrice", order.getTotal().toString())
            intent.putExtra("orderStatus", order.getStatus().toString())
            intent.putExtra("paymentMethodID", order.getPaymentMethodId().toString())
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserve(userId: Int){
        appModel.setUpOrderViewModel(this, userId);
        appModel.getOrderViewModel().orderProduct.observe(viewLifecycleOwner){
            onGoingOrderListAdapter.addOrders(it.filter { it.getStatus()==0 } as ArrayList<Order>) ;
            onGoingOrderListAdapter.notifyDataSetChanged();

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OnGoingOrder.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OnGoingOrder().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}