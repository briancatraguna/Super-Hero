package com.briancatraguna.superhero.core.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.briancatraguna.superhero.core.domain.ResultsItem
import com.briancatraguna.superhero.databinding.ItemSuperheroBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridSuperHeroAdapter(val listSuperHero: List<ResultsItem>): RecyclerView.Adapter<GridSuperHeroAdapter.GridViewHolder>() {
    inner class GridViewHolder(private val binding: ItemSuperheroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(superhero: ResultsItem){
            with(binding){
                Glide.with(itemView.context)
                    .load(superhero.image?.url)
                    .apply(RequestOptions().override(500,500))
                    .into(image)
                println(superhero.image)
                title.text = superhero.name
                desc.text = superhero.biography?.aliases?.get(0).toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listSuperHero[position])
    }

    override fun getItemCount(): Int {
        return listSuperHero.size
    }
}