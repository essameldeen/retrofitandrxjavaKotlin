package com.example.essam.retrofitandrxjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.essam.retrofitandrxjava.Adapter.PostsAdapter
import com.example.essam.retrofitandrxjava.Model.Post
import com.example.essam.retrofitandrxjava.Servicse.ApiInterface
import com.example.essam.retrofitandrxjava.Servicse.RetrofitClient

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var api: ApiInterface
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var adapter: PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()
        // init api
        val retofit = RetrofitClient.getourInstance()
        api = retofit.create(ApiInterface::class.java)
        //

        // init recycler
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        //

        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(api.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { posts -> displayData(posts) })
    }

    private fun displayData(posts: List<Post>?) {
        if (posts != null) {
            adapter = PostsAdapter(this)
            adapter.setData(posts)
            recycler.adapter = adapter
        } else {
            Toast.makeText(this, "No Data ", Toast.LENGTH_LONG).show()
        }
    }
}
