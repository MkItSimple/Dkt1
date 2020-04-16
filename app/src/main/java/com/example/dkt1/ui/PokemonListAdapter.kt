package com.example.dkt1.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dkt1.R
import com.example.dkt1.data.Pokemon
import kotlinx.android.synthetic.main.item_list.view.*

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {
    val pokemonList = ArrayList<Pokemon>()
    lateinit var listener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_list,null)
        return PokemonViewHolder(view)
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.pokemonName.text = pokemonList.get(position).name
        holder.pokemonName.setOnClickListener {
            listener.onClick(position,it)
        }
    }

    class  PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val pokemonName = view.pokemonName
    }

    fun addPokmons(list: ArrayList<Pokemon>){
        pokemonList.clear()
        pokemonList.addAll(list)
        notifyDataSetChanged()
    }

    fun setClickListener(listener: OnClickListener){
        this.listener = listener
    }


}