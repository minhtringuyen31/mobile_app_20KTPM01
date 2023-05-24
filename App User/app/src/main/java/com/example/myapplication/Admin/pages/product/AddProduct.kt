package com.example.myapplication.Admin.pages.product

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.controllers.ProductController
import com.example.myapplication.Admin.modals.Category
import com.example.myapplication.Admin.utils.RealPathUtil
import com.example.myapplication.R
import com.example.myapplication.utils.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
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

    @RequiresApi(Build.VERSION_CODES.O)
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
            categories = categories.reversed()
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
                "multipart/form-data".toMediaTypeOrNull(),
                productName.text.toString()
            )
            val requestDesc = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                productDesc.text.toString()
            )
            val requestSize = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                "M")
            val requestSizeS = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                productSizeS.text.toString()
            )
            val requestSizeM = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                productSizeM.text.toString()
            )
            val requestSizeL = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                productSizeL.text.toString()
            )
            val requestStatus = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "1")
            val requestCategory = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                categories[categorySpinner.selectedItemPosition].getId().toString()
            )
            val requestUpdateDate = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                LocalDateTime.now().toString()
            )
            val requestReleaseDate = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                LocalDateTime.now().toString()
            )
            val requestSales = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "0")
            val requestImage = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile)
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

            val handler = Handler(Looper.getMainLooper())
            val loadingDialog = Utils.Companion.CustomLoadingDialog(this@AddProduct)
            loadingDialog.show()
            handler.postDelayed(Runnable {


//
                loadingDialog.dismiss()
                val intent = Intent(this, Products::class.java)
                startActivity(intent)
            }, 4000)



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