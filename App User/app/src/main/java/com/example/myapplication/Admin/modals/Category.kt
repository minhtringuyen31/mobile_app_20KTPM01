package com.example.myapplication.Admin.modals

import java.io.Serializable


class Category(
    private var id: Int? = null,
    private var name: String? = null,
    private var is_disable: Int? = null,
    private var image: String? = null
) : Serializable {
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

    fun getIsDisable(): Int? {
        return is_disable
    }

    fun setIsDisable(is_disable: Int?) {
        this.is_disable = is_disable
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }
}