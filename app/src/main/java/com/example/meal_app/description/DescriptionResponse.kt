package com.example.meal_app.description

import com.example.meal_app.recipes.Recipe
import com.google.gson.annotations.SerializedName

class DescriptionResponse {

    @SerializedName("meals")
    var description: List<Description>? = null
}

class Description {

    var idMeal: String? = null
    var strMeal: String? = null
    var strDrinkAlternate: String? = null
    var strCategory: String? = null
    var strArea: String? = null
    var strInstructions: String? = null
    var strMealThumb: String? = null
    var strTags: String? = null
    var strYoutube: String? = null
    var strIngredient1: String? = null
    var strIngredient2: String? = null
    var strIngredient5: String? = null
    var strIngredient6: String? = null
    var strIngredient7: String? = null
    var strIngredient8: String? = null
    var strIngredient9: String? = null
    var strIngredient10: String? = null
    var strIngredient11: String? = null
    var strIngredient12: String? = null
    var strIngredient13: String? = null
    var strIngredient14: String? = null
    var strIngredient15: String? = null
    var strIngredient16: String? = null
    var strIngredient17: String? = null
    var strIngredient18: String? = null
    var strIngredient19: String? = null
    var strIngredient20: String? = null
    var strMeasure1: String? = null
    var strMeasure2: String? = null
    var strMeasure5: String? = null
    var strMeasure6: String? = null
    var strMeasure7: String? = null
    var strMeasure8: String? = null
    var strMeasure9: String? = null
    var strMeasure10: String? = null
    var strMeasure11: String? = null
    var strMeasure12: String? = null
    var strMeasure13: String? = null
    var strMeasure14: String? = null
    var strMeasure15: String? = null
    var strMeasure16: String? = null
    var strMeasure17: String? = null
    var strMeasure18: String? = null
    var strMeasure19: String? = null
    var strMeasure20: String? = null
    var strSource: String? = null
    var strImageSource: String? = null
    var strCreativeCommonsConfirmed:String?=null
    var dateModified: String? = null
}