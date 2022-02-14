package com.example.osagocalculation.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.osagocalculation.R
import com.example.osagocalculation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_main, MainFragment.newInstance())
                .commit()
        }
    }

}
