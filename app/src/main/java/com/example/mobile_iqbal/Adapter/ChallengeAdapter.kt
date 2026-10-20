
package com.example.mobile_iqbal.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mobile_iqbal.R

class ChallengeAdapter(
    context: Context,
    private val challengeList: List<ChallengeModel>
) : ArrayAdapter<ChallengeModel>(context, 0, challengeList) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_challenge, parent, false)

        val imgChallenge = view.findViewById<ImageView>(R.id.imgChallenge)
        val txtChallengeName =
            view.findViewById<TextView>(R.id.txtChallengeName)
        val txtChallengeDesc =
            view.findViewById<TextView>(R.id.txtChallengeDesc)
        val txtChallengeTarget =
            view.findViewById<TextView>(R.id.txtChallengeTarget)

        val data = challengeList[position]

        imgChallenge.setImageResource(data.gambar)
        txtChallengeName.text = data.nama
        txtChallengeDesc.text = data.deskripsi
        txtChallengeTarget.text = "Target: ${data.target}"

        view.setOnClickListener {
            Toast.makeText(
                context,
                "Challenge dipilih: ${data.nama}",
                Toast.LENGTH_SHORT
            ).show()
        }

        return view
    }
}
