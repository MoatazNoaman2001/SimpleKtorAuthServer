package com.example.service

import com.example.models.CreateNewUser
import com.example.models.User

interface UserServiceInterface {

    suspend fun CreateNewUser(user:CreateNewUser) : User?

    suspend fun FindExistUser(email:String) : User?
}