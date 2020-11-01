package com.example.vviped.model

import com.google.gson.GsonBuilder
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface RetrofitInterface {
    @Multipart
    @POST("api.php?apicall=upload")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("product_price") product_price: RequestBody,
        @Part("product_name") product_name: RequestBody,
        @Part("product_desc") product_desc: RequestBody,
        @Part("seller_loc") seller_loc: RequestBody

    ): Call<UploadResponse>

    companion object {
        operator fun invoke(): RetrofitInterface {

            return Retrofit.Builder()
                .baseUrl("http://192.168.43.93/vviped/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitInterface::class.java)

        }
    }
}