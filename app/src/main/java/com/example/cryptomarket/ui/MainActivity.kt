package com.example.cryptomarket.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptomarket.R
import com.example.cryptomarket.ui.coins_list.CoinsListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}