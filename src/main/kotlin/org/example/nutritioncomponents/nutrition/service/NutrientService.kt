package org.example.nutritioncomponents.nutrition.service

import jakarta.transaction.Transactional
import org.example.nutritioncomponents.nutrition.NutrientExceptions
import org.example.nutritioncomponents.nutrition.controller.NutrientController
import org.example.nutritioncomponents.nutrition.entity.Nutrient
import org.example.nutritioncomponents.nutrition.repository.NutrientRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NutrientService(
    private val nutrientRepository: NutrientRepository
) {

    fun findNutrients(
        pageable: Pageable,
        foodName: String?,
        researchYear: String?,
        makerName: String?,
        foodCode: String?
    ): Page<Nutrient> {
        return nutrientRepository.findAll(
            pageable = pageable,
            foodCode = foodCode,
            foodName = foodName,
            makerName = makerName,
            researchYear = researchYear
        )
    }

    fun findNutrient(id: Long): Nutrient {
        return nutrientRepository.findByIdOrNull(id) ?: throw NutrientExceptions.NotFound("Nutrition does not exist")
    }

    @Transactional
    fun createNutrient(
        request: NutrientController.CreateNutrientRequest
    ): Nutrient {
        return with(request) {
            Nutrient(
                foodCode = foodCode,
                groupName = groupName,
                foodName = foodName,
                researchYear = researchYear,
                makerName = makerName,
                referenceName = referenceName,
                servingSize = servingSize,
                calorie = calorie,
                carbohydrate = carbohydrate,
                protein = protein,
                fat = fat,
                sugars = sugars,
                sodium = sodium,
                cholesterol = cholesterol,
                saturatedFattyAcids = saturatedFattyAcids,
                transFat = transFat
            ).let(nutrientRepository::save)
        }
    }

    @Transactional
    fun updateNutrient(
        id: Long,
        request: NutrientController.UpdateNutrientRequest
    ): Nutrient {
        return nutrientRepository.findByIdOrNull(id)
            ?.apply {
                this.foodCode = request.foodCode
                this.groupName = request.groupName
                this.foodName = request.foodName
                this.researchYear = request.researchYear
                this.makerName = request.makerName
                this.referenceName = request.referenceName
                this.servingSize = request.servingSize
                this.calorie = request.calorie
                this.carbohydrate = request.carbohydrate
                this.protein = request.protein
                this.fat = request.fat
                this.sugars = request.sugars
                this.sodium = request.sodium
                this.cholesterol = request.cholesterol
                this.saturatedFattyAcids = request.saturatedFattyAcids
                this.transFat = request.transFat
            }
            ?.also(nutrientRepository::save)
            ?: throw NutrientExceptions.NotFound("Nutrition does not exist")
    }

    @Transactional
    fun deleteNutrient(id: Long) {
        nutrientRepository.findByIdOrNull(id)
            ?.also(nutrientRepository::delete)
    }
}