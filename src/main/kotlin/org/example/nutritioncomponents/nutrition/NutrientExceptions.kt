package org.example.nutritioncomponents.nutrition

sealed class NutrientExceptions(
    override val message: String
): RuntimeException(message) {
    data class NotFound(override val message: String) : NutrientExceptions(message)
}