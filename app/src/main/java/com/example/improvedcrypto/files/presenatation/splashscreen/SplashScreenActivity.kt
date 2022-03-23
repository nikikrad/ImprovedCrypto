package com.example.improvedcrypto.files.presenatation.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.example.improvedcrypto.files.presenatation.MainActivity
import com.example.improvedcrypto.R
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)

            val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
            lifecycleScope.launch {
                val getGecon = retrofit.getCrypto()
                val bodyGecon = getGecon.body()
//                val bundle = Bundle()
//                savedInstanceState?.putParcelable("COIN", bodyGecon)
            }

            finish()
        }, 500)


    }
}
