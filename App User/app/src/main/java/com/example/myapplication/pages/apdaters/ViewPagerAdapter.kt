package com.example.myapplication.pages.apdaters

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.pages.fragments.HistoryOrder
import com.example.myapplication.pages.fragments.OnGoingOrder

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return OnGoingOrder()
            1 -> return HistoryOrder()
            else ->{
                return Fragment()
            }
        }
    }
}