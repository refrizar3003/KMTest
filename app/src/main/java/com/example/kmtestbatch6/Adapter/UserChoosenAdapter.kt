package com.example.kmtestbatch6.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kmtestbatch6.databinding.ItemUserBinding
import com.example.kmtestbatch6.model.UserEntity

class UserChoosenAdapter(private var listUser: List<UserEntity>) : RecyclerView.Adapter<UserChoosenAdapter.ViewHolder>() {

    var onCardClick : ((UserEntity)->Unit)? = null


    class ViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvFullname.text = "${listUser[position].firstName} ${listUser[position].lastName}"
        holder.binding.tvEmail.text = listUser[position].email
        Glide
            .with(holder.itemView)
            .load(listUser[position].avatar)
            .into(holder.binding.imgUser)

        holder.binding.cvUser.setOnClickListener {
            onCardClick?.invoke(listUser[position])
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}