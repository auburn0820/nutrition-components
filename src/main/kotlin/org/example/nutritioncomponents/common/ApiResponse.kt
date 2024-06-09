package org.example.nutritioncomponents.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null
)

fun <T> success(
    httpStatus: HttpStatus = HttpStatus.OK,
    code: Int = SUCCESS_CODE,
    message: String = SUCCESS_MESSAGE,
    data: T? = null
) = ResponseEntity(ApiResponse(code, message, data), httpStatus)

fun failure(
    httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    code: Int = FAILURE_CODE,
    message: String = FAILURE_MESSAGE
) = ResponseEntity(ApiResponse(code, message, null), httpStatus)


const val SUCCESS_CODE = 0
const val SUCCESS_MESSAGE = "success"

const val FAILURE_CODE = 1
const val FAILURE_MESSAGE = "fail"