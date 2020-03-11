package com.example.dkt1

import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel(){

    fun getScore(finalScore: Int): String {
        return "ViewModel is working! Your score is " + finalScore
    }

}