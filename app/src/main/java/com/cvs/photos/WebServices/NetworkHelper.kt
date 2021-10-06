package com.cvs.photos.WebServices

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object NetworkHelper {
    const val BASE_URL = "http://api.flickr.com/"


    fun createGitHubAPI(): PhotosAPI {
        return prepareRetrofitClient()
            .create<PhotosAPI>(
                PhotosAPI::class.java)
    }

    private fun prepareRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(prepareOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun prepareOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }




    private val httpLoggingInterceptor: HttpLoggingInterceptor
        private get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}
