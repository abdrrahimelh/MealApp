package com.example.meal_app.recipes

import com.google.gson.annotations.SerializedName

class RecipeResponse {

    @SerializedName("meals")
    var recipes: List<Recipe>? = null
}

class Recipe {

    var strMeal: String? = null
    var strMealThumb: String? = null
    var idMeal: String? = null
}