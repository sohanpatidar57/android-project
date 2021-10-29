package com.example.newkotlin

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    fun getPhotos(@Field("email_address") email:String ,
                  @Field("password") pass:String ,
                  @Field("user_type") user_type:String ,
                  @Field("device_id") device_id:String ,
                  @Field("device_type") android:String ): Call<DataModel>
}