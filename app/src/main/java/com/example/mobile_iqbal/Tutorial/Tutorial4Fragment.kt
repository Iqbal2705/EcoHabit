package com.example.mobile_iqbal.Tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mobile_iqbal.R
import com.google.android.material.button.MaterialButton

class Tutorial4Fragment : Fragment() {

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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Ilustrasi halaman tutorial keempat
        view.findViewById<TextView>(R.id.txtIllustration).text = "🌱 🏆 🌍"

        // Judul tutorial
        view.findViewById<TextView>(R.id.txtTitle).text =
            "Kumpulkan Eco Points!"

        // Deskripsi tutorial
        view.findViewById<TextView>(R.id.txtDescription).text =
            "Selesaikan aktivitas ramah lingkungan, kumpulkan Eco Points, " +
                    "dan tingkatkan level tanamanmu. Mulai perubahan kecil untuk bumi yang lebih baik!"

        view.findViewById<MaterialButton>(R.id.btnSkip).visibility =
            View.GONE

        view.findViewById<MaterialButton>(R.id.btnNext).text =
            "Mulai Sekarang"

        view.findViewById<MaterialButton>(R.id.btnNext)
            .setOnClickListener {
                (activity as? TutorialActivity)?.selesaiTutorial()
            }
    }
}