
package com.example.mobile_iqbal.Tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.example.mobile_iqbal.R

class Tutorial1Fragment : Fragment() {

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

        view.findViewById<TextView>(R.id.txtIllustration).text = "🌱"

        view.findViewById<TextView>(R.id.txtTitle).text =
            "Kenali Beranda EcoHabit"

        view.findViewById<TextView>(R.id.txtDescription).text =
            "Pantau level tanaman dan kumpulkan Eco Points " +
                    "melalui kebiasaan baik setiap hari. " +
                    "Kamu juga dapat melihat checklist aktivitas harian " +
                    "dan informasi challenge langsung dari halaman Beranda."

        view.findViewById<MaterialButton>(R.id.btnSkip)
            .setOnClickListener {
                (activity as? TutorialActivity)?.selesaiTutorial()
            }

        view.findViewById<MaterialButton>(R.id.btnNext)
            .setOnClickListener {
                (activity as? TutorialActivity)?.lanjutKeHalaman(1)
            }
    }
}
