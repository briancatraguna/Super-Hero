package com.briancatraguna.superhero.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.briancatraguna.superhero.R
import com.briancatraguna.superhero.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this,factory)[SearchHeroesViewModel::class.java]

        viewModel.getHeroes("spider-man").observe(this,{result ->
            binding.tvTest.text = result.response
        })
    }
}