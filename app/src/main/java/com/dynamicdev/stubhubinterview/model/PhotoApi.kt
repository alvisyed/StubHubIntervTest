package com.dynamicdev.stubhubinterview.model

import com.dynamicdev.stubhubinterview.constant.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    //get https://pixabay.com/api?key=22209845-0e41a1e637ade5054d52c799e&image_type=photo&safesearch=true&per_page=50
    @GET(Constant.END_POINT)
    suspend fun getImageApi(
        @Query("key")
        keyApi: String = Constant.API_KEY,
        @Query("image_type")
        imageType: String = Constant.IMAGE_TYPE,
        @Query("safesearch")
        searchType: Boolean = Constant.SEARCH_TYPE,
        @Query("per_page")
        pageLimit: Int = Constant.PAGE_LIMIT
    ): Response<ApiResponse>

    companion object{
        fun initRetrofit(): Retrofit{
            return Retrofit.Builder().client(initClient())
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        private fun initClient(): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(
                initOkHttpInterceptor()
            ).build()
        }

        private fun initOkHttpInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        }
    }
}