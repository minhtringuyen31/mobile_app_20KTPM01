package com.example.appadmin.pages.product

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.controllers.ProductController
import com.example.appadmin.modals.Category
import com.example.appadmin.utils.RealPathUtil
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.time.LocalDateTime

class AddProduct : AppCompatActivity() {
    private lateinit var categories: List<Category>
    private val REQUEST_CODE = 100
    private lateinit var imageFile: File
    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val imageUri = it.data!!.data
            val imageView = findViewById<ImageView>(R.id.addProductImage)
            val realPathUtil = RealPathUtil().getRealPath(this, imageUri!!)
            imageFile = realPathUtil?.let { it1 -> File(it1) }!!
            imageView.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val productImageBtn = findViewById<Button>(R.id.addProductImageBtn)
        val productName = findViewById<TextView>(R.id.addProductName)
        val productDesc = findViewById<TextView>(R.id.addProductDesc)
        val productSize = findViewById<Spinner>(R.id.addProductSize)
        val productSizeS = findViewById<TextView>(R.id.addProductSizeS)
        val productSizeM = findViewById<TextView>(R.id.addProductSizeM)
        val productSizeL = findViewById<TextView>(R.id.addProductSizeL)
        val categorySpinner = findViewById<Spinner>(R.id.addProductCategory)

        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arrayOf("S", "M", "L")
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            productSize.adapter = adapter
        }

        val categoryViewAdapter = ViewModelProvider(this)[CategoryController::class.java]
        categoryViewAdapter.getAllCategory().observe(this) {
            categories = it
            val categoriesListName = mutableListOf<String>()
            for (category in categories) {
                category.getName()?.let { it1 -> categoriesListName.add(it1) }
            }
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categoriesListName
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                categorySpinner.adapter = adapter
            }
        }

        findViewById<Button>(R.id.addProduct_saveBtn).setOnClickListener {
            val requestName = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                productName.text.toString()
            )
            val requestDesc = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                productDesc.text.toString()
            )
            val requestSize = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                productSize.selectedItemPosition.let { it1 -> arrayOf("S", "M", "L")[it1] }
            )
            val requestSizeS = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                productSizeS.text.toString()
            )
            val requestSizeM = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                productSizeM.text.toString()
            )
            val requestSizeL = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                productSizeL.text.toString()
            )
            val requestStatus = RequestBody.create(MediaType.parse("multipart/form-data"), "1")
            val requestCategory = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                categories[categorySpinner.selectedItemPosition].getId().toString()
            )
            val requestUpdateDate = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                LocalDateTime.now().toString()
            )
            val requestReleaseDate = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                LocalDateTime.now().toString()
            )
            val requestSales = RequestBody.create(MediaType.parse("multipart/form-data"), "0")
            val requestImage = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
            val image = MultipartBody.Part.createFormData("image", imageFile.name, requestImage)

            val productViewAdapter = ViewModelProvider(this)[ProductController::class.java]
            productViewAdapter.createProduct(
                requestName,
                requestDesc,
                requestSize,
                requestSizeS,
                requestSizeM,
                requestSizeL,
                requestStatus,
                requestCategory,
                requestUpdateDate,
                requestReleaseDate,
                requestSales,
                image
            ).observe(this) {

            }
            val intent = Intent(this, Products::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.addProduct_cancelBtn).setOnClickListener {
            val intent = Intent(this, Products::class.java)
            startActivity(intent)
        }

        productImageBtn.setOnClickListener {
            onCLickRequestPermission()
        }
    }

    private fun onCLickRequestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            val permission = arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            requestPermissions(permission, REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
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