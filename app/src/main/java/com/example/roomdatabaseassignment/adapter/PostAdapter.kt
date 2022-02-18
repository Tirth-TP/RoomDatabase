package com.example.roomdatabaseassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseassignment.api.PostList
import com.example.roomdatabaseassignment.data.User
import com.example.roomdatabaseassignment.databinding.RvApiCallingBinding

class PostAdapter(val postList: ArrayList<PostList> , val btnCardView: OnCardViewClick) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {


    class MyViewHolder(var binding: RvApiCallingBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(RvApiCallingBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.retro = postList[position]

        holder.binding.cardView.setOnClickListener {
            btnCardView.onClickCard(postList[position])
        }

    }
    open interface OnCardViewClick {
        fun onClickCard(post : PostList)

    }
}