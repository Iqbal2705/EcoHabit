
package com.example.mobile_iqbal.Statistic

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.BaseActivity
import com.example.mobile_iqbal.R
import com.example.mobile_iqbal.databinding.FragmentStatisticBinding

class StatisticFragment : Fragment() {

    private var _binding: FragmentStatisticBinding? = null
    private val binding get() = _binding!!

    private var periodeAktif = "Mingguan"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticBinding.inflate(
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

        binding.toolbarStatistic.setNavigationOnClickListener {
            (activity as? BaseActivity)?.bukaHome()
        }

        binding.btnMingguan.setOnClickListener {
            ubahPeriode("Mingguan")
        }

        binding.btnBulanan.setOnClickListener {
            ubahPeriode("Bulanan")
        }

        binding.btnTahunan.setOnClickListener {
            ubahPeriode("Tahunan")
        }

        ubahPeriode(periodeAktif)
    }

    private fun ubahPeriode(periode: String) {
        periodeAktif = periode

        aturTombol(binding.btnMingguan, periode == "Mingguan")
        aturTombol(binding.btnBulanan, periode == "Bulanan")
        aturTombol(binding.btnTahunan, periode == "Tahunan")

        when (periode) {
            "Mingguan" -> {
                binding.txtTotalPoin.text = "300"
                binding.txtTotalAktivitas.text = "100"
                binding.chartAktivitas.setData(
                    arrayOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min"),
                    floatArrayOf(40f, 60f, 35f, 80f, 55f, 70f, 90f),
                    floatArrayOf(55f, 45f, 70f, 50f, 85f, 60f, 75f),
                    floatArrayOf(30f, 65f, 45f, 75f, 50f, 80f, 60f)
                )
            }

            "Bulanan" -> {
                binding.txtTotalPoin.text = "1.200"
                binding.txtTotalAktivitas.text = "420"
                binding.chartAktivitas.setData(
                    arrayOf("1", "2", "3", "4", "5", "6"),
                    floatArrayOf(58f, 37f, 81f, 42f, 34f, 60f),
                    floatArrayOf(72f, 36f, 39f, 89f, 95f, 88f),
                    floatArrayOf(82f, 36f, 51f, 50f, 86f, 72f)
                )
            }

            "Tahunan" -> {
                binding.txtTotalPoin.text = "8.500"
                binding.txtTotalAktivitas.text = "2.800"
                binding.chartAktivitas.setData(
                    arrayOf("Jan", "Mar", "Mei", "Jul", "Sep", "Nov"),
                    floatArrayOf(45f, 65f, 75f, 50f, 85f, 90f),
                    floatArrayOf(55f, 70f, 60f, 80f, 75f, 95f),
                    floatArrayOf(35f, 55f, 70f, 65f, 80f, 85f)
                )
            }
        }
    }

    private fun aturTombol(
        tombol: TextView,
        aktif: Boolean
    ) {
        if (aktif) {
            tombol.setBackgroundResource(R.drawable.bg_filter_selected)
            tombol.setTextColor(Color.WHITE)
        } else {
            tombol.setBackgroundResource(R.drawable.bg_filter)
            tombol.setTextColor(Color.parseColor("#1F5D42"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
