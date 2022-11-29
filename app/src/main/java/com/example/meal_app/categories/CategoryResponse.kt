package com.example.meal_app.categories

import com.google.gson.annotations.SerializedName

class CategoryResponse {

    @SerializedName("categories")
    var categories: List<Category>? = null
}

class Category {

    var idCategory: String? = null
    var strCategory: String? = null
    var strCategoryThumb: String? = null
    var strCategoryDescription: String? = null
}