package com.example.myapplication.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Refund:ViewModel() {
    private val status = MutableLiveData<Boolean>()
    val order: LiveData<Boolean> = status
    private class RefundData(id:String,amount: String) {
        var AppId: String
        var ZpTransId: String
        var Amount: String
        var RefundId: String
        var Timestamp: String
        var Mac: String
        var Description: String

        init {
            val timestamp = System.currentTimeMillis().toString()
            val uid = timestamp + (111 + Random().nextInt(999)).toString()
            AppId = AppInfo.APP_ID.toString()
            ZpTransId = id
            Amount = amount
            RefundId = getCurrentTimeString("yyMMdd") + "_$AppId" + "_$uid"
            Description = "ZaloPay Intergration Demo"
            Timestamp = System.currentTimeMillis().toString()
            val data =
            "$AppId|$ZpTransId|$Amount|$Description|$Timestamp"
            Mac = Helpers.getMac(AppInfo.MAC_KEY, data).toString()
        }
        private fun getCurrentTimeString(format: String): String {
            val cal = GregorianCalendar(TimeZone.getTimeZone("GMT+7"))
            val fmt = SimpleDateFormat(format)
            fmt.calendar = cal
            return fmt.format(cal.timeInMillis)
        }

        override fun toString(): String {
            return "RefundData(AppId='$AppId', ZpTransId='$ZpTransId', Amount='$Amount', RefundId='$RefundId', Timestamp='$Timestamp', Mac='$Mac', Description='$Description')"
        }

    }

    @Throws(Exception::class)
    fun refund(id:String,amount: String) {
        val input = RefundData(id,amount)
        println(input)
        viewModelScope.launch(Dispatchers.IO){
            val formBody: RequestBody = FormBody.Builder()
                .add("appid", input.AppId)
                .add("mrefundid", input.RefundId)
                .add("zptransid", input.ZpTransId)
                .add("amount", input.Amount)
                .add("timestamp", input.Timestamp)
                .add("description", input.Description)
                .add("mac", input.Mac)
                .build()

            val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // Thiết lập timeout kết nối (10 giây)
                .readTimeout(30, TimeUnit.SECONDS) // Thiết lập timeout đọc (30 giây)
                .writeTimeout(30, TimeUnit.SECONDS) // Thiết lập timeout ghi (30 giây)
                .build()

            val request = Request.Builder()
                .url(AppInfo.URL_REFUND_ORDER)
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()
            val data = response.body?.string()
            val jsonObject = JSONObject(data)
            println(data)
            if(jsonObject!=null)
            {
                status.postValue(true)
            }

        }

    }
}



