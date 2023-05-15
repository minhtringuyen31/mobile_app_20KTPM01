package com.example.myapplication.services

import com.example.myapplication.modals.*
import retrofit2.http.*

interface UserService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): User

    @POST("users/findEmail")
    suspend fun getUserByEmail(userEmail: String): User

    @GET("users/")
    suspend fun getAllUser(): List<User>


    @POST("users/update/{id}")
    suspend fun updateUser(@Path("id") userId: Int, @Body user: User): User

    @DELETE("users/delete/{id}")
    suspend fun deleteUser(@Path("id") userId: Int):Boolean

    @POST("users/create/")
    suspend fun createUser(@Body user: User): User

    @POST("users/signup")
    suspend fun SignUp(@Body signupRequest: SignupRequest ): SignupRequest

    @POST("users/changepassword/{id}")
    suspend fun ChangePass(@Path("id") userId:Int,@Body changePassRequest:ChangePassRequest ): ChangePassRequest

    @POST ("users/editprofile/{id}")
    suspend fun EditInfo(@Path("id") userId:Int,@Body editInfoRequest: EditInfoRequest):EditInfoRequest

    @POST("users/sendotp")
    suspend fun SetOTP(@Body forgotPassRequest: ForgotPassRequest):ForgotPassRequest
    @POST("users/checkotp")
    suspend fun CheckOTP(@Body forgotPassRequest: ForgotPassRequest):ForgotPassRequest

}