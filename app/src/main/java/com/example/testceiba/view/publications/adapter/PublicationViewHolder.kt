package com.example.testceiba.view.publications.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.testceiba.databinding.ItemPublicationBinding
import com.example.testceiba.domain.model.PublicationView

class PublicationViewHolder(
    val binding: ItemPublicationBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(publication: PublicationView){
        binding.tvTitlePublication.text = publication.title
        binding.tvBodyPublication.text = publication.body
    }
}