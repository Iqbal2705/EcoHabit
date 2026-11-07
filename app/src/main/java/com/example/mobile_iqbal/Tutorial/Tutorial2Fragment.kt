
package com.example.mobile_iqbal.Tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.example.mobile_iqbal.R

class Tutorial2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_tutorial_page,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.txtIllustration).text = "♻️"

        view.findViewById<TextView>(R.id.txtTitle).text =
            "Jelajahi Berbagai Aktivitas"

        view.findViewById<TextView>(R.id.txtDescription).text =
            "Temukan berbagai kebiasaan ramah lingkungan " +
                    "melalui kategori Sampah, Energi, Air, Transportasi, " +
                    "dan Kebiasaan Hijau. Pilih aktivitas yang ingin " +
                    "kamu lakukan untuk mulai membangun kebiasaan positif."

        view.findViewById<MaterialButton>(R.id.btnSkip)
            .setOnClickListener {
                (activity as? TutorialActivity)?.selesaiTutorial()
            }

        view.findViewById<MaterialButton>(R.id.btnNext)
            .setOnClickListener {
                (activity as? TutorialActivity)?.lanjutKeHalaman(2)
            }
    }
}
