package com.example.appadmin.pages.topping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.controllers.ToppingController
import com.example.appadmin.modals.Category
import com.example.appadmin.modals.Topping

class EditTopping : AppCompatActivity() {
    private lateinit var categories: List<Category>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_topping)

        val name = findViewById<EditText>(R.id.editToppingName)
        val price = findViewById<EditText>(R.id.editToppingPrice)
        val checked = findViewById<CheckBox>(R.id.editToppingChecked)
        val category = findViewById<Spinner>(R.id.editToppingCategory)

        val intent = intent
        val id = intent.getStringExtra("toppingId")

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
                category.adapter = adapter
            }
        }

        val toppingViewProvider = ViewModelProvider(this)[ToppingController::class.java]
        toppingViewProvider.getTopping(id!!.toInt()).observe(this) {
            name.setText(it.getName())
            price.setText(it.getPrice().toString())
            checked.isChecked = it.getChecked() == 1
            category.setSelection(it.getCategory_id()!! - 1)
        }

        findViewById<Button>(R.id.editTopping_cancelBtn).setOnClickListener {
            val intent = Intent(this, Toppings::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.editTopping_saveBtn).setOnClickListener {
            val topping = Topping(
                categories[category.selectedItemPosition].getId(),
                name.text.toString(),
                price.text.toString().toDouble(),
                checked.isChecked.let { if (it) 1 else 0 },
                1
            )

            toppingViewProvider.updateTopping(id!!.toInt(), topping).observe(this) {

            }
            val intent = Intent(this, Toppings::class.java)
            startActivity(intent)
        }
    }
}