package com.briancatraguna.superhero.ui

import android.content.Intent
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
                title.text = superhero.name
                desc.text = superhero.biography?.aliases?.get(0).toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_IMAGE,superhero.image?.url)
                    intent.putExtra(DetailActivity.EXTRA_TITLE,superhero.name)
                    intent.putExtra(DetailActivity.EXTRA_STRENGTH,superhero.powerstats?.strength)
                    intent.putExtra(DetailActivity.EXTRA_DURABILITY,superhero.powerstats?.durability)
                    intent.putExtra(DetailActivity.EXTRA_COMBAT,superhero.powerstats?.combat)
                    intent.putExtra(DetailActivity.EXTRA_POWER,superhero.powerstats?.power)
                    intent.putExtra(DetailActivity.EXTRA_SPEED,superhero.powerstats?.speed)
                    intent.putExtra(DetailActivity.EXTRA_INTELLIGENCE,superhero.powerstats?.intelligence)
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