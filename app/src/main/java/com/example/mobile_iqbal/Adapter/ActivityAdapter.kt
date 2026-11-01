
package com.example.mobile_iqbal.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.util.Log
import com.example.mobile_iqbal.R

class ActivityAdapter(
    context: Context,
    private val activityList: List<ActivityModel>
) : ArrayAdapter<ActivityModel>(context, 0, activityList) {

    // Memuat gambar dari assets/drawable-activity
    private fun loadActivityAsset(fileName: String): Bitmap? {
        return try {
            context.assets.open("drawable-activity/$fileName").use { stream ->
                BitmapFactory.decodeStream(stream)
            }
        } catch (e: Exception) {
            Log.e("ActivityAdapter", "Gagal memuat gambar: $fileName", e)
            null
        }
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_activity, parent, false)

        val imgIcon = view.findViewById<ImageView>(R.id.imgActivityIcon)
        val txtName = view.findViewById<TextView>(R.id.txtActivityName)
        val txtDescription =
            view.findViewById<TextView>(R.id.txtActivityDescription)

        val data = activityList[position]

        txtName.text = data.nama
        txtDescription.text = data.deskripsi

        // Memuat ikon aktivitas
        val gambar = loadActivityAsset(data.gambar)

        if (gambar != null) {
            imgIcon.setImageBitmap(gambar)
        } else {
            // Menggunakan ikon sampah sebagai gambar cadangan
            val gambarCadangan = loadActivityAsset("ic_sampah.png")
            imgIcon.setImageBitmap(gambarCadangan)
        }

        return view
    }
}
