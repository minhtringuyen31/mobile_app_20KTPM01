package com.example.myapplication.modals

class TokenFireBaseRequest(
    private var user_id:Int,
    private var token:String,
) {
    private var id:Int=0
    fun setUserID(id:String)
    {
        this.user_id=id.toInt()
    }
    fun getUserID():Int{
        return this.user_id
    }
    fun setTokenID(_token:String)
    {
        this.token=_token
    }
    fun getToken():String{
        return this.token
    }
    override fun toString(): String {
        return "TokenFireBaseRequest(user_id=$user_id, token='$token')"
    }

}