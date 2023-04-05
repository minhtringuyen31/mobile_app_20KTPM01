package com.example.appadmin.pages.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.Account
import com.example.appadmin.Product
import com.example.appadmin.R
import com.example.appadmin.pages.product.ProductAdapter

class Accounts : AppCompatActivity() {
    private val accountItems = ArrayList<Account>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))
        accountItems.add(Account("0", "0", "0", "0", "0", "0", "0", false, "0", "0"))

        val adapter = AccountAdapter(this, accountItems)

        val rvProduct = findViewById<RecyclerView>(R.id.accountRV)

        rvProduct.adapter = adapter

        rvProduct.layoutManager = LinearLayoutManager(this)
    }
}