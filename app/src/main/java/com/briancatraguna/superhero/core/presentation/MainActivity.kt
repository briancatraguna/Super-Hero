package com.briancatraguna.superhero.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.briancatraguna.superhero.MyApplication
import com.briancatraguna.superhero.core.domain.ResultsItem
import com.briancatraguna.superhero.databinding.ActivityMainBinding

import com.briancatraguna.superhero.core.domain.SearchHeroRepository
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
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)

        viewModel.getHeroes("prikitiew").observe(this,{results->
            println(results.response)
            if (results.response != "error"){
                binding.textView.text = results?.results?.get(0)?.name
                val adapter = GridSuperHeroAdapter(results.results as List<ResultsItem>)
                recyclerView.adapter = adapter
            } else {
                binding.textView.text = "NO RESULT FOUND"
            }
        })

        viewModel.getLoadingStatus().observe(this,{loading->
            if (loading){
                binding.progressBar.visibility = View.VISIBLE
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