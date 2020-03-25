package com.example.dkt1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dkt1.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    override fun getLayoutById() = R.layout.activity_main
//    private val pokemonListFragment = PokemonListFragment()
//
//    override fun initUI() {
//        supportFragmentManager.beginTransaction().add(R.id.container, pokemonListFragment).commit()
//    }
}
