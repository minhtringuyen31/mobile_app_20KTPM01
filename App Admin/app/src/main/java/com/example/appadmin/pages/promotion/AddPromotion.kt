package com.example.appadmin.pages.promotion

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.PromotionController
import com.example.appadmin.utils.RealPathUtil
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.Calendar

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
                MediaType.parse("multipart/form-data"),
                promotionName.text.toString()
            )
            val requestBodyDesc = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                promotionDesc.text.toString()
            )
            val requestBodyDiscount = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                promotionDiscount.text.toString()
            )
            val requestBodyStartDate = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                promotionStartDateSplit[2] + "-" + promotionStartDateSplit[1] + "-" + promotionStartDateSplit[0] + "T00:00:00"
            )
            val requestBodyEndDate = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                promotionEndDateSplit[2] + "-" + promotionEndDateSplit[1] + "-" + promotionEndDateSplit[0] + "T00:00:00"
            )
            val requestBodyQuantity = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                promotionQuantity.value.toString()
            )
            val requestBodyCode = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                promotionCode.text.toString()
            )
            val requestBodyImage =
                RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
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
            val intent = Intent(this, Promotions::class.java)
            startActivity(intent)


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