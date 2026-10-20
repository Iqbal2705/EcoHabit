package com.example.mobile_iqbal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobile_iqbal.databinding.ActivityLogin3Binding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogin3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Mengambil data SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Mengecek apakah pengguna sudah login
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        binding = ActivityLogin3Binding.inflate(layoutInflater)
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

        // Tombol Login
        binding.btnLogin.setOnClickListener {

            val user = binding.edtUser.text.toString().trim()
            val pass = binding.edtPass.text.toString()

            // Validasi login sederhana
            if (user.isNotEmpty() && pass.isNotEmpty() && user == pass) {

                // Menyimpan status login dan username
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", user)
                editor.apply()

                Log.d("LoginActivity", "Login berhasil: $user")

                Toast.makeText(
                    this,
                    "Login berhasil",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(
                    this@LoginActivity,
                    BaseActivity::class.java
                )

                intent.putExtra("nama", "M. Iqbal Alfayyadh Riyadi")
                intent.putExtra("umur", 21)

                startActivity(intent)
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Username dan password tidak sesuai",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}