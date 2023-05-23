package com.example.myapplication.Admin.pages.product

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.controllers.ProductController
import com.example.myapplication.Admin.modals.Category
import com.example.myapplication.Admin.modals.Product
import com.example.myapplication.Admin.utils.RealPathUtil
import com.example.myapplication.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.time.LocalDateTime


class EditProduct : AppCompatActivity() {
    private lateinit var categories: List<Category>
    private val REQUEST_CODE = 100
    private lateinit var imageFile: File
    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val imageUri = it.data!!.data
            val imageView = findViewById<ImageView>(R.id.editProductImage)
            val realPathUtil = RealPathUtil().getRealPath(this, imageUri!!)
            imageFile = realPathUtil?.let { it1 -> File(it1) }!!
            imageView.setImageURI(imageUri)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val productId = intent.getStringExtra("product_id")!!.toInt()

        val productImageBtn = findViewById<Button>(R.id.editProductImageBtn)
        val productImage = findViewById<ImageView>(R.id.editProductImage)
        val productName = findViewById<TextView>(R.id.editProductName)
        val productDesc = findViewById<TextView>(R.id.editProductDesc)
        val productSize = findViewById<Spinner>(R.id.editProductSize)
        val productSizeS = findViewById<TextView>(R.id.editProductSizeS)
        val productSizeM = findViewById<TextView>(R.id.editProductSizeM)
        val productSizeL = findViewById<TextView>(R.id.editProductSizeL)
        val categorySpinner = findViewById<Spinner>(R.id.editProductCategory)

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

        val productViewProvider = ViewModelProvider(this)[ProductController::class.java]
        productViewProvider.getProduct(productId).observe(this) {
            Glide.with(this).load(it.getImage()).into(productImage)
            productName.text = it.getName()
            productDesc.text = it.getDescription()
            productSize.setSelection(if (it.getSize() == "S") 0 else if (it.getSize() == "M") 1 else 2)
            productSizeS.text = it.getPriceS().toString()
            productSizeM.text = it.getPriceM().toString()
            productSizeL.text = it.getPriceL().toString()
            categorySpinner.setSelection(it.getCategoryId()!! - 1)
        }

        findViewById<Button>(R.id.editProduct_cancelBtn).setOnClickListener {
            val intent1 = Intent(this, ProductDetail::class.java)
            intent1.putExtra("product_id", productId.toString())
            startActivity(intent1)
        }

        findViewById<Button>(R.id.editProduct_saveBtn).setOnClickListener {
            if (!this::imageFile.isInitialized) {
                val product = Product(
                    productId,
                    productName.text.toString(),
                    productDesc.text.toString(),
                    productSize.selectedItem.toString(),
                    productSizeS.text.toString().toDouble(),
                    productSizeM.text.toString().toDouble(),
                    productSizeL.text.toString().toDouble(),
                    "0",
                    "0",
                    0,
                    categories[categorySpinner.selectedItemPosition].getId()!!,
                    LocalDateTime.now().toString(),
                    null,
                    null,
                    0
                )
                productViewProvider.updateWithoutImage(productId, product).observe(this) {}
            } else {
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
                    productSize.selectedItemPosition.let { it1 -> arrayOf("S", "M", "L")[it1] }
                )
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
                val requestCategory = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    categories[categorySpinner.selectedItemPosition].getId().toString()
                )
                val requestUpdateDate = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    LocalDateTime.now().toString()
                )
                val requestImage = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile)
                val image = MultipartBody.Part.createFormData("image", imageFile.name, requestImage)

                val productViewAdapter = ViewModelProvider(this)[ProductController::class.java]
                productViewAdapter.updateProduct(
                    productId,
                    requestName,
                    requestDesc,
                    requestSize,
                    requestSizeS,
                    requestSizeM,
                    requestSizeL,
                    requestCategory,
                    requestUpdateDate,
                    image
                ).observe(this) {

                }
            }
            val intent1 = Intent(this, Products::class.java)
            startActivity(intent1)
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