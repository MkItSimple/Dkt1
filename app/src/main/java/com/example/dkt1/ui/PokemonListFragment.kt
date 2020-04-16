package com.example.dkt1.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dkt1.BaseApp
import com.example.dkt1.POKEMON_DETAILS_KEY
import com.example.dkt1.R
import com.example.dkt1.data.PokemonResponse
import com.example.dkt1.viewmodel.PokeMonListViewModel
import com.example.dkt1.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import javax.inject.Inject


data class Movie(val title: String, val year: Int, val image: String)
data class Pokemon (val name:String , val url:String)

class PokemonListFragment : Fragment(), OnClickListener {
    private val pokemonDetailsFragment = PokemonDetailsFragment()

    private lateinit var pokeMonListViewModel: PokeMonListViewModel
    val pokadapter = PokemonListAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ( activity?.applicationContext as BaseApp).appComponent
            .newPokemonLisComponent().inject(this)
        pokeMonListViewModel = ViewModelProviders.of(this,viewModelFactory)[PokeMonListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }

    fun setupView(view: View) {
        val linearLayoutManager = LinearLayoutManager(context)
        pokadapter.setClickListener(this)
        pokemonList.apply {
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = pokadapter
        }
    }

    fun getPokemonListData() {
        pokeMonListViewModel.getPokemonList()
        observePokemonList()
    }

    fun setData(response: PokemonResponse?) {
        response?.results?.let { pokadapter.addPokmons(it) }
    }

    override fun onClick(position: Int, view: View) {
        getPokemonDetails(position+2)
    }

    fun observePokemonList() {
        pokeMonListViewModel.getLivePokemonList().observe(this, Observer {
            setData(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    fun initUI(view: View) {
        setupView(view)
        getPokemonListData()
    }

    fun getPokemonDetails(id: Int) {
        val bundle = Bundle()
        bundle.putInt(POKEMON_DETAILS_KEY,id)
        pokemonDetailsFragment.arguments = bundle

        Log.d("PLF", "getPokemonDetails $id");

        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.container, pokemonDetailsFragment)
            .addToBackStack(null)
            .commit()


    }

}
