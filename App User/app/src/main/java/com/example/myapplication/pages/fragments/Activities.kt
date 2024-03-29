package com.example.myapplication.pages.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.pages.apdaters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Activities.newInstance] factory method to
 * create an instance of this fragment.
 */
class Activities : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var tabLayout : TabLayout?=null
    private var viewPager : ViewPager? = null
    private var viewPagerAdapter: ViewPagerAdapter? =null
    private lateinit var view:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    fun getItem(position:Int):Fragment{
       return viewPagerAdapter!!.getItem(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view=inflater.inflate(R.layout.fragment_activities, container, false)
        // Inflate the layout for this fragment
        (activity as MainActivity).showToolbarAndNavigationBar(true)
        initUI(view)
        return view;
    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        val fragmentHistory: Fragment = HistoryOrder()
        requireFragmentManager().beginTransaction()
            .add(HistoryOrder(),"HistoryOrder")
            .addToBackStack("Homepage").commit()
        val fragmentOnGoing: Fragment = OnGoingOrder()
        requireFragmentManager().beginTransaction()
            .add(OnGoingOrder(),"OnGoing")
            .addToBackStack("Homepage").commit()
        val fragmentConfirmOrder: Fragment = ConfirmOrder()
        requireFragmentManager().beginTransaction()
            .add(OnGoingOrder(),"Confirm")
            .addToBackStack("Homepage").commit()
        val fragmentCancel: Fragment = CancelOrder()
        requireFragmentManager().beginTransaction()
            .add(OnGoingOrder(),"Cancel")
            .addToBackStack("Homepage").commit()
        val sharedPreference=view.context.getSharedPreferences("Setting", Activity.MODE_PRIVATE)
        val language=sharedPreference.getString("My_Lang","")
        if (language != null&&language=="vn") {

            adapter.addFragment(fragmentOnGoing, "Đang xử lý")
            adapter.addFragment(fragmentConfirmOrder, "Đơn hàng được xác nhận")
            adapter.addFragment(fragmentHistory, "Giao hàng thành công")
            adapter.addFragment(fragmentCancel, "Khác")
        }
        else{
            adapter.addFragment(fragmentOnGoing, "On Going")
            adapter.addFragment(fragmentConfirmOrder, "Order confirmed")
            adapter.addFragment(fragmentHistory, "Delivery successful")
            adapter.addFragment(fragmentCancel, "Other")
        }


        viewPager.adapter = adapter
    }

    fun initUI(view: View){
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager?.offscreenPageLimit = 4;
        viewPager?.let { setupViewPager(it) }

        tabLayout?.setupWithViewPager(viewPager)



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Activities.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Activities().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}