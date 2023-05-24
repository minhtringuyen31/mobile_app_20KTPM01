package com.example.myapplication.Admin.pages.category

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.utils.RealPathUtil
import com.example.myapplication.R
import com.example.myapplication.utils.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AddCategory : AppCompatActivity() {
    private val REQUEST_CODE = 100
    private lateinit var imageFile: File
    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val imageUri = it.data!!.data
            val imageView = findViewById<ImageView>(R.id.addCategoryImage)
            val realPathUtil = RealPathUtil().getRealPath(this, imageUri!!)
            imageFile = realPathUtil?.let { it1 -> File(it1) }!!
            imageView.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        val categoryImageBtn = findViewById<Button>(R.id.addCategoryImageBtn)

        findViewById<Button>(R.id.addCategory_cancelBtn).setOnClickListener {
            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.addCategory_saveBtn).setOnClickListener {
            val categoryName = findViewById<TextView>(R.id.addCategoryName).text.toString()
            val requestBodyName = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), categoryName)
            val requestBodyImage = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile)
            val image = MultipartBody.Part.createFormData("image", imageFile.name, requestBodyImage)

            val categoryViewProvider = ViewModelProvider(this)[CategoryController::class.java]
            categoryViewProvider.createCategory(requestBodyName, image)


            val handler = Handler(Looper.getMainLooper())
            val loadingDialog = Utils.Companion.CustomLoadingDialog(this@AddCategory)
            loadingDialog.show()
            handler.postDelayed(Runnable {


//
                loadingDialog.dismiss()
                val intent = Intent(this, Categories::class.java)
                startActivity(intent)
            }, 4000)

        }

        categoryImageBtn.setOnClickListener {
            onCLickRequestPermission()
        }
    }

    private fun onCLickRequestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
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