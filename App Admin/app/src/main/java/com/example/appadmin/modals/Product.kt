package com.example.appadmin.modals

class Product(
    private var id: String? = null,
    private var name: String? = null,
    private var description: String? = null,
    private var price: Double? = null,
    private var size: String? = null,
    private var price_S: Double? = null,
    private var price_M: Double? = null,
    private var price_L: Double? = null,
    private var note: String? = null,
    private var image: String? = null,
    private var status: Boolean? = null,
    private var category_id: String? = null,
    private var updatedAt: String? = null,
    private var releaseDate: String? = null,
    private var sales: Int? = 0
)