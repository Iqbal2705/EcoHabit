package com.example.mobile_iqbal.Challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.BaseActivity
import com.example.mobile_iqbal.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {

    private var _binding: FragmentChallengeBinding? = null
    private val binding get() = _binding!!

    private var jumlahHari = 3
    private var sudahSelesaiHariIni = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChallengeBinding.inflate(
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

        binding.toolbarChallenge.setNavigationOnClickListener {
            (activity as? BaseActivity)?.bukaHome()
        }

        tampilkanProgress()

        binding.btnSelesaikanChallenge.setOnClickListener {
            if (!sudahSelesaiHariIni) {

                if (jumlahHari < 7) {
                    jumlahHari++
                }

                sudahSelesaiHariIni = true
                tampilkanProgress()

                Toast.makeText(
                    requireContext(),
                    "Challenge berhasil diselesaikan!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Challenge hari ini sudah selesai.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun tampilkanProgress() {
        binding.progressChallenge.progress = jumlahHari

        binding.txtTargetChallenge.text =
            "$jumlahHari dari 7 Hari"

        if (sudahSelesaiHariIni) {
            binding.btnSelesaikanChallenge.text =
                "✓  Sudah Diselesaikan"

            binding.btnSelesaikanChallenge.isEnabled = false
            binding.btnSelesaikanChallenge.alpha = 0.7f
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
