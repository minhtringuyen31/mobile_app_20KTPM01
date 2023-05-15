package com.example.myapplication.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)


    }
}
//sealed class Resource<out T> {
//    data class success<out T>(val data: T) : Resource<T>()
//    data class error(val message: String) : Resource<Nothing>()
//    object Loading : Resource<Nothing>()
//}