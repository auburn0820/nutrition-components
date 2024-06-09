package org.example.nutritioncomponents.nutrition.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.example.nutritioncomponents.nutrition.entity.Nutrient
import org.example.nutritioncomponents.nutrition.entity.QNutrient
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface NutrientRepository : JpaRepository<Nutrient, Long>, NutrientCustomRepository {

}


interface NutrientCustomRepository {
    fun findAll(
        pageable: Pageable,
        foodName: String?,
        researchYear: String?,
        makerName: String?,
        foodCode: String?
    ): Page<Nutrient>
}

class NutrientCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : NutrientCustomRepository {
    override fun findAll(
        pageable: Pageable,
        foodName: String?,
        researchYear: String?,
        makerName: String?,
        foodCode: String?
    ): Page<Nutrient> {
        val nutrient = QNutrient.nutrient

        return queryFactory.selectFrom(nutrient)
            .where(
                foodName?.let { nutrient.foodName.eq(it) },
                researchYear?.let { nutrient.researchYear.eq(it) },
                makerName?.let { nutrient.makerName.eq(it) },
                foodCode?.let { nutrient.foodCode.eq(it) }
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
            .let {
                PageImpl(it, pageable, it.size.toLong())
            }
    }

}