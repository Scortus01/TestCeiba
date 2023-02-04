package com.example.testceiba.view.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.testceiba.databinding.ItemUsersBinding
import com.example.testceiba.domain.model.UserView

class UserViewHolder(
    val binding: ItemUsersBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserView){
        binding.tvName.text = user.userName
        binding.tvPhone.text = user.userPhone
        binding.tvMail.text = user.userMail
    }
}