package com.example.dkt1.domain

import com.example.dkt1.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonUsecase(private val repository: PokemonRepository) {

    fun getPokemonList(offset: Int) = repository.getPokemonList(offset)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getPokemonDetails(id: Int) = repository.getPokemonDetails(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}