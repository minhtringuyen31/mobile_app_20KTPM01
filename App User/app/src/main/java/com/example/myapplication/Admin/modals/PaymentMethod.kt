package com.example.myapplication.Admin.modals

class PaymentMethod(
    private var id: Int? = null,
    private var name: String? = null,
) {
    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }
}