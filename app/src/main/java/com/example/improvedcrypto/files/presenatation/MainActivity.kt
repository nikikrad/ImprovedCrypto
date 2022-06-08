package com.example.improvedcrypto.files.presenatation

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.ActivityMainBinding
import com.example.improvedcrypto.files.presenatation.internet_connection.ConnectivityReceiver
import com.example.improvedcrypto.files.presenatation.main.dialogs.internet_connection.InternetConnectionDialogFragment

class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    private fun showDialog(isConnected: Boolean) {

        val dialogFragment = InternetConnectionDialogFragment()

        if (!isConnected) {
            dialogFragment.show(supportFragmentManager, "Hello")
        } else {
            dialogFragment.dismissDialog()
        }
    }
    override fun onResume() {
        super.onResume()

        ConnectivityReceiver.connectivityReceiverListener = this
    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showDialog(isConnected)
    }
}
