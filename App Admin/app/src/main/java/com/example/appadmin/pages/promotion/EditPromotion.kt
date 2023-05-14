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
import com.bumptech.glide.Glide
import com.example.appadmin.R
import com.example.appadmin.controllers.PromotionController
import com.example.appadmin.modals.Promotion
import com.example.appadmin.utils.RealPathUtil
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class EditPromotion : AppCompatActivity() {
    private val PERMISSION_CODE = 1000
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
        setContentView(R.layout.activity_edit_promotion)

        val promotionId = intent.getStringExtra("promotionId")

        val promotionImage = findViewById<ImageView>(R.id.editPromotionImage)
        val promotionImageBtn = findViewById<Button>(R.id.editPromotionImageBtn)
        val promotionName = findViewById<TextView>(R.id.editPromotionName)
        val promotionDesc = findViewById<TextView>(R.id.editPromotionDesc)
        val promotionDiscount = findViewById<TextView>(R.id.editPromotionDiscount)
        val promotionStartDate = findViewById<TextView>(R.id.editPromotionStartDate)
        val promotionEndDate = findViewById<TextView>(R.id.editPromotionEndDate)
        val promotionQuantity = findViewById<NumberPicker>(R.id.editPromotionQuantity)
        val promotionCode = findViewById<TextView>(R.id.editPromotionCode)

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

        val promotionViewProvider = ViewModelProvider(this)[PromotionController::class.java]
        promotionViewProvider.getPromotion(promotionId!!.toInt()).observe(this) {
            Glide.with(this).load(it.getImage()).into(promotionImage)
            promotionName.text = it.getName()
            promotionDesc.text = it.getDescription()
            promotionDiscount.text = it.getDiscount().toString()
            promotionStartDate.text = LocalDateTime.parse(
                it.getStartDate().toString(),
                DateTimeFormatter.ISO_DATE_TIME
            )
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            promotionEndDate.text = LocalDateTime.parse(
                it.getEndDate().toString(),
                DateTimeFormatter.ISO_DATE_TIME
            )
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            promotionQuantity.value = it.getQuantity()!!
            promotionCode.text = it.getCode()
        }

        findViewById<Button>(R.id.editPromotion_cancelBtn).setOnClickListener {
            val intent = Intent(this, PromotionDetail::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.editPromotion_saveBtn).setOnClickListener {
            val promotionStartDateSplit = promotionStartDate.text.split("/")
            val promotionEndDateSplit = promotionEndDate.text.split("/")

            if (!this::imageFile.isInitialized) {
                val promotion = Promotion(
                    promotionId.toInt(),
                    promotionName.text.toString(),
                    promotionDesc.text.toString(),
                    promotionDiscount.text.toString().toDouble(),
                    promotionStartDateSplit[2] + "-" + promotionStartDateSplit[1] + "-" + promotionStartDateSplit[0] + "T00:00:00",
                    promotionEndDateSplit[2] + "-" + promotionEndDateSplit[1] + "-" + promotionEndDateSplit[0] + "T00:00:00",
                    "",
                    0,
                    promotionQuantity.value.toString().toInt(),
                    promotionCode.text.toString()
                )
                promotionViewProvider.updatePromotionWithoutImage(promotionId.toInt(), promotion)
                    .observe(this) {}
            } else {

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
                val requestBodyImage = RequestBody.create(
                    MediaType.parse("multipart/form-data"),
                    imageFile
                )
                val multipartBody = MultipartBody.Part.createFormData(
                    "image",
                    imageFile.name,
                    requestBodyImage
                )

                promotionViewProvider.updatePromotion(
                    promotionId.toInt(),
                    requestBodyName,
                    requestBodyDesc,
                    requestBodyDiscount,
                    requestBodyStartDate,
                    requestBodyEndDate,
                    requestBodyQuantity,
                    requestBodyCode,
                    multipartBody
                ).observe(this) {}
            }


            val intent = Intent(this, PromotionDetail::class.java)
            startActivity(intent)
        }
    }

    private fun onCLickRequestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_CODE
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
            PERMISSION_CODE -> {
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