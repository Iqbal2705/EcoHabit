
package com.example.mobile_iqbal.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.BaseActivity
import com.example.mobile_iqbal.databinding.FragmentActivityBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class ActivityFragment : Fragment() {

    private var _binding: FragmentActivityBinding? = null
    private val binding get() = _binding!!

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

        // Tombol kembali ke Home
        binding.toolbarActivity.setNavigationOnClickListener {
            (activity as? BaseActivity)?.bukaHome()
        }

        val shape = ShapeAppearanceModel.builder()
            .setAllCornerSizes(12f)
            .build()

        binding.tabLayoutActivity.background = MaterialShapeDrawable(shape).apply {
            setTint(android.graphics.Color.parseColor("#DDEEDC"))
        }

        // Membuat adapter ViewPager2
        val tabsAdapter = ActivityTabsAdapter(
            requireActivity()
        )

        binding.viewPagerActivity.adapter = tabsAdapter

        // Menghubungkan TabLayout dan ViewPager2
        TabLayoutMediator(
            binding.tabLayoutActivity,
            binding.viewPagerActivity
        ) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Jelajahi"
                    tab.icon = loadTabIcon("ic_kebiasaan_hijau.png")
                }

                1 -> {
                    tab.text = "Favorit"
                    tab.icon = loadTabIcon("ic_barang_pakai_ulang.png")
                }

                2 -> {
                    tab.text = "Riwayat"
                    tab.icon = loadTabIcon("ic_daur_ulang.png")
                }

                3 -> {
                    tab.text = "Catatan"
                }
            }

            tab.icon?.setBounds(
                0,
                0,
                (24 * resources.displayMetrics.density).toInt(),
                (24 * resources.displayMetrics.density).toInt()
            )

        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadTabIcon(fileName: String): android.graphics.drawable.Drawable? {
        return try {
            val bitmap = requireContext().assets
                .open("drawable-activity/$fileName")
                .use { android.graphics.BitmapFactory.decodeStream(it) }

            android.graphics.drawable.BitmapDrawable(resources, bitmap).apply {
                val ukuran = (24 * resources.displayMetrics.density).toInt()
                setBounds(0, 0, ukuran, ukuran)
            }
        } catch (e: Exception) {
            null
        }
    }
}
