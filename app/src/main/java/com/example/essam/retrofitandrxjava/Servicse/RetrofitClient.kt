package com.example.essam.retrofitandrxjava.Servicse

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object RetrofitClient {

    private var ourInstance: Retrofit? = null

    fun getourInstance(): Retrofit {
        if (this.ourInstance == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            ourInstance = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return this.ourInstance!!
    }
}