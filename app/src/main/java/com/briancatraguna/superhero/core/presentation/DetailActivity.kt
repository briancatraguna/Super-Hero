package com.briancatraguna.superhero.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import com.briancatraguna.superhero.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object{
        val EXTRA_IMAGE = "image"
        val EXTRA_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}