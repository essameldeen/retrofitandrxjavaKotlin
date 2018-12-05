package com.example.essam.retrofitandrxjava.Servicse

import com.example.essam.retrofitandrxjava.Model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @get:GET("posts")
     val posts:Observable<List<Post>>

}