package com.example.mobile_iqbal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobile_iqbal.databinding.ActivityLogin3Binding
import com.example.mobile_iqbal.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Mengecek status login
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        // Jika belum login, kembali ke LoginActivity
        if (!isLogin) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // Mengambil data pengguna
        val nama = intent.getStringExtra("nama")
        val umur = intent.getIntExtra("umur", 0)
        val username = sharedPref.getString("username", "Iqbal")

        Log.w("Hasil", "Nama: $nama, Umur: $umur")
        Log.d("MainActivity", "Username: $username")

        // Menampilkan sapaan pengguna
        binding.txtGreeting.text = "Halo, $username! 🌿"

        // Tombol kembali pada Toolbar
        binding.toolbarMain.setNavigationOnClickListener {
            finish()
        }

        // Menu Toolbar
        binding.toolbarMain.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_notification -> {
                    Toast.makeText(
                        this,
                        "Belum ada notifikasi baru",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }

                R.id.action_logout -> {
                    tampilkanDialogLogout()
                    true
                }

                else -> false
            }
        }

        // Checklist aktivitas tumbler
        binding.checkTumbler.setOnClickListener {
            val selesai = it.tag != true
            it.tag = selesai

            binding.checkTumbler.setImageResource(
                if (selesai) R.drawable.checkbox
                else R.drawable.unchecked
            )
        }

        // Checklist aktivitas lampu
        binding.checkLampu.setOnClickListener {
            val selesai = it.tag != true
            it.tag = selesai

            binding.checkLampu.setImageResource(
                if (selesai) R.drawable.checkbox
                else R.drawable.unchecked
            )
        }

        // Checklist aktivitas pilah sampah
        binding.checkSampah.setOnClickListener {
            val selesai = it.tag != true
            it.tag = selesai

            binding.checkSampah.setImageResource(
                if (selesai) R.drawable.checkbox
                else R.drawable.unchecked
            )
        }

        // Membuka DetailActivity dari kartu Challenge
        binding.cardChallenge.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                DetailActivity::class.java
            )
            startActivity(intent)
        }
    }

    // Dialog Logout
    private fun tampilkanDialogLogout() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Logout")
            .setMessage("Apakah kamu yakin ingin keluar?")

            // Tombol Tidak
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }

            // Tombol Ya
            .setPositiveButton("Ya") { _, _ ->

                // Menghapus status login dan username
                val sharedPref = getSharedPreferences(
                    "user_pref",
                    MODE_PRIVATE
                )

                sharedPref.edit()
                    .clear()
                    .apply()

                // Kembali ke LoginActivity
                val intent = Intent(
                    this@MainActivity,
                    LoginActivity::class.java
                )

                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
                finish()
            }
            .show()
    }
}