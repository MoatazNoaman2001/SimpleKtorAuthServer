package com.example.repository

import com.example.models.CreateNewUser
import com.example.service.UserServiceImpl

class UserRepoImpl(val userServiceImpl: UserServiceImpl) : UserRepo {
    override suspend fun registerUser(user: CreateNewUser): BaseResponse<Any> {
        return if (isEmailExist(user.email)){
            BaseResponse.FailedResponse(message = "User is Already Exist")
        }else{
            val user = userServiceImpl.CreateNewUser(user)
            if (user != null){
                BaseResponse.SuccessResponse(data =  user , message = "User Created Succefully")
            }else{
                BaseResponse.FailedResponse(message = "Failed To Create User")
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email: String) : Boolean{
        return userServiceImpl.FindExistUser(email) != null
    }
}