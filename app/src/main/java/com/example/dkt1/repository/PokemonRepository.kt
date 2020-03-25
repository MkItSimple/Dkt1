package com.example.dkt1.repository

import com.example.dkt1.LIMIT
import com.example.dkt1.data.PokemonDetails
import com.example.dkt1.data.PokemonResponse
import com.example.dkt1.network.PokemonApi
import io.reactivex.Observable
import javax.inject.Inject

class PokemonRepository (val pokemonApi: PokemonApi){

    fun getPokemonList(offset:Int): Observable<PokemonResponse>{
        return  pokemonApi.getPokemonList(offset , LIMIT)
    }

    fun getPokemonDetails(id:Int):Observable<PokemonDetails>{
        return pokemonApi.getPokemonDetails(id)
    }

}