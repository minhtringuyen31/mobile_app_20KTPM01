package com.example.myapplication.services

import com.example.myapplication.modals.ChangePassRequest
import com.example.myapplication.modals.EditInfoRequest
import com.example.myapplication.modals.SignupRequest
import com.example.myapplication.modals.User
import retrofit2.http.*

interface UserService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): User

    @GET("users/")
    suspend fun getAllUser(): List<User>


    @POST("users/update/{id}")
    suspend fun updateUser(@Path("id") userId: Int, @Body user: User): User

    @DELETE("users/delete/{id}")
    suspend fun deleteUser(@Path("id") userId: Int):Boolean

    @POST("users/create/")
    suspend fun createUser(@Body user: User): User

    @POST("users/signup/")
    suspend fun SignUp(@Body signupRequest: SignupRequest ): SignupRequest

    @POST("users/changepassword/{id}")
    suspend fun ChangePass(@Path("id") userId:Int,@Body changePassRequest:ChangePassRequest ): ChangePassRequest

    @POST ("users/editprofile/{id}")
    suspend fun EditInfo(@Path("id") userId:Int,@Body editInfoRequest: EditInfoRequest):EditInfoRequest

}