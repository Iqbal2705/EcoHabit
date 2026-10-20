
package com.example.mobile_iqbal.Challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.Adapter.ChallengeAdapter
import com.example.mobile_iqbal.Adapter.ChallengeModel
import com.example.mobile_iqbal.R
import com.example.mobile_iqbal.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {

    private var _binding: FragmentChallengeBinding? = null
    private val binding get() = _binding!!

    private val challengeList = listOf(
        ChallengeModel(
            "Kurangi Sampah Plastik",
            "Kurangi penggunaan plastik sekali pakai.",
            "7 Hari",
            R.drawable.tnmnchallange
        ),
        ChallengeModel(
            "Bawa Tumbler",
            "Gunakan botol minum sendiri saat bepergian.",
            "3 Hari",
            R.drawable.tumbler
        ),
        ChallengeModel(
            "Hemat Energi",
            "Matikan lampu dan alat elektronik saat tidak digunakan.",
            "5 Hari",
            R.drawable.light
        ),
        ChallengeModel(
            "Pilah Sampah",
            "Pisahkan sampah sesuai jenisnya.",
            "7 Hari",
            R.drawable.recycle
        )
    )

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChallengeAdapter(
            requireContext(),
            challengeList
        )

        binding.listViewChallenge.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
