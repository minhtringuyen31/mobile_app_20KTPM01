package com.example.myapplication.pages.activities.apdaters.interfaces

import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.Product

interface OnItemClickListener {
    fun onItemClick(position: Int, product: Product)
    fun onCartItemClick(position: Int, cartItem: CartItem)
    fun onCartItemClickUpdate(position: Int, cartItem: CartItem)
}

interface OnItemClickProductHomepage {
    fun onItemClickHompage(position: Int, product: Product)
}