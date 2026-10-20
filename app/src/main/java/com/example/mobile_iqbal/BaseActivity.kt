
package com.example.mobile_iqbal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.databinding.ActivityBaseBinding
import com.example.mobile_iqbal.Home.HomeFragment
import com.example.mobile_iqbal.Activity.ActivityFragment
import com.example.mobile_iqbal.Statistic.StatisticFragment
import com.example.mobile_iqbal.Challenge.ChallengeFragment
import com.example.mobile_iqbal.Profile.ProfileFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.bottomNavView.selectedItemId = R.id.nav_home
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.nav_activity -> {
                    replaceFragment(ActivityFragment())
                    true
                }

                R.id.nav_statistic -> {
                    replaceFragment(StatisticFragment())
                    true
                }

                R.id.nav_challenge -> {
                    replaceFragment(ChallengeFragment())
                    true
                }

                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    fun bukaHome() {
        binding.bottomNavView.selectedItemId = R.id.nav_home
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
