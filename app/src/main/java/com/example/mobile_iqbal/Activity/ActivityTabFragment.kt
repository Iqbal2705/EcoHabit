
package com.example.mobile_iqbal.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobile_iqbal.Adapter.ActivityModel
import com.example.mobile_iqbal.Adapter.ActivityRecyclerAdapter
import com.example.mobile_iqbal.databinding.FragmentActivityTabBinding

class ActivityTabFragment : Fragment() {

    private var _binding: FragmentActivityTabBinding? = null
    private val binding get() = _binding!!

    private var jenisTab = TAB_JELAJAHI
    private var kategoriAktif = "Semua"

    private var dataTampil = listOf<ActivityModel>()

    companion object {
        private const val ARG_TAB = "jenis_tab"

        const val TAB_JELAJAHI = 0
        const val TAB_FAVORIT = 1
        const val TAB_RIWAYAT = 2

        fun newInstance(jenis: Int): ActivityTabFragment {
            return ActivityTabFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TAB, jenis)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jenisTab = arguments?.getInt(ARG_TAB) ?: TAB_JELAJAHI
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityTabBinding.inflate(
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

        // ChipGroup hanya digunakan pada tab Jelajahi
        binding.scrollKategori.visibility =
            if (jenisTab == TAB_JELAJAHI) View.VISIBLE
            else View.GONE

        binding.rvActivity.layoutManager =
            GridLayoutManager(requireContext(), 2)

        if (jenisTab == TAB_JELAJAHI) {
            aturChipGroup()
        }

        tampilkanData()
    }

    private fun aturChipGroup() {
        binding.chipGroupKategori.setOnCheckedChangeListener {
                _, checkedId ->

            kategoriAktif = when (checkedId) {
                binding.chipSemua.id -> "Semua"
                binding.chipSampah.id -> "Sampah"
                binding.chipEnergi.id -> "Energi"
                binding.chipAir.id -> "Air"
                binding.chipTransportasi.id -> "Transportasi"
                binding.chipKebiasaanHijau.id -> "Kebiasaan Hijau"
                else -> return@setOnCheckedChangeListener
            }

            tampilkanData()
        }

        binding.chipSemua.isChecked = true
    }

    private fun ambilData(): List<ActivityModel> {
        val semua = ActivityData.semuaAktivitas
        val context = requireContext()

        return when (jenisTab) {
            TAB_JELAJAHI -> {
                if (kategoriAktif == "Semua") {
                    semua
                } else {
                    semua.filter {
                        it.kategori == kategoriAktif
                    }
                }
            }

            TAB_FAVORIT -> {
                val favorit = ActivityStore.getFavorites(context)

                semua.filter {
                    it.nama in favorit
                }
            }

            TAB_RIWAYAT -> {
                val riwayat = ActivityStore.getHistory(context)

                semua.filter {
                    it.nama in riwayat
                }.reversed()
            }

            else -> emptyList()
        }
    }

    private fun tampilkanData() {
        dataTampil = ambilData()

        val adapter = ActivityRecyclerAdapter(
            requireContext(),
            dataTampil,
            onItemClick = { aktivitas ->

                ActivityStore.addHistory(
                    requireContext(),
                    aktivitas.nama
                )

                Toast.makeText(
                    requireContext(),
                    "${aktivitas.nama}: ${aktivitas.deskripsi}",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onItemLongClick = { aktivitas ->

                ActivityStore.toggleFavorite(
                    requireContext(),
                    aktivitas.nama
                )

                val statusFavorit =
                    aktivitas.nama in ActivityStore.getFavorites(
                        requireContext()
                    )

                Toast.makeText(
                    requireContext(),
                    if (statusFavorit)
                        "${aktivitas.nama} ditambahkan ke favorit"
                    else
                        "${aktivitas.nama} dihapus dari favorit",
                    Toast.LENGTH_SHORT
                ).show()

                tampilkanData()
            }
        )

        binding.rvActivity.adapter = adapter

        binding.txtEmpty.visibility =
            if (dataTampil.isEmpty()) View.VISIBLE
            else View.GONE
    }

    override fun onResume() {
        super.onResume()

        if (_binding != null) {
            tampilkanData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
