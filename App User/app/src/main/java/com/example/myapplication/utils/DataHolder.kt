package com.example.myapplication.utils

import android.content.Context
import android.util.Log
import com.example.myapplication.modals.Product
import com.google.gson.Gson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*
import java.io.OutputStreamWriter

object DataHolder {
    private var data:  ArrayList<Product> = ArrayList()

    fun setData(newData : ArrayList<Product>){
        data = newData
    }
    fun getData(): ArrayList<Product>{
        return data
    }

//    fun saveFile(fileName: String, context: Context){
//        clearFile(fileName, context)
//        println(data)
//        val gson = Gson()
//        val json  =gson.toJson(data)
//
//        val file = File(context.filesDir, fileName)
//        val fileWriter = FileWriter(file)
//        fileWriter.write(json)
//
//        if (file.length() > 0) {
//            println("Ghi dữ liệu thành công")
//        } else {
//            println("Ghi dữ liệu thất bại")
//        }
//        fileWriter.close()
//    }

    fun readFile(fileName: String, context: Context){
        val file = File(context.filesDir, fileName)
        val fileReader = FileReader(file)
        val bufferedReader = BufferedReader(fileReader)
        val json = bufferedReader.readText()
        bufferedReader.close()

        val gson = Gson()
        val products = gson.fromJson(json, Array<Product>::class.java).toList()
        data = ArrayList(products)
    }

    fun deleteItem(productId: Int){
        val iterator = data.iterator()
        while (iterator.hasNext()) {
            val product = iterator.next()
            if (product.getId() == productId) {
                iterator.remove()
                break
            }
        }
    }

    fun addItem(newProduct: Product){
        data.add(newProduct)
    }

    fun clearFile(fileName: String, context: Context) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write("".toByteArray())
        }
    }

}
