package com.example.mobile_iqbal

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
            startActivity(Intent(this, BaseActivity::class.java))
            finish()
            return
        }

        // Menghubungkan layout dengan Activity
        binding = ActivityLogin3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Memuat aset halaman Login
        tampilkanAssetsLogin()

        // Mengatur system bar
        // Padding hanya dipasang ke ScrollView (konten),
        // bukan ke root, supaya gelombang menempel sampai tepi atas layar
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            val d = resources.displayMetrics.density

            binding.scrollLogin?.setPadding(
                systemBars.left + (24 * d).toInt(),
                systemBars.top + (12 * d).toInt(),
                systemBars.right + (24 * d).toInt(),
                systemBars.bottom + (12 * d).toInt()
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

                // Berpindah ke halaman utama
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

    // Membaca gambar dari assets/drawable-login
    // Jika tidak ditemukan, mencoba mengambil gambar dari res/drawable
    private fun loadLoginAsset(fileName: String): Drawable? {

        try {
            assets.open("drawable-login/$fileName").use { inputStream ->
                return Drawable.createFromStream(inputStream, null)
            }

        } catch (e: Exception) {

            Log.w(
                "LoginActivity",
                "Aset $fileName tidak ditemukan di assets/drawable-login. Mencoba res/drawable.",
                e
            )
        }

        // Fallback ke res/drawable jika file belum tersedia di assets
        val resourceName = fileName.substringBeforeLast(".")
        val resourceId = resources.getIdentifier(
            resourceName,
            "drawable",
            packageName
        )

        return if (resourceId != 0) {
            androidx.core.content.ContextCompat.getDrawable(this, resourceId)
        } else {
            Log.e("LoginActivity", "Gambar $fileName tidak ditemukan.")
            null
        }
    }

    // Memasang aset halaman Login
    private fun tampilkanAssetsLogin() {

        // Background halaman Login
        loadLoginAsset("wpeco.png")?.let {
            binding.root.background = it
        }

        // Ikon email
        binding.imgEmail?.setImageDrawable(
            loadLoginAsset("maillogo.png")
        )

        // Ikon password
        binding.imgPassword?.setImageDrawable(
            loadLoginAsset("keylogo.png")
        )

        // Ikon tampilkan password
        binding.btnShowPassword?.setImageDrawable(
            loadLoginAsset("eyelogo.png")
        )

        // Ikon Google
        binding.btnGoogle?.apply {
            icon = loadLoginAsset("googlelogo.png")
            setIconTint(null)
            setIconSize(
                (18 * resources.displayMetrics.density).toInt()
            )
        }

        // Ikon Apple
        binding.btnApple?.apply {
            icon = loadLoginAsset("applelogo.png")
            setIconTint(null)
            setIconSize(
                (18 * resources.displayMetrics.density).toInt()
            )
        }
    }
}