package com.example.vviped

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.vviped.ui.home.HomeFragment
import com.example.vviped.ui.profile.ProfileFragment
import com.example.vviped.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navigation_home -> {
                var homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, homeFragment).commit()
                return true
            }
            R.id.navigation_search -> {
                var searchFragment = SearchFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, searchFragment).commit()
                return true
            }
            R.id.navigation_profile -> {
                var profileFragment = ProfileFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, profileFragment).commit()
                return true
            }
            else -> return false

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(this)

    }
}