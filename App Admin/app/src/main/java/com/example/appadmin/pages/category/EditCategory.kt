package com.example.appadmin.pages.category

import android.app.AlertDialog
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
import com.bumptech.glide.Glide
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.modals.Category
import com.example.appadmin.utils.RealPathUtil
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class EditCategory : AppCompatActivity() {
    private lateinit var category: Category
    private val REQUEST_CODE = 100
    private lateinit var imageFile: File
    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val imageUri = it.data!!.data
            val imageView = findViewById<ImageView>(R.id.editCategoryImage)
            val realPathUtil = RealPathUtil().getRealPath(this, imageUri!!)
            imageFile = realPathUtil?.let { it1 -> File(it1) }!!
            imageView.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)

        val id = intent.getStringExtra("categoryId")
        val name = findViewById<TextView>(R.id.editCategoryName)
        val image = findViewById<ImageView>(R.id.editCategoryImage)
        val disableBtn = findViewById<Button>(R.id.editCategory_disableBtn)
        val imageBtn = findViewById<Button>(R.id.editCategoryImageBtn)

        val categoryViewProvider = ViewModelProvider(this)[CategoryController::class.java]
        categoryViewProvider.getCategory(id!!.toInt()).observe(this) {
            category = it
            name.text = it.getName()
            Glide.with(this).load(it.getImage()).into(image)
            disableBtn.text = if (it.getIsDisable() == 1) "Mở" else "Khóa"
        }

        findViewById<Button>(R.id.editCategory_saveBtn).setOnClickListener {

            if (!::imageFile.isInitialized) {
                val category = Category(
                    id.toInt(),
                    name.text.toString(),
                    0,
                    "0"
                )
                categoryViewProvider.updateCategoryWithoutImage(
                    id.toInt(),
                    category
                ).observe(this) { }
            } else {
                val requestBodyName = RequestBody.create(
                    MediaType.parse("multipart/form-data"),
                    name.text.toString()
                )
                val requestBodyImage = RequestBody.create(
                    MediaType.parse("multipart/form-data"),
                    imageFile
                )
                val multipartBody = MultipartBody.Part.createFormData(
                    "image",
                    imageFile.name,
                    requestBodyImage
                )
                categoryViewProvider.updateCategory(
                    id.toInt(),
                    requestBodyName,
                    multipartBody
                ).observe(this) {}
            }

            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.editCategory_cancelBtn).setOnClickListener {
            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.editCategory_disableBtn).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Bạn có chắc chắn muốn ${disableBtn.text} danh mục này?")
            builder.setCancelable(true)

            builder.setNegativeButton("Có") { dialog, _ ->
                if (category.getIsDisable() == 1)
                    categoryViewProvider.enableCategory(id.toInt()).observe(this) {}
                else {
                    categoryViewProvider.disableCategory(id.toInt()).observe(this) {}
                }
                val intent = Intent(this, Categories::class.java)
                startActivity(intent)
                dialog.cancel()
            }

            builder.setPositiveButton("Không") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }

        findViewById<Button>(R.id.editCategory_deleteBtn).setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("Bạn có chắc chắn muốn xóa danh mục này?")

            builder.setCancelable(true)

            builder.setNegativeButton("Có") { dialog, _ ->
                categoryViewProvider.deleteCategory(id.toInt()).observe(this) {}
                val intent = Intent(this, Categories::class.java)
                startActivity(intent)
                dialog.cancel()
            }

            builder.setPositiveButton("Không") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }

        imageBtn.setOnClickListener {
            onCLickRequestPermission()
        }
    }

    private fun onCLickRequestPermission() {
        if (checkSelfPermission(android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_DENIED) {
            openGallery()
        } else {
            val permission = arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
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
            REQUEST_CODE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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