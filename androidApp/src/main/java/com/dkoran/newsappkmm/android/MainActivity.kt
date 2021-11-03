package com.dkoran.newsappkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.breakingNewsFragment, R.id.newsArticleFragment,
            R.id.favoriteNewsFragment, R.id.searchNewsFragment))
        val navController by lazy { findNavController(R.id.newNavHostFragment) }
       // NavigationUI.setupWithNavController(appBar, navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

    }
    fun showBottomNavigation()
    {

        bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation()
    {
        bottomNavigationView.visibility = View.GONE
    }

//    fun showToolbar()
//    {
//        appBar.visibility = View.VISIBLE
//    }
//
//    fun hideToolbar()
//    {
//        appBar.visibility = View.GONE
//    }
}
