package com.example.myapplication.Admin.pages.topping

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.controllers.ToppingController
import com.example.myapplication.Admin.modals.Topping
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Toppings : AppCompatActivity() {
    private lateinit var toppingList: List<Topping>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toppings)

        val toppingRv = findViewById<RecyclerView>(R.id.toppingRV)
        val searchTopping = findViewById<AutoCompleteTextView>(R.id.searchTopping)
        val toppingSpinner = findViewById<Spinner>(R.id.toppingSpinner)
        toppingRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        findViewById<Button>(R.id.addTopping).setOnClickListener {
            val intent = Intent(this, AddTopping::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.backToppingBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        ViewModelProvider(this)[CategoryController::class.java].getAllCategory().observe(this) {
            var categories = it
            categories = categories.reversed()

            val categoryListName = mutableListOf<String>()
            categoryListName.add("All")
            for (category in categories) {
                println(category.getName())
                category.getName()?.let { it1 -> categoryListName.add(it1) }
            }
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categoryListName
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                toppingSpinner.adapter = adapter
            }
        }

        ViewModelProvider(this)[ToppingController::class.java].getAllTopping().observe(this) {
            val toppings = it
            toppingList = it

            val adapter = ToppingAdapter(this, toppings)
            toppingRv.adapter = adapter
            val adapterACTV = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_single_choice,
                toppingList
            )
            searchTopping.setAdapter(adapterACTV)
        }
        searchTopping.addTextChangedListener {
            val toppingResult = toppingList.filter { topping ->
                topping.getName()!!.contains(searchTopping.text.toString(), true)
            }
            val adapter = ToppingAdapter(
                this,
                toppingResult
            )
            toppingRv.adapter = adapter
        }
        toppingSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position == 0) {
                    val adapter = ToppingAdapter(this@Toppings, toppingList)
                    toppingRv.adapter = adapter
                    return
                } else {
                    val toppingResult = toppingList.filter { topping ->
                        topping.getCategory_id() == position
                    }
                    val adapter = ToppingAdapter(
                        this@Toppings,
                        toppingResult
                    )
                    toppingRv.adapter = adapter
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
}