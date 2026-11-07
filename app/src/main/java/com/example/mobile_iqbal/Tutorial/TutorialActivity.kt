package com.example.mobile_iqbal.Tutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_iqbal.BaseActivity
import com.example.mobile_iqbal.LoginActivity
import com.example.mobile_iqbal.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TutorialFragmentAdapter(this)

        binding.tutorialViewPager.adapter = adapter

        // Menghubungkan titik indikator dengan ViewPager2
        binding.dotIndicator.attachTo(binding.tutorialViewPager)

        // Mencegah halaman kembali ke posisi awal saat rotasi layar
        binding.tutorialViewPager.isUserInputEnabled = true
    }

    fun lanjutKeHalaman(position: Int) {
        binding.tutorialViewPager.currentItem = position
    }

    fun selesaiTutorial() {

        val sharedPref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        val isLogin = sharedPref.getBoolean(
            "isLogin",
            false
        )

        val tujuan = if (isLogin) {
            BaseActivity::class.java
        } else {
            LoginActivity::class.java
        }

        startActivity(Intent(this, tujuan))
        finish()
    }
}