package com.example.appadmin.pages.category

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.modals.Category

class AddCategory : AppCompatActivity() {
    private val REQUEST_CODE = 100
    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val imageUri = it.data!!.data
            val imageView = findViewById<ImageView>(R.id.addCategoryImage)
            imageView.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        val categoryImage = findViewById<ImageView>(R.id.addCategoryImage)
        val categoryImageBtn = findViewById<Button>(R.id.addCategoryImageBtn)

        findViewById<Button>(R.id.addCategory_cancelBtn).setOnClickListener {
            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.addCategory_saveBtn).setOnClickListener {
            val category = Category(
                1,
                findViewById<TextView>(R.id.addCategoryName).text.toString(),
                0,
                categoryImage.resources.toString()
            )

            val categoryViewProvider = ViewModelProvider(this)[(CategoryController::class.java)]
            categoryViewProvider.createCategory(category).observe(this) {}

            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }

        categoryImageBtn.setOnClickListener {
            onCLickRequestPermission()
        }
    }

    private fun onCLickRequestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE ->
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }
}