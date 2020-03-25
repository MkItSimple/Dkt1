package com.example.dkt1.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dkt1.BaseApp
import com.example.dkt1.POKEMON_DETAILS_KEY
import com.example.dkt1.R
import com.example.dkt1.data.PokemonResponse
import com.example.dkt1.viewmodel.PokeMonListViewModel
import com.example.dkt1.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


data class Movie(val title: String, val year: Int, val image: String)
data class Pokemon (val name:String , val url:String)

class PokemonListFragment : Fragment() {

    companion object {
        val TAG = "PokemonListFragment"
    }

    private val pokemonDetailsFragment = PokemonDetailsFragment()

    val pokadapter = PokemonListAdapter()

    private val Movies = listOf(
        Movie("Raising Arizona", 1987, "raising_arizona.jpg"),
        Movie("Vampire's Kiss", 1988, "vampires_kiss.png"),
        Movie("Con Air", 1997, "con_air.jpg"),
        Movie("Face/Off", 1997, "face_off.jpg"),
        Movie("National Treasure", 2004, "national_treasure.jpg"),
        Movie("The Wicker Man", 2006, "wicker_man.jpg"),
        Movie("Bad Lieutenant", 2009, "bad_lieutenant.jpg"),
        Movie("Kick-Ass", 2010, "kickass.jpg")
    )

    private lateinit var pokeMonListViewModel: PokeMonListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

                ( activity?.applicationContext as BaseApp).appComponent
            .newPokemonLisComponent().inject(this)
//
        pokeMonListViewModel = ViewModelProviders.of(this,viewModelFactory)[PokeMonListViewModel::class.java]
////        Log.d(TAG, "Choreyn")
//
//
        pokeMonListViewModel.getPokemonList()
        pokeMonListViewModel.getLivePokemonList().observe(this, Observer {
//            it.results.forEach {
//                Log.d(TAG, "" + it.name)
//            }
            setData(it)
        })
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        list_recycler_view.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = ListAdapter(nicCageMovies)
//        }
//
//        ( activity?.applicationContext as BaseApp).appComponent
//            .newPokemonLisComponent().inject(this)
//
//        pokeMonListViewModel = ViewModelProviders.of(this,viewModelFactory)[PokeMonListViewModel::class.java]
////        Log.d(TAG, "Choreyn")
//
//
//        pokeMonListViewModel.getPokemonList()
//        pokeMonListViewModel.getLivePokemonList().observe(this, Observer {
//            //it.results.forEach {
//                Log.d(TAG, "" + it.results)
//            //}
//        })
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_main,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initUI(view)

        list_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = pokadapter
        }
    }

    fun setData(response: PokemonResponse?) {
        response?.results?.let { pokadapter.addPokmons(it) }
    }




    fun getPokemonDetails(id: Int) {
        val bundle = Bundle()
        bundle.putInt(POKEMON_DETAILS_KEY,id)
        pokemonDetailsFragment.arguments = bundle

        (activity as BaseActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.container, pokemonDetailsFragment)
            .addToBackStack(null)
            .commit()
    }
}
