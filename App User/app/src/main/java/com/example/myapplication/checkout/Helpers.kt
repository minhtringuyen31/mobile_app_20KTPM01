package com.example.myapplication.checkout

import android.annotation.SuppressLint
import com.example.myapplication.checkout.HMac.HMacUtil
import org.jetbrains.annotations.NotNull
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    private var transIdDefault = 1

    @NotNull
    @SuppressLint("DefaultLocale")
    fun getAppTransId(): String {
        if (transIdDefault >= 100000) {
            transIdDefault = 1
        }
        transIdDefault += 1
        val formatDateTime = SimpleDateFormat("yyMMdd_hhmmss")
        val timeString = formatDateTime.format(Date())
        return String.format("%s%06d", timeString, transIdDefault)
    }

    @NotNull
    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
    fun getMac(@NotNull key: String, @NotNull data: String): String? {
        return Objects.requireNonNull(HMacUtil.HMacHexStringEncode(HMacUtil.HMACSHA256, key, data))
    }



}