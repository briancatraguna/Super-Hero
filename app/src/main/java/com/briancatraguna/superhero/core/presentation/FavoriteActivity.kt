package com.briancatraguna.superhero.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.briancatraguna.superhero.MyApplication
import com.briancatraguna.superhero.databinding.ActivityFavoriteBinding
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadFavoritesData()
    }

    private fun loadFavoritesData() {

    }

    private fun initView() {
        binding.toolbar.tvTitle.text = "Favorites"
        binding.toolbar.imgBack.visibility = View.VISIBLE
        binding.toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}