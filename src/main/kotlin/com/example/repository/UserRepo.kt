package com.example.repository

import com.example.models.CreateNewUser
import io.ktor.http.*

interface UserRepo {
    suspend fun registerUser(user: CreateNewUser):BaseResponse<Any>
    suspend fun loginUser(email:String , password: String):BaseResponse<Any>
}

sealed class BaseResponse<T>(val statusCode : HttpStatusCode = HttpStatusCode.OK){
    data class SuccessResponse<T>(val data:T ?= null , val message:String? = null) : BaseResponse<T>()
    data class FailedResponse<T>(val exception:T ?= null , val message:String? = null) : BaseResponse<T>()
}