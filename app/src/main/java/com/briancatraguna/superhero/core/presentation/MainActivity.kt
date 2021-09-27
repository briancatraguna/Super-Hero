package com.briancatraguna.superhero.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.briancatraguna.superhero.MyApplication
import com.briancatraguna.superhero.core.domain.ResultsItem
import com.briancatraguna.superhero.databinding.ActivityMainBinding

import com.briancatraguna.superhero.core.domain.SearchHeroRepository
import com.jakewharton.rxbinding2.widget.RxTextView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)

        initView()
    }

    private fun initView(){
        binding.toolbar.tvTitle.text = "Home"
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
                recyclerView.visibility = View.VISIBLE
                binding.tvNoResults.visibility = View.GONE
                binding.imgNoResult.visibility = View.GONE
                val adapter = GridSuperHeroAdapter(results.results as List<ResultsItem>,this)
                recyclerView.adapter = adapter
            } else {
                binding.tvNoResults.visibility = View.VISIBLE
                binding.imgNoResult.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
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