
package com.example.mobile_iqbal.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mobile_iqbal.R

class ActivityAdapter(
    context: Context,
    private val activityList: List<ActivityModel>
) : ArrayAdapter<ActivityModel>(context, 0, activityList) {

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

        imgIcon.setImageResource(data.gambar)
        txtName.text = data.nama
        txtDescription.text = data.deskripsi

        return view
    }
}
