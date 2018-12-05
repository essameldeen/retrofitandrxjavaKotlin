package com.example.essam.retrofitandrxjava.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.essam.retrofitandrxjava.Model.Post
import com.example.essam.retrofitandrxjava.R
import java.lang.StringBuilder

class PostsAdapter(var context: Context) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    private var posts: List<Post>? = null

    fun setData(posts: List<Post>) {
        this.posts = posts
    }

    fun getItem(poistion: Int): Post {
        return posts!!.get(poistion)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_post, p0, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        if (posts != null)
            return this.posts!!.size
        else
            return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val post = getItem(p1)
        holder.title.text = post.title
        holder.content.text = StringBuilder(post.body.substring(0, 20)).append("...").toString()
        holder.owner.text = post.userId.toString()
    }

    inner class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        var title: TextView
        var content: TextView
        var owner: TextView

        init {
            title = viewItem.findViewById(R.id.title)
            content = viewItem.findViewById(R.id.content)
            owner = viewItem.findViewById(R.id.owner)
        }
    }
}