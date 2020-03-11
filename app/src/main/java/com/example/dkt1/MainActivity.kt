package com.example.dkt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // Initialize ViewModel
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = ScoreViewModelFactory(0)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ScoreViewModel::class.java]

        val text = viewModel.getScore(23);

        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this, text, duration)
        toast.show()
    }

}
