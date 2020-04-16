package com.example.dkt1.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dkt1.BaseApp
import com.example.dkt1.POKEMON_DETAILS_KEY
import com.example.dkt1.R
import com.example.dkt1.data.PokemonDetails
import com.example.dkt1.viewmodel.PokemonDetailsViewModel
import com.example.dkt1.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import javax.inject.Inject

class PokemonDetailsFragment : BaseFragment() {
    private lateinit var pokemonDetailsViewModel: PokemonDetailsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as BaseApp).appComponent
            .newPokemonDetailsComponent().inject(this)
        pokemonDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)[PokemonDetailsViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(POKEMON_DETAILS_KEY)
        id?.let { getPokemonDetails(it) }
    }

    fun getPokemonDetails(id: Int) {
        if (isVisible) {
            pokemonDetailsViewModel.getPokemonDetails(id)
            observePokemonDetails()
        }

    }

    fun observePokemonDetails() {
        pokemonDetailsViewModel.getLivePokemonDetails().observe(this, Observer {
            setData(it)
        })
    }

    fun setData(response: PokemonDetails?) {
        Picasso.get().load(response?.sprites?.front_default).into(pokemonImage)
        pokemonWeight.text = "Weight is :".plus(response?.weight.toString())
        pokemonHeight.text = "Height is :".plus(response?.height.toString())
    }

    override fun getLayoutById() = R.layout.fragment_pokemon_details
}