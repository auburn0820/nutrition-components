package org.example.nutritioncomponents.nutrition.controller

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.example.nutritioncomponents.common.ApiResponse
import org.example.nutritioncomponents.common.success
import org.example.nutritioncomponents.nutrition.controller.NutrientController.Companion.NUTRIENT_API_V1_PATH
import org.example.nutritioncomponents.nutrition.entity.Nutrient
import org.example.nutritioncomponents.nutrition.service.NutrientService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(NUTRIENT_API_V1_PATH)
class NutrientController(
    private val nutrientService: NutrientService
) {

    companion object {
        const val NUTRIENT_API_V1_PATH = "/api/v1.0/nutrients"
    }

    @PostMapping("")
    fun create(@RequestBody request: CreateNutrientRequest): ResponseEntity<ApiResponse<NutrientResponse>> {
        return success(
            httpStatus = HttpStatus.CREATED,
            data = nutrientService.createNutrient(request)
                .run(NutrientResponse::fromEntity)
        )
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long): ResponseEntity<ApiResponse<NutrientResponse>> {
        return success(
            httpStatus = HttpStatus.OK,
            data = nutrientService.findNutrient(id).run(NutrientResponse::fromEntity)
        )
    }

    @GetMapping("")
    fun findAllNutrients(
        pageable: Pageable,
        @RequestParam("food_name") foodName: String?,
        @RequestParam("research_year") researchYear: String?,
        @RequestParam("maker_name") makerName: String?,
        @RequestParam("food_code") foodCode: String?
    ): ResponseEntity<ApiResponse<Page<NutrientResponse>>> {
        return success(
            httpStatus = HttpStatus.OK,
            data = nutrientService.findNutrients(
                pageable = pageable,
                researchYear = researchYear,
                foodName = foodName,
                makerName = makerName,
                foodCode = foodCode
            ).map(NutrientResponse::fromEntity)
        )
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: UpdateNutrientRequest
    ): ResponseEntity<ApiResponse<NutrientResponse>> {
        return success(
            httpStatus = HttpStatus.OK,
            data = nutrientService.updateNutrient(id, request).run(NutrientResponse::fromEntity)
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<ApiResponse<Nothing>> {
        nutrientService.deleteNutrient(id)

        return success(httpStatus = HttpStatus.NO_CONTENT)
    }

    data class CreateNutrientRequest(
        val foodCode: String,
        val groupName: String,
        val foodName: String,
        val researchYear: String,
        val makerName: String,
        val referenceName: String,
        val servingSize: Double,
        val calorie: Double,
        val carbohydrate: Double,
        val protein: Double,
        val fat: Double,
        val sugars: Double,
        val sodium: Double,
        val cholesterol: Double,
        val saturatedFattyAcids: Double,
        val transFat: Double
    )

    data class UpdateNutrientRequest(
        val foodCode: String,
        val groupName: String,
        val foodName: String,
        val researchYear: String,
        val makerName: String,
        val referenceName: String,
        val servingSize: Double,
        val calorie: Double,
        val carbohydrate: Double,
        val protein: Double,
        val fat: Double,
        val sugars: Double,
        val sodium: Double,
        val cholesterol: Double,
        val saturatedFattyAcids: Double,
        val transFat: Double
    )

    data class NutrientResponse(
        val foodCode: String,
        val groupName: String,
        val foodName: String,
        val researchYear: String,
        val makerName: String,
        val referenceName: String,
        val servingSize: Double,
        val calorie: Double,
        val carbohydrate: Double,
        val protein: Double,
        val fat: Double,
        val sugars: Double,
        val sodium: Double,
        val cholesterol: Double,
        val saturatedFattyAcids: Double,
        val transFat: Double
    ) {
        companion object {
            fun fromEntity(entity: Nutrient): NutrientResponse = with(entity) {
                NutrientResponse(
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
                )
            }
        }
    }
}