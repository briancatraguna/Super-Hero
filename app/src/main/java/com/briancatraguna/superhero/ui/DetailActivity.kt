package com.briancatraguna.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.briancatraguna.superhero.MyApplication
import com.briancatraguna.superhero.R
import com.briancatraguna.superhero.core.domain.HeroEntity
import com.briancatraguna.superhero.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: MainViewModel
    private var isFavorite = false

    companion object{
        const val EXTRA_IMAGE = "image"
        const val EXTRA_TITLE = "title"
        const val EXTRA_STRENGTH = "strength"
        const val EXTRA_DURABILITY = "durability"
        const val EXTRA_COMBAT = "combat"
        const val EXTRA_POWER = "power"
        const val EXTRA_SPEED = "speed"
        const val EXTRA_INTELLIGENCE = "intelligence"
    }

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]

        initView()
    }

    private fun initView(){

        binding.toolbar.imgBack.visibility = View.VISIBLE
        binding.toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }
        val image = intent.getStringExtra(EXTRA_IMAGE)
        val title = intent.getStringExtra(EXTRA_TITLE)
        val strength = intent.getStringExtra(EXTRA_STRENGTH)
        val durability = intent.getStringExtra(EXTRA_DURABILITY)
        val combat = intent.getStringExtra(EXTRA_COMBAT)
        val power = intent.getStringExtra(EXTRA_POWER)
        val speed = intent.getStringExtra(EXTRA_SPEED)
        val intelligence = intent.getStringExtra(EXTRA_INTELLIGENCE)

        binding.toolbar.tvTitle.text = title
        Glide.with(this)
            .load(image)
            .apply(RequestOptions().override(500,500))
            .into(binding.imgSuperhero)
        binding.tvName.text = "Name: ${title}"
        binding.tvStrength.text = "Strength: ${strength}"
        binding.tvDurability.text = "Durability: ${durability}"
        binding.tvCombat.text = "Combat: ${combat}"
        binding.tvPower.text = "Power: ${power}"
        binding.tvSpeed.text = "Speed: ${speed}"
        binding.tvIntelligence.text = "Intelligence ${intelligence}"

        initFavorite(title)

        binding.imgFavorite.setOnClickListener {
            if (isFavorite){
                Thread{
                    viewModel.deleteFavoriteHero(title.toString())
                }.start()
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite_border)
                isFavorite = false
                Toast.makeText(this,"${title} is removed from favorites!",Toast.LENGTH_SHORT).show()
            } else {
                Thread{
                    viewModel.insertFavoriteHero(HeroEntity(0,image.toString(),title.toString(),strength.toString(),durability.toString(),combat.toString(),power.toString(),speed.toString(),intelligence.toString()))
                }.start()
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
                isFavorite = true
                Toast.makeText(this,"${title} is added to favorites!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initFavorite(title: String?) {
        viewModel.getFavoriteHeroes().observe(this,{heroes->
            for (hero in heroes){
                if (title == hero.name){
                    binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
                    isFavorite = true
                }
            }
        })
    }
}