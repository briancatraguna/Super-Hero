package com.briancatraguna.superhero.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
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
        val EXTRA_IMAGE = "image"
        val EXTRA_TITLE = "title"
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
        binding.toolbar.tvTitle.text = "Details"
        binding.toolbar.imgBack.visibility = View.VISIBLE
        binding.toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }
        val image = intent.getStringExtra(EXTRA_IMAGE)
        val title = intent.getStringExtra(EXTRA_TITLE)
        Glide.with(this)
            .load(image)
            .apply(RequestOptions().override(500,500))
            .into(binding.imgSuperhero)
        binding.tvSuperheroName.text = title

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
                    viewModel.insertFavoriteHero(HeroEntity(0,image.toString(),title.toString()))
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