
package com.example.mobile_iqbal.Activity

import android.graphics.Color
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
import com.example.mobile_iqbal.R
import com.example.mobile_iqbal.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {

    private var _binding: FragmentActivityBinding? = null
    private val binding get() = _binding!!

    private lateinit var activityAdapter: ActivityAdapter

    // Daftar seluruh aktivitas EcoHabit
    private val semuaAktivitas = listOf(

        // Kategori Sampah
        ActivityModel(
            "Sampah",
            "Kelola dan pilah sampah sesuai jenisnya",
            "Sampah",
            "ic_sampah.png"
        ),
        ActivityModel(
            "Daur Ulang",
            "Manfaatkan kembali barang bekas agar berguna",
            "Sampah",
            "ic_daur_ulang.png"
        ),
        ActivityModel(
            "Kurangi Plastik",
            "Gunakan tas belanja dan wadah pakai ulang",
            "Sampah",
            "ic_kurangi_plastik.png"
        ),
        ActivityModel(
            "Kompos Organik",
            "Olah sisa makanan menjadi pupuk alami",
            "Sampah",
            "ic_kompos.png"
        ),

        // Kategori Energi
        ActivityModel(
            "Energi",
            "Hemat energi dalam kegiatan sehari-hari",
            "Energi",
            "ic_energi.png"
        ),
        ActivityModel(
            "Matikan Perangkat",
            "Matikan perangkat elektronik setelah digunakan",
            "Energi",
            "ic_matikan_perangkat.png"
        ),
        ActivityModel(
            "Gunakan Cahaya Alami",
            "Manfaatkan sinar matahari pada siang hari",
            "Energi",
            "ic_cahaya_alami.png"
        ),
        ActivityModel(
            "Cabut Charger",
            "Lepaskan charger saat tidak digunakan",
            "Energi",
            "ic_cabut_charger.png"
        ),

        // Kategori Air
        ActivityModel(
            "Air",
            "Gunakan air secara bijak dan bertanggung jawab",
            "Air",
            "ic_air.png"
        ),
        ActivityModel(
            "Tutup Keran",
            "Tutup keran ketika air tidak diperlukan",
            "Air",
            "ic_tutup_keran.png"
        ),
        ActivityModel(
            "Gunakan Air Secukupnya",
            "Hindari penggunaan air secara berlebihan",
            "Air",
            "ic_hemat_air.png"
        ),
        ActivityModel(
            "Tampung Air Hujan",
            "Manfaatkan air hujan untuk menyiram tanaman",
            "Air",
            "ic_air_hujan.png"
        ),

        // Kategori Transportasi
        ActivityModel(
            "Transportasi",
            "Pilih moda transportasi yang lebih ramah lingkungan",
            "Transportasi",
            "ic_transportasi.png"
        ),
        ActivityModel(
            "Berjalan Kaki",
            "Berjalan kaki untuk perjalanan jarak dekat",
            "Transportasi",
            "ic_jalan_kaki.png"
        ),
        ActivityModel(
            "Bersepeda",
            "Gunakan sepeda untuk perjalanan singkat",
            "Transportasi",
            "ic_sepeda.png"
        ),

        // Kategori Kebiasaan Hijau
        ActivityModel(
            "Kebiasaan Hijau",
            "Mulai perubahan dari kebiasaan kecil",
            "Kebiasaan Hijau",
            "ic_kebiasaan_hijau.png"
        ),
        ActivityModel(
            "Menanam Tanaman",
            "Tanam dan rawat tanaman di sekitar rumah",
            "Kebiasaan Hijau",
            "ic_tanaman.png"
        ),
        ActivityModel(
            "Bawa Tumbler",
            "Kurangi penggunaan botol minum sekali pakai",
            "Kebiasaan Hijau",
            "ic_tumbler.png"
        ),
        ActivityModel(
            "Gunakan Barang Pakai Ulang",
            "Pilih barang yang bisa digunakan berkali-kali",
            "Kebiasaan Hijau",
            "ic_barang_pakai_ulang.png"
        ),
        ActivityModel(
            "Bersihkan Lingkungan",
            "Jaga kebersihan rumah dan lingkungan sekitar",
            "Kebiasaan Hijau",
            "ic_bersihkan_lingkungan.png"
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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
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

        // Aksi saat item aktivitas ditekan
        binding.listViewActivity.setOnItemClickListener {
                _, _, position, _ ->

            val aktivitas = activityAdapter.getItem(position)

            if (aktivitas != null) {
                Toast.makeText(
                    requireContext(),
                    "${aktivitas.nama}: ${aktivitas.deskripsi}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Tombol kembali ke Home
        binding.toolbarActivity.setNavigationOnClickListener {
            (activity as? BaseActivity)?.bukaHome()
        }

        aturTampilanFilter()
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
