package com.example.myapplication.checkout

import okhttp3.FormBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.text.SimpleDateFormat

import java.util.*

class Refund {
    private class RefundData(amount: String) {
        var AppId: String
        var ZpTransId: String
        var Amount: String
        var RefundId: String
        var Timestamp: String
        var Mac: String
        var Description: String

        init {
            val temp = "230505000007320"
            val timestamp = System.currentTimeMillis().toString()
            val uid = timestamp + (111 + Random().nextInt(999)).toString()
            AppId = AppInfo.APP_ID.toString()
            ZpTransId = temp.toString()
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
    fun refund(amount: String): JSONObject {
        val input = RefundData(amount)
        println(input)
        val formBody: RequestBody = FormBody.Builder()
            .add("appid", input.AppId)
            .add("mrefundid", input.RefundId)
            .add("zptransid", input.ZpTransId)
            .add("amount", input.Amount)
            .add("timestamp", input.Timestamp)
            .add("description", input.Description)
            .add("mac", input.Mac)
            .build()
        val data = HttpProvider.sendPost(AppInfo.URL_REFUND_ORDER, formBody)
        println(data)
        return data!!

    }
}



