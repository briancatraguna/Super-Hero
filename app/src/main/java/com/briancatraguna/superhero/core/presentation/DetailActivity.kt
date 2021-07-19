package com.briancatraguna.superhero.core.presentation

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import com.briancatraguna.superhero.R
import com.briancatraguna.superhero.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var theImage: String? = null
    private var theText: String? = null

    companion object{
        val VIEW_IMAGE = "image"
        val VIEW_TITLE = "title"
        val EXTRA_ITEM = "item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        theImage = intent.getStringExtra(VIEW_IMAGE)
        theText = intent.getStringExtra(VIEW_TITLE)

        val imageView = binding.imgSuperhero
        val textView = binding.tvSuperheroName

        ViewCompat.setTransitionName(imageView, VIEW_IMAGE)
        ViewCompat.setTransitionName(textView, VIEW_TITLE)

        populateView()
    }

    private fun populateView(){
        Glide.with(this)
            .load(theImage)
            .apply(RequestOptions().override(500,500))
            .into(binding.imgSuperhero)
        binding.tvSuperheroName.text = theText
    }
}