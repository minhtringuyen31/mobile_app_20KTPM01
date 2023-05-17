package com.example.myapplication.Admin.pages.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.modals.DashboardItems
import com.example.myapplication.R
import com.example.myapplication.pages.activities.user.Login


class Dashboard : AppCompatActivity() {
    private val dashboardItems = ArrayList<DashboardItems>()
    private lateinit var logo:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dashboardItems.add(DashboardItems("Product", R.drawable.baseline_shopping_bag_24))
        dashboardItems.add(DashboardItems("Category", R.drawable.baseline_category_24))
        dashboardItems.add(DashboardItems("User", R.drawable.baseline_account_circle_24))
        dashboardItems.add(DashboardItems("Order", R.drawable.baseline_shopping_cart_24))
        dashboardItems.add(DashboardItems("Topping", R.drawable.baseline_fastfood_24))
        dashboardItems.add(DashboardItems("Promotion", R.drawable.baseline_discount_24))
        dashboardItems.add(DashboardItems("Comment", R.drawable.baseline_comment_24))

        logo = findViewById(R.id.logoAvatar)
        logo.setOnClickListener {
            val intent = Intent(
                this,
                Login::class.java
            )
            val sharedPreferences =
                getSharedPreferences("user", MODE_PRIVATE)
            val editor1 = sharedPreferences.edit().remove("role").apply()
            val editor2 = sharedPreferences.edit().remove("userID").apply()
            startActivity(intent)
        }

        val adapter = DashboardAdapter(this, dashboardItems)

        val rvDashboard = findViewById<RecyclerView>(R.id.dashboardRV)

        rvDashboard.adapter = adapter

        rvDashboard.layoutManager = GridLayoutManager(this, 2)
    }
}