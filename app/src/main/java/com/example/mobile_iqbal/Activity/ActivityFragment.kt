
package com.example.mobile_iqbal.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.Adapter.ActivityAdapter
import com.example.mobile_iqbal.Adapter.ActivityModel
import com.example.mobile_iqbal.BaseActivity
import com.example.mobile_iqbal.Home.HomeFragment
import com.example.mobile_iqbal.R
import com.example.mobile_iqbal.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {

    private var _binding: FragmentActivityBinding? = null
    private val binding get() = _binding!!

    private lateinit var activityAdapter: ActivityAdapter


    private val semuaAktivitas = listOf(
        ActivityModel(
            "Sampah",
            "Kelola sampah dengan bijak",
            "Sampah",
            R.drawable.recycle
        ),
        ActivityModel(
            "Energi",
            "Hemat energi untuk masa depan",
            "Energi",
            R.drawable.light
        ),
        ActivityModel(
            "Air",
            "Jaga setiap tetes air",
            "Air",
            R.drawable.tumbler
        ),
        ActivityModel(
            "Transportasi",
            "Pilih transportasi yang ramah lingkungan",
            "Transportasi",
            R.drawable.recycle
        ),
        ActivityModel(
            "Kebiasaan Hijau",
            "Mulai dari kebiasaan kecil",
            "Kebiasaan Hijau",
            R.drawable.tnmnlevel
        ),
        ActivityModel(
            "Daur Ulang",
            "Manfaatkan kembali barang yang masih bisa dipakai",
            "Sampah",
            R.drawable.recycle
        ),
        ActivityModel(
            "Kurangi Plastik",
            "Gunakan tas belanja yang dapat dipakai ulang",
            "Sampah",
            R.drawable.recycle
        ),
        ActivityModel(
            "Kompos Organik",
            "Olah sisa makanan menjadi pupuk alami",
            "Sampah",
            R.drawable.recycle
        ),
        ActivityModel(
            "Matikan Perangkat",
            "Matikan perangkat elektronik setelah digunakan",
            "Energi",
            R.drawable.light
        ),
        ActivityModel(
            "Gunakan Cahaya Alami",
            "Manfaatkan sinar matahari pada siang hari",
            "Energi",
            R.drawable.light
        ),
        ActivityModel(
            "Cabut Charger",
            "Cabut pengisi daya saat tidak digunakan",
            "Energi",
            R.drawable.light
        ),
        ActivityModel(
            "Tutup Keran",
            "Pastikan keran tertutup setelah digunakan",
            "Air",
            R.drawable.tumbler
        ),
        ActivityModel(
            "Gunakan Air Secukupnya",
            "Hindari penggunaan air secara berlebihan",
            "Air",
            R.drawable.tumbler
        ),
        ActivityModel(
            "Tampung Air Hujan",
            "Manfaatkan air hujan untuk menyiram tanaman",
            "Air",
            R.drawable.tumbler
        ),
        ActivityModel(
            "Berjalan Kaki",
            "Pilih berjalan kaki untuk perjalanan dekat",
            "Transportasi",
            R.drawable.recycle
        ),
        ActivityModel(
            "Bersepeda",
            "Gunakan sepeda untuk perjalanan singkat",
            "Transportasi",
            R.drawable.recycle
        ),
        ActivityModel(
            "Menanam Tanaman",
            "Tambahkan tanaman hijau di sekitar rumah",
            "Kebiasaan Hijau",
            R.drawable.tnmnlevel
        ),
        ActivityModel(
            "Bawa Tumbler",
            "Kurangi botol minum plastik sekali pakai",
            "Kebiasaan Hijau",
            R.drawable.tumbler
        ),
        ActivityModel(
            "Gunakan Barang Pakai Ulang",
            "Pilih barang yang dapat digunakan berkali-kali",
            "Kebiasaan Hijau",
            R.drawable.recycle
        ),
        ActivityModel(
            "Bersihkan Lingkungan",
            "Jaga kebersihan rumah dan lingkungan sekitar",
            "Kebiasaan Hijau",
            R.drawable.tnmnlevel
        )
    )


    private var kategoriAktif = "Semua"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityAdapter = ActivityAdapter(
            requireContext(),
            semuaAktivitas
        )

        binding.listViewActivity.adapter = activityAdapter

        // Filter kategori
        binding.btnSemua.setOnClickListener {
            tampilkanKategori("Semua")
        }

        binding.btnSampah.setOnClickListener {
            tampilkanKategori("Sampah")
        }

        binding.btnEnergi.setOnClickListener {
            tampilkanKategori("Energi")
        }

        binding.btnAir.setOnClickListener {
            tampilkanKategori("Air")
        }

        // Aksi saat item daftar ditekan
        binding.listViewActivity.setOnItemClickListener {
                _, _, position, _ ->

            val aktivitas = activityAdapter.getItem(position)

            if (aktivitas != null) {
                Toast.makeText(
                    requireContext(),
                    aktivitas.nama + ": " + aktivitas.deskripsi,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        binding.toolbarActivity.setNavigationOnClickListener {
            (activity as? BaseActivity)?.bukaHome()
        }


    }

    private fun tampilkanKategori(kategori: String) {
        kategoriAktif = kategori

        val dataFilter = if (kategori == "Semua") {
            semuaAktivitas
        } else {
            semuaAktivitas.filter {
                it.kategori == kategori
            }
        }

        activityAdapter = ActivityAdapter(
            requireContext(),
            dataFilter
        )

        binding.listViewActivity.adapter = activityAdapter

        aturTampilanFilter()
    }

    private fun aturTampilanFilter() {
        aturTombol(binding.btnSemua, kategoriAktif == "Semua")
        aturTombol(binding.btnSampah, kategoriAktif == "Sampah")
        aturTombol(binding.btnEnergi, kategoriAktif == "Energi")
        aturTombol(binding.btnAir, kategoriAktif == "Air")
    }

    private fun aturTombol(tombol: TextView, aktif: Boolean) {
        if (aktif) {
            tombol.setBackgroundResource(R.drawable.bg_filter_selected)
            tombol.setTextColor(
                android.graphics.Color.WHITE
            )
        } else {
            tombol.setBackgroundResource(R.drawable.bg_filter)
            tombol.setTextColor(
                android.graphics.Color.parseColor("#1F5D42")
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
