package com.briancatraguna.superhero.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.briancatraguna.superhero.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.toolbar.tvTitle.text = "Favorites"
        binding.toolbar.imgBack.visibility = View.VISIBLE
        binding.toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}