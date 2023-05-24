package com.example.myapplication.modals

class Cart {
    private var id:Int=0
    private var user_id:Int=0
    override fun toString(): String {
        return "Cart(id=$id, user_id=$user_id)"
    }
}

class CartSingleton private constructor() {
    companion object {
        private var instance: Cart? = null

        fun getInstance(): Cart {
            if (instance == null) {
                instance = Cart()
            }
            return instance!!
        }
    }
}