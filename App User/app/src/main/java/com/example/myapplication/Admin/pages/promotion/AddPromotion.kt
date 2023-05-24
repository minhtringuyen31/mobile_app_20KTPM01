package com.example.myapplication.Admin.pages.promotion

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Admin.controllers.PromotionController
import com.example.myapplication.Admin.utils.RealPathUtil
import com.example.myapplication.R
import com.example.myapplication.utils.Utils.Companion.CustomLoadingDialog
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*


class AddPromotion : AppCompatActivity() {
    private val IMAGE_PICK_CODE = 1000
    private lateinit var imageFile: File
    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val imageUri = it.data!!.data
            val imageView = findViewById<ImageView>(R.id.addPromotionImage)
            val realPathUtil = RealPathUtil().getRealPath(this, imageUri!!)
            imageFile = realPathUtil?.let { it1 -> File(it1) }!!
            imageView.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_promotion)

        val promotionImageBtn = findViewById<Button>(R.id.addPromotionImageBtn)
        val promotionName = findViewById<TextView>(R.id.addPromotionName)
        val promotionDesc = findViewById<TextView>(R.id.addPromotionDesc)
        val promotionDiscount = findViewById<TextView>(R.id.addPromotionDiscount)
        val promotionStartDate = findViewById<TextView>(R.id.addPromotionStartDate)
        val promotionEndDate = findViewById<TextView>(R.id.addPromotionEndDate)
        val promotionQuantity = findViewById<NumberPicker>(R.id.addPromotionQuantity)
        val promotionCode = findViewById<TextView>(R.id.addPromotionCode)

        promotionQuantity.minValue = 1
        promotionQuantity.maxValue = 1000

        val calendar = Calendar.getInstance()

        promotionStartDate.setOnClickListener {
            DatePickerDialog(
                this,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    promotionStartDate.text = "$dayOfMonth/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        promotionEndDate.setOnClickListener {
            DatePickerDialog(
                this,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    promotionEndDate.text = "$dayOfMonth/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        promotionImageBtn.setOnClickListener {
            onCLickRequestPermission()
        }

        findViewById<Button>(R.id.addPromotion_cancelBtn).setOnClickListener {
            val intent = Intent(this, Promotions::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.addPromotion_saveBtn).setOnClickListener {
            val promotionStartDateSplit = promotionStartDate.text.split("/")
            val promotionEndDateSplit = promotionEndDate.text.split("/")

            val requestBodyName = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                promotionName.text.toString()
            )
            val requestBodyDesc = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                promotionDesc.text.toString()
            )
            val requestBodyDiscount = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                promotionDiscount.text.toString()
            )
            val requestBodyStartDate = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                promotionStartDateSplit[2] + "-" + promotionStartDateSplit[1] + "-" + promotionStartDateSplit[0] + "T00:00:00"
            )
            val requestBodyEndDate = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                promotionEndDateSplit[2] + "-" + promotionEndDateSplit[1] + "-" + promotionEndDateSplit[0] + "T00:00:00"
            )
            val requestBodyQuantity = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                promotionQuantity.value.toString()
            )
            val requestBodyCode = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                promotionCode.text.toString()
            )
            val requestBodyImage =
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile)
            val multipartBodyImage =
                MultipartBody.Part.createFormData("image", imageFile.name, requestBodyImage)

            val promotionViewProvider = ViewModelProvider(this)[PromotionController::class.java]

            promotionViewProvider.createPromotion(
                requestBodyName,
                requestBodyDesc,
                requestBodyDiscount,
                requestBodyStartDate,
                requestBodyEndDate,
                requestBodyQuantity,
                requestBodyCode,
                multipartBodyImage
            ).observe(this) {

            }

            val handler = Handler(Looper.getMainLooper())
            val loadingDialog = CustomLoadingDialog(this@AddPromotion)
            loadingDialog.show()
            handler.postDelayed(Runnable {


//
                loadingDialog.dismiss()
                val intent = Intent(this, Promotions::class.java)
                startActivity(intent)
            }, 3000)

            // Khi bạn muốn ẩn biểu tượng loading




        }
    }

    private fun onCLickRequestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            val permission = arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            requestPermissions(permission, IMAGE_PICK_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            IMAGE_PICK_CODE -> {
                println(grantResults[0])
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
//                    openGallery()
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