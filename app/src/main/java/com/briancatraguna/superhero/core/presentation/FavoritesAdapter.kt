package com.briancatraguna.superhero.core.presentation

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.briancatraguna.superhero.core.domain.HeroEntity
import com.briancatraguna.superhero.databinding.ItemSuperheroBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoritesAdapter(val listFavorite: List<HeroEntity>): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSuperheroBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(heroEntity: HeroEntity){
            with(binding){
                Glide.with(itemView.context)
                    .load(heroEntity.image)
                    .apply(RequestOptions().override(500,500))
                    .into(image)
                title.text = heroEntity.name
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context,DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_IMAGE,heroEntity.image)
                    intent.putExtra(DetailActivity.EXTRA_TITLE,heroEntity.name)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int {
        return listFavorite.size
    }
}