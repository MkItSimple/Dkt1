package com.example.dkt1.viewmodel

import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : BaseViewModel(){

    fun getScore(finalScore: Int): String {
        return "ViewModel is working! Your score is " + finalScore
    }
}