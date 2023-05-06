package com.example.appadmin.pages.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.modals.Category

class EditCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)

        val id = intent.getStringExtra("categoryId")
        val name = findViewById<TextView>(R.id.editCategoryName)
        val image = findViewById<ImageView>(R.id.editCategoryImage)
        val disableBtn = findViewById<Button>(R.id.editCategory_disableBtn)
        val disableIcon = findViewById<ImageView>(R.id.isDisableCategory)

        val categoryViewProvider = ViewModelProvider(this)[CategoryController::class.java]
        categoryViewProvider.getCategory(id!!.toInt()).observe(this) {
            name.text = it.getName()
            Glide.with(this).load(it.getImage()).into(image)
            disableBtn.text = if (it.getIsDisable() == 1) "Mở" else "Khóa"
            disableIcon.setImageResource(
                if (it.getIsDisable() == 1)
                    R.drawable.baseline_disable_24
                else R.drawable.baseline_able_24
            )
        }

        findViewById<Button>(R.id.editCategory_saveBtn).setOnClickListener {

            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.editCategory_cancelBtn).setOnClickListener {
            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }
    }
}