package com.example.roomdatabaseassignment.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseassignment.data.User
import com.example.roomdatabaseassignment.databinding.CustomRowBinding

class UserAdapter (val btnlistener: BtnClickListener) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {



    private var userList = emptyList<User>()

    class MyViewHolder(var binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //Background of recyclerview

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#aed6dc"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

        // holder.itemView.user = userList(position)
        holder.binding.user = userList[position]



        val item = userList[position]

        holder.binding.btnEdit.setOnClickListener {
            btnlistener.onBtnEdit(userList[position])
        }
        holder.binding.btnDelete.setOnClickListener {
            btnlistener.onBtnDelete(userList[position])
        }
    }

    open interface BtnClickListener {
        fun onBtnEdit(user: User)
        fun onBtnDelete(user: User)
    }

    fun setData(userList: List<User>){
        this.userList = userList
        notifyDataSetChanged()
    }


}