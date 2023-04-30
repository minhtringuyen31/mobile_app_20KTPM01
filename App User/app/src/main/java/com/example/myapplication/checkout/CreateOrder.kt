package com.example.myapplication.checkout

import okhttp3.FormBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*

class CreateOrder {
    private inner class CreateOrderData(amount: String) {
        var AppId: String
        var AppUser = "The Coffee Shop"
        var AppTime: String
        var Amount: String
        var AppTransId: String
        var EmbedData = "{}"
        var Items = "[]"
        var BankCode = "zalopayapp"
        var Description: String
        var Mac: String

        init {
            val appTime = Date().time
            AppId = AppInfo.APP_ID.toString()
            AppTime = appTime.toString()
            Amount = amount
            AppTransId = Helpers.getAppTransId()
            Description = "Payment pay for order #$AppTransId"
            val inputHMac = String.format(
                "%s|%s|%s|%s|%s|%s|%s", this.AppId, this.AppTransId, this.AppUser, this.Amount,
                this.AppTime, this.EmbedData, this.Items
            )
            Mac = Helpers.getMac(AppInfo.MAC_KEY, inputHMac).toString()
        }

    }

    @Throws(Exception::class)
    fun createOrder(amount: String): JSONObject {
        val input = CreateOrderData(amount)
        val formBody = FormBody.Builder()
            .add("appid", input.AppId)
            .add("appuser", input.AppUser)
            .add("apptime", input.AppTime)
            .add("amount", input.Amount)
            .add("apptransid", input.AppTransId)
            .add("embeddata", input.EmbedData)
            .add("item", input.Items)
            .add("bankcode", input.BankCode)
            .add("description", input.Description)
            .add("mac", input.Mac)
            .build()

        val data = HttpProvider.sendPost(AppInfo.URL_CREATE_ORDER, formBody)
        return data!!
    }
}