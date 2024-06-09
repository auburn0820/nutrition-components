package org.example.nutritioncomponents.nutrition.entity

import jakarta.persistence.Entity
import org.example.nutritioncomponents.common.BaseEntity

@Entity
class Nutrient(
    var foodCode: String,
    var groupName: String,
    var foodName: String,
    var researchYear: String,
    var makerName: String,
    var referenceName: String,
    var servingSize: Double,
    var calorie: Double,
    var carbohydrate: Double,
    var protein: Double,
    var fat: Double,
    var sugars: Double,
    var sodium: Double,
    var cholesterol: Double,
    var saturatedFattyAcids: Double,
    var transFat: Double
) : BaseEntity()