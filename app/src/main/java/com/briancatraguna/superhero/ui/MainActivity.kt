package com.briancatraguna.superhero.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.briancatraguna.superhero.MyApplication
import com.briancatraguna.superhero.core.domain.ResultsItem
import com.briancatraguna.superhero.databinding.ActivityMainBinding

import com.jakewharton.rxbinding2.widget.RxTextView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]
        binding.recyclerView.layoutManager = GridLayoutManager(applicationContext,2)

        initView()
    }

    private fun initView(){
        binding.toolbar.tvTitle.text = "Home"
        binding.toolbar.imgFavoriteToolbar.visibility = View.VISIBLE
        binding.toolbar.imgFavoriteToolbar.setOnClickListener {
            val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
            startActivity(intent)
        }
        val searchStream = RxTextView.textChanges(binding.searchBar)
        searchStream.subscribe {
            loadAPIData()
        }
    }

    private fun loadAPIData(){
        val searchQuery = binding.searchBar.text.toString()
        viewModel.getHeroes(searchQuery).observe(this,{results->
            println(results.response)
            if (results.response != "error"){
                binding.recyclerView.visibility = View.VISIBLE
                binding.tvNoResults.visibility = View.GONE
                binding.imgNoResult.visibility = View.GONE
                val adapter = GridSuperHeroAdapter(results.results as List<ResultsItem>)
                binding.recyclerView.adapter = adapter
            } else {
                binding.tvNoResults.visibility = View.VISIBLE
                binding.imgNoResult.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
        })

        viewModel.getLoadingStatus().observe(this,{loading->
            if (loading){
                binding.progressBar.visibility = View.VISIBLE
                binding.imgNoResult.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.getConnectionStatus().observe(this,{success->
            if (success){
                binding.imgFail.visibility = View.GONE
            } else {
                binding.imgFail.visibility = View.VISIBLE
            }
        })
    }
}