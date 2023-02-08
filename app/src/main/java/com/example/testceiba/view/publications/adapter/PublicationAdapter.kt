package com.example.testceiba.view.publications.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testceiba.databinding.ItemPublicationBinding
import com.example.testceiba.domain.model.PublicationView

class PublicationAdapter: RecyclerView.Adapter<PublicationViewHolder>() {

    private var publication: List<PublicationView> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun onLoadPublication(publications: List<PublicationView>){
        this.publication = publications
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationViewHolder =
        PublicationViewHolder(
            ItemPublicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = publication.size

    override fun onBindViewHolder(holder: PublicationViewHolder, position: Int) {
        holder.bind(publication[position])
    }

}