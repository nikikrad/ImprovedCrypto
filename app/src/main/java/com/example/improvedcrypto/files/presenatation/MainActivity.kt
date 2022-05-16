package com.example.improvedcrypto.files.presenatation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgument
import androidx.navigation.ui.setupWithNavController
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.ActivityMainBinding
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

//        var array : ArrayList<CoinResponse> = intent.getParcelableArrayListExtra<Parcelable>("COIN") as ArrayList<CoinResponse>
//        intent.putExtra("COIN", intent.getParcelableArrayListExtra<Parcelable>("COIN"))
//        val bundle = Bundle()
//        bundle.putParcelableArrayList("COIN", intent.getParcelableArrayListExtra("COIN"))
//        var intent = Intent()
//        var array: ArrayList<CoinResponse> = intent.getStringArrayListExtra("COIN") as ArrayList<CoinResponse>
//        intent.getParcelableArrayListExtra<>("COIN")
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
    }
}