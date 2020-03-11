package com.example.dkt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var info: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMagicBox.create().poke(this)
        val mInfo = info.text

//        lateinit var viewModel: ScoreViewModel
//        viewModel = ViewModelProviders.of(this)[ScoreViewModel::class.java]
//        val text = viewModel.addScore();

        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this, mInfo, duration)
        toast.show()
    }

    class Info(val text: String)

//    class Info @Inject constructor(val text: String)

//    class Info @Inject constructor() {
//        val text = "Hello Dagger 2"
//    }
}
