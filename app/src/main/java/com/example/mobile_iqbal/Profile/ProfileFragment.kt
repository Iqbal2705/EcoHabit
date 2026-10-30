
package com.example.mobile_iqbal.Profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.BaseActivity
import com.example.mobile_iqbal.LoginActivity
import com.example.mobile_iqbal.databinding.FragmentProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        tampilkanDataProfil()

        // Tombol kembali ke Home
        binding.toolbarProfile.setNavigationOnClickListener {
            (activity as? BaseActivity)?.bukaHome()
        }

        // Menu Informasi Akun
        binding.rowInformasiAkun.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Menu Informasi Akun",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Menu Statistik Saya
        binding.rowStatistikSaya.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Menu Statistik Saya",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Menu Pencapaian
        binding.rowPencapaian.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Menu Pencapaian",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Menu Keluar
        binding.rowKeluar.setOnClickListener {
            tampilkanDialogKeluar()
        }
    }

    private fun tampilkanDataProfil() {

        val sharedPref = requireContext()
            .getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        val username = sharedPref.getString(
            "username",
            "Iqbal Alfayyadh"
        )

        val email = sharedPref.getString(
            "email",
            "iqbal@gmail.com"
        )

        binding.txtProfileName.text = username
        binding.txtProfileEmail.text = email
    }

    private fun tampilkanDialogKeluar() {

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Keluar")
            .setMessage("Apakah kamu yakin ingin keluar dari EcoHabit?")
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Ya, Keluar") { _, _ ->

                val sharedPref = requireContext()
                    .getSharedPreferences("user_pref", Context.MODE_PRIVATE)

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
