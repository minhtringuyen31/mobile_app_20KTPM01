package com.example.myapplication.modals

class RefundOrder(
    private var order_id:Int,
    private var token:String
) {
    private var id:Int=0
    fun setOrderId(id:Int){
        this.order_id=id
    }
    fun setToken(token:String){
        this.token=token
    }
    fun getToken():String{
        return this.token;
    }

    override fun toString(): String {
        return "RefundOrder(oder_id=$order_id, token='$token')"
    }

}