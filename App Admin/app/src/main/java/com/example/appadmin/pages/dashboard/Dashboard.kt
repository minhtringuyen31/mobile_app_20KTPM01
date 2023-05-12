package com.example.appadmin.pages.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.modals.DashboardItems

class Dashboard : AppCompatActivity() {
    private val dashboardItems = ArrayList<DashboardItems>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dashboardItems.add(DashboardItems("Product", R.drawable.baseline_shopping_bag_24))
        dashboardItems.add(DashboardItems("Category", R.drawable.baseline_category_24))
        dashboardItems.add(DashboardItems("User", R.drawable.baseline_account_circle_24))
        dashboardItems.add(DashboardItems("Order", R.drawable.baseline_shopping_cart_24))
        dashboardItems.add(DashboardItems("Topping", R.drawable.baseline_fastfood_24))
        dashboardItems.add(DashboardItems("Promotion", R.drawable.baseline_discount_24))

        val adapter = DashboardAdapter(this, dashboardItems)

        val rvDashboard = findViewById<RecyclerView>(R.id.dashboardRV)

        rvDashboard.adapter = adapter

        rvDashboard.layoutManager = GridLayoutManager(this, 2)
    }
}