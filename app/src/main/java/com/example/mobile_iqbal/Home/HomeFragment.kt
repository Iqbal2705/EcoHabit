
package com.example.mobile_iqbal.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.DetailActivity
import com.example.mobile_iqbal.LoginActivity
import com.example.mobile_iqbal.R
import com.example.mobile_iqbal.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences(
            "user_pref",
            Context.MODE_PRIVATE
        )

        val username = sharedPref.getString("username", "Iqbal")
        binding.txtGreeting.text = "Halo, $username! 🌿"

        // Menu notifikasi dan logout
        binding.toolbarMain.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_notification -> {
                    Toast.makeText(
                        requireContext(),
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

        // Checklist tumbler
        binding.checkTumbler.setOnClickListener {
            val selesai = it.tag != true
            it.tag = selesai
            binding.checkTumbler.setImageResource(
                if (selesai) R.drawable.checkbox
                else R.drawable.unchecked
            )
        }

        // Checklist lampu
        binding.checkLampu.setOnClickListener {
            val selesai = it.tag != true
            it.tag = selesai
            binding.checkLampu.setImageResource(
                if (selesai) R.drawable.checkbox
                else R.drawable.unchecked
            )
        }

        // Checklist pilah sampah
        binding.checkSampah.setOnClickListener {
            val selesai = it.tag != true
            it.tag = selesai
            binding.checkSampah.setImageResource(
                if (selesai) R.drawable.checkbox
                else R.drawable.unchecked
            )
        }

        // Buka detail challenge
        binding.cardChallenge.setOnClickListener {
            startActivity(
                Intent(requireContext(), DetailActivity::class.java)
            )
        }
    }

    private fun tampilkanDialogLogout() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Logout")
            .setMessage("Apakah kamu yakin ingin keluar?")
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref = requireContext().getSharedPreferences(
                    "user_pref",
                    Context.MODE_PRIVATE
                )

                sharedPref.edit().clear().apply()

                val intent = Intent(
                    requireContext(),
                    LoginActivity::class.java
                )

                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
                requireActivity().finish()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
