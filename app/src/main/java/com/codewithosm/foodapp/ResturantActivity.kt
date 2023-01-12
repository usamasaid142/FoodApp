package com.codewithosm.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codewithosm.foodapp.databinding.ActivityResturantBinding
import com.codewithosm.foodapp.mdel.ResturantResponseItem

class ResturantActivity : AppCompatActivity() {

    private lateinit var binding:ActivityResturantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResturantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resturantmodel=intent.getParcelableExtra<ResturantResponseItem>("resturantmodel")
        val actionbar=supportActionBar
        actionbar?.subtitle=resturantmodel?.address
        actionbar?.title=resturantmodel?.name
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
}