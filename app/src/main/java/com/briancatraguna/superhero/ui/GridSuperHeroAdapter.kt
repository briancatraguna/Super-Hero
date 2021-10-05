package com.briancatraguna.superhero.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.briancatraguna.superhero.core.domain.HeroItem
import com.briancatraguna.superhero.databinding.ItemSuperheroBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridSuperHeroAdapter(val listSuperHero: List<HeroItem>): RecyclerView.Adapter<GridSuperHeroAdapter.GridViewHolder>() {
    inner class GridViewHolder(private val binding: ItemSuperheroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(superhero: HeroItem){
            with(binding){
                Glide.with(itemView.context)
                    .load(superhero.image)
                    .apply(RequestOptions().override(500,500))
                    .into(image)
                title.text = superhero.name
                desc.text = superhero.desc
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_HERO,superhero)
                    itemView.context.startActivity(intent)
                }
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