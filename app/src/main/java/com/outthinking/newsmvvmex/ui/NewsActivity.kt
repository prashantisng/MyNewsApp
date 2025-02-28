package com.outthinking.newsmvvmex.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.outthinking.newsmvvmex.R
import com.outthinking.newsmvvmex.api.RetrofitInstance
import com.outthinking.newsmvvmex.databinding.ActivityNewsBinding
import com.outthinking.newsmvvmex.db.ArticleDatabase
import com.outthinking.newsmvvmex.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {
    private  val binding by lazy{ActivityNewsBinding.inflate(layoutInflater)}
    private lateinit var navController: NavController
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   enableEdgeToEdge()
       // binding= ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) //
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController =
            navHostFragment.navController        // Find reference to bottom navigation view
        // Hook your navigation controller to bottom navigation view
        //val navController = findNavController(R.id.fragment_container_view)
        binding.bottomNavigationView.setupWithNavController(navController)
       // binding.bottomNavigationView.setupWithNavController(binding.newsNavHostFragment.findNavController())
    }

}