package com.example.testceiba.view.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testceiba.databinding.ItemUsersBinding
import com.example.testceiba.domain.model.PublicationView
import com.example.testceiba.domain.model.UserView

class UsersAdapter(
    private val onItemClick: (Int, String, String, String) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {

    private var users: List<UserView> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun onLoadUser(users: List<UserView>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
        holder.binding.tvShowPublications.setOnClickListener {
            onItemClick(
                users[position].userId,
                users[position].userName,
                users[position].userMail,
                users[position].userPhone
            )
        }
    }

    override fun getItemCount(): Int = users.size
}