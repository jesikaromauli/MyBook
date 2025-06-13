package com.jesika0104.mybook.network

import com.jesika0104.mybook.model.Buku
import com.jesika0104.mybook.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://store.sthresearch.site/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BukuApiService {
    @GET("mybook.php")
    suspend fun getBuku(
        @Header("Authorization") userId: String
    ): List<Buku>

    @Multipart
    @POST("mybook.php")
    suspend fun postBuku(
        @Header("Authorization") userId: String,
        @Part("judul") judul: RequestBody,
        @Part("penulis") penulis: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("mybook.php")
    suspend fun deleteBuku(
        @Header("Authorization") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object BukuApi {
    val service: BukuApiService by lazy {
        retrofit.create(BukuApiService::class.java)
    }

    fun getBukuUrl(imageId: String): String {
        return "${BASE_URL}image.php?id=$imageId"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }