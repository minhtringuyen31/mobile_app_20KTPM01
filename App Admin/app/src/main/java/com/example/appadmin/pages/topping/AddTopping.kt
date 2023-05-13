package com.example.appadmin.pages.topping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.controllers.ToppingController
import com.example.appadmin.modals.Category
import com.example.appadmin.modals.Topping

class AddTopping : AppCompatActivity() {
    private lateinit var categories: List<Category>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_topping)

        val toppingSpinner = findViewById<Spinner>(R.id.addToppingCategory)

        val categoryViewProvider = ViewModelProvider(this)[CategoryController::class.java]
        categoryViewProvider.getAllCategory().observe(this) {
            categories = it
            val toppingsListName = mutableListOf<String>()
            for (topping in categories) {
                topping.getName()?.let { it1 -> toppingsListName.add(it1) }
            }
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                toppingsListName
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                toppingSpinner.adapter = adapter
            }
        }

        findViewById<Button>(R.id.addTopping_cancelBtn).setOnClickListener {
            val intent = Intent(this, Toppings::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.addTopping_saveBtn).setOnClickListener {
            val topping = Topping(
                categories[toppingSpinner.selectedItemPosition].getId(),
                findViewById<EditText>(R.id.addToppingName).text.toString(),
                findViewById<EditText>(R.id.addToppingPrice).text.toString().toDouble(),
                findViewById<CheckBox>(R.id.addToppingChecked).isChecked.let { if (it) 1 else 0 },
                1
            )
            val toppingViewProvider = ViewModelProvider(this)[ToppingController::class.java]
            toppingViewProvider.createTopping(topping).observe(this) {

            }
            val intent = Intent(this, Toppings::class.java)
            startActivity(intent)
        }
    }
}