package org.example.nutritioncomponents.common

import org.example.nutritioncomponents.nutrition.NutrientExceptions
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NutrientExceptions.NotFound::class)
    fun handleDefaultException(exception: NutrientExceptions.NotFound) = failure(
        httpStatus = HttpStatus.NOT_FOUND,
        message = exception.message
    )
}