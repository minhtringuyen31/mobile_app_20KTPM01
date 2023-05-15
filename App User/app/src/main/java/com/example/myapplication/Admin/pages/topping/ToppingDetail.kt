package com.example.myapplication.Admin.pages.topping

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.controllers.ToppingController
import com.example.myapplication.R

class ToppingDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topping_detail)

        val intent = intent
        val id = intent.getStringExtra("toppingId")

        val toppingViewProvider = ViewModelProvider(this)[ToppingController::class.java]
        toppingViewProvider.getTopping(id!!.toInt()).observe(this) {
            findViewById<TextView>(R.id.detailToppingName).text = it.getName()
            findViewById<TextView>(R.id.detailToppingPrice).text = it.getPrice().toString()
            ViewModelProvider(this)[CategoryController::class.java].getCategory(
                it.getCategory_id()!!
            ).observe(this) { category ->
                findViewById<TextView>(R.id.detailToppingCategory).text = category.getName()
            }
        }

        findViewById<Button>(R.id.detailTopping_cancelBtn).setOnClickListener {
            val intent1 = Intent(this, Toppings::class.java)
            startActivity(intent1)
        }

        findViewById<Button>(R.id.detailTopping_editBtn).setOnClickListener {
            val intent2 = Intent(this, EditTopping::class.java)
            intent2.putExtra("toppingId", id)
            startActivity(intent2)
        }

    }
}