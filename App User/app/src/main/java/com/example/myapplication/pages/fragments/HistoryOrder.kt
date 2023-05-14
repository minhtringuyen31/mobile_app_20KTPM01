package com.example.myapplication.pages.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Order
import com.example.myapplication.pages.OrderDetail
import com.example.myapplication.pages.apdaters.OrderListAdapter
import com.example.myapplication.viewmodels.AppViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryOrder.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryOrder : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var historyOrderListAdapter: OrderListAdapter
    private lateinit var historyOrderListRecyclerView: RecyclerView
    private val appModel: AppViewModel by activityViewModels()
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

        val view =inflater.inflate(R.layout.fragment_history_order, container, false) ;
        println("History")

        val sharedPreferences: SharedPreferences =
            view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userID", "").toString().toInt()
        initUI(view)
        setUpObserve(userId);
        return view;
    }


     fun initUI(view: View){
        historyOrderListRecyclerView = view.findViewById(R.id.historyOderListRV)
        historyOrderListAdapter = OrderListAdapter(arrayListOf())
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        historyOrderListRecyclerView.layoutManager = layoutManager
        historyOrderListRecyclerView.adapter=historyOrderListAdapter;
         historyOrderListAdapter.onItemClick = {order ->
             val intent  = Intent(
                 requireContext(),
                 OrderDetail::class.java
             )
             intent.putExtra("orderId", order.getId().toString())
             println("Order ID " + order.getId())
             intent.putExtra("orderPromotion", order.getPromotionId().toString())
             intent.putExtra("orderTotalPrice", order.getTotal().toString())
             intent.putExtra("orderStatus", order.getStatus().toString())
             startActivity(intent)
         }
    }


    @SuppressLint("NotifyDataSetChanged")

    private fun setUpObserve(userId: Int){
        appModel.setUpOrderViewModel(this, userId);
        appModel.getOrderViewModel().orderProduct.observe(viewLifecycleOwner){
            historyOrderListAdapter.addOrders(it.filter { it.getStatus()==2 } as ArrayList<Order>) ;
            historyOrderListAdapter.notifyDataSetChanged();
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryOrder.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryOrder().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}